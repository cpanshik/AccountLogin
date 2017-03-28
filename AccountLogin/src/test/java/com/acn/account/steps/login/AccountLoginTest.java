package com.acn.account.steps.login;

import java.util.concurrent.TimeUnit;

import javax.validation.constraints.AssertTrue;

import org.junit.Assert;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import com.acn.account.steps.context.StepContext;

import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;

public class AccountLoginTest {

	String appURI = StepContext.getApplicationURI();
	public WebDriver driver;
	WebDriverWait driverWait;
	WebElement errorSpan;
	WebElement usernameElement;
	WebElement passwordElement;
	WebElement submitButton;
	public WebElement registrationLink;

	public AccountLoginTest(StepContext stepContext) {
		driver = stepContext.getWebDriver();
	}

	@Given("^open the browser$")
	public void open_the_browser() throws Throwable {
		Assert.assertNotNull(driver);
	}

	@Given("^provide the application url ending with \"([^\"]*)\"$")
	public void provide_the_application_url_ending_with(String path) throws Throwable {
		driver.get(appURI + path);
	}

	@When("^i hit enter in the browser$")
	public void i_hit_enter_in_the_browser() throws Throwable {
		driverWait = new WebDriverWait(driver, 5);
		driverWait.pollingEvery(1, TimeUnit.SECONDS);
		WebElement element = driverWait.until(ExpectedConditions.elementToBeClickable(By.className("container")));
		Assert.assertTrue(element.isDisplayed());
	}

	@Then("^the application should show me login page ending with \"([^\"]*)\"$")
	public void the_application_should_show_me_login_page_ending_with(String path) throws Throwable {
		Assert.assertTrue(driver.getCurrentUrl().equals(appURI + path));
	}

	@When("^i provide invalid user as \"([^\"]*)\" and ivalid password as \"([^\"]*)\"$")
	public void i_provide_invalid_user_as_and_ivalid_password_as(String username, String password) throws Throwable {
		errorSpan = driver.findElement(By.xpath("//form//span[2]"));
		usernameElement = driver.findElement(By.name("username"));
		passwordElement = driver.findElement(By.name("password"));
		submitButton = driver.findElement(By.xpath("//form//button[text()='Log In']"));
		usernameElement.sendKeys(username);
		passwordElement.sendKeys(password);
		Assert.assertTrue(errorSpan.getText().isEmpty());
		submitButton.click();
	}

	@Then("^the application should show me an error \"([^\"]*)\"$")
	public void the_application_should_show_me_an_error(String errorMessage) throws Throwable {
		errorSpan = driver.findElement(By.xpath("//form//span[2]"));
		String errorSpanText = errorSpan.getText();
		Assert.assertTrue(errorSpanText.equals(errorMessage));
	}

	@Then("^the application should provide me a link in the login page for creating a new account$")
	public void the_application_should_provide_me_a_link_in_the_login_page_for_creating_a_new_account()
			throws Throwable {
		registrationLink = driver.findElement(By.xpath("//form//h4/a[text()='Create an account']"));
	}

	@When("^i click on the new registration link$")
	public void i_click_on_the_new_registration_link() throws Throwable {
		registrationLink = driver.findElement(By.xpath("//form//h4/a[text()='Create an account']"));
		registrationLink.click();
	}

	@Then("^the application should show me the registration page ending with \"([^\"]*)\" for creating a new account$")
	public void the_application_should_show_me_the_registration_page_ending_with_for_creating_a_new_account(String path)
			throws Throwable {
		driverWait = new WebDriverWait(driver, 5);
		driverWait.pollingEvery(1, TimeUnit.SECONDS);
		WebElement element = driverWait.until(ExpectedConditions.elementToBeClickable(By.className("container")));
		Assert.assertTrue(driver.getCurrentUrl().equals(appURI + path));
	}

}
