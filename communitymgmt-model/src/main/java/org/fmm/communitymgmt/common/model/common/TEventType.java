package org.fmm.communitymgmt.common.model.common;

import org.fmm.communitymgmt.common.util.enums.EventTypeEnum;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;

@Entity(name = "t_event_types")
public class TEventType {
	@Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;

	@Column
	private String serviceTypeName;
	
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public String getServiceTypeName() {
		return serviceTypeName;
	}
	public void setServiceTypeName(String serviceTypeName) {
		this.serviceTypeName = serviceTypeName;
	}
	
	public static TEventType from(EventTypeEnum typeEnum) {
		TEventType type = new TEventType();
		type.setId(typeEnum.getId());
		type.setServiceTypeName(typeEnum.getName());
		return type;
	}
}
