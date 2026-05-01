package org.fmm.communitymgmt.calendar.rules.liturgy;

import org.fmm.communitymgmt.calendar.rules.RuleKindEnum;
import org.fmm.communitymgmt.calendar.rules.liturgy.computus.AbstractComputus;

public abstract class AbstractLiturgyRule {

	protected String id;
	protected String name;
	private RuleKindEnum kind;
	protected LiturgyRuleScope scope;
	
	private AbstractComputus computus;
	private int liturgicalYearShift=0;
	protected String override;
	
    public AbstractLiturgyRule(String id,
                String name,
        		int liturgicalYearShift,
        		RuleKindEnum kind,
        		LiturgyRuleScope scope,
                AbstractComputus computus,
                String override) {
        this.id = id;
        this.name = name;
        this.kind = kind;
        this.scope = scope;
        this.computus = computus;
		this.liturgicalYearShift=liturgicalYearShift;
		this.override=override;
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

	public String getId() {
		return id;
	}

	public String getName() {
		return name;
	}

	public RuleKindEnum getKind() {
		return kind;
	}

	public LiturgyRuleScope getScope() {
		return scope;
	}

	public String getOverride() {
		return override;
	}
}
