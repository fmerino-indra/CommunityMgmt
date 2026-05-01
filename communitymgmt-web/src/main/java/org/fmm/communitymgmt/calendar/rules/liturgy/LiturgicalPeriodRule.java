package org.fmm.communitymgmt.calendar.rules.liturgy;

import org.fmm.communitymgmt.calendar.rules.RuleKindEnum;
import org.fmm.communitymgmt.calendar.rules.liturgy.computus.AbstractComputus;

public class LiturgicalPeriodRule extends AbstractLiturgyRule {
    public LiturgicalPeriodRule(String id,
            String name,
    		int liturgicalYearShift,
    		LiturgyRuleScope scope,
            AbstractComputus computus,
            String override) {
    	super(id,name,liturgicalYearShift,RuleKindEnum.LITURGICAL_PERIOD,scope,computus,override);
    }
}
