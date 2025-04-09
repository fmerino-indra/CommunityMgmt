package org.fmm.communitymgmt.common.model;

import java.io.Serializable;
import jakarta.persistence.*;

import java.util.ArrayList;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;


/**
 * The persistent class for the r_others database table.
 * 
 */
@Entity
@Table(name="r_others")
@NamedQuery(name="ROther.findAll", query="SELECT r FROM ROther r")
@JsonIdentityInfo(
		  generator = ObjectIdGenerators.PropertyGenerator.class, 
		  property = "id")
public class ROther extends Relationship implements Serializable {
	private static final long serialVersionUID = 1L;

	private Integer count;

	@Column(name="description")
	private String description;

	//bi-directional many-to-one association to ROthersPerson
	@OneToMany(mappedBy="ROther", cascade = CascadeType.ALL, orphanRemoval = true)
	@JsonManagedReference
	private List<ROthersPerson> ROthersPersons;

	public ROther() {
	}

	public Integer getCount() {
		return this.count;
	}

	public void setCount(Integer count) {
		this.count = count;
	}

	public List<ROthersPerson> getROthersPersons() {
		return this.ROthersPersons;
	}

	public void setROthersPersons(List<ROthersPerson> ROthersPersons) {
		this.ROthersPersons = ROthersPersons;
	}

	public ROthersPerson addROthersPerson(ROthersPerson ROthersPerson) {
		if (getROthersPersons() == null) {
			setROthersPersons(new ArrayList<ROthersPerson>());
		}
		getROthersPersons().add(ROthersPerson);
		ROthersPerson.setROther(this);

		return ROthersPerson;
	}

	public ROthersPerson removeROthersPerson(ROthersPerson ROthersPerson) {
		getROthersPersons().remove(ROthersPerson);
		ROthersPerson.setROther(null);

		return ROthersPerson;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

}