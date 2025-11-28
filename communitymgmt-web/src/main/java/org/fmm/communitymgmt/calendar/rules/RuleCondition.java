package org.fmm.communitymgmt.calendar.rules;

import java.time.LocalDate;

public interface RuleCondition {
    boolean evaluate(LocalDate date, RuleContext context);
    boolean matches(LocalDate date);
}
