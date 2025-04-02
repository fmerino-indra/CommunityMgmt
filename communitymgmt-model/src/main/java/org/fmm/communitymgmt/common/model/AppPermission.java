package org.fmm.communitymgmt.common.model;

import java.io.Serializable;
import jakarta.persistence.*;
import java.util.List;


/**
 * The persistent class for the app_permission database table.
 * 
 */
@Entity
@Table(name="app_permission")
@NamedQuery(name="AppPermission.findAll", query="SELECT a FROM AppPermission a")
public class AppPermission implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	private Integer id;

	private String permission;

	//bi-directional many-to-many association to AppRole
	@ManyToMany(mappedBy="appPermissions")
	private List<AppRole> appRoles;

	public AppPermission() {
	}

	public Integer getId() {
		return this.id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getPermission() {
		return this.permission;
	}

	public void setPermission(String permission) {
		this.permission = permission;
	}

	public List<AppRole> getAppRoles() {
		return this.appRoles;
	}

	public void setAppRoles(List<AppRole> appRoles) {
		this.appRoles = appRoles;
	}

}