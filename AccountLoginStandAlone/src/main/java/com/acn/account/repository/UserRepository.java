package com.acn.account.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.acn.account.model.User;

/**
 * The Interface UserRepository.
 */
public interface UserRepository extends JpaRepository<User, Long> {
    
    /**
     * Find by username.
     *
     * @param username the username
     * @return the user
     */
    User findByUsername(String username);
}
