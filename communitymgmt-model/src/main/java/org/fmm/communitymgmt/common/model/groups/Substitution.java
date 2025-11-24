package org.fmm.communitymgmt.common.model.groups;

import java.io.Serializable;

import org.fmm.communitymgmt.common.model.Person;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.NamedQuery;


/**
 * The persistent class for the substitution database table.
 * 
 */
@Entity
@NamedQuery(name="Substitution.findAll", query="SELECT s FROM Substitution s")
public class Substitution implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
    @Column(columnDefinition = "serial")
	private Integer id;

	//bi-directional many-to-one association to Person
	@ManyToOne
	private Person person;

	//bi-directional many-to-one association to ServicePerson
	@ManyToOne
	@JoinColumn(name="raffle_person_id")
	private RafflePerson rafflePerson;

    @Column(name="can_go")
    private Boolean canGo;

    @Column(name="has_gone")
    private Boolean hasGone;

    @Column(name="have_gone")
    private Boolean haveGone;

	public Substitution() {
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

	public RafflePerson getRafflePerson() {
		return this.rafflePerson;
	}

	public void setRafflePerson(RafflePerson rafflePerson) {
		this.rafflePerson = rafflePerson;
	}

    public Boolean getCanGo() {
        return canGo;
    }

    public void setCanGo(Boolean canGo) {
        this.canGo = canGo;
    }

    public Boolean getHasGone() {
        return hasGone;
    }

    public void setHasGone(Boolean hasGone) {
        this.hasGone = hasGone;
    }

    public Boolean getHaveGone() {
        return haveGone;
    }

    public void setHaveGone(Boolean haveGone) {
        this.haveGone = haveGone;
    }

}