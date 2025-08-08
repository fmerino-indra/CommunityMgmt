package org.fmm.communitymgmt.common.model;

import java.io.Serializable;

import com.fasterxml.jackson.annotation.JsonSubTypes;
import com.fasterxml.jackson.annotation.JsonTypeInfo;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Inheritance;
import jakarta.persistence.InheritanceType;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.SequenceGenerator;


/**
 * The persistent class for the relationship database table.
 * 
 */
@Entity
@Inheritance(strategy = InheritanceType.JOINED)
@JsonTypeInfo(
	    use = JsonTypeInfo.Id.NAME,
	    include = JsonTypeInfo.As.PROPERTY,
	    property = "type"
	)
	@JsonSubTypes({
	    @JsonSubTypes.Type(value = InvitationRMarriage.class, name = "Marriage"),
	    @JsonSubTypes.Type(value = InvitationRSingle.class, name = "Single")
	})
public abstract class InvitationRelationship implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
//    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "invitation_relationship_seq")
    @SequenceGenerator(name = "invitation_relationship_seq", sequenceName = "communitymgmt.invitation_relationship_seq", allocationSize = 1)
	private Integer id;

	@Column(name="relationship_name")
	private String relationshipName;

	//bi-directional many-to-one association to Address
	@ManyToOne
	@JoinColumn(name="address_id")
	private Address address;

	@Column
	private Integer orderList;
	public InvitationRelationship() {
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

	public Integer getOrderList() {
		return orderList;
	}

	public void setOrderList(Integer orderList) {
		this.orderList = orderList;
	}

}