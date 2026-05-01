package org.fmm.communitymgmt.common.model;

import java.io.Serializable;
import java.util.Objects;

public class CommunitySettingsYearPK implements Serializable {
	private static final long serialVersionUID = 1L;
	
	private Integer communityId;
	private Integer year;
	
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (!(obj instanceof CommunitySettingsYearPK communitySettingsYearPK))
			return false;
		return communityId == communitySettingsYearPK.communityId && year == communitySettingsYearPK.year;
	}
	
	@Override
	public int hashCode() {
		return Objects.hash(communityId, year);
	}
}
