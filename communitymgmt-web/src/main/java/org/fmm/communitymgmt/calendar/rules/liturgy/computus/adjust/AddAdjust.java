package org.fmm.communitymgmt.calendar.rules.liturgy.computus.adjust;

import java.time.LocalDate;
import java.time.temporal.ChronoUnit;

public class AddAdjust extends AbstractAdjust {
	private int value;
	private ChronoUnit unit; // días o semanas
	
	public AddAdjust(int value, ChronoUnit unit) {
		super(AdjustEnum.ADD_VALUES);
		this.value = value;
		this.unit = unit;
		if (!(unit == ChronoUnit.DAYS || unit == ChronoUnit.WEEKS))
			throw new RuntimeException("[FMMP] Bad JSON Configuration (WEEKDAY)");
	}

	@Override
	public LocalDate apply(LocalDate base) {
		LocalDate date = null;
		switch (unit) {
			case ChronoUnit.DAYS:
			case ChronoUnit.WEEKS: 
				date = base.plus(value, unit);
				break;
			default:
				throw new RuntimeException("[FMMP] Bad JSON Configuration (WEEKDAY)");
		}
		return date;
	}
    @Override
    public String toString() {
        return String.format("{%s:Add %d %s}", getType(), value, unit);
    }
	
}