package org.fmm.communitymgmt.common.model;

import java.io.Serializable;
import jakarta.persistence.*;


/**
 * The persistent class for the r_single database table.
 * 
 */
@Entity
@Table(name="r_single")
@NamedQuery(name="RSingle.findAll", query="SELECT r FROM RSingle r")
@PrimaryKeyJoinColumn(name = "id")
public class RSingle extends Relationship implements Serializable {
	private static final long serialVersionUID = 1L;

	//bi-directional many-to-one association to Person
	@ManyToOne
	@JoinColumn(name="person_id")
	private Person person;
	
	public RSingle() {
	}

	public Person getPerson() {
		return this.person;
	}

	public void setPerson(Person person) {
		this.person = person;
	}
}