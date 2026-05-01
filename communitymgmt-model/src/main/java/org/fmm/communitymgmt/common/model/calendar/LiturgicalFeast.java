package org.fmm.communitymgmt.common.model.calendar;

import java.io.Serializable;
import java.time.LocalDate;

import com.fasterxml.jackson.annotation.JsonBackReference;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;


/**
 * The persistent class for the community database table.
 * 
 */
@Entity
public class LiturgicalFeast implements Serializable {
	private static final long serialVersionUID = 1L;
	
	@Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;

	@Column
	private String ruleId;
	
	@Column
	private String name;
	
	@Column(columnDefinition = "DATE")
	private LocalDate feastDate;
	
	@ManyToOne
	@JoinColumn(name = "year_id", insertable = true,updatable = true, nullable = false)
	@JsonBackReference
	private LiturgicalYear year;
	
	public LiturgicalFeast() {
	}

	public Integer getId() {
		return this.id;
	}

	public void setId(Integer id) {
		this.id = id;
	}
	public String getRuleId() {
		return ruleId;
	}

	public void setRuleId(String ruleId) {
		this.ruleId = ruleId;
	}

	public LocalDate getFeastDate() {
		return feastDate;
	}

	public void setFeastDate(LocalDate feastDate) {
		this.feastDate = feastDate;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public LiturgicalYear getYear() {
		return year;
	}

	public void setYear(LiturgicalYear year) {
		this.year = year;
	}

	@Override
	public String toString() {
		return String.format("%s-%s-%s (%d)", getFeastDate(), getRuleId(), getName(), getId());
	}
}