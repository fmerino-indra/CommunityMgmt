package org.fmm.communitymgmt.calendar.rules;

import java.time.LocalDate;
import java.util.List;

import org.fmm.communitymgmt.common.util.enums.TripodEnum;

public class AbstractRule {

	protected String id;
	protected String name;
	private RuleKindEnum kind;
	protected RuleScope scope;
	protected List<RuleCondition> conditions;

	public AbstractRule(String id, String name, RuleKindEnum kind, RuleScope scope, List<RuleCondition> conditions) {
		super();
		this.id = id;
		this.name = name;
		this.kind = kind;
		this.scope = scope;
		this.conditions = conditions;
	}

	public boolean matchesScope(LocalDate date, TripodEnum type) {
	    return scope == null || scope.isInScope(type);
	}

	public boolean appliesTo(TripodEnum type) {
		return matchesScope(null, type);
	}

	public boolean evaluate(LocalDate date, RuleContext ctx) {
	    for (RuleCondition c : conditions) {
	        if (!c.evaluate(date, ctx)) return false;
	    }
	    return true;
	}

	public boolean matches(LocalDate date) {
		return conditions.stream().allMatch(c -> c.matches(date));
	}

	public String getId() {
		return id;
	}

	public String getName() {
		return name;
	}

	public RuleKindEnum getKind() {
		return kind;
	}
	
	@Override
	public String toString() {
		return String.format("%s|%s\n\t%s\n", id, name, scope);
	}
}