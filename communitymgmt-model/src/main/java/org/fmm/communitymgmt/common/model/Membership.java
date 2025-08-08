package org.fmm.communitymgmt.common.model;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.JoinTable;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.ManyToOne;


/**
 * The persistent class for the relationship database table.
 * 
 */
@Entity
//@NamedQuery(name="Membership.findAll", query="SELECT m FROM Membership m")
public class Membership implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;

	//bi-directional many-to-one association to Address
	@ManyToOne
	@JoinColumn(name="type_id")
	private TMembership membershipType;
	
	@ManyToOne
	@JoinColumn(name="community_id")
	private Community community;

	@ManyToOne
	@JoinColumn(name="person_id")
	private Person person;
	
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name="relationship_id")
	private Relationship relationship;
	
	@ManyToMany(cascade = {CascadeType.MERGE, CascadeType.PERSIST})
	@JoinTable(
		name="community_charges"
		, joinColumns={
			@JoinColumn(name="membership_id")
			}
		, inverseJoinColumns={
			@JoinColumn(name="charge_type_id")
			}
		)
	private List<TCharge> charges;
	
	@Column
	private Integer orderList;
	
	public Membership() {
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}


	public Integer getOrderList() {
		return orderList;
	}

	public void setOrderList(Integer orderList) {
		this.orderList = orderList;
	}

	public TMembership getMembershipType() {
		return membershipType;
	}

	public void setMembershipType(TMembership membershipType) {
		this.membershipType = membershipType;
	}

	public Community getCommunity() {
		return community;
	}

	public void setCommunity(Community community) {
		this.community = community;
	}

	public Person getPerson() {
		return person;
	}

	public void setPerson(Person person) {
		this.person = person;
	}

	public Relationship getRelationship() {
		return relationship;
	}

	public void setRelationship(Relationship relationship) {
		this.relationship = relationship;
	}

	public List<TCharge> getCharges() {
		return charges;
	}

	public void setCharges(List<TCharge> charges) {
		this.charges = charges;
	}
	public TCharge addCharge(TCharge charge) {
		if (getCharges() == null) {
			setCharges(new ArrayList<TCharge>());
		}
		getCharges().add(charge);
		return charge;
	}

	public List<TCharge> addCharges(List<TCharge> charges) {
		if (charges != null) {
			for (TCharge item: charges) {
				addCharge(item);
			}
		}
		return getCharges();
	}


	
	
	public TCharge removeROthersPerson(TCharge charge) {
		getCharges().remove(charge);
		return charge;
	}


}