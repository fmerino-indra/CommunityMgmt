package org.fmm.communitymgmt.calendar.rules.conditions;

import org.fmm.communitymgmt.calendar.rules.CalendarContext;

public class IsHolidayCondition implements Condition {
    @Override
    public boolean evaluate(CalendarContext ctx) {
        return ctx.isHoliday;
    }
}
