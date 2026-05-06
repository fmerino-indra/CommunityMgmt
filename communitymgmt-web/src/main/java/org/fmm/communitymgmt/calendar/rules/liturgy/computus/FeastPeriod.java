package org.fmm.communitymgmt.calendar.rules.liturgy.computus;

import com.fasterxml.jackson.databind.JsonNode;

public class FeastPeriod extends PeriodGen<String> {
	/**
    "period": {
        "initFeast": "first_sunday_advent",
        "endFeast": "christmas"
    }

	 *
	 * @param node
	 * @return
	 */
	
	public static FeastPeriod fromJsonNode(JsonNode node) {
		FeastPeriod period = null;
		period = new FeastPeriod();
		
		period.setInit((node.get("initFeast")!=null) ? node.get("initFeast").asText() : null);
		period.setEnd((node.get("endFeast")!=null)?node.get("endFeast").asText():null);
		return period;
	}
	public String toString() {
      return String.format("Period{ (init: %s - end: %s) -> () }", getInit(), getEnd());
	}

}
