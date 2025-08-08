package org.fmm.communitymgmt.common.util;

import org.fmm.communitymgmt.common.model.TMembership;

public enum MembershipType {
	CURRENT(1,"Current"),
	MISSION(2, "Mission"),
	OTHER(3, "Other"),
	ORIGIN(4, "Origin");
	
	private final Integer id;
	private final String membership;
	
	private MembershipType(Integer id, String membership) {
		this.id = id;
		this.membership = membership;
	}
	
	public Integer getId() {
		return id;
	}

	public String getMembership() {
		return membership;
	}
	
	public TMembership toModel() {
		TMembership m = new TMembership();
		m.setId(this.getId());
		m.setMembership(this.getMembership());
		
		return m;
	}
}
