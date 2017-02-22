package com.acn.account.model;


import java.util.Set;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.Table;
import javax.persistence.Transient;

/**
 * The Class User.
 */
@Entity
@Table(name = "user")
public class User {
    

    /** The User id. */
    private Long id;
    

    /** The User username. */
    private String username;
    

    /** The User password. */
    private String password;
    
 
    /** The User password confirm. */
    private String passwordConfirm;
    

    /** The User's roles. */
    private Set<Role> roles;


    /**
     * Gets the User id.
     *
     * @return the id
     */
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    public Long getId() {
        return id;
    }


    /**
     * Sets the User id.
     *
     * @param id the new id
     */
    public void setId(Long id) {
        this.id = id;
    }


    /**
     * Gets the User username.
     *
     * @return the username
     */
    public String getUsername() {
        return username;
    }


    /**
     * Sets the User username.
     *
     * @param username the new username
     */
    public void setUsername(String username) {
        this.username = username;
    }


    /**
     * Gets the User password.
     *
     * @return the password
     */
    public String getPassword() {
        return password;
    }


    /**
     * Sets the User password.
     *
     * @param password the new password
     */
    public void setPassword(String password) {
        this.password = password;
    }


    /**
     * Gets the User password confirm.
     *
     * @return the password confirm
     */
    @Transient
    public String getPasswordConfirm() {
        return passwordConfirm;
    }


    /**
     * Sets the User password confirm.
     *
     * @param passwordConfirm the new password confirm
     */
    public void setPasswordConfirm(String passwordConfirm) {
        this.passwordConfirm = passwordConfirm;
    }


    /**
     * Gets the User's roles.
     *
     * @return the roles
     */
    @ManyToMany
    @JoinTable(name = "user_role", joinColumns = @JoinColumn(name = "user_id"), inverseJoinColumns = @JoinColumn(name = "role_id"))
    public Set<Role> getRoles() {
        return roles;
    }


    /**
     * Sets the User's roles.
     *
     * @param roles the new roles
     */
    public void setRoles(Set<Role> roles) {
        this.roles = roles;
    }
}
