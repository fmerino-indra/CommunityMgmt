package org.fmm.communitymgmt.common.model.common;

import org.fmm.communitymgmt.common.util.enums.MembershipTypeEnum;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;

@Entity(name = "t_membership")
public class TMembership {
	@Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;

	@Column
	private String membership;
	
	@Column(name="same_time")
	private Boolean sameTime;
	
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public String getMembership() {
		return membership;
	}
	public void setMembership(String membership) {
		this.membership = membership;
	}
	public Boolean getSameTime() {
		return sameTime;
	}
	public void setSameTime(Boolean sameTime) {
		this.sameTime = sameTime;
	}
	
	public static TMembership from(MembershipTypeEnum membershipTypeEnum) {
		TMembership aux = null;
	    aux = new TMembership();
	    aux.setId(membershipTypeEnum.getId());
	    aux.setMembership(membershipTypeEnum.getMembership());
	    aux.setSameTime(membershipTypeEnum.getSameTime());
	    return aux;
	}

}
