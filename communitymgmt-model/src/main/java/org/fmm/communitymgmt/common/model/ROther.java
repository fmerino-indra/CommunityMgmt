package org.fmm.communitymgmt.common.model;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.JoinTable;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.NamedQuery;
import jakarta.persistence.Table;


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
//	@OneToMany(mappedBy="ROther", cascade = CascadeType.ALL, orphanRemoval = true)
//	@JsonManagedReference
//	private List<ROthersPerson> ROthersPersons;

	@ManyToMany(cascade = {CascadeType.MERGE, CascadeType.PERSIST})
	@JoinTable(
		name="r_others_persons"
		, joinColumns={
			@JoinColumn(name="r_others_id")
			}
		, inverseJoinColumns={
			@JoinColumn(name="related_persons_id")
			}
		)
	private List<Person> relatedPersons;
	
	public ROther() {
	}

	public Integer getCount() {
		return this.count;
	}

	public void setCount(Integer count) {
		this.count = count;
	}

//	public List<ROthersPerson> getROthersPersons() {
//		return this.ROthersPersons;
//	}
//
//	public void setROthersPersons(List<ROthersPerson> ROthersPersons) {
//		this.ROthersPersons = ROthersPersons;
//	}

//	public ROthersPerson addROthersPerson(ROthersPerson ROthersPerson) {
//		if (getROthersPersons() == null) {
//			setROthersPersons(new ArrayList<ROthersPerson>());
//		}
//		getROthersPersons().add(ROthersPerson);
//		ROthersPerson.setROther(this);
//
//		return ROthersPerson;
//	}
//
//	public ROthersPerson removeROthersPerson(ROthersPerson ROthersPerson) {
//		getROthersPersons().remove(ROthersPerson);
//		ROthersPerson.setROther(null);
//
//		return ROthersPerson;
//	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public List<Person> getRelatedPersons() {
		return relatedPersons;
	}

	public void setRelatedPersons(List<Person> relatedPersons) {
		this.relatedPersons = relatedPersons;
	}
	
	public Person addRelatedPerson(Person relatedPerson) {
		if (getRelatedPersons() == null) {
			setRelatedPersons(new ArrayList<Person>());
		}
		getRelatedPersons().add(relatedPerson);
		return relatedPerson;
	}

	public Person removeROthersPerson(Person relatedPerson) {
		getRelatedPersons().remove(relatedPerson);
		return relatedPerson;
	}

}