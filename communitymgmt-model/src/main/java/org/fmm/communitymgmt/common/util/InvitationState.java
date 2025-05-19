package org.fmm.communitymgmt.common.util;

public enum InvitationState {
	G("Generated"),
	P("Processing"),
	F("Finished");
	
	
	private final String description;
	
	InvitationState(String description) {
		this.description = description;
	}
	
	public String getDescription() {
		return description;
	}
	
}
