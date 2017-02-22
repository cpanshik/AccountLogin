package com.acn.util;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.mockito.Mockito;
import org.powermock.api.mockito.PowerMockito;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.Errors;

import com.acn.account.model.Role;
import com.acn.account.model.User;
import com.acn.account.repository.RoleRepository;
import com.acn.account.repository.UserRepository;
import com.acn.account.service.SecurityService;
import com.acn.account.service.UserService;
import com.acn.account.validator.UserValidator;

public class MockUtil {

	public static UserDetailsService mockUserDetailsService() {

		UserDetailsService mock = PowerMockito.mock(UserDetailsService.class);
		UserDetails userDetails = mockUserDetails();

		PowerMockito.when(mock.loadUserByUsername(Mockito.anyString())).thenReturn(userDetails);

		return mock;
	}

	public static UserDetails mockUserDetails() {

		UserDetails mock = PowerMockito.mock(UserDetails.class);

		PowerMockito.when(mock.getUsername()).thenReturn("username");

		return mock;

	}

	public static AuthenticationManager mockAuthenticationManager() {

		AuthenticationManager mock = PowerMockito.mock(AuthenticationManager.class);

		return mock;

	}

	public static UsernamePasswordAuthenticationToken mockUsernamePasswordAuthenticationToken() {

		UsernamePasswordAuthenticationToken mock = PowerMockito.mock(UsernamePasswordAuthenticationToken.class);

		PowerMockito.when(mock.isAuthenticated()).thenReturn(true);

		return mock;
	}

	public static SecurityContext mockSecurityContext() {

		SecurityContext mock = PowerMockito.mock(SecurityContext.class);

		Authentication authenticationMock = mockAuthentication();
		PowerMockito.when(mock.getAuthentication()).thenReturn(authenticationMock);

		return mock;
	}

	public static Model mockModel() {

		Model mock = PowerMockito.mock(Model.class);

		return mock;
	}

	public static Authentication mockAuthentication() {

		Authentication mock = PowerMockito.mock(Authentication.class);

		UserDetails userDetailsMock = mockUserDetails();
		PowerMockito.when(mock.getDetails()).thenReturn(userDetailsMock);

		return mock;
	}

	public static UserValidator mockUserValidator() {

		UserValidator mock = PowerMockito.mock(UserValidator.class);

		return mock;
	}

	public static SecurityService mockSecurityService() {

		SecurityService mock = PowerMockito.mock(SecurityService.class);

		return mock;
	}

	public static UserService mockUserService() {

		UserService mock = PowerMockito.mock(UserService.class);

		User user = mockUser();
		PowerMockito.when(mock.findByUsername(Mockito.anyString())).thenReturn(user);

		return mock;
	}

	public static User mockUser() {

		User mock = PowerMockito.mock(User.class);

		Role role = mockRole();
		Set<Role> roles = new HashSet<Role>();
		roles.add(role);

		PowerMockito.when(mock.getUsername()).thenReturn("userName");
		PowerMockito.when(mock.getPassword()).thenReturn("password");
		PowerMockito.when(mock.getPasswordConfirm()).thenReturn("password");
		PowerMockito.when(mock.getRoles()).thenReturn(roles);

		return mock;
	}

	public static Role mockRole() {

		Role mock = PowerMockito.mock(Role.class);
		PowerMockito.when(mock.getName()).thenReturn("role");

		return mock;
	}

	public static BindingResult mockBindingResult() {

		BindingResult mock = PowerMockito.mock(BindingResult.class);

		return mock;
	}

	public static Errors mockErrors() {

		Errors mock = PowerMockito.mock(Errors.class);

		return mock;
	}

	public static BCryptPasswordEncoder mockBCryptPasswordEncoder() {

		BCryptPasswordEncoder mock = PowerMockito.mock(BCryptPasswordEncoder.class);

		PowerMockito.when(mock.encode(Mockito.anyString())).thenReturn("encoded");

		return mock;
	}

	public static RoleRepository mockRoleRepository() {

		RoleRepository mock = PowerMockito.mock(RoleRepository.class);

		return mock;
	}

	public static UserRepository mockUserRepository() {

		UserRepository mock = PowerMockito.mock(UserRepository.class);

		User user = mockUser();
		PowerMockito.when(mock.findByUsername("username")).thenReturn(user);

		return mock;
	}

	public static void mockStaticSecurityContextHolder() {

		PowerMockito.mockStatic(SecurityContextHolder.class);

		SecurityContext securityContextMock = mockSecurityContext();

		PowerMockito.when(SecurityContextHolder.getContext()).thenReturn(securityContextMock);

	}

	public void mockStatic(String... staticClassesToMock) {

		List<String> mockStaticClassMethods = new ArrayList<String>();

		mockStaticClassMethods.add("mockStaticSecurityContextHolder");

		for (String staticClassToMock : staticClassesToMock) {

			for (String mockStaticClassMethod : mockStaticClassMethods) {

				if (mockStaticClassMethod.contains(staticClassToMock)) {

					Method method = null;

					try {
						method = this.getClass().getMethod(mockStaticClassMethod);
					} catch (NoSuchMethodException | SecurityException e) {
						e.printStackTrace();
					}

					if (method != null) {
						try {
							method.invoke(this);
						} catch (IllegalAccessException | IllegalArgumentException | InvocationTargetException e) {
							e.printStackTrace();
						}
					}

				}

			}

		}

	}

}
