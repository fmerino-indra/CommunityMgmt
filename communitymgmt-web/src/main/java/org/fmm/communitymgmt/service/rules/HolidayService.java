package org.fmm.communitymgmt.service.rules;

import java.time.LocalDate;

public interface HolidayService {

	boolean isHoliday(LocalDate date, String regionCode);
	
}
