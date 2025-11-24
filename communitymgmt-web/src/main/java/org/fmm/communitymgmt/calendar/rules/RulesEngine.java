package org.fmm.communitymgmt.calendar.rules;

import java.util.List;
import java.util.Optional;

import org.fmm.communitymgmt.common.util.enums.TripodEnum;

public class RulesEngine {
    private final List<Rule> rules;

    public RulesEngine(List<Rule> rules) {
        this.rules = rules;
    }

    /** Devuelve la primera razón que bloquea o empty si se puede celebrar */
    public Optional<String> blockingReason(TripodEnum type, CalendarContext ctx) {
        return rules.stream()
                .filter(r -> r.applies(type, ctx))
                .map(r -> r.reason != null ? r.reason : r.id)
                .findFirst();
    }
}
