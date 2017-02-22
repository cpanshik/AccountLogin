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
public class RoleTest {

	Role cut;

	@Before
	public void setUp() {

		cut = new Role();
	}

	@Test
	public void test_getterSetters() {

		// Setup

		Long id = 123L;
		String name = "fooName";
		Set<User> users = new HashSet<User>();

		// Test
		cut.setId(id);
		cut.setName(name);
		cut.setUsers(users);

		// Validate
		Assert.assertEquals(id, cut.getId());
		Assert.assertEquals(name, cut.getName());
		Assert.assertEquals(users, cut.getUsers());

	}

}
