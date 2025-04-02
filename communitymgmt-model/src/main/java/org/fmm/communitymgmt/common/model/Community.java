package org.fmm.communitymgmt.common.model;

import java.io.Serializable;
import jakarta.persistence.*;


/**
 * The persistent class for the community database table.
 * 
 */
@Entity
@NamedQuery(name="Community.findAll", query="SELECT c FROM Community c")
public class Community implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	private Integer id;

	private String city;

	private String country;

	private String name;

	private String parrish;

	public Community() {
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

	public String getCountry() {
		return this.country;
	}

	public void setCountry(String country) {
		this.country = country;
	}

	public String getName() {
		return this.name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getParrish() {
		return this.parrish;
	}

	public void setParrish(String parrish) {
		this.parrish = parrish;
	}

}