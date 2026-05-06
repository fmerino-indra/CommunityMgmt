package org.fmm.communitymgmt.calendar.rules.liturgy;

import java.time.LocalDate;

import org.fmm.communitymgmt.calendar.rules.RuleKindEnum;
import org.fmm.communitymgmt.common.model.calendar.AbstractPeriod;
import org.fmm.communitymgmt.common.model.calendar.LiturgicalPeriod;
import org.fmm.communitymgmt.common.model.calendar.OtherPeriod;

public class PeriodDto {
	private String id;
	private String name;
	private LocalDate initDate;
	private LocalDate endDate;
	private RuleKindEnum kind;
	
	public LocalDate getEndDate() {
		return endDate;
	}
	public void setEndDate(LocalDate endDate) {
		this.endDate = endDate;
	}

	public final String ruleId;
	
	public String getRuleId() {
		return ruleId;
	}
	public PeriodDto(String id, String name, LocalDate initDate, LocalDate endDate, RuleKindEnum kind, String ruleId) {
		super();
		this.id = id;
		this.name = name;
		this.initDate = initDate;
		this.endDate = endDate;
		this.kind = kind;
		this.ruleId = ruleId;
	}
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public LocalDate getInitDate() {
		return initDate;
	}
	public void setInitDate(LocalDate date) {
		this.initDate = date;
	}
	
	public RuleKindEnum getKind() {
		return kind;
	}
	public void setKind(RuleKindEnum kind) {
		this.kind = kind;
	}

	@Override
    public String toString() {
        return String.format("PeriodDto{ %s: %s -> [%s - %s] (rule=%s)}", id, name, initDate, endDate, ruleId);
    }
	
	public static AbstractPeriod fromDto(PeriodDto dto) {
		AbstractPeriod period = null;
		
		
		if (dto.getKind() == RuleKindEnum.LITURGICAL_PERIOD)
			period = new LiturgicalPeriod();
		else
			period = new OtherPeriod();
		
		period.setInitDate(dto.initDate);
		period.setEndDate(dto.endDate);
		period.setName(dto.name);
		
		return period;
	}

}
