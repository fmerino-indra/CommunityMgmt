package org.fmm.communitymgmt.calendar.rules.liturgy.computus;

import java.time.LocalDate;

public class Period {
	private LocalDate initDate;
	private LocalDate endDate;
	
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
	public String toString() {
        return String.format("Period{ (init: %s - end: %s) -> () }", initDate, endDate);
	}
	
}
