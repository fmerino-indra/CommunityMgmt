package org.fmm.communitymgmt.calendar.rules.liturgy;

import java.time.LocalDate;
import java.time.ZoneId;
import java.util.Locale;
// ISO3166-1 Y ISO3166-2
public class LiturgyRuleContext {
	public int liturgicalYear; 
	public LocalDate liturgicalYearStart; // starts 1st sunday of Advent (previous year) 
	
	
	
	public LiturgyRuleContext(int liturgicalYear, LocalDate liturgicalYearStart) {
		super();
		this.liturgicalYear = liturgicalYear;
		this.liturgicalYearStart = liturgicalYearStart;
	}

	public Locale region; // ES_es
	public ZoneId zoneId;
	
	public void pp() {
		region = Locale.of("es", "ES");
		zoneId = ZoneId.systemDefault();
	}
}
