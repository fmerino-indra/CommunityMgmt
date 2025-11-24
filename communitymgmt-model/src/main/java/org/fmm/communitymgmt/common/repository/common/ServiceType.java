package org.fmm.communitymgmt.common.repository.common;

import java.io.Serializable;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.NamedQuery;
import jakarta.persistence.Table;
import jakarta.persistence.Transient;


/**
 * The persistent class for the service_type database table.
 * 
 */
@Entity
@Table(name="service_type")
@NamedQuery(name="ServiceType.findAll", query="SELECT s FROM ServiceType s")
public class ServiceType implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
//	@GeneratedValue(strategy=GenerationType.AUTO)
	private Integer id;

	@Column(name="service_type")
	private String serviceType;

	@Transient
    private Integer needed;
	@Transient
    private Integer nReserves;
    
	public ServiceType() {
	}

	public Integer getId() {
		return this.id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getServiceType() {
		return this.serviceType;
	}

	public void setServiceType(String serviceType) {
		this.serviceType = serviceType;
	}

    public Integer getNeeded() {
        return needed;
    }

    public void setNeeded(Integer needed) {
        this.needed = needed;
    }

    public Integer getnReserves() {
        return nReserves;
    }

    public void setnReserves(Integer nReserves) {
        this.nReserves = nReserves;
    }
}