package org.fmm.communitymgmt.calendar.rules.liturgy.computus;

import java.time.LocalDate;

import org.fmm.communitymgmt.calendar.rules.liturgy.LiturgyRuleContext;
import org.fmm.communitymgmt.calendar.rules.liturgy.LiturgyRuleRegistry;
import org.fmm.communitymgmt.calendar.rules.liturgy.result.LiturgyPeriodResult;

public class FixedPeriodComputus extends AbstractComputus {

	protected FixedPeriod periodRuleData = null;

	public FixedPeriodComputus(FixedPeriod periodRuleData) {
		super(ComputusTypeEnum.FIXED_RANGE_COMPUTUS);
		this.periodRuleData = periodRuleData;
	}
	@Override
	public LiturgyPeriodResult compute(int liturgicalYear, LiturgyRuleContext ctx, LiturgyRuleRegistry registry) {
		Period period = new Period();
		
		period.setInitDate(LocalDate.of(liturgicalYear, periodRuleData.getInit().getMonth(), periodRuleData.getInit().getDay()));
		period.setEndDate(LocalDate.of(liturgicalYear, periodRuleData.getEnd().getMonth(), periodRuleData.getEnd().getDay()));
		return new LiturgyPeriodResult(period);
	}
    

}
