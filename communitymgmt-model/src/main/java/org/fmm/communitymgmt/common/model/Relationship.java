package org.fmm.communitymgmt.common.model;

import java.io.Serializable;
import jakarta.persistence.*;


/**
 * The persistent class for the relationship database table.
 * 
 */
@Entity
@NamedQuery(name="Relationship.findAll", query="SELECT r FROM Relationship r")
@Inheritance(strategy = InheritanceType.JOINED)
public abstract class Relationship implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
//    @GeneratedValue(strategy = GenerationType.IDENTITY)
	// FMMP por aquí, hay que crear la secuencia en postgresql
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "relationship_seq")
    @SequenceGenerator(name = "relationship_seq", sequenceName = "communitymgmt.relationship_seq", allocationSize = 1)
	private Integer id;

	@Column(name="relationship_name")
	private String relationshipName;

	//bi-directional many-to-one association to Address
	@ManyToOne
	@JoinColumn(name="address_id")
	private Address address;

	public Relationship() {
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getRelationshipName() {
		return this.relationshipName;
	}

	public void setRelationshipName(String relationshipName) {
		this.relationshipName = relationshipName;
	}

	public Address getAddress() {
		return this.address;
	}

	public void setAddress(Address address) {
		this.address = address;
	}

}