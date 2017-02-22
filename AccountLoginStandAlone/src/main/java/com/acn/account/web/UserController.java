package com.acn.account.web;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.acn.account.model.User;
import com.acn.account.service.SecurityService;
import com.acn.account.service.UserService;
import com.acn.account.validator.UserValidator;

/**
 * The Class UserController.
 */
@Controller
public class UserController {
  
	/** The user service. */
	@Autowired
    private UserService userService;

    /** The security service. */
    @Autowired
    private SecurityService securityService;

    /** The user validator. */
    @Autowired
    private UserValidator userValidator;
    
    private static final String WELCOME = "welcome";
	private static final String LOGIN = "login";
	private static final String REGISTRATION = "registration";
	private static final String USER_FORM = "userForm";
	private static final String MESSAGE = "message";
	private static final String ERROR = "error";

    /**
     * Registration. Creates New User.
     *
     * @param model the model
     * @return the string
     */
    @RequestMapping(value = "/registration", method = RequestMethod.GET)
    public String registration(Model model) {
      
    	// Create New User
    	model.addAttribute(USER_FORM, new User());

        return REGISTRATION;
    }

    /**
     * Registration. Validates New User from UserForm. Saves New User. Logs User Into Welcome Page.
     *
     * @param userForm the user form
     * @param bindingResult the binding result
     * @param model the model
     * @return the string
     */
    @RequestMapping(value = "/registration", method = RequestMethod.POST)
    public String registration(@ModelAttribute(USER_FORM) User userForm, BindingResult bindingResult, Model model) {
      
    	// Validate User Registration Username and Password
    	userValidator.validate(userForm, bindingResult);

    	// Return if Validation Error
        if (bindingResult.hasErrors()) {
            return REGISTRATION;
        }
        
        // Save User
        userService.save(userForm);

        // Login User
        securityService.login(userForm.getUsername(), userForm.getPasswordConfirm());

        // Send User to Welcome Login Page
        return "redirect:/welcome";
    }

    /**
     * Login. Landing Page. Displays Login Messages.
     *
     * @param model the model
     * @param error the error
     * @param logout the logout
     * @return the string
     */
    @RequestMapping(value = "/login", method = RequestMethod.GET)
    public String login(Model model, String error, String logout) {
      
    	if (error != null) {
            model.addAttribute(ERROR, "Your username and password is invalid.");
        }

        if (logout != null) {
            model.addAttribute(MESSAGE, "You have been logged out successfully.");
        }

        return LOGIN;
    }

    /**
     * Welcome. For Logged In Users only.
     *
     * @param model the model
     * @return the string
     */
    @RequestMapping(value = {"/", "/welcome"}, method = RequestMethod.GET)
    public String welcome(Model model) {
        return WELCOME;
    }
}
