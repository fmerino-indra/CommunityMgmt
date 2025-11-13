package org.fmm.communitymgmt.common.util.enums;

import org.fmm.communitymgmt.common.model.common.TCharge;

public enum ChargeEnum {
	RESPONSIBLE(1,"Responsible"),
	RESPONSIBLE_SPOUSE(2, "Responsible Spouse"),
	CORESPONSIBLE(3, "Coresponsible"),
	CORESPONSIBLE_SPOUSE(4, "Coresponsible Spouse"),
	GUARANTEE(5, "Guarantee"),
	PSALM_SINGER(6, "PSalm singer"),
	PSALM_SINGER_RESPONSIBLE(7, "PSalm singer Responsible"),
	READER(8, "PSalm singer"),
	READER_RESPONSIBLE(9, "Reader Responsible"),
	OSTIARY(10, "Ostiary"),
	PRIEST(11, "Priest");

	
	
	private final Integer id;
	private final String charge;
	
	private ChargeEnum(Integer id, String charge) {
		this.id = id;
		this.charge = charge;
	}
	
	public Integer getId() {
		return id;
	}

	public String getCharge() {
		return charge;
	}
	
	public TCharge toModel() {
		TCharge charge = new TCharge();
		charge.setId(this.getId());
		charge.setCharge(this.getCharge());
		
		return charge;
	}
}
