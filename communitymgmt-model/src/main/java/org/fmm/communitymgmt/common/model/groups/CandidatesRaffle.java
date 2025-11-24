package org.fmm.communitymgmt.common.model.groups;

import java.io.Serializable;

import org.fmm.communitymgmt.common.model.Person;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.NamedQuery;
import jakarta.persistence.Table;


/**
 * The persistent class for the candidates_raffle database table.
 * 
 */
@Entity
@Table(name="candidates_raffle")
@NamedQuery(name="CandidatesRaffle.findAll", query="SELECT c FROM CandidatesRaffle c")
public class CandidatesRaffle implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
    @GeneratedValue(strategy=GenerationType.IDENTITY)
	private Integer id;

	//bi-directional many-to-one association to Person
	@ManyToOne
	private Person person;

	//bi-directional many-to-one association to Raffle
	@ManyToOne
	private Raffle raffle;

	private Integer nAssigned;
    private Integer nReserved;
	
	private Boolean surviveAssigned;
    private Boolean surviveReserve;
	
	public CandidatesRaffle() {
	}

	public Integer getId() {
		return this.id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public Person getPerson() {
		return this.person;
	}

	public void setPerson(Person person) {
		this.person = person;
	}

	public Raffle getRaffle() {
		return this.raffle;
	}

	public void setRaffle(Raffle raffle) {
		this.raffle = raffle;
	}

    public Integer getnAssigned() {
        return nAssigned;
    }

    public void setnAssigned(Integer nAssigned) {
        this.nAssigned = nAssigned;
    }

    @Override
    public String toString() {
        return this.raffle.toString() + "|" + this.person;
    }

    public Boolean getSurviveAssigned() {
        return surviveAssigned;
    }

    public void setSurviveAssigned(Boolean surviveAssigned) {
        this.surviveAssigned = surviveAssigned;
    }

    public Boolean getSurviveReserve() {
        return surviveReserve;
    }

    public void setSurviveReserve(Boolean surviveReserve) {
        this.surviveReserve = surviveReserve;
    }

    public Integer getnReserved() {
        return nReserved;
    }

    public void setnReserved(Integer nReserved) {
        this.nReserved = nReserved;
    }
}