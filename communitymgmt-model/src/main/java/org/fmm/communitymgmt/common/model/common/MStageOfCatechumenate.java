package org.fmm.communitymgmt.common.model.common;

import java.io.Serializable;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;


/**
 * The persistent class for the service_type database table.
 * 
 */
@Entity
@Table(name="m_stage")
public class MStageOfCatechumenate implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
//	@GeneratedValue(strategy=GenerationType.AUTO)
	private Integer id;

	@Column(name="stage")
	private String stage;

	@Column
	private String feature;
	
	public MStageOfCatechumenate() {
	}

	public Integer getId() {
		return this.id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getStage() {
		return stage;
	}

	public void setStage(String stage) {
		this.stage = stage;
	}

	public String getFeature() {
		return feature;
	}

	public void setFeature(String feature) {
		this.feature = feature;
	}
}