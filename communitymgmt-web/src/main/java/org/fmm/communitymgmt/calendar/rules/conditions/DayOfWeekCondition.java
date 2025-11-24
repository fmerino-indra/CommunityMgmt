package org.fmm.communitymgmt.calendar.rules.conditions;

import java.time.DayOfWeek;

import org.fmm.communitymgmt.calendar.rules.CalendarContext;

public class DayOfWeekCondition implements Condition {
    private final DayOfWeek target;

    public DayOfWeekCondition(DayOfWeek target) {
        this.target = target;
    }

    @Override
    public boolean evaluate(CalendarContext ctx) {
        return ctx.date.getDayOfWeek() == target;
    }
}
