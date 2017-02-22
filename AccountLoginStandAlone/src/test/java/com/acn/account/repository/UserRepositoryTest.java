package com.acn.account.repository;

import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.powermock.modules.junit4.PowerMockRunner;

import com.acn.account.model.User;

@RunWith(PowerMockRunner.class)
public class UserRepositoryTest {

	UserRepository cut = Mockito.spy(UserRepository.class);

	@Test
	public void test_findByUsername() {

		// Setup
		String username = "username";

		// Test
		User result = cut.findByUsername(username);

		// Validate
		Assert.assertNull(result);
	}
}
