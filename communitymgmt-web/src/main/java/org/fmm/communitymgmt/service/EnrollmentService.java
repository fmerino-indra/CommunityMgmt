package org.fmm.communitymgmt.service;

import java.util.List;

import org.fmm.communitymgmt.common.model.Invitation;
import org.fmm.communitymgmt.dto.SignatureDTO;

public interface EnrollmentService {
    public List<Invitation> getInvitationsByCommunity(Integer communityId);
    public Invitation createInvitation(Invitation invitation);
	public Invitation createAllInvitationStructure(Invitation invitation, Integer personId);
	public List<Invitation> getInvitationsByPerson(Integer personId);
	public Invitation updateBrotherSignature(Integer communityId, Integer invitationId, SignatureDTO signatureDTO);
	public void acceptBrother(Integer communityId, Integer invitationId);
	

}