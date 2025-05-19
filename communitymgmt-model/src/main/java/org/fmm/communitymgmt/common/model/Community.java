package org.fmm.communitymgmt.common.model;

import java.io.Serializable;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;


/**
 * The persistent class for the community database table.
 * 
 */
@Entity
//@NamedQuery(name="Community.findAll", query="SELECT c FROM Community c")
public class Community implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;

	@Column(name="community_number")
	private String communityNumber;
	
	private String parish;
	private String parishAddress;
	private String parishAddressNumber;
	private String parishAddressPostalCode;
	private String parishAddressCity;
	@Column(name = "activated")
	private Boolean isActivated;
	private String country;
	
	public Community() {
	}

	public Integer getId() {
		return this.id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getCountry() {
		return this.country;
	}

	public void setCountry(String country) {
		this.country = country;
	}

	public String getParish() {
		return this.parish;
	}

	public void setParish(String parish) {
		this.parish = parish;
	}

	public String getParishAddress() {
		return parishAddress;
	}

	public void setParishAddress(String parishAddress) {
		this.parishAddress = parishAddress;
	}

	public String getCommunityNumber() {
		return communityNumber;
	}

	public void setCommunityNumber(String communityNumber) {
		this.communityNumber = communityNumber;
	}

	public String getParishAddressNumber() {
		return parishAddressNumber;
	}

	public void setParishAddressNumber(String parishAddressNumber) {
		this.parishAddressNumber = parishAddressNumber;
	}

	public String getParishAddressPostalCode() {
		return parishAddressPostalCode;
	}

	public void setParishAddressPostalCode(String parishAddressPostalCode) {
		this.parishAddressPostalCode = parishAddressPostalCode;
	}

	public String getParishAddressCity() {
		return parishAddressCity;
	}

	public void setParishAddressCity(String parishAddressCity) {
		this.parishAddressCity = parishAddressCity;
	}

	public Boolean getIsActivated() {
		return isActivated;
	}

	public void setIsActivated(Boolean isActivated) {
		this.isActivated = isActivated;
	}
}