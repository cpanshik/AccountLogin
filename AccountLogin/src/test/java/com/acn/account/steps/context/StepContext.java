package com.acn.account.steps.context;

import java.net.MalformedURLException;
import java.net.URL;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.remote.RemoteWebDriver;

import cucumber.api.java.After;
import cucumber.api.java.Before;

public class StepContext {

	private static String APP_URI = System.getProperty("app.accountlogin.uri") + "/";
	private static String CHROME_DRIVER_URI = System.getProperty("app.chromedriver.uri");
	private static boolean initialized = false;
	private WebDriver driver = null;

	@Before
	public void setUp() throws MalformedURLException {

		/*
		 * -Dtest=CucumberTest
		 * -Dapp.accountlogin.uri=http://localhost:8080
		 * -Dapp.chromedriver.uri=http://localhost:9515 test
		 */
		driver = new RemoteWebDriver(new URL(CHROME_DRIVER_URI), DesiredCapabilities.chrome());		
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
