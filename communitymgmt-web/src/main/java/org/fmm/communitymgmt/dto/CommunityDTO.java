package org.fmm.communitymgmt.dto;

import java.util.List;

import org.fmm.communitymgmt.common.model.Community;
import org.fmm.communitymgmt.common.model.common.TCharge;

public class CommunityDTO {
	private Community myCommunityData;
	private List<TCharge> myCharges;
	public Community getMyCommunityData() {
		return myCommunityData;
	}
	public void setMyCommunityData(Community myCommunityData) {
		this.myCommunityData = myCommunityData;
	}
	public List<TCharge> getMyCharges() {
		return myCharges;
	}
	public void setMyCharges(List<TCharge> myCharges) {
		this.myCharges = myCharges;
	} 
}
