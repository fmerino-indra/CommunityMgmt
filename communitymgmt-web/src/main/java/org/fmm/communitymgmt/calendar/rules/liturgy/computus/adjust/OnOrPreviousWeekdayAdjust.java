package org.fmm.communitymgmt.calendar.rules.liturgy.computus.adjust;

import java.time.DayOfWeek;
import java.time.temporal.TemporalAdjusters;

public class OnOrPreviousWeekdayAdjust extends AbstractOnOrWeekday {
	public OnOrPreviousWeekdayAdjust(DayOfWeek weekday) {
		super(AdjustEnum.ON_OR_PREVIOUS_NTH_WEEKDAY, weekday, TemporalAdjusters.previous(weekday));
	}

    @Override
    public String toString() {
        return String.format("{%s:OnOrPrevious to %s}", this.getType(), weekday );
    }
}
