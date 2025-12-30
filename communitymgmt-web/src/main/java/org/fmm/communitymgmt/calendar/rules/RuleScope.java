package org.fmm.communitymgmt.calendar.rules;

import java.util.List;
import java.util.stream.Collectors;

import org.fmm.communitymgmt.common.util.enums.TripodEnum;

public class RuleScope {

    private List<TripodEnum> celebrations=null;

//    public RuleScope(List<TripodEnum> celebrations) {
//        this.celebrations = celebrations;
//    }

    public RuleScope(List<String> celebrations) {
    	if (celebrations == null)
    		throw new RuntimeException("Exception while RuleScope instance (celebrations can't be null)");
        this.celebrations = celebrations.stream()
        		.map(TripodEnum::from)
        		.collect(Collectors.toList());
    }

    public boolean isInScope(TripodEnum type) {
        return celebrations == null || celebrations.contains(type);
    }
    @Override
    public String toString() {
    	String aux = "Scope: [";
    	String auxContent = "";
    	for (TripodEnum t: celebrations) {
    		auxContent+=t.toString()+",";
    	}
    	if (auxContent.length() > 0)
    		auxContent= auxContent.substring(0,auxContent.length()-1);
    	aux += auxContent+"]";
    	return aux;
    }
}
