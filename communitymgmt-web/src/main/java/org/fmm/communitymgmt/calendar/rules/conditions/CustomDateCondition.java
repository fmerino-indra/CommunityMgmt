package org.fmm.communitymgmt.calendar.rules.conditions;

import java.time.LocalDate;

import org.fmm.communitymgmt.calendar.rules.CalendarContext;

public class CustomDateCondition implements Condition {
    private final LocalDate date;

    public CustomDateCondition(String yyyyMmDd) {
        this.date = LocalDate.parse(yyyyMmDd);
    }

    @Override
    public boolean evaluate(CalendarContext ctx) {
        return ctx.date.equals(date);
    }
}
