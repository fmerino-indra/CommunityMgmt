package org.fmm.communitymgmt.calendar.rules.liturgy.computus.adjust;

import java.time.DayOfWeek;
import java.time.temporal.TemporalAdjusters;

public class NextNthWeekdayAdjust extends AbstractNthWeekday {
	public NextNthWeekdayAdjust(DayOfWeek weekday, int nth) {
		super(AdjustEnum.NEXT_NTH_WEEKDAY, weekday, nth, TemporalAdjusters.next(weekday));
//		this.weekday = weekday;
//		this.nth = nth;
//		this.temporalAdjuster = TemporalAdjusters.next(weekday);
	}
	
    @Override
    public String toString() {
        return String.format("{%s:Next %d %s}", this.getType(), nth, weekday );
    }
	
	
}
