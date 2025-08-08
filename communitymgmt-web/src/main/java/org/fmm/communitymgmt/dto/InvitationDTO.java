package org.fmm.communitymgmt.dto;

import org.fmm.communitymgmt.common.model.Community;
import org.fmm.communitymgmt.common.model.Invitation;
import org.fmm.communitymgmt.common.util.InvitationState;

public class InvitationDTO {
	private Integer id;
	private String name;
	private String signature;
	private Long nbf;
	private Long exp;
	private Long iat;
	private String kpub;
	private InvitationState state;
	private Integer communityId;
	private Integer personId;
	// This field indicates that the invitation is for two people (marriage)
	private Boolean forMarriage;
	private Integer invitationType;
	
	public static Invitation toInvitation(InvitationDTO dto, Community comunity) {
		Invitation invitation = new Invitation();
		invitation.setId(dto.getId());
		invitation.setCommunity(comunity);
		invitation.setExp(dto.exp);
		invitation.setIat(dto.iat);
		invitation.setResponsiblePublicKey(dto.getKpub());
		invitation.setName(dto.getName());
		invitation.setNbf(dto.getNbf());
		invitation.setResponsibleSignature(dto.getSignature());
		invitation.setState(InvitationState.P);
		invitation.setForMarriage(dto.forMarriage);
		
		return invitation;
	}
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getSignature() {
		return signature;
	}
	public void setSignature(String signature) {
		this.signature = signature;
	}
	public Long getNbf() {
		return nbf;
	}
	public void setNbf(Long nbf) {
		this.nbf = nbf;
	}
	public Long getExp() {
		return exp;
	}
	public void setExp(Long exp) {
		this.exp = exp;
	}
	public Long getIat() {
		return iat;
	}
	public void setIat(Long iat) {
		this.iat = iat;
	}
	public String getKpub() {
		return kpub;
	}
	public void setKpub(String kpub) {
		this.kpub = kpub;
	}
	public InvitationState getState() {
		return state;
	}
	public void setState(InvitationState state) {
		this.state = state;
	}
	public Integer getCommunityId() {
		return communityId;
	}
	public void setCommunityId(Integer communityId) {
		this.communityId = communityId;
	}
	public Integer getPersonId() {
		return personId;
	}
	public void setPersonId(Integer personId) {
		this.personId = personId;
	}
	public Boolean getForMarriage() {
		return forMarriage;
	}
	public void setForMarriage(Boolean forMarriage) {
		this.forMarriage = forMarriage;
	}
	public Integer getInvitationType() {
		return invitationType;
	}
	public void setInvitationType(Integer invitationType) {
		this.invitationType = invitationType;
	}

}
