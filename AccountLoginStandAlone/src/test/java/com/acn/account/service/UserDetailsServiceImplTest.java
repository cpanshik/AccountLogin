package com.acn.account.service;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.powermock.core.classloader.annotations.PrepareForTest;
import org.powermock.modules.junit4.PowerMockRunner;
import org.powermock.reflect.Whitebox;
import org.springframework.security.core.userdetails.UserDetails;

import com.acn.account.repository.UserRepository;
import com.acn.util.MockUtil;

@RunWith(PowerMockRunner.class)
@PrepareForTest({ UserDetailsServiceImpl.class })
public class UserDetailsServiceImplTest {

	UserDetailsServiceImpl cut;

	UserRepository userRepositoryMock;
    
    
    
    
	@Before
	public void setUp() {

		 userRepositoryMock = MockUtil.mockUserRepository();
	    
	    

		cut = Mockito.spy(UserDetailsServiceImpl.class);

		Whitebox.setInternalState(cut, "userRepository", userRepositoryMock);
	
	
	}

	@Test
	public void test_findByUsername_Resltu() {

		// Setup
	
		String username = "username";

		

		// Test
		UserDetails result = cut.loadUserByUsername(username);
	
		// Validate
		Assert.assertNotNull(result);
		

	}
	
	@Test
	public void test_findByUsername_noResltu() {

		// Setup
	
		String username = "fakeUsername";

		

		// Test
		UserDetails result = cut.loadUserByUsername(username);
	
		// Validate
		Assert.assertNull(result);
		

	}
}
