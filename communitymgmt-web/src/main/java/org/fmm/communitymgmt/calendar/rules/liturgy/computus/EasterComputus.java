package org.fmm.communitymgmt.calendar.rules.liturgy.computus;

import java.time.LocalDate;

import org.fmm.communitymgmt.calendar.rules.RuleUtil;
import org.fmm.communitymgmt.calendar.rules.liturgy.LiturgyRuleContext;
import org.fmm.communitymgmt.calendar.rules.liturgy.LiturgyRuleRegistry;

public class EasterComputus extends AbstractComputus {
	public EasterComputus() {
		super(ComputusTypeEnum.EASTER_COMPUTUS);
	}
	@Override
	public LocalDate compute(int liturgicalYear, LiturgyRuleContext ctx, LiturgyRuleRegistry registry) {
		// Easter falls in the spring of the calendar year that corresponds to the liturgicalYear
		return RuleUtil.easterSunday(liturgicalYear);
	}
    @Override
    public String toString() {
        return String.format("Computus{ %s}", type.name());
    }
	
}
