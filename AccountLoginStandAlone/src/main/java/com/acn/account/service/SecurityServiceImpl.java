package com.acn.account.service;

import java.util.Collection;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.stereotype.Service;

/**
 * The Class SecurityServiceImpl.
 */
@Service
public class SecurityServiceImpl implements SecurityService {

	/** The authentication manager. */
	@Autowired
	private AuthenticationManager authenticationManager;

	/** The user details service. */
	@Autowired
	private UserDetailsService userDetailsService;

	private static final Logger logger = LoggerFactory.getLogger(SecurityServiceImpl.class);

	/*
	 * @see com.acn.account.service.SecurityService#autologin(java.lang.String,
	 * java.lang.String)
	 */
	@Override
	public void login(String username, String password) {

		// Get User Info
		UserDetails userDetails = userDetailsService.loadUserByUsername(username);

		if (userDetails != null) {
			Collection<? extends GrantedAuthority> authorities = userDetails.getAuthorities();
			UsernamePasswordAuthenticationToken usernamePasswordAuthenticationToken = new UsernamePasswordAuthenticationToken(
					userDetails, password, authorities);

			// Authenticate User
			authenticationManager.authenticate(usernamePasswordAuthenticationToken);

			// Log User In
			if (usernamePasswordAuthenticationToken.isAuthenticated()) {
				SecurityContext context = SecurityContextHolder.getContext();
				context.setAuthentication(usernamePasswordAuthenticationToken);
				logger.debug(String.format("Auto login %s successfully!", username));
			}
		}
	}
}
