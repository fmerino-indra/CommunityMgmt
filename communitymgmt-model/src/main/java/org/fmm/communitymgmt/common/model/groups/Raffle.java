package org.fmm.communitymgmt.common.model.groups;

import java.io.Serializable;
import java.sql.Timestamp;
import java.time.ZonedDateTime;
import java.util.ArrayList;
import java.util.List;

import org.fmm.communitymgmt.common.model.Person;

import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.NamedQuery;
import jakarta.persistence.OneToMany;


/**
 * The persistent class for the raffle database table.
 * 
 */
@Entity
@NamedQuery(name="Raffle.findAll", query="SELECT r FROM Raffle r")
@JsonIdentityInfo(
        generator = ObjectIdGenerators.PropertyGenerator.class, 
        property = "id")
public class Raffle implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private Integer id;

	private ZonedDateTime date;

    private Timestamp raffleDate;

    @Column(name = "nCandidates")
	private Integer nCandidates;
	
    @Column(name = "nReserves")
	private Integer nReserves;
	
    //bi-directional many-to-one association to Service
    @ManyToOne
    private Service service;

	//bi-directional many-to-one association to ServicePerson
	@OneToMany(mappedBy="raffle")
	@JsonIgnore
	private List<AssignedRafflePerson> assignedPersons;

    @OneToMany(mappedBy="raffle")
    @JsonIgnore
    private List<ReserveRafflePerson> reservePersons;

    private Integer nSurvivedCandidates;
    private Integer nSurvivedReserves;
    
	public Raffle() {
	}

	public Integer getId() {
		return this.id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public ZonedDateTime getDate() {
		return this.date;
	}

	public void setDate(ZonedDateTime date) {
		this.date = date;
	}

//	public RafflePerson addRafflePerson(RafflePerson rafflePerson) {
//		getAssignedPersons().add(rafflePerson);
//		rafflePerson.setRaffle(this);
//
//		return rafflePerson;
//	}

//	public RafflePerson removeRafflePerson(RafflePerson rafflePerson) {
//		getAssignedPersons().remove(rafflePerson);
//		rafflePerson.setRaffle(null);
//
//		return rafflePerson;
//	}

    public Integer getnCandidates() {
        return nCandidates;
    }

    public void setnCandidates(Integer nCandidates) {
        this.nCandidates = nCandidates;
    }

    public Integer getnReserves() {
        return nReserves;
    }

    public void setnReserves(Integer nReserves) {
        this.nReserves = nReserves;
    }

    public Timestamp getRaffleDate() {
        return raffleDate;
    }

    public void setRaffleDate(Timestamp raffleDate) {
        this.raffleDate = raffleDate;
    }

    public Service getService() {
        return this.service;
    }

    public void setService(Service service) {
        this.service = service;
    }

    @Override
    public String toString() {
        return this.raffleDate + "|" + this.nCandidates + "|" + this.service!=null?this.service.toString():"";
    }

    public Integer getnSurvivedCandidates() {
        return nSurvivedCandidates;
    }

    public void setnSurvivedCandidates(Integer nSurvivedCandidates) {
        this.nSurvivedCandidates = nSurvivedCandidates;
    }

    public List<AssignedRafflePerson> getAssignedPersons() {
        if (this.assignedPersons == null)
            this.assignedPersons = new ArrayList<AssignedRafflePerson>();
        return this.assignedPersons;
    }

    public void setAssignedPersons(List<AssignedRafflePerson> assignedPersons) {
        this.assignedPersons = assignedPersons;
    }

    public AssignedRafflePerson addAssignedCandidate(Person assigned) {
        AssignedRafflePerson rp = new AssignedRafflePerson();
        rp.setRaffle(this);
        rp.setPerson(assigned);
        this.getAssignedPersons().add(rp);
        
        return rp;
    }
    
    public List<ReserveRafflePerson> getReservePersons() {
        if (this.reservePersons == null)
            this.reservePersons = new ArrayList<ReserveRafflePerson>();
        return reservePersons;
    }

    public void setReservePersons(List<ReserveRafflePerson> reservePersons) {
        this.reservePersons = reservePersons;
    }

    public ReserveRafflePerson addReserveCandidate(Person reserve) {
        ReserveRafflePerson rp = new ReserveRafflePerson();
        rp.setRaffle(this);
        rp.setPerson(reserve);
        this.getReservePersons().add(rp);
        
        return rp;
    }

    public Integer getnSurvivedReserves() {
        return nSurvivedReserves;
    }

    public void setnSurvivedReserves(Integer nSurvivedReserves) {
        this.nSurvivedReserves = nSurvivedReserves;
    }
}