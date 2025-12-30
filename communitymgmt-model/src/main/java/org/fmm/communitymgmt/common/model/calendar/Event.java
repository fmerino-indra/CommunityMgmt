package org.fmm.communitymgmt.common.model.calendar;

import java.io.Serializable;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;

import org.fmm.communitymgmt.common.model.Community;
import org.fmm.communitymgmt.common.model.common.TEventType;
import org.fmm.communitymgmt.common.model.common.TTripod;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Transient;


/**
 * The persistent class for the community database table.
 * 
 */
@Entity
public class Event implements Serializable {
/*	
	private static final String pattern = "dd-MM-yyyy";
	private static final String timePattern = "HH:mm";
	private static SimpleDateFormat simpleDateFormat = new SimpleDateFormat(pattern);
	private static SimpleDateFormat simpleTimeFormat = new SimpleDateFormat(timePattern);
*/	
	private static final long serialVersionUID = 1L;

	
	@Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;

	@Column
	private String eventName;
	
	@Column(columnDefinition = "DATE")
//	@Column(columnDefinition = "TIMESTAMP")
	private LocalDate eventDate;
	
	@Column(columnDefinition = "TIME")
	private LocalTime eventTime;
	
	@Column
	private Boolean needGroup;
	
	@ManyToOne
	private TEventType eventLocation; //domestic or in parish
	
	@ManyToOne
	private TTripod tripodType;
	
	@ManyToOne
	private Community community;
	
	@Transient
	private LocalDateTime ldt;
	
	public Event() {
	}

	public Integer getId() {
		return this.id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getEventName() {
		return eventName;
	}

	public void setEventName(String eventName) {
		this.eventName = eventName;
	}

	public Boolean getNeedGroup() {
		return needGroup;
	}

	public void setNeedGroup(Boolean needGroup) {
		this.needGroup = needGroup;
	}

	public Community getCommunity() {
		return community;
	}

	public void setCommunity(Community community) {
		this.community = community;
	}

	public TTripod getTripodType() {
		return tripodType;
	}

	public void setTripodType(TTripod tripodType) {
		this.tripodType = tripodType;
	}

	public TEventType getEventLocation() {
		return eventLocation;
	}

	public void setEventLocation(TEventType eventLocation) {
		this.eventLocation = eventLocation;
	}

	public LocalTime getEventTime() {
		return eventTime;
	}

	public void setEventTime(LocalTime eventTime) {
		this.eventTime = eventTime;
		updateEventDateTime();
	}

	public LocalDate getEventDate() {
		return eventDate;
	}

	public void setEventDate(LocalDate eventDate) {
		this.eventDate = eventDate;
		updateEventDateTime();
	}

	private void updateEventDateTime() {
		if (eventDate != null && eventTime != null)
			this.ldt = LocalDateTime.of(eventDate, eventTime);
		else
			this.ldt = null;
	}

	public LocalDateTime getEventDateTime() {
		return ldt;
	}

	@Override
	public String toString() {
		return String.format("%s-%s-%s", getEventDate(), getEventName(), getEventTime());
	}
}