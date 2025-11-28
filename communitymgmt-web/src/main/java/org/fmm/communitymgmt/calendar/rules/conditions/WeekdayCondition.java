package org.fmm.communitymgmt.calendar.rules.conditions;

import java.time.DayOfWeek;
import java.time.LocalDate;

import org.fmm.communitymgmt.calendar.rules.RuleCondition;
import org.fmm.communitymgmt.calendar.rules.RuleContext;

public class WeekdayCondition implements RuleCondition {

    private final DayOfWeek day;

    public WeekdayCondition(DayOfWeek day) {
        this.day = day;
    }

    @Override
    public boolean evaluate(LocalDate date, RuleContext context) {
        return date.getDayOfWeek() == day;
    }

	@Override
	public boolean matches(LocalDate date) {
		return date.getDayOfWeek() == day;
	}
}
