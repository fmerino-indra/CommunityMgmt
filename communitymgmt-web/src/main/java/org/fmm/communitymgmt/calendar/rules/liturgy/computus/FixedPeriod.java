package org.fmm.communitymgmt.calendar.rules.liturgy.computus;

import com.fasterxml.jackson.databind.JsonNode;

public class FixedPeriod extends PeriodGen<DayMonth> {
	/**
    "period": {
        "initFix": {
            "day": 1,
            "month": 7
        },
        "endFix": {
            "day": 31,
            "month": 8
        }
    }

	 *
	 * @param node
	 * @return
	 */
	public static FixedPeriod fromJsonNode(JsonNode node) {
		FixedPeriod period = null;
		period = new FixedPeriod();
		DayMonth dayMonth = null;
		if (node.get("initFix")!=null) {
			dayMonth = DayMonth.fromJsonNode(node.get("initFix"));
			period.setInit(dayMonth);
		}
		if (node.get("endFix")!=null) {
			dayMonth = DayMonth.fromJsonNode(node.get("endFix"));
			period.setEnd(dayMonth);
		}
		return period;
	}
	public String toString() {
        return String.format("Period{ (init: %s - end: %s) -> () }", getInit(), getEnd());
	}
	
}
