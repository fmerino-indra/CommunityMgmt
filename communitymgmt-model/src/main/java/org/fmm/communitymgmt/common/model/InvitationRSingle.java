package org.fmm.communitymgmt.common.model;

import java.io.Serializable;

import com.fasterxml.jackson.annotation.JsonView;

import jakarta.persistence.*;


/**
 * The persistent class for the r_single database table.
 * 
 */
@Entity
@Table(name="invitation_r_single")
@PrimaryKeyJoinColumn(name = "id")
@JsonView(InvitationJsonViews.ComplexInvitation.class)
public class InvitationRSingle extends InvitationRelationship implements Serializable {
	private static final long serialVersionUID = 1L;

	//bi-directional many-to-one association to Person
	@ManyToOne
	@JoinColumn(name="person_id")
	private Person person;
	
	public InvitationRSingle() {
	}

	public Person getPerson() {
		return this.person;
	}

	public void setPerson(Person person) {
		this.person = person;
	}
}