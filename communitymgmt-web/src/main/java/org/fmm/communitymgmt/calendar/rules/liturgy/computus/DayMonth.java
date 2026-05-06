package org.fmm.communitymgmt.calendar.rules.liturgy.computus;

import java.time.LocalDate;

import com.fasterxml.jackson.databind.JsonNode;

public class DayMonth {
	private LocalDate date;
	
	public LocalDate getDate() {
		return date;
	}
	public int getDay() {
		return day;
	}
	public int getMonth() {
		return month;
	}
	private int day;
	private int month;
	
	public static DayMonth fromJsonNode(JsonNode node) {
		DayMonth dayMonth = null;
		dayMonth = new DayMonth();
		int day = 0;
		int month = 0;

    	day = node.get("day").asInt();
    	month = node.get("month").asInt();
		dayMonth.day = day;
		dayMonth.month = month;
		dayMonth.date = null;
		return dayMonth;
	}
	public String toString() {
        return String.format("DayMonth{ (day: %d - month: %d) -> () }", day, month);
	}
	
}
