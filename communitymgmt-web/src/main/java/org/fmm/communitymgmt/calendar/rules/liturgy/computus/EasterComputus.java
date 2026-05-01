package org.fmm.communitymgmt.calendar.rules.liturgy.computus;

import org.fmm.communitymgmt.calendar.rules.RuleUtil;
import org.fmm.communitymgmt.calendar.rules.liturgy.LiturgyRuleContext;
import org.fmm.communitymgmt.calendar.rules.liturgy.LiturgyRuleRegistry;
import org.fmm.communitymgmt.calendar.rules.liturgy.result.LiturgyDateResult;

public class EasterComputus extends AbstractComputus {
	public EasterComputus() {
		super(ComputusTypeEnum.EASTER_COMPUTUS);
	}
	@Override
	public LiturgyDateResult compute(int liturgicalYear, LiturgyRuleContext ctx, LiturgyRuleRegistry registry) {
		
		// Easter falls in the spring of the calendar year that corresponds to the liturgicalYear
		return new LiturgyDateResult( RuleUtil.easterSunday(liturgicalYear));
	}
    @Override
    public String toString() {
        return String.format("Computus{ %s}", type.name());
    }
	
}
