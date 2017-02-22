package com.acn.account.validator;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.powermock.api.mockito.PowerMockito;
import org.powermock.core.classloader.annotations.PrepareForTest;
import org.powermock.modules.junit4.PowerMockRunner;
import org.powermock.reflect.Whitebox;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.validation.Errors;

import com.acn.account.model.User;
import com.acn.account.service.UserService;
import com.acn.util.MockUtil;

@RunWith(PowerMockRunner.class)
@PrepareForTest({ UserValidator.class, SecurityContextHolder.class })
public class UserValidatorTest {

	UserValidator cut;

	User userMock;
	Errors errorsMock;
	
	UserService userServiceMock;

	@Before
	public void setUp() {



	userMock = MockUtil.mockUser();
	errorsMock = MockUtil.mockErrors();
	userServiceMock = MockUtil.mockUserService();
		
		cut = Mockito.spy(UserValidator.class);

		Whitebox.setInternalState(cut, "userService", userServiceMock);
	
		
	}

	@Test
	public void test_validate_rejectDuplicateUser() {

		// Setup
		
		// Test
		cut.validate(userMock, errorsMock);

		// Validate
		Mockito.verify(errorsMock,Mockito.times(1)).rejectValue(Mockito.anyString(), Mockito.anyString());
		Mockito.verify(errorsMock,Mockito.times(1)).rejectValue("username", "Duplicate.userForm.username");
		
		
	}
	
	@Test
	public void test_validate_rejectShortUserName() {

		// Setup
		PowerMockito.when(userMock.getUsername()).thenReturn("short");
		PowerMockito.when(userServiceMock.findByUsername(Mockito.anyString())).thenReturn(null );
		
		
		// Test
		cut.validate(userMock, errorsMock);

		// Validate
		Mockito.verify(errorsMock,Mockito.times(1)).rejectValue(Mockito.anyString(), Mockito.anyString());
		Mockito.verify(errorsMock,Mockito.times(1)).rejectValue("username", "Size.userForm.username");
		
		
		
	}
	
	@Test
	public void test_validate_successfu() {

		// Setup
		PowerMockito.when(userServiceMock.findByUsername(Mockito.anyString())).thenReturn(null);
		
		
		// Test
		cut.validate(userMock, errorsMock);

		// Validate
		Mockito.verify(errorsMock,Mockito.times(0)).rejectValue(Mockito.anyString(), Mockito.anyString());
		
		
		
	}
	
}
