package org.fmm.communitymgmt.dto;

import org.fmm.communitymgmt.calendar.rules.planning.PlanningRule;
import org.fmm.communitymgmt.common.model.calendar.Event;

public class EventDTO {
	private Event event;
	private PlanningRule appliedRule;
	
	public Event getEvent() {
		return event;
	}
	public void setEvent(Event event) {
		this.event = event;
	}
	
	public PlanningRule getAppliedRule() {
		return appliedRule;
	}
	public void setAppliedRule(PlanningRule appliedRule) {
		this.appliedRule = appliedRule;
	}
	
	
	@Override
	public String toString() {
		String eventStr = event.toString();
		
		String ruleStr = "";
		if (appliedRule != null)
			ruleStr = appliedRule.toString();
		
		return eventStr + " " + ruleStr;
	}
}
