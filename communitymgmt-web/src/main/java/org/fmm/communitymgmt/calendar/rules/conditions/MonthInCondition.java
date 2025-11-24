package org.fmm.communitymgmt.calendar.rules.conditions;

import java.util.List;

import org.fmm.communitymgmt.calendar.rules.CalendarContext;

public class MonthInCondition implements Condition {
    private final List<Integer> months;

    public MonthInCondition(List<Integer> months) {
        this.months = months;
    }

    @Override
    public boolean evaluate(CalendarContext ctx) {
        return months.contains(ctx.date.getMonthValue());
    }
}
