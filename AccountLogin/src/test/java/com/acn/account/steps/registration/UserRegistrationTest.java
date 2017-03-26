package com.acn.account.steps.registration;

import java.util.concurrent.TimeUnit;

import org.junit.Assert;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import com.acn.account.steps.context.StepContext;
import com.acn.account.steps.login.AccountLoginTest;

import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;

public class UserRegistrationTest {

	String appURI = StepContext.getApplicationURI();
	WebDriverWait driverWait;
	public WebDriver driver;
	public WebElement registrationLink;
	WebElement usernameElement;
	WebElement passwordElement;
	WebElement passwordReenterElement;
	WebElement submitButton;
	WebElement errorSpan;

	public UserRegistrationTest(StepContext stepContext, AccountLoginTest accountLoginTest) {
		this.driver = accountLoginTest.driver;
		this.registrationLink = accountLoginTest.registrationLink;
	}

	@Given("^i clicked on the link to create a new account$")
	public void i_clicked_on_the_link_to_create_a_new_account() throws Throwable {
		registrationLink = driver.findElement(By.xpath("//form//h4/a[text()='Create an account']"));
		registrationLink.click();
	}

	@Given("^it opened up the web page for new registration with path ending \"([^\"]*)\"$")
	public void it_opened_up_the_web_page_for_new_registration_with_path_ending(String path) throws Throwable {
		driverWait = new WebDriverWait(driver, 5);
		driverWait.pollingEvery(1, TimeUnit.SECONDS);
		driverWait.until(ExpectedConditions.elementToBeClickable(By.className("container")));
		Assert.assertTrue(driver.getCurrentUrl().equals(appURI + path));
	}

	@When("^i provided a new username as \"([^\"]*)\" and password as \"([^\"]*)\"$")
	public void i_provided_a_new_username_as_and_password_as(String username, String password) throws Throwable {
		usernameElement = driver.findElement(By.name("username"));
		passwordElement = driver.findElement(By.name("password"));
		passwordReenterElement = driver.findElement(By.name("passwordConfirm"));
		submitButton = driver.findElement(By.xpath("//form//button[text()='Submit']"));
		usernameElement.sendKeys(username);
		passwordElement.sendKeys(password);
		passwordReenterElement.sendKeys(password);
		submitButton.click();
	}

	@Then("^the user should get successfully created and proceed to login page with path ending \"([^\"]*)\"$")
	public void the_user_should_get_successfully_created_and_proceed_to_login_page_with_path_ending(String path)
			throws Throwable {
		driverWait = new WebDriverWait(driver, 5);
		driverWait.pollingEvery(1, TimeUnit.SECONDS);
		WebElement element = driverWait.until(ExpectedConditions.elementToBeClickable(By.className("container")));
		Assert.assertTrue(driver.getCurrentUrl().equals(appURI + path));
	}

	@When("^i provide a valid user as \"([^\"]*)\" and valid password as \"([^\"]*)\"$")
	public void i_provide_a_valid_user_as_and_valid_password_as(String username, String password) throws Throwable {
		usernameElement = driver.findElement(By.name("username"));
		passwordElement = driver.findElement(By.name("password"));
		submitButton = driver.findElement(By.xpath("//form//button[text()='Log In']"));
		usernameElement.sendKeys(username);
		passwordElement.sendKeys(password);
		submitButton.click();
	}

	@Then("^the application should proceed to login page with path ending \"([^\"]*)\"$")
	public void the_application_should_proceed_to_login_page_with_path_ending(String path) throws Throwable {
		driverWait = new WebDriverWait(driver, 5);
		driverWait.pollingEvery(1, TimeUnit.SECONDS);
		WebElement element = driverWait.until(ExpectedConditions.elementToBeClickable(By.className("container")));
		String currentURL = driver.getCurrentUrl();
		Assert.assertTrue(driver.getCurrentUrl().equals(appURI + path));
	}

	@Then("^it should give me error for username saying \"([^\"]*)\"$")
	public void it_should_give_me_error_for_username_saying(String errorMessage) throws Throwable {
		errorSpan = driver.findElement(By.id("username.errors"));
		String errorSpanText = errorSpan.getText();
		Assert.assertTrue(errorSpanText.equals(errorMessage));
	}

}
