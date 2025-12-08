package org.fmm.communitymgmt.calendar.rules.liturgy;

import java.util.List;

import org.fmm.communitymgmt.calendar.rules.AbstractRule;
import org.fmm.communitymgmt.calendar.rules.RuleCondition;
import org.fmm.communitymgmt.calendar.rules.RuleKindEnum;
import org.fmm.communitymgmt.calendar.rules.RuleScope;
import org.fmm.communitymgmt.calendar.rules.liturgy.computus.AbstractComputus;

public class LiturgyRule extends AbstractRule {

	private AbstractComputus computus;
	private int liturgicalYearShift=0;
	
    public LiturgyRule(String id,
                String name,
        		int liturgicalYearShift,
                RuleScope scope,
                List<RuleCondition> conditions,
                AbstractComputus computus) {
        super(id, name, RuleKindEnum.LITURGY, scope, conditions);
        this.computus = computus;
		this.liturgicalYearShift=liturgicalYearShift;
    }

	public AbstractComputus getComputus() {
		return computus;
	}
    @Override
    public String toString() {
//        return "LiturgyFeast{" + id + ": " + name + " -> " + date + " (rule=" + ruleId +") + }";
        return String.format("LiturgicalRule{ %s: %s -> computus=%s}", id, name, computus);
    }

	public int getLiturgicalYearShift() {
		return liturgicalYearShift;
	}
}
