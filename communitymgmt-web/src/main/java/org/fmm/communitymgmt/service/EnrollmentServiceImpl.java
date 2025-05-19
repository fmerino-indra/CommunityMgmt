package org.fmm.communitymgmt.service;

import java.util.List;

import org.fmm.communitymgmt.common.model.Invitation;
import org.fmm.communitymgmt.common.repository.InvitationRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service("EnrollmentService")
public class EnrollmentServiceImpl implements EnrollmentService {

	@Autowired
	InvitationRepository invitationRepository;
	
	@Override
	public List<Invitation> getInvitationsByCommunity(Integer communityId) {
		List<Invitation> list = invitationRepository.listInvitationsByCommunityId(communityId); 
		return list;
	}

	@Override
	public Invitation createInvitation(Invitation invitation) {
		return invitationRepository.save(invitation);
	}

}
