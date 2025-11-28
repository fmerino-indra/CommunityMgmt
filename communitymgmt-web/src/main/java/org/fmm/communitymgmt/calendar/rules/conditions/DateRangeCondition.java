package org.fmm.communitymgmt.calendar.rules.conditions;

import java.time.LocalDate;
import java.time.MonthDay;

import org.fmm.communitymgmt.calendar.rules.RuleCondition;
import org.fmm.communitymgmt.calendar.rules.RuleContext;

public class DateRangeCondition implements RuleCondition {

    private final LocalDate start;
    private final LocalDate end;
    private final MonthDay mdStart;
    private final MonthDay mdEnd;

    public DateRangeCondition(String startStr, String endStr) {
    	this(LocalDate.parse(startStr),LocalDate.parse(endStr));
    }
    public DateRangeCondition(LocalDate start, LocalDate end) {
        this.start = start;
        this.end = end;
        this.mdStart = MonthDay.from(start);
        this.mdEnd = MonthDay.from(end);
        
    }

    @Override
    public boolean evaluate(LocalDate date, RuleContext context) {
        return (!date.isBefore(start) && !date.isAfter(end));
    }

    @Override
	public boolean matches(LocalDate date) {
    	MonthDay md = MonthDay.from(date);
    	
    	if (start.isBefore(end) || start.equals(end)) {
    		return !md.isBefore(mdStart) && !md.isAfter(mdEnd);
    	} else {
    		return !md.isAfter(mdEnd) || !md.isBefore(mdStart);
    	}
	}
}
