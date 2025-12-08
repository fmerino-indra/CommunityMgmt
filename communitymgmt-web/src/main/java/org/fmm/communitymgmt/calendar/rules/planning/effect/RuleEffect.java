package org.fmm.communitymgmt.calendar.rules.planning.effect;

public abstract class RuleEffect {

    private RuleEffectType type;

    public RuleEffect(RuleEffectType type) {
        this.type = type;
    }

    public RuleEffectType getType() { return type; }
}
