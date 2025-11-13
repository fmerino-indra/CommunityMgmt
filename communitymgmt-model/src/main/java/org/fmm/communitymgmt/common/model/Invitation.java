package org.fmm.communitymgmt.common.model;

import java.io.Serializable;

import org.fmm.communitymgmt.common.model.common.TMembership;
import org.fmm.communitymgmt.common.util.enums.InvitationStateEnum;

import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.JsonIdentityReference;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonView;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;


/**
 * The persistent class for the community database table.
 * 
 */
@Entity
/*
 * @JsonIdentityInfo(
		  generator = ObjectIdGenerators.PropertyGenerator.class, 
		  property = "id")

	@JsonBackReference
	@JsonManagedReference
	@JsonIgnore
	@JsonIgnoreProperties
    @JsonIdentityReference(alwaysAsId=true) // otherwise first ref as POJO, others as id

 */

public class Invitation implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
	@JsonView(InvitationJsonViews.SimpleInvitation.class)
	private Integer id;

	@Column
	@JsonView(InvitationJsonViews.SimpleInvitation.class)
	private String name;
	@Column
	@JsonView(InvitationJsonViews.SimpleInvitation.class)
	private String responsibleSignature;
	@Column
	@JsonView(InvitationJsonViews.SimpleInvitation.class)
	private String responsiblePublicKey;

	@Column
	private String personSignature;
	@Column
	private String personPublicKey;

	@Column
	@JsonView(InvitationJsonViews.SimpleInvitation.class)
	private Long nbf;
	@Column
	@JsonView(InvitationJsonViews.SimpleInvitation.class)
	private Long exp;
	@Column
	@JsonView(InvitationJsonViews.SimpleInvitation.class)
	private Long iat;
	
	@Column(nullable = false)
	@JsonView(InvitationJsonViews.SimpleInvitation.class)
	private Boolean forMarriage = false;

	@ManyToOne
	@JoinColumn(name="invitation_type")
	@JsonIdentityReference(alwaysAsId=true)
	@JsonIdentityInfo(
			  generator = ObjectIdGenerators.PropertyGenerator.class, 
			  property = "id"
			  )
	@JsonView(InvitationJsonViews.SimpleInvitation.class)
	private TMembership invitationType;
 
	@Enumerated(EnumType.STRING) // Guarda "M" o "F" como texto
	@Column(nullable = false, length = 1)
	@JsonView(InvitationJsonViews.SimpleInvitation.class)
	private InvitationStateEnum state;

	@ManyToOne(cascade = CascadeType.ALL)
//	@JsonIdentityInfo(
//			  generator = ObjectIdGenerators.PropertyGenerator.class, 
//			  property = "id"
//			  )
//	@JsonIdentityReference(alwaysAsId=true) // otherwise first ref as POJO, others as id
//	@JsonProperty("communityId")
//	@JsonView(InvitationJsonViews.SimpleInvitation.class)
//	@JsonIgnore
	private Community community;

	@ManyToOne
	@JoinColumn(name="person_id")
	@JsonView(InvitationJsonViews.ComplexInvitation.class)
	private Person person;
	
	@ManyToOne
	@JsonView(InvitationJsonViews.ComplexInvitation.class)
	private InvitationRelationship invitationRelationship;
	
	public Invitation() {
	}

	public Integer getId() {
		return this.id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Long getNbf() {
		return nbf;
	}

	public void setNbf(Long nbf) {
		this.nbf = nbf;
	}

	public Long getExp() {
		return exp;
	}

	public void setExp(Long exp) {
		this.exp = exp;
	}

	public Long getIat() {
		return iat;
	}

	public void setIat(Long iat) {
		this.iat = iat;
	}

	public InvitationStateEnum getState() {
		return state;
	}

	public void setState(InvitationStateEnum state) {
		this.state = state;
	}

	@JsonProperty("communityId")
	@JsonView(InvitationJsonViews.SimpleInvitation.class)
	public Integer getCommunityId() {
		if (community != null)
			return community.getId();
		else
			return null;
	}
	@JsonProperty("community")
	@JsonView(InvitationJsonViews.ComplexInvitation.class)
	public Community getCommunity() {
		return community;
	}

	public void setCommunity(Community community) {
		this.community = community;
	}

	public Boolean getForMarriage() {
		return forMarriage;
	}

	public void setForMarriage(Boolean forMarriage) {
		this.forMarriage = forMarriage;
	}

	public InvitationRelationship getInvitationRelationship() {
		return invitationRelationship;
	}

	public void setInvitationRelationship(InvitationRelationship invitationRelationship) {
		this.invitationRelationship = invitationRelationship;
	}

	@JsonView(InvitationJsonViews.SimpleInvitation.class)
	@JsonProperty("invitationTypeId")
	public Integer getInvitationTypeId() {
		return invitationType != null?invitationType.getId():null;
	}
	@JsonView(InvitationJsonViews.ComplexInvitation.class)
	public TMembership getInvitationType() {
		return invitationType;
	}

	public void setInvitationType(TMembership invitationType) {
		this.invitationType = invitationType;
	}

	public Person getPerson() {
		return person;
	}

	public void setPerson(Person person) {
		this.person = person;
	}

	public String getResponsibleSignature() {
		return responsibleSignature;
	}

	public void setResponsibleSignature(String responsibleSignature) {
		this.responsibleSignature = responsibleSignature;
	}

	public String getResponsiblePublicKey() {
		return responsiblePublicKey;
	}

	public void setResponsiblePublicKey(String responsiblePublicKey) {
		this.responsiblePublicKey = responsiblePublicKey;
	}

	public String getPersonSignature() {
		return personSignature;
	}

	public void setPersonSignature(String personSignature) {
		this.personSignature = personSignature;
	}

	public String getPersonPublicKey() {
		return personPublicKey;
	}

	public void setPersonPublicKey(String personPublicKey) {
		this.personPublicKey = personPublicKey;
	}

}