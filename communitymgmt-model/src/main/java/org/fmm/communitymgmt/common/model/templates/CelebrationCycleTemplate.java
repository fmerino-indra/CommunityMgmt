package org.fmm.communitymgmt.common.model.templates;

import java.io.Serializable;
import java.util.List;

import org.fmm.communitymgmt.common.model.common.MStepOfCatechumenateStage;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonManagedReference;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;


/**
 * The persistent class for the service_type database table.
 * 
 */
@Entity
@Table(name="celebration_cycle_template")
public class CelebrationCycleTemplate implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
//	@GeneratedValue(strategy=GenerationType.AUTO)
	private Integer id;

	@Column(name="name")
	private String name;

	@Column
    private Integer number;
	
	@Column
    private Boolean teamNeaded;
    
	@ManyToOne
	@JoinColumn(name = "step_id", insertable = true,updatable = true, nullable = true)
	@JsonBackReference
	private MStepOfCatechumenateStage step;

	
//	@OneToMany(mappedBy="cct", cascade = CascadeType.ALL, orphanRemoval = true)
	@OneToMany(mappedBy="cct", cascade = CascadeType.ALL)
	@JsonManagedReference
	private List<CelebrationEventTemplate> celebrations;


	public CelebrationCycleTemplate() {
	}

	public Integer getId() {
		return this.id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Integer getNumber() {
		return number;
	}

	public void setNumber(Integer number) {
		this.number = number;
	}

	public Boolean getTeamNeaded() {
		return teamNeaded;
	}

	public void setTeamNeaded(Boolean teamNeaded) {
		this.teamNeaded = teamNeaded;
	}

	public MStepOfCatechumenateStage getStep() {
		return step;
	}

	public void setStep(MStepOfCatechumenateStage step) {
		this.step = step;
	}

	public List<CelebrationEventTemplate> getCelebrations() {
		return celebrations;
	}

}