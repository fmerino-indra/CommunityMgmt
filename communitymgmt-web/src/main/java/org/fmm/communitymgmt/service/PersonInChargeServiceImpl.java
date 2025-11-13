package org.fmm.communitymgmt.service;

import java.util.List;
import java.util.Optional;

import org.fmm.communitymgmt.common.model.Community;
import org.fmm.communitymgmt.common.model.Membership;
import org.fmm.communitymgmt.common.model.Person;
import org.fmm.communitymgmt.common.model.common.TCharge;
import org.fmm.communitymgmt.common.repository.CommunityRepository;
import org.fmm.communitymgmt.common.repository.MembershipRepository;
import org.fmm.communitymgmt.common.util.enums.MembershipTypeEnum;
import org.springframework.beans.factory.annotation.Autowired;

@org.springframework.stereotype.Service("PersonInChargeService")
public class PersonInChargeServiceImpl implements PersonInChargeService {

	@Autowired
	CommunityRepository communityRepository;
	
	@Autowired
	MembershipRepository membershipRepository;
	
	@Override
	public Community createCommunity(String communityNumber, String parish, String address, String addressNumber, String postalCode, String city, String country, Boolean activated) {
		Community community = new Community();
		community.setCommunityNumber(communityNumber);
		community.setParish(parish);
		community.setParishAddress(address);
		community.setParishAddressNumber(addressNumber);
		community.setParishAddressPostalCode(postalCode);
		community.setParishAddressCity(city);
		community.setCountry(country);
		community.setIsActivated(activated);
		
		community = communityRepository.save(community);
		return community;
	}

	@Override
	public Optional<Community> getCommunity(Integer idCommunity) {
		return communityRepository.findById(idCommunity);
	}

	@Override
	public Community createCommunity(Community community) {
		community = communityRepository.save(community);
		return community;
	}
	
	@Override
	public Membership addMember(Community community, Person person, List<TCharge> chargeList) {
		Membership membership = null;
		
		membership = new Membership();
		membership.setCommunity(community);
		membership.setPerson(person);
		membership.setMembershipType(MembershipTypeEnum.CURRENT.toModel());
		membership.addCharges(chargeList);

		membership = membershipRepository.save(membership);
		
		return membership;
	}

	@Override
	public Membership addMember(Community community, Person person, TCharge charge) {
		Membership membership = null;
		
		membership = new Membership();
		membership.setCommunity(community);
		membership.setPerson(person);
		membership.setMembershipType(MembershipTypeEnum.CURRENT.toModel());

		if (charge != null)
			membership.addCharge(charge);
		membership = membershipRepository.save(membership);
		
		return membership;
	}
}
