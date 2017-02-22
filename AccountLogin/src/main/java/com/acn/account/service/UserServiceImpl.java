package com.acn.account.service;

import java.util.HashSet;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import com.acn.account.model.User;
import com.acn.account.repository.RoleRepository;
import com.acn.account.repository.UserRepository;

/**
 * The Class UserServiceImpl.
 */
@Service
public class UserServiceImpl implements UserService {
   
	/** The user repository. */
	@Autowired
    private UserRepository userRepository;
   
    /** The role repository. */
    @Autowired
    private RoleRepository roleRepository;
  
    /** The b crypt password encoder. */
    @Autowired
    private BCryptPasswordEncoder bCryptPasswordEncoder;

    /* (non-Javadoc)
     * @see com.acn.account.service.UserService#save(com.acn.account.model.User)
     */
    @Override
    public void save(User user) {
       
    	// Set User Attributes
    	user.setPassword(bCryptPasswordEncoder.encode(user.getPassword()));
        user.setRoles(new HashSet<>(roleRepository.findAll()));
        
        // Save User into User Repository
        userRepository.save(user);
    }

    /* (non-Javadoc)
     * @see com.acn.account.service.UserService#findByUsername(java.lang.String)
     */
    @Override
    public User findByUsername(String username) {
        return userRepository.findByUsername(username);
    }
}
