package org.fmm.communitymgmt.calendar.rules.liturgy.computus;

import java.time.LocalDate;

import org.fmm.communitymgmt.calendar.rules.liturgy.LiturgyRuleContext;
import org.fmm.communitymgmt.calendar.rules.liturgy.LiturgyRuleRegistry;

public class FixedComputus extends AbstractComputus {
	private int month; // 1..12
	private int day; // 1..31
	
	public FixedComputus(int month, int day) {
		super(ComputusTypeEnum.FIXED_COMPUTUS);
		this.month = month;
		this.day = day;
	}
	public int getMonth() {
		return month;
	}
	public int getDay() {
		return day;
	}
	@Override
	public LocalDate compute(int liturgicalYear, LiturgyRuleContext ctx, LiturgyRuleRegistry registry) {
		return LocalDate.of(liturgicalYear, month, day);
	}
    @Override
    public String toString() {
        return String.format("Computus{ %s: %d/%d}", type.name(), day, month);
    }
	
}
