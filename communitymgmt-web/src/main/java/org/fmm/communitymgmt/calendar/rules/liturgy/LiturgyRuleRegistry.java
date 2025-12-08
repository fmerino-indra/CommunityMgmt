package org.fmm.communitymgmt.calendar.rules.liturgy;

import java.time.LocalDate;
import java.util.Collection;
import java.util.HashMap;
import java.util.Map;

public class LiturgyRuleRegistry {
	private final Map<String, LiturgyRule> rules = new HashMap<>();
	private final Map<String, LocalDate> computedDates = new HashMap<>();
	
	public void register(LiturgyRule r) {
		rules.put(r.getId(), r);
	}
	
	public LiturgyRule get(String id) {
		return rules.get(id);
	}
	
	public Collection<LiturgyRule> all() {
		return rules.values();
	}
	
	public void setComputedDate(String id, LocalDate d) {
		computedDates.put(id, d);
	}
	public LocalDate getComputedDate(String id) {
		return computedDates.get(id);
	}
	public boolean hasComputedDate(String id) {
		return computedDates.containsKey(id);
	}
}
