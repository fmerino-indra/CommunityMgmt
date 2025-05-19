package org.fmm.communitymgmt.service;

import java.util.List;
import java.util.Optional;

import org.fmm.communitymgmt.common.model.Community;
import org.fmm.communitymgmt.common.repository.CommunityRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service(value = "CommunityService")
public class CommunityServiceImpl implements CommunityService {

	@Autowired
	private CommunityRepository communityRepository;
	
	@Override
	public Optional<Community> getCommunityByPerson(Integer personId) {
		// TODO Auto-generated method stub
		return Optional.empty();
	}

	@Override
	public Optional<Community> getCommunityById(Integer communityId) {
		return communityRepository.findById(communityId);
	}

	@Override
	public List<Community> getCommunitiesById(List<Integer> communityIds) {
		return communityRepository.listCommunitiesByIds(communityIds);
	}

}
