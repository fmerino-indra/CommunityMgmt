package org.fmm.communitymgmt.calendar.rules.conditions;

import java.time.MonthDay;

import org.fmm.communitymgmt.calendar.rules.CalendarContext;

public class DateEqualsMmDdCondition implements Condition {
    private final MonthDay target;

    public DateEqualsMmDdCondition(String mmdd) {
        // "12-25" -> MonthDay.of(12,25)
        String[] parts = mmdd.split("-");
        int m = Integer.parseInt(parts[0]);
        int d = Integer.parseInt(parts[1]);
        this.target = MonthDay.of(m, d);
    }

    @Override
    public boolean evaluate(CalendarContext ctx) {
        return MonthDay.from(ctx.date).equals(target);
    }
}
