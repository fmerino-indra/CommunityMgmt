package org.fmm.communitymgmt.common.util.enums;

public enum InvitationStateEnum {
	G("Generated"),
	P("Processing"),
	F("Finished");
	
	
	private final String description;
	
	InvitationStateEnum(String description) {
		this.description = description;
	}
	
	public String getDescription() {
		return description;
	}
	
}
