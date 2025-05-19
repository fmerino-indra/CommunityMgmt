package org.fmm.communitymgmt.dto;

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

}
