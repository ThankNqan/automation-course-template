package com;

import org.junit.runner.RunWith;
import io.cucumber.junit.Cucumber;
import io.cucumber.junit.CucumberOptions;

@RunWith(Cucumber.class)
@CucumberOptions(features = { "src/test/resources/features" }, glue = { "com.steps", "com.hooks" }, plugin = { "pretty",
		"html:target/cucumber-report.html", "json:target/cucumber.json" }, monochrome = true, tags = "@Smoke")
public class RunCucumberTest {

}
