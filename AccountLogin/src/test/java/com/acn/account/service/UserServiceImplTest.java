package com.acn.account.service;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.powermock.core.classloader.annotations.PrepareForTest;
import org.powermock.modules.junit4.PowerMockRunner;
import org.powermock.reflect.Whitebox;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import com.acn.account.model.User;
import com.acn.account.repository.RoleRepository;
import com.acn.account.repository.UserRepository;
import com.acn.util.MockUtil;

@RunWith(PowerMockRunner.class)
@PrepareForTest({ UserServiceImpl.class, SecurityContextHolder.class })
public class UserServiceImplTest {

	UserServiceImpl cut;

	UserRepository userRepositoryMock;

	RoleRepository roleRepositoryMock;

	BCryptPasswordEncoder bCryptPasswordEncoderMock;

	User user;

	@Before
	public void setUp() {

		userRepositoryMock = MockUtil.mockUserRepository();

		roleRepositoryMock = MockUtil.mockRoleRepository();

		bCryptPasswordEncoderMock = MockUtil.mockBCryptPasswordEncoder();
		user = MockUtil.mockUser();

		cut = Mockito.spy(UserServiceImpl.class);

		Whitebox.setInternalState(cut, "userRepository", userRepositoryMock);
		Whitebox.setInternalState(cut, "roleRepository", roleRepositoryMock);
		Whitebox.setInternalState(cut, "bCryptPasswordEncoder", bCryptPasswordEncoderMock);

	}

	@Test
	public void test_findByUsername_noResltu() {

		// Setup

		String username = "fakeUser";

		// Test
		User result = cut.findByUsername(username);

		// Validate
		Assert.assertNull(result);

	}

	@Test
	public void test_findByUsername_Resltu() {

		// Setup
		String username = "username";

		// Test
		User result = cut.findByUsername(username);

		// Validate
		Assert.assertNotNull(result);

	}

	@Test
	public void test_save() {

		// Setup

		// Test
		cut.save(user);

		// Validate
		Mockito.verify(userRepositoryMock, Mockito.times(1)).save(user);
		Mockito.verify(user, Mockito.times(1)).setPassword(Mockito.anyString());
		Mockito.verify(user, Mockito.times(1)).setRoles(Mockito.anySet());

	}
}
