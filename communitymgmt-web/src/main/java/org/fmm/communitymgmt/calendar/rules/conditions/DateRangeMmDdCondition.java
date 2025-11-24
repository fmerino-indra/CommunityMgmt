package org.fmm.communitymgmt.calendar.rules.conditions;

import java.time.MonthDay;

import org.fmm.communitymgmt.calendar.rules.CalendarContext;

public class DateRangeMmDdCondition implements Condition {
    private final MonthDay from;
    private final MonthDay to;

    public DateRangeMmDdCondition(String fromMmDd, String toMmDd) {
        String[] a = fromMmDd.split("-");
        this.from = MonthDay.of(Integer.parseInt(a[0]), Integer.parseInt(a[1]));
        String[] b = toMmDd.split("-");
        this.to = MonthDay.of(Integer.parseInt(b[0]), Integer.parseInt(b[1]));
    }

    @Override
    public boolean evaluate(CalendarContext ctx) {
        MonthDay md = MonthDay.from(ctx.date);
        // Asumimos que from <= to en el mismo año; si crosses-year habría que adaptar.
        return !md.isBefore(from) && !md.isAfter(to);
    }
}
