package org.fmm.communitymgmt.common.model.templates;

import java.io.Serializable;

import org.fmm.communitymgmt.common.model.common.TEventType;

import com.fasterxml.jackson.annotation.JsonBackReference;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;


/**
 * The persistent class for the service_type database table.
 * 
 */
@Entity
@Table(name="celebration_event_template")
public class CelebrationEventTemplate implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
//	@GeneratedValue(strategy=GenerationType.AUTO)
	private Integer id;

	@Column(name="name")
	private String name;

	@Column(name="celebration_order")
    private Integer order;
    
	@ManyToOne
	@JoinColumn(name = "cycle_id", insertable = true,updatable = true, nullable = false)
	@JsonBackReference
	private CelebrationCycleTemplate cct;

	@Column
    private Boolean teamNeaded;
	
    @ManyToOne
    @JoinColumn(name = "event_location_id", insertable = true,updatable = true, nullable = false)
    private TEventType eventLocation;

	public CelebrationEventTemplate() {
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

	public Boolean getTeamNeaded() {
		return teamNeaded;
	}

	public void setTeamNeaded(Boolean teamNeaded) {
		this.teamNeaded = teamNeaded;
	}

	public CelebrationCycleTemplate getCct() {
		return cct;
	}

	public void setCct(CelebrationCycleTemplate cct) {
		this.cct = cct;
	}

	public Integer getOrder() {
		return order;
	}

	public void setOrder(Integer order) {
		this.order = order;
	}

	public TEventType getEventLocation() {
		return eventLocation;
	}

	public void setEventLocation(TEventType eventLocation) {
		this.eventLocation = eventLocation;
	}

}