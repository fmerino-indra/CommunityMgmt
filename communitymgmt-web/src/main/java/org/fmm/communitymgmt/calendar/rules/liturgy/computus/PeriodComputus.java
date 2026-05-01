package org.fmm.communitymgmt.calendar.rules.liturgy.computus;

import org.fmm.communitymgmt.calendar.rules.liturgy.LiturgyRuleContext;
import org.fmm.communitymgmt.calendar.rules.liturgy.LiturgyRuleRegistry;
import org.fmm.communitymgmt.calendar.rules.liturgy.result.LiturgyPeriodResult;

/**
 * 
    {
        "id": "advent",
        "name": "Adviento",
        "kind": "PERIOD_LITURGY",
        "computus": {
            "type": "RELATIVE_TO_PERIOD_COMPUTUS",
            "period" : {
                "init": "first_sunday_advent",
                "end": "christmas"
            }
        }
    }
 */
public class PeriodComputus extends AbstractComputus {
	Period period = null;
	String init = null;
	String end = null;
	
	public PeriodComputus(Period period) {
		super(ComputusTypeEnum.RELATIVE_TO_PERIOD_COMPUTUS);
		this.period = period;
	}

	@Override
	public LiturgyPeriodResult compute(int liturgicalYear, LiturgyRuleContext ctx, LiturgyRuleRegistry registry) {
		this.period.setInitDate(registry.getComputedDate(period.init));
		this.period.setEndDate(registry.getComputedDate(period.end));
		return new LiturgyPeriodResult(this.period);
	}
    
    @Override
    public String toString() {
    	String aux = String.format("Computus{ %s = %s -> %s", type.name(), period != null? period.init : "", period != null? period.end : "");
        return aux;
    }
    
}
