package com.manish.javadev.app.entities;

import java.util.HashSet;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.persistence.Table;

/**
 * @author Manish
 *
 */
@Entity
@Table(name = "tbl_role")
public class RoleEntity {

	private static final long serialVersionUID = -2952735933715107252L;
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "ROLE_ID")
	private long id;

	@Column(name = "ROLE")
	private String role;

	@ManyToMany(mappedBy = "roles", fetch = FetchType.LAZY, cascade = CascadeType.ALL)
	private Set<UserEntity> users;

	public RoleEntity() {
		super();
	}

	public RoleEntity(String role) {
		super();
		this.role = role;
	}

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public String getRole() {
		return role;
	}

	public void setRole(String role) {
		this.role = role;
	}

	public Set<UserEntity> getUsers() {
		if (users == null) {
			users = new HashSet<UserEntity>();
		}
		return users;
	}

}