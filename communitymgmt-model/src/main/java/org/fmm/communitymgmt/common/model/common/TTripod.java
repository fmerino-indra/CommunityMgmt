package org.fmm.communitymgmt.common.model.common;

import java.io.Serializable;

import org.fmm.communitymgmt.common.util.enums.TripodEnum;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;


/**
 * The persistent class for the service_type database table.
 * 
 */
@Entity
@Table(name="t_tripod")
public class TTripod implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
//	@GeneratedValue(strategy=GenerationType.AUTO)
	private Integer id;

	@Column
	private String tripodName;

	@Column
    private Integer frequency; // Always referred to days
	
	public TTripod() {
	}

	public Integer getId() {
		return this.id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getTripodName() {
		return tripodName;
	}

	public void setTripodName(String tripodName) {
		this.tripodName = tripodName;
	}

	public Integer getFrequency() {
		return frequency;
	}

	public void setFrequency(Integer frequency) {
		this.frequency = frequency;
	}

	public static TTripod from(TripodEnum tripodEnum) {
	    TTripod aux = null;
	    aux = new TTripod();
	    aux.setId(tripodEnum.getId());
	    aux.setTripodName(tripodEnum.getName());
	    return aux;
	}
	
}