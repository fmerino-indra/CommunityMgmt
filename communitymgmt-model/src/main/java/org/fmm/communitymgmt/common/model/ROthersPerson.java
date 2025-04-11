package org.fmm.communitymgmt.common.model;

import java.io.Serializable;
import jakarta.persistence.*;


/**
 * The persistent class for the r_others_persons database table.
 * 
 */
//@Entity
//@Table(name="r_others_persons")
//@NamedQuery(name="ROthersPerson.findAll", query="SELECT r FROM ROthersPerson r")
@Deprecated
public class ROthersPerson implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;

	//bi-directional many-to-one association to Person
	@ManyToOne
	private Person person;

	//bi-directional many-to-one association to ROther
	@ManyToOne
	@JoinColumn(name="r_others_id")
	private ROther ROther;

	public ROthersPerson() {
	}

	public Integer getId() {
		return this.id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public Person getPerson() {
		return this.person;
	}

	public void setPerson(Person person) {
		this.person = person;
	}

	public ROther getROther() {
		return this.ROther;
	}

	public void setROther(ROther ROther) {
		this.ROther = ROther;
	}

}