package org.fmm.communitymgmt.service;

import java.util.List;
import java.util.Optional;

import org.fmm.communitymgmt.common.model.Community;

public interface CommunityService {
    public Optional<Community> getCommunityByPerson(Integer personId);
    public Optional<Community> getCommunityById(Integer communityId);
    public List<Community> getCommunitiesById(List<Integer> communityIds);
}