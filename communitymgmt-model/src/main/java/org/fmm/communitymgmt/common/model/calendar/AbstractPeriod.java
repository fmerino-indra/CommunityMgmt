package org.fmm.communitymgmt.common.model.calendar;

import java.time.LocalDate;

import com.fasterxml.jackson.annotation.JsonBackReference;

import jakarta.persistence.Column;
import jakarta.persistence.DiscriminatorColumn;
import jakarta.persistence.DiscriminatorType;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Inheritance;
import jakarta.persistence.InheritanceType;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;

@Entity
@Inheritance(strategy = InheritanceType.SINGLE_TABLE)
@DiscriminatorColumn(name = "selected_type", discriminatorType = DiscriminatorType.INTEGER)
@Table(name="period")
public class AbstractPeriod {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;
	@Column
	private String name;
	@Column
	private LocalDate initDate;
	@Column
	private LocalDate endDate;
	
	@ManyToOne
	@JoinColumn(name = "year_id", insertable = true, updatable = true, nullable = false)
	@JsonBackReference
	private LiturgicalYear year;

	public AbstractPeriod() {
		super();
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

	public LiturgicalYear getYear() {
		return year;
	}

	public void setYear(LiturgicalYear year) {
		this.year = year;
	}

	public LocalDate getInitDate() {
		return initDate;
	}

	public void setInitDate(LocalDate initDate) {
		this.initDate = initDate;
	}

	public LocalDate getEndDate() {
		return endDate;
	}

	public void setEndDate(LocalDate endDate) {
		this.endDate = endDate;
	}

	@Override
	public String toString() {
		return String.format("%s-%TH-%TH (%d)", getName(), getInitDate(),getEndDate(), getId());
	}

}