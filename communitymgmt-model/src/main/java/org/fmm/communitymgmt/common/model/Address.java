package org.fmm.communitymgmt.common.model;

import java.io.Serializable;
import jakarta.persistence.*;
import java.util.List;


/**
 * The persistent class for the address database table.
 * 
 */
@Entity
@NamedQuery(name="Address.findAll", query="SELECT a FROM Address a")
public class Address implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	private Integer id;

	private String city;

	private String cp;

	private String door;

	private String floor;

	@Column(name="street_name")
	private String streetName;

	@Column(name="street_number")
	private String streetNumber;

	//bi-directional many-to-one association to Relationship
	@OneToMany(mappedBy="address")
	private List<Relationship> relationships;

	public Address() {
	}

	public Integer getId() {
		return this.id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getCity() {
		return this.city;
	}

	public void setCity(String city) {
		this.city = city;
	}

	public String getCp() {
		return this.cp;
	}

	public void setCp(String cp) {
		this.cp = cp;
	}

	public String getDoor() {
		return this.door;
	}

	public void setDoor(String door) {
		this.door = door;
	}

	public String getFloor() {
		return this.floor;
	}

	public void setFloor(String floor) {
		this.floor = floor;
	}

	public String getStreetName() {
		return this.streetName;
	}

	public void setStreetName(String streetName) {
		this.streetName = streetName;
	}

	public String getStreetNumber() {
		return this.streetNumber;
	}

	public void setStreetNumber(String streetNumber) {
		this.streetNumber = streetNumber;
	}

	public List<Relationship> getRelationships() {
		return this.relationships;
	}

	public void setRelationships(List<Relationship> relationships) {
		this.relationships = relationships;
	}

	public Relationship addRelationship(Relationship relationship) {
		getRelationships().add(relationship);
		relationship.setAddress(this);

		return relationship;
	}

	public Relationship removeRelationship(Relationship relationship) {
		getRelationships().remove(relationship);
		relationship.setAddress(null);

		return relationship;
	}

}