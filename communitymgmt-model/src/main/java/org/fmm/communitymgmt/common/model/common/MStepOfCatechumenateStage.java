package org.fmm.communitymgmt.common.model.common;

import java.io.Serializable;

import com.fasterxml.jackson.annotation.JsonBackReference;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;


/**
 * The persistent class for the service_type database table.
 * 
 */
@Entity
@Table(name="m_step")
public class MStepOfCatechumenateStage implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
//	@GeneratedValue(strategy=GenerationType.AUTO)
	private Integer id;

	@Column(name="step")
	private String step;

	@ManyToOne
	@JoinColumn(name = "stage_id", insertable = true,updatable = true, nullable = false)
	@JsonBackReference
	private MStageOfCatechumenate stage;
	
	public MStepOfCatechumenateStage() {
	}

	public Integer getId() {
		return this.id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getStep() {
		return step;
	}

	public void setStep(String step) {
		this.step = step;
	}

	public MStageOfCatechumenate getStage() {
		return stage;
	}

	public void setStage(MStageOfCatechumenate stage) {
		this.stage = stage;
	}

}