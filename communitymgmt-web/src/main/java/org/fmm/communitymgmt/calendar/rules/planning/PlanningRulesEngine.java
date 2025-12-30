package org.fmm.communitymgmt.calendar.rules.planning;

import java.time.LocalDate;
import java.util.List;

import org.fmm.communitymgmt.calendar.rules.RulesEngine;
import org.fmm.communitymgmt.calendar.rules.planning.effect.RuleEffect;
import org.fmm.communitymgmt.common.util.enums.TripodEnum;
import org.springframework.stereotype.Component;

@Component
public class PlanningRulesEngine extends RulesEngine {

    public PlanningRulesEngine(List<PlanningRule> rules) {
        super(rules);
    }

    /*
    public RuleEffect evaluate(LocalDate date, TripodEnum type, RuleContext ctx) {

        for (Rule r : rules) {

            if (!r.matchesScope(date, type)) continue;

            if (!r.evaluate(date, ctx)) continue;

            return r.getEffect();
        }

        return new RuleEffect(RuleEffectType.NONE, null);
    }

     */
    public RuleEffect evaluateOld(TripodEnum celebrationType, LocalDate date) {
    	return rules.stream()
    			.filter(r -> r.appliesTo(celebrationType))
    			.filter(r -> r.matches(date))
    			.map(PlanningRule::getEffect)
    			.findFirst()
    			.orElse(null);
    }
    public PlanningRule evaluate(TripodEnum celebrationType, LocalDate date) {
    	return rules.stream()
    			.filter(r -> r.appliesTo(celebrationType))
    			.filter(r -> r.matches(date))
    			.findFirst()
    			.orElse(null);
    }
}
