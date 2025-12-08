package org.fmm.communitymgmt.calendar.rules.liturgy.computus.adjust;

import java.time.DayOfWeek;
import java.time.LocalDate;
import java.time.temporal.TemporalAdjuster;

public abstract class AbstractNthWeekday extends AbstractAdjust {

	protected DayOfWeek weekday;
	protected int nth;
	protected TemporalAdjuster temporalAdjuster=null;

	public AbstractNthWeekday(AdjustEnum type, DayOfWeek weekday, int nth, TemporalAdjuster temporalAdjuster) {
		super(type);
		this.weekday = weekday;
		this.nth = nth;
		this.temporalAdjuster = temporalAdjuster;
		
	}

	public DayOfWeek getWeekday() {
		return weekday;
	}

	public int getNth() {
		return nth;
	}

	@Override
	public LocalDate apply(LocalDate base) {
		LocalDate calcDate = null;
		for (int i=0;i<nth;i++) {
			calcDate = base.with(temporalAdjuster);
		}
		return calcDate;
	}

}