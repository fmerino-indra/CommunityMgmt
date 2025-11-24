package org.fmm.communitymgmt.calendar.rules.conditions;

import org.fmm.communitymgmt.calendar.rules.CalendarContext;

public interface Condition {
    boolean evaluate(CalendarContext ctx);
}
