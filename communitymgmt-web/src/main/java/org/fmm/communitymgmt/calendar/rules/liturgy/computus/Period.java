package org.fmm.communitymgmt.calendar.rules.liturgy.computus;

import java.time.LocalDate;

import com.fasterxml.jackson.databind.JsonNode;

public class Period {
	private LocalDate initDate;
	private LocalDate endDate;
	String init = null;
	String end = null;
	
	public LocalDate getInitDate() {
		return initDate;
	}
	public void setInitDate(LocalDate initDate) {
		this.initDate = initDate;
	}
	public LocalDate getEndDate() {
		return endDate;
	}
	public void setEndDate(LocalDate endDate) {
		this.endDate = endDate;
	}
	
	public static Period fromJsonNode(JsonNode node) {
		Period period = null;
		period = new Period();
		period.init = node.get("init").asText();
		period.end = node.get("end").asText();
		period.setInitDate(null);
		return period;
	}
	public String toString() {
        return String.format("Period{ (init: %s - end: %s) -> () }", init, end);
	}
	
}
