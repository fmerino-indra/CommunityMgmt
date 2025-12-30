package org.fmm.communitymgmt.calendar.rules.planning;

import java.util.List;

import org.fmm.communitymgmt.calendar.rules.AbstractRule;
import org.fmm.communitymgmt.calendar.rules.RuleCondition;
import org.fmm.communitymgmt.calendar.rules.RuleKindEnum;
import org.fmm.communitymgmt.calendar.rules.RuleScope;
import org.fmm.communitymgmt.calendar.rules.planning.effect.RuleEffect;

public class PlanningRule extends AbstractRule {

    private RuleEffect effect;

    public PlanningRule(String id,
                String name,
                RuleScope scope,
                List<RuleCondition> conditions,
                RuleEffect effect) {
        super(id, name, RuleKindEnum.PLANNING, scope, conditions);
        this.effect = effect;
    }

    public RuleEffect getEffect() {
        return effect;
    }
    
    @Override
    public String toString() {
    	return super.toString();
    }
}
