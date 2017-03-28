Feature: Login page functionality
	The user should see login page when try to access the path 'AccountLogin'
	The user should see an error if the username and password is invalid
	There should be a link in the login page for creating a new account
	On clicking the new account link in the login page should show registration page	
	
Scenario: The user should see login page when try to access the path 'AccountLogin'
	Given open the browser
		And provide the application url ending with "AccountLogin"
	When i hit enter in the browser
	Then the application should show me login page ending with "AccountLogin/login"
	
Scenario: The user should see an error if the username and password is invalid
	Given open the browser
		And provide the application url ending with "AccountLogin"
		And i hit enter in the browser
	When i provide invalid user as "" and ivalid password as "password"
	Then the application should show me an error "Your username and password is invalid."
	
Scenario: There should be a link in the login page for creating a new account
	Given open the browser
		And provide the application url ending with "AccountLogin"
		And i hit enter in the browser
	When i hit enter in the browser
	Then the application should provide me a link in the login page for creating a new account
	
Scenario: On clicking the new account link in the login page should show registration page
	Given open the browser
		And provide the application url ending with "AccountLogin"
		And i hit enter in the browser
	When i click on the new registration link
	Then the application should show me the registration page ending with "AccountLogin/registration" for creating a new account