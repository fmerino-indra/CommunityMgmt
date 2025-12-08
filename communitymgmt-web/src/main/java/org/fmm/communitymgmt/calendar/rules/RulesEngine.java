package org.fmm.communitymgmt.calendar.rules;

import java.util.List;

import org.fmm.communitymgmt.calendar.rules.planning.PlanningRule;

public class RulesEngine {

	protected final List<PlanningRule> rules;

	public RulesEngine(List<PlanningRule> rules) {
		super();
		this.rules = rules;
	}

}