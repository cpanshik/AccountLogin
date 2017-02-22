package com.acn.account.service;

import java.util.HashSet;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.transaction.annotation.Transactional;

import com.acn.account.model.Role;
import com.acn.account.model.User;
import com.acn.account.repository.UserRepository;

/**
 * The Class UserDetailsServiceImpl.
 */
public class UserDetailsServiceImpl implements UserDetailsService{
  
	/** The user repository. */
	@Autowired
    private UserRepository userRepository;

    /* (non-Javadoc)
     * @see org.springframework.security.core.userdetails.UserDetailsService#loadUserByUsername(java.lang.String)
     */
    @Override
    @Transactional(readOnly = true)
    public UserDetails loadUserByUsername(final String username) throws UsernameNotFoundException {
      
        UserDetails userDetails = null;
        
        // Get User from Repo
        final User user = userRepository.findByUsername(username);
        if (user != null) {
        	
        	// Get User's Roles
	        Set<GrantedAuthority> grantedAuthorities = new HashSet<>();
	        for (Role role : user.getRoles()){
	        	if(role != null) {
	        		grantedAuthorities.add(new SimpleGrantedAuthority(role.getName()));
	        	}
	        }
	        
	        // Create User Details
	        userDetails =  new org.springframework.security.core.userdetails.User(user.getUsername(), user.getPassword(), grantedAuthorities);
	    }
        return userDetails;
    }
}
