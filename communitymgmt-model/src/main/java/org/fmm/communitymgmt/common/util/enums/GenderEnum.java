package org.fmm.communitymgmt.common.util.enums;

public enum GenderEnum {
	M("Male"),
	F("Female");
	
	private final String description;
	
	GenderEnum(String description) {
		this.description = description;
	}
	
	public String getDescription() {
		return description;
	}
}
