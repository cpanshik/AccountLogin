package com.acn.account.service;

import com.acn.account.model.User;

/**
 * The Interface UserService.
 */
public interface UserService {
    
    /**
     * Save.
     *
     * @param user the user
     */
    void save(User user);

    /**
     * Find by username.
     *
     * @param username the username
     * @return the user
     */
    User findByUsername(String username);
}
