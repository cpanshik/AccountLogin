package com.acn.account.repository;

import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.powermock.modules.junit4.PowerMockRunner;

@RunWith(PowerMockRunner.class)
public class RoleRepositoryTest {

	RoleRepository cut = Mockito.spy(RoleRepository.class); 

	@Test
	public void test_count() {

		// Setup
		

		// Test
		long result = cut.count();

		// Validate
		Assert.assertEquals(0, result);
	}
}
