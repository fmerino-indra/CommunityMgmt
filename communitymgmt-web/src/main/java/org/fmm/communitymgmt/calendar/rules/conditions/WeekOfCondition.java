package org.fmm.communitymgmt.calendar.rules.conditions;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import org.fmm.communitymgmt.calendar.rules.RuleCondition;
import org.fmm.communitymgmt.calendar.rules.RuleContext;
import org.fmm.communitymgmt.calendar.rules.RuleUtil;

public class WeekOfCondition implements RuleCondition {

    private final List<Integer> offset;
    private final int weekOfMonth;
    
    public WeekOfCondition(String dateStr, List<Integer> offset) {
    	this.weekOfMonth = RuleUtil.weekOfDate(LocalDate.parse(dateStr));
    	this.offset = offset;
	}
    @Override
    public boolean evaluate(LocalDate date, RuleContext context) {
    	return matches(date);
    }

	@Override
	public boolean matches(LocalDate date) {
    	boolean response = false;
    	for (int i = 0; i<offset.size(); i++) {
    		response = response || this.weekOfMonth+offset.get(i) == RuleUtil.weekOfDate(date); 
    	}
    	return response;
	}
}
