package org.fmm.communitymgmt.dto;

import org.fmm.communitymgmt.common.model.Invitation;
import org.fmm.communitymgmt.common.util.enums.InvitationStateEnum;

import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;

public class FullInvitationDTO {
	private Integer id;
	private String name;
	private Long nbf;
	private Long exp;
	private Long iat;
	@Enumerated(EnumType.STRING) // Guarda "M" o "F" como texto
	private InvitationStateEnum state;
	private Boolean forMarriage;
	private Integer invitationType;

	private Integer communityId;
	private String communityFullName;
	private String communityCity;
	private String communityCountry;
	
	private Integer personId;
	private String personFullName;
	private String personPublicKey;
	private String personSignature;
	
	private Integer responsibleId;
	private String responsibleFullName;
	private String responsiblePublicKey;
	private String responsibleSignature;
	
	public static FullInvitationDTO fromInvitation(Invitation invitation) {
		FullInvitationDTO fullDTO = new FullInvitationDTO();
		fullDTO.setExp(invitation.getExp());
		fullDTO.setForMarriage(invitation.getForMarriage());
		fullDTO.setIat(invitation.getIat());
		fullDTO.setId(invitation.getId());
		fullDTO.setInvitationType(invitation.getInvitationType().getId());
		fullDTO.setName(invitation.getName());
		fullDTO.setNbf(invitation.getNbf());

		fullDTO.setCommunityId(invitation.getCommunity().getId());
		fullDTO.setCommunityFullName(invitation.getCommunity().getCommunityNumber() + " - " + invitation.getCommunity().getParish());
		fullDTO.setCommunityCity(invitation.getCommunity().getParishAddressCity());
		fullDTO.setCommunityCountry(invitation.getCommunity().getCountry());
		
		fullDTO.setPersonId(invitation.getPerson().getId());
		fullDTO.setPersonFullName(invitation.getPerson().getName() + " " + invitation.getPerson().getSurname1());
		fullDTO.setPersonPublicKey(invitation.getPersonPublicKey());
		fullDTO.setPersonSignature(invitation.getPersonSignature());
		//@TODO 
		fullDTO.setResponsibleId(0);
		fullDTO.setResponsibleFullName("");
		fullDTO.setResponsiblePublicKey(invitation.getResponsiblePublicKey());
		fullDTO.setResponsibleSignature(invitation.getResponsibleSignature());
		
		fullDTO.setState(invitation.getState());
		return fullDTO;
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
	public InvitationStateEnum getState() {
		return state;
	}
	public void setState(InvitationStateEnum state) {
		this.state = state;
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
	public Integer getCommunityId() {
		return communityId;
	}
	public void setCommunityId(Integer communityId) {
		this.communityId = communityId;
	}
	public String getCommunityFullName() {
		return communityFullName;
	}
	public void setCommunityFullName(String communityFullName) {
		this.communityFullName = communityFullName;
	}
	public Integer getPersonId() {
		return personId;
	}
	public void setPersonId(Integer personId) {
		this.personId = personId;
	}
	public String getPersonFullName() {
		return personFullName;
	}
	public void setPersonFullName(String personFullName) {
		this.personFullName = personFullName;
	}
	public String getPersonPublicKey() {
		return personPublicKey;
	}
	public void setPersonPublicKey(String personPublicKey) {
		this.personPublicKey = personPublicKey;
	}
	public String getPersonSignature() {
		return personSignature;
	}
	public void setPersonSignature(String personSignature) {
		this.personSignature = personSignature;
	}
	public Integer getResponsibleId() {
		return responsibleId;
	}
	public void setResponsibleId(Integer responsibleId) {
		this.responsibleId = responsibleId;
	}
	public String getResponsibleFullName() {
		return responsibleFullName;
	}
	public void setResponsibleFullName(String responsibleFullName) {
		this.responsibleFullName = responsibleFullName;
	}
	public String getResponsiblePublicKey() {
		return responsiblePublicKey;
	}
	public void setResponsiblePublicKey(String responsiblePublicKey) {
		this.responsiblePublicKey = responsiblePublicKey;
	}
	public String getResponsibleSignature() {
		return responsibleSignature;
	}
	public void setResponsibleSignature(String responsibleSignature) {
		this.responsibleSignature = responsibleSignature;
	}

	public String getCommunityCity() {
		return communityCity;
	}

	public void setCommunityCity(String communityCity) {
		this.communityCity = communityCity;
	}

	public String getCommunityCountry() {
		return communityCountry;
	}

	public void setCommunityCountry(String communityCountry) {
		this.communityCountry = communityCountry;
	}

}
