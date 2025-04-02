package org.fmm.communitymgmt.common.model;

import java.io.Serializable;
import jakarta.persistence.*;
import java.util.List;


/**
 * The persistent class for the app_role database table.
 * 
 */
@Entity
@Table(name="app_role")
@NamedQuery(name="AppRole.findAll", query="SELECT a FROM AppRole a")
public class AppRole implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	private Integer id;

	@Column(name="role_name")
	private String roleName;

	//bi-directional many-to-many association to AppPermission
	@ManyToMany
	@JoinTable(
		name="app_role_permission"
		, joinColumns={
			@JoinColumn(name="role_id")
			}
		, inverseJoinColumns={
			@JoinColumn(name="permission_id")
			}
		)
	private List<AppPermission> appPermissions;

	//bi-directional many-to-one association to AppRole
	@ManyToOne
	@JoinColumn(name="role_parent")
	private AppRole appRole;

	//bi-directional many-to-one association to AppRole
	@OneToMany(mappedBy="appRole")
	private List<AppRole> appRoles;

	//bi-directional many-to-many association to Person
	@ManyToMany(mappedBy="appRoles")
	private List<Person> persons;

	public AppRole() {
	}

	public Integer getId() {
		return this.id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getRoleName() {
		return this.roleName;
	}

	public void setRoleName(String roleName) {
		this.roleName = roleName;
	}

	public List<AppPermission> getAppPermissions() {
		return this.appPermissions;
	}

	public void setAppPermissions(List<AppPermission> appPermissions) {
		this.appPermissions = appPermissions;
	}

	public AppRole getAppRole() {
		return this.appRole;
	}

	public void setAppRole(AppRole appRole) {
		this.appRole = appRole;
	}

	public List<AppRole> getAppRoles() {
		return this.appRoles;
	}

	public void setAppRoles(List<AppRole> appRoles) {
		this.appRoles = appRoles;
	}

	public AppRole addAppRole(AppRole appRole) {
		getAppRoles().add(appRole);
		appRole.setAppRole(this);

		return appRole;
	}

	public AppRole removeAppRole(AppRole appRole) {
		getAppRoles().remove(appRole);
		appRole.setAppRole(null);

		return appRole;
	}

	public List<Person> getPersons() {
		return this.persons;
	}

	public void setPersons(List<Person> persons) {
		this.persons = persons;
	}

}