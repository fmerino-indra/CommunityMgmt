package org.fmm.communitymgmt.service;

import java.util.Optional;

import org.fmm.communitymgmt.common.model.Community;
import org.fmm.communitymgmt.common.repository.CommunityRepository;
import org.springframework.beans.factory.annotation.Autowired;

@org.springframework.stereotype.Service("PersonInChargeService")
public class PersonInChargeServiceImpl implements PersonInChargeService {

	@Autowired
	CommunityRepository communityRepository;
	
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
}
