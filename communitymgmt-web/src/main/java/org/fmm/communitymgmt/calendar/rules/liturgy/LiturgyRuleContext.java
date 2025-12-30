package org.fmm.communitymgmt.calendar.rules.liturgy;

import java.time.LocalDate;
import java.time.ZoneId;
import java.util.Locale;
// ISO3166-1 Y ISO3166-2
public class LiturgyRuleContext {
	public int liturgicalYear; 
	public LocalDate liturgicalYearStart; // starts 1st sunday of Advent (previous year) 
	
	public String isoAlpha2CountryCode;
	public Locale region; // ES_es
	public ZoneId zoneId;
	
	
	
	public LiturgyRuleContext(int liturgicalYear, LocalDate liturgicalYearStart, Locale region) {
		super();
		this.liturgicalYear = liturgicalYear;
		this.liturgicalYearStart = liturgicalYearStart;
		this.region = region;
	}

	public void pp() {
		isoAlpha2CountryCode = "ES";
		region = Locale.of("es", isoAlpha2CountryCode);
		zoneId = ZoneId.systemDefault();
	}
}
