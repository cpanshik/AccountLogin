package com.acn.account.validator;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.ValidationUtils;
import org.springframework.validation.Validator;

import com.acn.account.model.User;
import com.acn.account.service.UserService;

/**
 * The Class UserValidator. This is a Bean.
 */
@Component
public class UserValidator implements Validator {

	/** The Constant DEFAULT_MAX_LENGTH. */
	private static final int DEFAULT_MAX_LENGTH = 32;

	/** The Constant DEFAULT_MIN_LENGTH. */
	private static final int DEFAULT_MIN_LENGTH = 6;

	/** The user service. */
	@Autowired
	private UserService userService;

	private static final String USERNAME = "username";
	private static final String PASSWORD = "password";
	private static final String VALIDATION_MESSAGE_EMPTY = "Empty";
	private static final String VALIDATION_MESSAGE_UNKNOWN = "Unknown";
	private static final String VALIDATION_MESSAGE_SIZE_USERNAME = "Size.userForm.username";
	private static final String VALIDATION_MESSAGE_SIZE_PASSWORD = "Size.userForm.password";
	private static final String VALIDATION_MESSAGE_DUPLICATE_USERNAME = "Duplicate.userForm.username";
	private static final String VALIDATION_MESSAGE_DIFF_PASSWORD = "Diff.userForm.password";

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.springframework.validation.Validator#validate(java.lang.Object,
	 * org.springframework.validation.Errors)
	 */
	@Override
	public void validate(Object o, Errors errors) {

		if (o instanceof User) {
			final User user = (User) o;
			final String userName = user.getUsername();
			validateUsername(errors, userName);
			validatePassword(errors, user);
		} else {
			errors.rejectValue(USERNAME, VALIDATION_MESSAGE_UNKNOWN);
		}
	}

	/**
	 * Validate password.
	 *
	 * @param errors
	 *            the errors
	 * @param user
	 *            the user
	 */
	private void validatePassword(Errors errors, User user) {

		// Reject bases of of incoming errors
		ValidationUtils.rejectIfEmptyOrWhitespace(errors, PASSWORD, VALIDATION_MESSAGE_EMPTY);

		String password = user.getPassword();
		if (password != null) {
			// Validate Password Length
			if (password.length() < DEFAULT_MIN_LENGTH || password.length() > DEFAULT_MAX_LENGTH) {
				errors.rejectValue(PASSWORD, VALIDATION_MESSAGE_SIZE_PASSWORD);
			}

			// Validate Passwords Match
			if (!user.getPasswordConfirm().equals(password)) {
				errors.rejectValue(PASSWORD, VALIDATION_MESSAGE_DIFF_PASSWORD);
			}
		} else {
			errors.rejectValue(PASSWORD, VALIDATION_MESSAGE_UNKNOWN);
		}
	}

	/**
	 * Validate username.
	 *
	 * @param errors
	 *            the errors
	 * @param userName
	 *            the user name
	 */
	private void validateUsername(Errors errors, String userName) {

		// Reject bases of of incoming errors
		ValidationUtils.rejectIfEmptyOrWhitespace(errors, USERNAME, VALIDATION_MESSAGE_EMPTY);

		if (userName != null) {
			// Validate Username Length
			if (userName.length() < DEFAULT_MIN_LENGTH || userName.length() > DEFAULT_MAX_LENGTH) {
				errors.rejectValue(USERNAME, VALIDATION_MESSAGE_SIZE_USERNAME);
			}

			// Validate Duplicate Username
			if (userService.findByUsername(userName) != null) {
				errors.rejectValue(USERNAME, VALIDATION_MESSAGE_DUPLICATE_USERNAME);
			}
		} else {
			errors.rejectValue(USERNAME, VALIDATION_MESSAGE_SIZE_USERNAME);
		}
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.springframework.validation.Validator#supports(java.lang.Class)
	 */
	@Override
	public boolean supports(Class<?> aClass) {
		return User.class.equals(aClass);
	}
}
