package org.fmm.communitymgmt.service;

import java.util.List;

import org.fmm.communitymgmt.common.model.Invitation;

public interface EnrollmentService {
    public List<Invitation> getInvitationsByCommunity(Integer communityId);
    public Invitation createInvitation(Invitation invitation);
}