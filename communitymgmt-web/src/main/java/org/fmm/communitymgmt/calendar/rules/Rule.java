package org.fmm.communitymgmt.calendar.rules;

import java.time.LocalDate;
import java.util.List;

import org.fmm.communitymgmt.calendar.rules.effect.RuleEffect;
import org.fmm.communitymgmt.common.util.enums.TripodEnum;

public class Rule {

    private String id;
    private String name;
    private RuleScope scope;
    private List<RuleCondition> conditions;
    private RuleEffect effect;

    public Rule(String id,
                String name,
                RuleScope scope,
                List<RuleCondition> conditions,
                RuleEffect effect) {
        this.id = id;
        this.name = name;
        this.scope = scope;
        this.conditions = conditions;
        this.effect = effect;
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

    public RuleEffect getEffect() {
        return effect;
    }

	public String getId() {
		return id;
	}

	public String getName() {
		return name;
	}
}
