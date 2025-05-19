package org.fmm.communitymgmt.service;

import java.util.List;
import java.util.stream.Collectors;

import org.fmm.communitymgmt.common.model.Membership;
import org.fmm.communitymgmt.common.model.Relationship;
import org.fmm.communitymgmt.common.repository.MembershipRepository;
import org.fmm.communitymgmt.common.repository.RelationshipRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service("RelationshipService")
public class RelationshipServiceImpl implements RelationshipService {

	@Autowired
	private RelationshipRepository relationshipRepository;
	@Autowired
	private MembershipRepository membershipRepository;
	@Override
	public List<Relationship> getRelationshipByCommunityId(Integer communityId) {
		// TODO Auto-generated method stub
		return relationshipRepository.listCommunityTiny(communityId);
	}

	@Override
	public List<Relationship> getMembersOfCommunity(Integer communityId) {
		List<Membership> communityList = null;
		List<Relationship> relationshipList = null;
		communityList = membershipRepository.findMembershipByCommunityId(communityId);
		
		relationshipList = communityList.stream()
				.map(e -> e.getRelationship())
				.collect(Collectors.toList());
		return relationshipList;
	}
	
	

}
