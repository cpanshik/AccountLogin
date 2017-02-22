package com.acn.account.service;

/**
 * The Interface SecurityService.
 */
public interface SecurityService {

    /**
     * Autologin.
     *
     * @param username the username
     * @param password the password
     */
    void login(String username, String password);
}
