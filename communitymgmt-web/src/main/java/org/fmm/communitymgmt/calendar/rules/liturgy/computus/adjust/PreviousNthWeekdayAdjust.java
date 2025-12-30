package org.fmm.communitymgmt.calendar.rules.liturgy.computus.adjust;

import java.time.DayOfWeek;
import java.time.temporal.TemporalAdjusters;

public class PreviousNthWeekdayAdjust extends AbstractNthWeekday {
	public PreviousNthWeekdayAdjust(DayOfWeek weekday, int nth) {
		super(AdjustEnum.PREVIOUS_NTH_WEEKDAY, weekday, nth, TemporalAdjusters.previous(weekday));
	}
    @Override
    public String toString() {
        return String.format("{%s:Previous %d %s}", this.getType(), nth, weekday );
    }
}
