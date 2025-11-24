package org.fmm.communitymgmt.calendar.rules.conditions;

import org.fmm.communitymgmt.calendar.rules.CalendarContext;

public class IsPalmSundayCondition implements Condition {
    @Override
    public boolean evaluate(CalendarContext ctx) {
        return ctx.isPalmSunday;
    }
}
