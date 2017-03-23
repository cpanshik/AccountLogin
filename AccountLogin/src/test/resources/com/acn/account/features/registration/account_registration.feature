Feature: Creating a new account functionality
	A new user with username "testuser" and password "password123" should get successfully created
	The new user "testuser" should be able to login successfully
	A new user with username "test" and password "123" should not be created
	
Scenario: A new user with username "testuser" and password "password123" should get successfully created
	Given open the browser
		And provide the application url ending with "AccountLogin"
		And i hit enter in the browser
		And i clicked on the link to create a new account
		And it opened up the web page for new registration with path ending "AccountLogin/registration" 
	When i provided a new username as "testuser" and password as "password123"
	Then the user should get successfully created and proceed to login page with path ending "AccountLogin/welcome"
	
Scenario: The new user "testuser" should be able to login successfully
	Given open the browser
		And provide the application url ending with "AccountLogin"
		And i hit enter in the browser
	When i provide a valid user as "testuser" and valid password as "password123"
	Then the application should proceed to login page with path ending "AccountLogin/"
	
Scenario: A new user with username "test" and password "123" should not be created
	Given open the browser
		And provide the application url ending with "AccountLogin"
		And i hit enter in the browser
		And i clicked on the link to create a new account
		And it opened up the web page for new registration with path ending "AccountLogin/registration" 
	When i provided a new username as "test" and password as "123"
	Then it should give me error for username saying "Must be between 6 and 32 characters."