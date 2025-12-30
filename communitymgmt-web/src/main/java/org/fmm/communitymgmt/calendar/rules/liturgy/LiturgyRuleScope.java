package org.fmm.communitymgmt.calendar.rules.liturgy;

import java.util.ArrayList;
import java.util.List;

public class LiturgyRuleScope {

	private String magnitude;
    private List<String> values;


    public LiturgyRuleScope(String magnitude, List<String> values) {
    	this.magnitude = magnitude;
    	this.values = values;
    }

    public boolean isInScope(String scope) {
        //return values == null || values.isEmpty() || values.contains(scope);
        return isUniversal() || scope == null || "".equals(scope) || values.contains(scope);
    }

	public String getMagnitude() {
		return magnitude;
	}

	public void addMagnitude(String magnitude) {
		if (values == null)
			values = new ArrayList<>();
		values.add(magnitude);
	}
	
	public boolean isUniversal() {
		return magnitude.equals("universal")
				|| values == null
				|| values.isEmpty();
	}
	
	@Override
	public String toString() {
		String response = "";
		response = String.format("Magnitude: %s -> Values: [",magnitude);
		if (values != null)
			for (int i = 0; i<values.size();i++) {
				response+=String.format("%s,", values.get(i));
			}
		response+="]";
		return response;
		
	}
}
