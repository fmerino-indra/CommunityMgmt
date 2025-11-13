package org.fmm.communitymgmt.common.model.common;

import org.fmm.communitymgmt.common.util.enums.ChargeEnum;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;

@Entity(name = "t_charges")
public class TCharge {
	@Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;

	@Column
	private String charge;
	
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public String getCharge() {
		return charge;
	}
	public void setCharge(String charge) {
		this.charge = charge;
	}
	
	public static TCharge from(ChargeEnum chargeEnum) {
		TCharge charge = new TCharge();
		
		charge.setId(chargeEnum.getId());
		charge.setCharge(chargeEnum.getCharge());
		
		return charge;
	}
}
