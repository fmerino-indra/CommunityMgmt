package org.fmm.communitymgmt.service;

import java.util.List;
import java.util.Optional;

import org.fmm.communitymgmt.common.model.Membership;
import org.fmm.communitymgmt.common.model.Person;
import org.fmm.communitymgmt.common.repository.MembershipRepository;
import org.fmm.communitymgmt.common.repository.PersonRepository;
import org.fmm.communitymgmt.contrroller.SocialUserInfo;
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
	
	@Override
	public Optional<UserInfoDTO> getSocialUserInfoByProviderId(String providerId, Jwt jwt) {
		UserInfoDTO userInfoDTO = null;
		SocialUserInfo socialUserInfo = null;
		Optional<SocialUser> socialUserOpt = null;
		SocialUser socialUser = null;
		userInfoDTO = new UserInfoDTO();
		List<Membership> allMemberships = null;
//		List<CommunityCharge> allResponsibles = null;
		
		// SocialUser + Person
		socialUserOpt = socialUserRepository.findSocialUserByProviderId(providerId);
		if (socialUserOpt.isPresent()) {
			socialUser=socialUserOpt.get();
			socialUserInfo = toSocialUserInfo(socialUser);
			
			userInfoDTO.setSocialUserInfo(socialUserInfo);
			userInfoDTO.setPerson((Person)socialUser.getPerson());

			// Se asignan todas comunidades a las que pertenece: mission, origen, otras
			// Si sólo pertenece a una, se pone también
			allMemberships = membershipRepository.findMembershipWithChargesByPersonId(userInfoDTO.getPerson().getId());
			userInfoDTO.fromMembership(allMemberships);
			
			if (userInfoDTO.getMyCommunities().size()==1) {
				userInfoDTO.setSelectedCommunity(userInfoDTO.getMyCommunities().get(0));
			}
			
			return Optional.of(userInfoDTO);
		} else {
			return Optional.empty();
			
		}
		
		// Community
	//	return userInfoDTOOpt;
	}

	@Override
	public UserInfoDTO createPersonAndSocialUser(UserInfoDTO userInfoDTO) {
		Person person = userInfoDTO.getPerson();
		person = personRepository.save(person);

		SocialUser socialUser = toSocialUser(userInfoDTO.getSocialUserInfo());
		socialUser.setPerson(person);

		socialUser = socialUserRepository.save(socialUser);
		
		userInfoDTO.setPerson(person);
		userInfoDTO.setSocialUserInfo(toSocialUserInfo(socialUser));
		return userInfoDTO;
	}
	
	private SocialUser toSocialUser(SocialUserInfo socialUserInfo) {
		SocialUser socialUser = new SocialUser();
		
		
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
