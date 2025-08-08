package org.fmm.communitymgmt.service;

import java.util.List;

import org.fmm.communitymgmt.common.model.Relationship;


public interface RelationshipService {
    public List<Relationship> getRelationshipByCommunityId(Integer communityId);
	
	@Deprecated
	public List<Relationship> getMembersOfCommunity(Integer communityId);
}