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
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.Table;

/**
 * @author Manish
 *
 */
@Entity
@Table(name = "tbl_user")
public class UserEntity {
	private static final long serialVersionUID = -2952735933715107252L;
	@Id
	@Column(name = "USER_ID")
	@GeneratedValue(strategy = GenerationType.AUTO)
	private long id;

	@Column(name = "USER_NAME")
	private String username;

	@Column(name = "PASSWORD")
	private String password;

	@Column(name = "STATUS")
	private boolean status;

	@ManyToMany(fetch = FetchType.EAGER, cascade = CascadeType.ALL)
	@JoinTable(name = "tbl_user_role", joinColumns = @JoinColumn(name = "USER_ID_FK", referencedColumnName = "USER_ID"), inverseJoinColumns = @JoinColumn(name = "ROLE_ID_FK", referencedColumnName = "ROLE_ID"))
	private Set<RoleEntity> roles;

	public UserEntity() {
		super();
	}

	public UserEntity(String username, String password, boolean status) {
		super();
		this.username = username;
		this.password = password;
		this.status = status;
	}

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public Set<RoleEntity> getRoles() {
		if (roles == null) {
			roles = new HashSet<RoleEntity>();
		}
		return roles;
	}

	@Override
	public String toString() {
		return "UserEntity [id=" + id + ", username=" + username + ", password=" + password + ", status=" + status
				+ "]";
	}

}