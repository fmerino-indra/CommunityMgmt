package org.fmm.communitymgmt.calendar.rules.conditions;

import java.time.LocalDate;

import org.fmm.communitymgmt.calendar.rules.RuleCondition;
import org.fmm.communitymgmt.calendar.rules.RuleContext;

public class SeasonCondition implements RuleCondition {

    private final String season;

    public SeasonCondition(String season) {
        this.season = season;
    }

    @Override
    public boolean evaluate(LocalDate date, RuleContext context) {
        return context.getHolidayService().isHoliday(date, season);
    }

    @Override
	public boolean matches(LocalDate date) {
    	return false;
	}
}
