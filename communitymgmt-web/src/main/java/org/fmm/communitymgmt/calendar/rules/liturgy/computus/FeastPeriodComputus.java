package org.fmm.communitymgmt.calendar.rules.liturgy.computus;

import org.fmm.communitymgmt.calendar.rules.liturgy.LiturgyRuleContext;
import org.fmm.communitymgmt.calendar.rules.liturgy.LiturgyRuleRegistry;
import org.fmm.communitymgmt.calendar.rules.liturgy.result.LiturgyPeriodResult;

public class FeastPeriodComputus extends AbstractComputus {

	protected FeastPeriod periodRuleData = null;

	public FeastPeriodComputus(FeastPeriod periodRuleData) {
		super(ComputusTypeEnum.FEAST_PERIOD_COMPUTUS);
		this.periodRuleData = periodRuleData;
	}
	@Override
	public LiturgyPeriodResult compute(int liturgicalYear, LiturgyRuleContext ctx, LiturgyRuleRegistry registry) {
		Period period = new Period();
		//LocalDate aux = null;
		period.setInitDate(registry.getComputedFeastRule(periodRuleData.getInit()));
		period.setEndDate(registry.getComputedFeastRule(periodRuleData.getEnd()));
		
//		return new LiturgyDateResult( LocalDate.of(liturgicalYear, month, day));
		
		return new LiturgyPeriodResult(period);
	}
    
    @Override
    public String toString() {
        return String.format("Computus{ %s, %s}", type.name(), periodRuleData);
    }

}
