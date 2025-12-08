package org.fmm.communitymgmt.calendar.rules.liturgy;

import java.time.LocalDate;

public class LiturgicalFeast {
	private String id;
	private String name;
	private LocalDate date;
    public final String ruleId;
	
//	private FeastRankEnum rank;
//	private FeastTypeEnum type;
	
	public LiturgicalFeast(String id, String name, LocalDate date, String ruleId) {
		super();
		this.id = id;
		this.name = name;
		this.date = date;
		this.ruleId = ruleId;
	}
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public LocalDate getDate() {
		return date;
	}
	public void setDate(LocalDate date) {
		this.date = date;
	}
	
    @Override
    public String toString() {
        return String.format("LiturgyFeast{ %s: %s -> %s (rule=%s)}", id, name, date, ruleId);
    }
	
}
