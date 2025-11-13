package org.fmm.communitymgmt.common.util.enums;

import org.fmm.communitymgmt.common.model.common.TMembership;

public enum MembershipTypeEnum {
	CURRENT(1,"Current", true),
	MISSION(2, "Mission", true),
	OTHER(3, "Other", false),
	ORIGIN(4, "Origin", false);
	
	private final Integer id;
	private final String membership;
	private final Boolean sameTime;
	
	private MembershipTypeEnum(Integer id, String membership, Boolean sameTime) {
		this.id = id;
		this.membership = membership;
		this.sameTime = sameTime;
	}
	
	public Integer getId() {
		return id;
	}

	public String getMembership() {
		return membership;
	}

	public Boolean getSameTime() {
		return sameTime;
	}
	
	public TMembership toModel() {
		TMembership m = new TMembership();
		m.setId(this.getId());
		m.setMembership(this.getMembership());
		m.setSameTime(this.getSameTime());
		return m;
	}
}
