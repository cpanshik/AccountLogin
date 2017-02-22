package com.acn.account.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.acn.account.model.Role;

/**
 * The Interface RoleRepository.
 */
public interface RoleRepository extends JpaRepository<Role, Long>{
}
