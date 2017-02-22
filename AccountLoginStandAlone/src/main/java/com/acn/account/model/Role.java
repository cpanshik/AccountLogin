package com.acn.account.model;

import java.util.Set;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.persistence.Table;

/**
 * The Class Role.
 */
@Entity
@Table(name = "role")
public class Role {

	/** The Role id. */
	private Long id;

	/** The Role name. */
	private String name;

	/** The users in this Role. */
	private Set<User> users;

	/**
	 * Gets the Role id.
	 *
	 * @return the id
	 */
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	public Long getId() {
		return id;
	}

	/**
	 * Sets the Role id.
	 *
	 * @param id the new id
	 */
	public void setId(Long id) {
		this.id = id;
	}

	/**
	 * Gets the Role name.
	 *
	 * @return the name
	 */
	public String getName() {
		return name;
	}

	/**
	 * Sets the Role name.
	 *
	 * @param name the new name
	 */
	public void setName(String name) {
		this.name = name;
	}

	/**
	 * Gets the users in this Role.
	 *
	 * @return the users
	 */
	@ManyToMany(mappedBy = "roles")
	public Set<User> getUsers() {
		return users;
	}

	/**
	 * Sets the users in this Role.
	 *
	 * @param users the new users
	 */
	public void setUsers(Set<User> users) {
		this.users = users;
	}
}
