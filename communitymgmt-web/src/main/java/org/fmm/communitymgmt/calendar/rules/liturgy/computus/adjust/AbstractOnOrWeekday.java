package org.fmm.communitymgmt.calendar.rules.liturgy.computus.adjust;

import java.time.DayOfWeek;
import java.time.LocalDate;
import java.time.temporal.TemporalAdjuster;

public abstract class AbstractOnOrWeekday extends AbstractAdjust {

	protected DayOfWeek weekday;
	protected TemporalAdjuster temporalAdjuster=null;

	public AbstractOnOrWeekday(AdjustEnum type, DayOfWeek weekday, TemporalAdjuster temporalAdjuster) {
		super(type);
		this.weekday = weekday;
		this.temporalAdjuster = temporalAdjuster;
	}

	public DayOfWeek getWeekday() {
		return weekday;
	}

	@Override
	public LocalDate apply(LocalDate base) {
		LocalDate calcDate = base;
		
		if (this.getWeekday() != base.getDayOfWeek())
			calcDate = base.with(temporalAdjuster);
		
		return calcDate;
	}
}