package org.fmm.communitymgmt.common.model.calendar;

import java.io.Serializable;
import java.util.List;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;


/**
 * The persistent class for the community database table.
 * 
 */
@Entity
public class LiturgicalYear implements Serializable {
	private static final long serialVersionUID = 1L;

	
	@Id
	private Integer id;

	@Column
	private Integer year;
	
	@Column
	private String yearName;
	
    @OneToMany(mappedBy = "year", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<LiturgicalFeast> feasts;
    
	public LiturgicalYear() {
	}

	public Integer getId() {
		return this.id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getYearName() {
		return yearName;
	}

	public void setYearName(String yearName) {
		this.yearName = yearName;
	}

	public Integer getYear() {
		return year;
	}

	public void setYear(Integer year) {
		this.year = year;
	}
	
	public List<LiturgicalFeast> getFeasts() {
		return feasts;
	}

	public void setFeasts(List<LiturgicalFeast> feasts) {
		this.feasts = feasts;
	}

	@Override
	public String toString() {
		return String.format("%s(%d)", getYearName(), getId());
	}
}