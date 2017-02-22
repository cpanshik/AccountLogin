package com.acn.account.web;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.powermock.core.classloader.annotations.PrepareForTest;
import org.powermock.modules.junit4.PowerMockRunner;
import org.powermock.reflect.Whitebox;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;

import com.acn.account.model.User;
import com.acn.account.service.SecurityService;
import com.acn.account.service.UserService;
import com.acn.account.validator.UserValidator;
import com.acn.util.MockUtil;

@RunWith(PowerMockRunner.class)
@PrepareForTest({ UserController.class })
public class UserControllerTest {

	UserController cut;

	UserService userServiceMock;

	SecurityService securityServiceMock;

	UserValidator userValidatorMock;
	
	Model modelMock;
	
	User userMock;
	BindingResult bindingResultMock;
	
	

	@Before
	public void setUp() {

		MockUtil mockUtil = new MockUtil();
		

		 userServiceMock = MockUtil.mockUserService();

		 securityServiceMock = MockUtil.mockSecurityService();

		 userValidatorMock = MockUtil.mockUserValidator();
		 
		modelMock = MockUtil.mockModel();
		
		 userMock = MockUtil.mockUser();
		 bindingResultMock = MockUtil.mockBindingResult();
		
		

		cut = Mockito.spy(UserController.class);

		Whitebox.setInternalState(cut, "userService", userServiceMock);
		Whitebox.setInternalState(cut, "securityService", securityServiceMock);
		Whitebox.setInternalState(cut, "userValidator", userValidatorMock);
	}

	@Test
	public void test_login() {

		// Setup
		String logout = "fooLogout";
		String error = "fooError";

		
		
		// Test
		String result = cut.login(modelMock, error, logout);

		// Validate
		Assert.assertNotNull(result);
		Assert.assertEquals("login", result);


	}
	
	@Test
	public void test_registration() {

		// Setup
		
		// Test
		String result = cut.registration(modelMock);

		// Validate
		Assert.assertNotNull(result);
		Assert.assertEquals("registration", result);


	}
	
	@Test
	public void test_registration_redirectToWelcome() {

		// Setup
		
		
		// Test
		String result = cut.registration(userMock, bindingResultMock, modelMock);
		

		// Validate
		Assert.assertNotNull(result);
		Assert.assertEquals("redirect:/welcome", result);


	}
	
}
