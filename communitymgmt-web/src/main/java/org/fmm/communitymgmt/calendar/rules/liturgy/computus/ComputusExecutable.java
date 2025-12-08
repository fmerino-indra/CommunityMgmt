package org.fmm.communitymgmt.calendar.rules.liturgy.computus;

import java.time.LocalDate;
import java.util.Collections;
import java.util.Set;

import org.fmm.communitymgmt.calendar.rules.liturgy.LiturgyRuleContext;
import org.fmm.communitymgmt.calendar.rules.liturgy.LiturgyRuleRegistry;

public interface ComputusExecutable {
	/**
	* Compute the LocalDate for this computus, given the liturgical year, context and registry.
	* The implementation is responsible for choosing the correct calendar year (see helpers below). NO
	*/
	LocalDate compute(int liturgicalYear, LiturgyRuleContext ctx, LiturgyRuleRegistry registry);
	
	/**
	* Returns referenced base rule ids (if any) — used to build dependency graph
	*/
	default Set<String> referencedRuleIds() {
		return Collections.emptySet();
	}
}
