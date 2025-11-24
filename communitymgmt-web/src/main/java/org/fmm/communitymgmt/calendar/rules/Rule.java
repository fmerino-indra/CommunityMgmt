package org.fmm.communitymgmt.calendar.rules;

import java.util.List;

import org.fmm.communitymgmt.calendar.rules.conditions.Condition;
import org.fmm.communitymgmt.common.util.enums.TripodEnum;

public class Rule {
    public final String id;
    public final String block; // "PALABRA", "EUCARISTIA", "CONVIVENCIA" or "*"
    public final List<Condition> conditions;
    public final String reason;
    public final Object meta;

    public Rule(String id, String block, List<Condition> conditions, String reason, Object meta) {
        this.id = id;
        this.block = block;
        this.conditions = conditions;
        this.reason = reason;
        this.meta = meta;
    }

    public boolean applies(TripodEnum type, CalendarContext ctx) {
        if (!"*".equals(block) && !block.equals(type.name())) return false;
        return conditions.stream().allMatch(c -> c.evaluate(ctx));
    }
}
