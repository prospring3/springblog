/**
 * Created on Oct 21, 2011
 */
package com.apress.prospring3.springblog.domain;

import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.persistence.Table;

/**
 * @author Clarence
 *
 */
@Entity
@Table(name = "role")
public class Role implements Serializable {

	private String roleId;
	private String description;
	private Set<AppUser> appUsers = new HashSet<AppUser>(0);

	public Role() {
	}

	public Role(String roleId) {
		this.roleId = roleId;
	}

	public Role(String roleId, String description, Set<AppUser> appUsers) {
		this.roleId = roleId;
		this.description = description;
		this.appUsers = appUsers;
	}

	@Id
	@Column(name = "ROLE_ID")
	public String getRoleId() {
		return this.roleId;
	}

	public void setRoleId(String roleId) {
		this.roleId = roleId;
	}

	@Column(name = "DESCRIPTION")
	public String getDescription() {
		return this.description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	@ManyToMany(fetch = FetchType.LAZY, mappedBy = "roles")
	public Set<AppUser> getAppUsers() {
		return this.appUsers;
	}

	public void setAppUsers(Set<AppUser> appUsers) {
		this.appUsers = appUsers;
	}

}
