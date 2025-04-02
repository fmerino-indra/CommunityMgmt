package org.fmm.communitymgmt.common.model;

import java.io.Serializable;
import java.util.Date;

import jakarta.persistence.Entity;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.NamedQuery;
import jakarta.persistence.PrimaryKeyJoinColumn;
import jakarta.persistence.Table;
import jakarta.persistence.Temporal;
import jakarta.persistence.TemporalType;


/**
 * The persistent class for the r_marriage database table.
 * 
 */
@Entity
@Table(name="r_marriage")
@NamedQuery(name="RMarriage.findAll", query="SELECT r FROM RMarriage r")
@PrimaryKeyJoinColumn(name = "id")
public class RMarriage extends Relationship implements Serializable {
	private static final long serialVersionUID = 1L;

	@Temporal(TemporalType.DATE)
	private Date date;

	private String description;

	//bi-directional many-to-one association to Person
	@ManyToOne
	@JoinColumn(name="husband_id")
	private Person husband;

	//bi-directional many-to-one association to Person
	@ManyToOne
	@JoinColumn(name="wife_id")
	private Person wife;

	public RMarriage() {
	}

	public Date getDate() {
		return this.date;
	}

	public void setDate(Date date) {
		this.date = date;
	}

	public String getDescription() {
		return this.description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public Person getHusband() {
		return this.husband;
	}

	public void setHusband(Person person1) {
		this.husband = person1;
	}

	public Person getWife() {
		return this.wife;
	}

	public void setWife(Person person2) {
		this.wife = person2;
	}
}