package org.fmm.communitymgmt.service;

import java.util.List;
import java.util.Optional;

import org.fmm.communitymgmt.common.model.Community;
import org.fmm.communitymgmt.common.model.Membership;
import org.fmm.communitymgmt.common.model.Person;
import org.fmm.communitymgmt.common.model.TCharge;
import org.fmm.communitymgmt.common.repository.ChargeTypeRepository;
import org.fmm.communitymgmt.common.repository.MembershipRepository;
import org.fmm.communitymgmt.common.repository.PersonRepository;
import org.fmm.communitymgmt.common.util.ChargeType;
import org.fmm.communitymgmt.controller.SocialUserInfo;
import org.fmm.communitymgmt.dto.CommunityDTO;
import org.fmm.communitymgmt.dto.UserInfoDTO;
import org.fmm.oauth.springsocial.security.oauth2.user.UserInfo;
import org.fmm.oauth2.common.model.model.AuthProvider;
import org.fmm.oauth2.common.model.model.SocialUser;
import org.fmm.oauth2.common.model.repository.SocialUserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.oauth2.jwt.Jwt;
import org.springframework.stereotype.Service;

@Service("UserInfoService")
public class UserInfoServiceImpl implements UserInfoService {

	@Autowired
	SocialUserRepository socialUserRepository;
	@Autowired
	MembershipRepository membershipRepository;

	@Autowired
	PersonRepository personRepository;

	@Autowired
	ChargeTypeRepository chargeTypeRepository;

	
	@Autowired
	PersonInChargeService personInChargeService;

	
	@Override
	public Optional<SocialUser> getUserInfo(String upn, String iss) {
		Optional<SocialUser> responseOpt = null;
		responseOpt = socialUserRepository.findSocialUserByUpnAndIssuer(upn, iss);
		if (responseOpt.isPresent()) {
			return responseOpt;
		}
		return Optional.empty();
	}

	@Override
	public Optional<SocialUser> getSocialUserByProviderId(String providerId) {
		Optional<SocialUser> responseOpt = null;
		responseOpt = socialUserRepository.findSocialUserByProviderId(providerId);
		if (responseOpt.isPresent()) {
			return responseOpt;
		}
		return Optional.empty();
	}
/* TODO Revisar los providers y añadirlo como parámetro
	@Override
	public Optional<UserInfoDTO> getSocialUserInfoByUpnAndProvider(String upn, String provider) {
		
	}
	*/
	@Override
	public Optional<UserInfoDTO> getSocialUserInfoByProviderId(String providerId, Jwt jwt) {
		Optional<SocialUser> socialUserOpt = null;

		// SocialUser + Person
		socialUserOpt = socialUserRepository.findSocialUserByProviderId(providerId);
		return prepareResponse(socialUserOpt);
		// Community
	//	return userInfoDTOOpt;
	}

	private Optional<UserInfoDTO> prepareResponse(Optional<SocialUser> socialUserOpt) {
		UserInfoDTO userInfoDTO = null;
		SocialUser socialUser = null;
		List<Membership> allMemberships = null;

		if (socialUserOpt.isPresent()) {
			socialUser=socialUserOpt.get();


			// Se asignan todas comunidades a las que pertenece: mission, origen, otras
			// Si sólo pertenece a una, se pone también
			allMemberships = membershipRepository.findMembershipWithChargesByPersonId(socialUser.getPerson().getId());
			userInfoDTO = new UserInfoDTO(toSocialUserInfo(socialUser), (Person)socialUser.getPerson());
			userInfoDTO.fromMembership(allMemberships);
			
			if (userInfoDTO.getMyCommunities().size()==1) {
				userInfoDTO.setSelectedCommunity(userInfoDTO.getMyCommunities().get(0));
			}
			
			return Optional.of(userInfoDTO);
		} else {
			return Optional.empty();
		}
		
	}
	@Override
	public UserInfoDTO createPersonAndSocialUser(UserInfoDTO userInfoDTO) {
		Person person = userInfoDTO.getPerson();

		SocialUser socialUser = toSocialUser(userInfoDTO.getSocialUserInfo());
		socialUser.setPerson(person);

		person = personRepository.save(person);
		socialUser = socialUserRepository.save(socialUser);
		userInfoDTO = new UserInfoDTO(toSocialUserInfo(socialUser), person);
		return userInfoDTO;
	}
	
	/**
	 * Update its own data and create Community. If community already exists throws an error
	 */
	@Override
	public UserInfoDTO createCommunity(UserInfoDTO userInfoDTO) {
		Person person = userInfoDTO.getPerson();

		SocialUser socialUser = toSocialUser(userInfoDTO.getSocialUserInfo());
		socialUser.setPerson(person);

//		person = personRepository.save(person);
//		socialUser = socialUserRepository.save(socialUser);
		
		CommunityDTO communityDTO = userInfoDTO.getSelectedCommunity();
		
		// [FMMP] 05/08/2025
		Community community = personInChargeService.createCommunity(communityDTO.getMyCommunityData());
		
		TCharge responsible = null;
		responsible = chargeTypeRepository.getReferenceById(ChargeType.RESPONSIBLE.getId());  
		
		Membership m = personInChargeService.addMember(community, person, responsible);
		
		communityDTO.setMyCharges(m.getCharges());
		communityDTO.setMyCommunityData(community);
		userInfoDTO.addCommunityDTO(communityDTO);
		userInfoDTO.setSelectedCommunity(communityDTO);
		
		userInfoDTO = new UserInfoDTO(toSocialUserInfo(socialUser), person);
		return userInfoDTO;
	}
	
	/**
	 * Transforms SocialUserInfo into a SocialUser entity
	 * @param socialUserInfo
	 * @return SocialUser entity
	 */
	private SocialUser toSocialUser(SocialUserInfo socialUserInfo) {
		SocialUser socialUser = new SocialUser();
		if (socialUserInfo.getId() != null) 
			socialUser.setId(socialUserInfo.getId());
		
		socialUser.setEmail(socialUserInfo.getEmail());
		if (socialUserInfo.getEmailVerified() != null)
			socialUser.setEmailVerified(socialUserInfo.getEmailVerified());
		else
			socialUser.setEmailVerified(false);
		socialUser.setImageUrl(socialUserInfo.getImageUrl());
		socialUser.setIss(socialUserInfo.getIss());
		socialUser.setName(socialUserInfo.getName());
		socialUser.setProvider(socialUserInfo.getProvider());
		socialUser.setProviderId(socialUserInfo.getProviderId());
		socialUser.setUpn(socialUserInfo.getUpn());
		socialUser.setIss(getIss(socialUserInfo.getProvider()));
		return socialUser;
	}
	
	private SocialUserInfo toSocialUserInfo(SocialUser socialUser) {
		return new SocialUserInfo(socialUser.getId(), 
				socialUser.getProvider(), 
				socialUser.getProviderId(), 
				socialUser.getName(),
				socialUser.getEmail(),
				socialUser.getImageUrl(),
				socialUser.getEmailVerified()
		);
	}
	
	private String getIss(String registrationId) {
        if(registrationId.equalsIgnoreCase(AuthProvider.google.toString())) {
            return UserInfo.GOOGLE_ISS;
        } else if (registrationId.equalsIgnoreCase(AuthProvider.wso2is.toString())) {
            return UserInfo.GOOGLE_ISS;
        } else if (registrationId.equalsIgnoreCase(AuthProvider.facebook.toString())) {
            return UserInfo.FACEBOOK_ISS;
        } else if (registrationId.equalsIgnoreCase(AuthProvider.github.toString())) {
            return UserInfo.GITHUB_ISS;
        } else
        	return UserInfo.GOOGLE_ISS;
	}
}
