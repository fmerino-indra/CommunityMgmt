package org.fmm.communitymgmt.calendar.rules.conditions;

import java.time.LocalDate;

import org.fmm.communitymgmt.calendar.rules.RuleCondition;
import org.fmm.communitymgmt.calendar.rules.RuleContext;

public class HolidayCondition implements RuleCondition {

    private final String holidayType;

    public HolidayCondition(String holidayType) {
        this.holidayType = holidayType;
    }

    @Override
    public boolean evaluate(LocalDate date, RuleContext context) {
        return context.getHolidayService().isHoliday(date, holidayType);
    }

    @Override
	public boolean matches(LocalDate date) {
    	return false;
	}
}
