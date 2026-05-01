package org.fmm.communitymgmt.common.model.calendar;

import java.io.Serializable;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;

import org.fmm.communitymgmt.common.model.Community;
import org.fmm.communitymgmt.common.model.common.TEventType;
import org.fmm.communitymgmt.common.model.common.TTripod;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;

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
@JsonIdentityInfo(
		  generator = ObjectIdGenerators.PropertyGenerator.class, 
		  property = "id") // Usará el campo 'id' de la entidad como referencia
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
	
//	@Column(columnDefinition = "TIMESTAMP")
	@Column(columnDefinition = "DATE")
	@JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd")
	private LocalDate eventDate;
	
	@Column(columnDefinition = "TIME", nullable = true)
	@JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "HH:mm:ss")
	private LocalTime eventTime;
	
	@Column
	@JsonProperty("groupNeeded")
	private Boolean groupNeeded = false;
	
	@Column
	@JsonProperty("groupAssigned")
	private Boolean groupAssigned = false;
	
	@Column
	@JsonProperty("published")
	private Boolean published = false;
	
	@ManyToOne
	@JsonIgnore
	private TEventType eventLocation; //domestic or in parish
	
	@ManyToOne(optional = true)
//	@JsonIdentityInfo( 
			  //generator = ObjectIdGenerators.PropertyGenerator.class,
			  //property = "id",
			// Esto lo que hace es un número secuencial, cada vez que cambia y lo poner en atributo @ref: así
			/*
			 VER ABAJO
			 */
//			  generator = ObjectIdGenerators.IntSequenceGenerator.class, 
//			  property = "@ref",
//			  scope = TTripod.class) // Usará el campo 'id' de la entidad como referencia
//	@JsonIdentityReference(alwaysAsId = false) // ESTO es la clave
	
	//@JsonIdentityReference( alwaysAsId = true)
	@JsonIgnore
	private TTripod tripodType;
	
	@ManyToOne
	@JsonIgnoreProperties("events") // Ignora la lista de eventos dentro de la comunidad al serializar el evento
	@JsonIgnore
	private Community community;
	
	@Transient
	@JsonIgnore
	private LocalDateTime ldt;
	
	@Column(name="year", insertable = false, updatable=false)
	private Integer year;
	
	@Column(name="month", insertable = false, updatable=false)
	private Integer month;
	
	public Integer getYear() {
		return year;
	}

	public void setYear(Integer year) {
		this.year = year;
	}

	public Integer getMonth() {
		return month;
	}

	public void setMonth(Integer month) {
		this.month = month;
	}

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

	public Boolean getGroupNeeded() {
		return (groupNeeded!=null)?groupNeeded:false;
	}

	public void setGroupNeeded(Boolean needGroup) {
		this.groupNeeded = needGroup;
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

	@JsonIgnore
	public LocalDateTime getEventDateTime() {
		return ldt;
	}

	@JsonProperty("eventType")
	public String getEventTypeName() {
		return (this.getTripodType() != null) ? this.getTripodType().getTripodName() : null;
	}

	public Boolean getPublished() {
		return (published != null) ? published:false;
	}

	public void setPublished(Boolean published) {
		this.published = published;
	}

	public Boolean getGroupAssigned() {
		return (groupAssigned!=null) ? groupAssigned:false;
	}

	public void setGroupAssigned(Boolean groupAssigned) {
		this.groupAssigned = groupAssigned;
	}
	
	@Override
	public String toString() {
		return String.format("%s-%s-%s", getEventDate(), getEventName(), getEventTime());
	}
}

/**

            {
                "id": 2851,
                "eventName": "Convivence febrero",
                "eventDate": "2025-02-16",
                "eventTime": "11:00:00",
                "needGroup": false,
                "tripodType": {
                    "@ref": 1,
                    "id": 3,
                    "tripodName": "Community",
                    "frequency": 30
                },
                "year": 2025,
                "month": 2
            },
            {
                "id": 2866,
                "eventName": "Eucharist febrero",
                "eventDate": "2025-02-01",
                "eventTime": "20:30:00",
                "needGroup": true,
                "tripodType": {
                    "@ref": 2,
                    "id": 1,
                    "tripodName": "Liturgy",
                    "frequency": 7
                },
                "year": 2025,
                "month": 2
            },

**/