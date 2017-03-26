package com.acn.account.steps.runner;

import org.junit.runner.RunWith;

import cucumber.api.CucumberOptions;
import cucumber.api.junit.Cucumber;

/* @formatter:off */
@RunWith(Cucumber.class)
@CucumberOptions(features = { "classpath:com/acn/account/features" }, 
				 plugin = { "pretty", "html:target/cucumber" },
				 strict = true,
				 //dryRun = true,
				 glue = { "classpath:com/acn/account/steps" })
public class CucumberTest {
}
/* @formatter:on */
