package org.fmm.communitymgmt.common.model;

import java.io.Serializable;

import org.fmm.communitymgmt.common.util.InvitationState;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.JsonIdentityReference;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
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
	private Integer id;

	@Column
	private String name;
	@Column
	private String signature;
	@Column
	private Long nbf;
	@Column
	private Long exp;
	@Column
	private Long iat;
	@Column
	private String kpub;
	
	@Enumerated(EnumType.STRING) // Guarda "M" o "F" como texto
	@Column(nullable = false, length = 1)
	private InvitationState state;

	@ManyToOne(cascade = CascadeType.ALL)
	@JsonIdentityInfo(
			  generator = ObjectIdGenerators.PropertyGenerator.class, 
			  property = "id"
			  )
	@JsonIdentityReference(alwaysAsId=true) // otherwise first ref as POJO, others as id
	@JsonProperty("communityId")
	private Community community;

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

	public String getSignature() {
		return signature;
	}

	public void setSignature(String signature) {
		this.signature = signature;
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

	public String getKpub() {
		return kpub;
	}

	public void setKpub(String kpub) {
		this.kpub = kpub;
	}

	public InvitationState getState() {
		return state;
	}

	public void setState(InvitationState state) {
		this.state = state;
	}

	public Community getCommunity() {
		return community;
	}

	public void setCommunity(Community community) {
		this.community = community;
	}

}