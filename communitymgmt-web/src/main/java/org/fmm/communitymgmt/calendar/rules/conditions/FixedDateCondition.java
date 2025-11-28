package org.fmm.communitymgmt.calendar.rules.conditions;

import java.time.LocalDate;

import org.fmm.communitymgmt.calendar.rules.RuleCondition;
import org.fmm.communitymgmt.calendar.rules.RuleContext;

public class FixedDateCondition implements RuleCondition {

    private final int month;
    private final int day;

    public FixedDateCondition(int month, int day) {
        this.month = month;
        this.day = day;
    }

    @Override
    public boolean evaluate(LocalDate date, RuleContext context) {
        return date.getMonthValue() == month &&
               date.getDayOfMonth() == day;
    }

	@Override
	public boolean matches(LocalDate date) {
		return date.getMonthValue() == month 
				&& date.getDayOfMonth() == day;
	}
	
}
