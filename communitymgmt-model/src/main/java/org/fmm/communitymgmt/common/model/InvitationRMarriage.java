package org.fmm.communitymgmt.common.model;

import java.io.Serializable;
import java.util.Date;

import com.fasterxml.jackson.annotation.JsonView;

import jakarta.persistence.Entity;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.PrimaryKeyJoinColumn;
import jakarta.persistence.Table;
import jakarta.persistence.Temporal;
import jakarta.persistence.TemporalType;


/**
 * The persistent class for the r_marriage database table.
 * 
 */
@Entity
@Table(name="invitation_r_marriage")
@PrimaryKeyJoinColumn(name = "id")
//@JsonIdentityInfo(
//		  generator = ObjectIdGenerators.PropertyGenerator.class, 
//		  property = "id")
@JsonView(InvitationJsonViews.ComplexInvitation.class)
public class InvitationRMarriage extends InvitationRelationship implements Serializable {
	private static final long serialVersionUID = 1L;

	@Temporal(TemporalType.DATE)
	private Date date;

	private String description;

	@ManyToOne
	@JoinColumn(name="husband_id")
	private Person husband;

	@ManyToOne
	@JoinColumn(name="wife_id")
	private Person wife;

	public InvitationRMarriage() {
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