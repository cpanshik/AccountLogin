package com.acn.account.steps.context;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

import cucumber.api.java.After;
import cucumber.api.java.Before;

public class StepContext {

	private static String APP_PORT = System.getProperty("app.accountlogin.port");
	private static String APP_URI = "http://localhost:"+ APP_PORT + "/";	
	private static boolean initialized = false;
	private WebDriver driver = null;

	@Before
	public void setUp() {
		
	/*	System.setProperty("webdriver.chrome.driver",
				"C:/Users/White Tiger/Documents/Arun/chromedriver_win32/chromedriver.exe");*/
		driver = new ChromeDriver();
	}

	public WebDriver getWebDriver() {
		return driver;
	}

	public static String getApplicationURI() {
		return APP_URI;
	}

	@After
	public void close() {
		if (driver != null) {
			driver.close();
		}
	}

}
