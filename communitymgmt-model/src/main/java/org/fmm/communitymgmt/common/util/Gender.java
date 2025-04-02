package org.fmm.communitymgmt.common.util;

public enum Gender {
	M("Male"),
	F("Female");
	
	private final String description;
	
	Gender(String description) {
		this.description = description;
	}
	
	public String getDescription() {
		return description;
	}
}
