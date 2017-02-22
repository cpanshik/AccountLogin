package com.acn.account.model;

import java.util.HashSet;
import java.util.Set;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.powermock.core.classloader.annotations.PrepareForTest;
import org.powermock.modules.junit4.PowerMockRunner;

import junit.framework.Assert;

@RunWith(PowerMockRunner.class)
@PrepareForTest(Role.class)
public class UserTest {

	User cut;

	@Before
	public void setUp() {

		cut = new User();
	}

	@Test
	public void test_getterSetters() {

		// Setup
		Long id = 123L;
		String password = "fooPw";
		Set<Role> roles = new HashSet<Role>();
		String passwordConfirm = "fooPw";
		String username = "fooUsername";

		// Test
		cut.setId(id);
		cut.setPassword(password);
		cut.setPasswordConfirm(passwordConfirm);
		cut.setRoles(roles);
		cut.setUsername(username);

		// Validate
		Assert.assertEquals(id, cut.getId());
		Assert.assertEquals(password, cut.getPassword());
		Assert.assertEquals(roles, cut.getRoles());
		Assert.assertEquals(passwordConfirm, cut.getPasswordConfirm());
		Assert.assertEquals(username, cut.getUsername());

	}

}
