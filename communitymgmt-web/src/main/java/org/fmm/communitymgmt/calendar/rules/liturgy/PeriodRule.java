package org.fmm.communitymgmt.calendar.rules.liturgy;

import org.fmm.communitymgmt.calendar.rules.RuleKindEnum;
import org.fmm.communitymgmt.calendar.rules.liturgy.computus.AbstractComputus;

public class PeriodRule extends AbstractLiturgyRule {
    public PeriodRule(String id,
            String name,
    		int liturgicalYearShift,
    		RuleKindEnum kind,
    		LiturgyRuleScope scope,
            AbstractComputus computus,
            String override) {
    	super(id,name,liturgicalYearShift,kind, scope,computus,override);
    }
}
