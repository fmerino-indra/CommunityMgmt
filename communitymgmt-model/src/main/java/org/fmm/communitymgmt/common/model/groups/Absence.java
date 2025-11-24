package org.fmm.communitymgmt.common.model.groups;

import java.io.Serializable;
import java.time.LocalDate;

import org.fmm.communitymgmt.common.model.Person;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.NamedQuery;


/**
 * The persistent class for the exception database table.
 * 
 */
@Entity
@NamedQuery(name="Absence.findAll", query="SELECT e FROM Absence e")
public class Absence implements Serializable {
	private static final long serialVersionUID = 1L;

    @Column(name="day", columnDefinition = "DATE")
	private LocalDate day;

    @Id
    @GeneratedValue(strategy=GenerationType.IDENTITY)
	private Integer id;

    @ManyToOne
//    @JoinColumn(name="person_id")
    private Person person;

	
	public Absence() {
	}

	public Integer getId() {
		return this.id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

    public Person getPerson() {
        return person;
    }

    public void setPerson(Person person) {
        this.person = person;
    }

    public LocalDate getDay() {
        return day;
    }

    public void setDay(LocalDate day) {
        this.day = day;
    }

}