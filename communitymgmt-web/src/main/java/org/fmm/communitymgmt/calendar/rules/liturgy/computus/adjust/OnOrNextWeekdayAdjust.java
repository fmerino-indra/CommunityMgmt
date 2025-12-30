package org.fmm.communitymgmt.calendar.rules.liturgy.computus.adjust;

import java.time.DayOfWeek;
import java.time.temporal.TemporalAdjusters;

public class OnOrNextWeekdayAdjust extends AbstractOnOrWeekday {
	public OnOrNextWeekdayAdjust(DayOfWeek weekday) {
		super(AdjustEnum.ON_OR_NEXT_NTH_WEEKDAY, weekday, TemporalAdjusters.next(weekday));
	}
	
    @Override
    public String toString() {
        return String.format("{%s:OnOrNext to %s}", this.getType(), weekday );
    }
}
