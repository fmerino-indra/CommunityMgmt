package org.fmm.communitymgmt.service.rules;

import java.time.LocalDate;

import org.fmm.communitymgmt.common.util.enums.TripodEnum;

public interface LiturgyService {

	public boolean isCelebrationCancelled(TripodEnum type, LocalDate date);

	LocalDate easterSunday(int year);

	LocalDate palmSunday(int year);

	LocalDate inAlbis(int year);

	boolean isHolyWeek(LocalDate date);
	
}
