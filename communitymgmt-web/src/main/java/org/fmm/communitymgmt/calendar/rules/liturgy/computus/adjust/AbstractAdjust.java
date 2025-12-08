package org.fmm.communitymgmt.calendar.rules.liturgy.computus.adjust;

import java.time.LocalDate;

public abstract class AbstractAdjust {
	private AdjustEnum type;

	
	public AbstractAdjust(AdjustEnum type) {
		super();
		this.type = type;
	}
	public AdjustEnum getType() {
		return type;
	}
	public abstract LocalDate apply(LocalDate base);
	
	
}
