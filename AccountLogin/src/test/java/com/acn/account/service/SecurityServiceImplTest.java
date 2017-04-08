package com.acn.account.service;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.powermock.api.mockito.PowerMockito;
import org.powermock.core.classloader.annotations.PrepareForTest;
import org.powermock.modules.junit4.PowerMockRunner;
import org.powermock.reflect.Whitebox;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetailsService;

import com.acn.util.MockUtil;

@RunWith(PowerMockRunner.class)
@PrepareForTest({ SecurityServiceImpl.class, SecurityContextHolder.class })
public class SecurityServiceImplTest {

	// Class Under Test
	SecurityServiceImpl cut;

	UserDetailsService userDetailsServiceMock;
	AuthenticationManager authenticationManagerMock;
	UsernamePasswordAuthenticationToken usernamePasswordAuthenticationTokenMock;
	SecurityContext securityContextMock;

	@Before
	public void setUp() {

		MockUtil mockUtil = new MockUtil();
		mockUtil.mockStatic("StaticSecurityContextHolder");

		userDetailsServiceMock = MockUtil.mockUserDetailsService();
		authenticationManagerMock = MockUtil.mockAuthenticationManager();
		usernamePasswordAuthenticationTokenMock = MockUtil.mockUsernamePasswordAuthenticationToken();
		securityContextMock = MockUtil.mockSecurityContext();

		cut = Mockito.spy(SecurityServiceImpl.class);

		Whitebox.setInternalState(cut, "userDetailsService", userDetailsServiceMock);
		Whitebox.setInternalState(cut, "authenticationManager", authenticationManagerMock);

		try {
			PowerMockito.whenNew(UsernamePasswordAuthenticationToken.class).withAnyArguments()
					.thenReturn(usernamePasswordAuthenticationTokenMock);
		} catch (Exception e) {
			e.printStackTrace();
		}

		PowerMockito.mockStatic(SecurityContextHolder.class);
		PowerMockito.when(SecurityContextHolder.getContext()).thenReturn(securityContextMock);
	}

	@Test
	public void test_login_sucessfull() {

		// Setup
		String password = "fooPw";
		String username = "fooUser";



		// Test
		cut.login(username, password);

		// Validate
		PowerMockito.verifyStatic(Mockito.times(1));
		SecurityContextHolder.getContext();

		Mockito.verify(securityContextMock, Mockito.times(1))
				.setAuthentication(usernamePasswordAuthenticationTokenMock);

	}
}
