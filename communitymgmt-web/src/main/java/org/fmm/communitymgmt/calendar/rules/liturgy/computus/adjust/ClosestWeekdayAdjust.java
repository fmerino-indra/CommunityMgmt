package org.fmm.communitymgmt.calendar.rules.liturgy.computus.adjust;

import java.time.DayOfWeek;
import java.time.LocalDate;
import java.time.temporal.ChronoUnit;
import java.time.temporal.TemporalAdjusters;

public class ClosestWeekdayAdjust extends AbstractAdjust {
	private DayOfWeek weekday;

	public ClosestWeekdayAdjust(DayOfWeek weekday) {
		super(AdjustEnum.CLOSEST_WEEKDAY);
		this.weekday = weekday;
	}

	public DayOfWeek getWeekday() {
		return weekday;
	}

	@Override
	public LocalDate apply(LocalDate base) {
		LocalDate next,previous;
		long iN, iP;
		next = base.with(TemporalAdjusters.nextOrSame(weekday));
		previous = base.with(TemporalAdjusters.previousOrSame(weekday));
		
		iN = Math.abs(ChronoUnit.DAYS.between(next, base));
		iP = Math.abs(ChronoUnit.DAYS.between(base, previous));
		
		if (iN<iP)
			return next;
		else
			return previous;
	}
	
    @Override
    public String toString() {
        return String.format("{%s:Closest %s }", getType(), weekday);
    }
	
}
