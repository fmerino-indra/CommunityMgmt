package org.fmm.communitymgmt.calendar.rules;

import java.time.LocalDate;
import java.util.List;

import org.fmm.communitymgmt.calendar.rules.effect.RuleEffect;
import org.fmm.communitymgmt.common.util.enums.TripodEnum;
import org.springframework.stereotype.Component;

@Component
public class RulesEngine {

    private final List<Rule> rules;

    public RulesEngine(List<Rule> rules) {
        this.rules = rules;
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
    public RuleEffect evaluate(TripodEnum celebrationType, LocalDate date) {
    	return rules.stream()
    			.filter(r -> r.appliesTo(celebrationType))
    			.filter(r -> r.matches(date))
    			.map(Rule::getEffect)
    			.findFirst()
    			.orElse(null);
    }
}
