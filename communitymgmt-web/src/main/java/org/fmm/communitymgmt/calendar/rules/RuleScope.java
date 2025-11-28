package org.fmm.communitymgmt.calendar.rules;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import org.fmm.communitymgmt.common.util.enums.TripodEnum;

public class RuleScope {

    private List<TripodEnum> celebrations;

//    public RuleScope(List<TripodEnum> celebrations) {
//        this.celebrations = celebrations;
//    }

    public RuleScope(List<String> celebrations) {

        this.celebrations = celebrations.stream()
        		.map(TripodEnum::from)
        		.collect(Collectors.toList());
    }

    public boolean isInScope(TripodEnum type) {
        return celebrations == null || celebrations.contains(type);
    }
}
