package qa.runners;

import io.cucumber.testng.AbstractTestNGCucumberTests;
import io.cucumber.testng.CucumberOptions;

@CucumberOptions(
		features="src/test/resources/featureFiles/TestCase5.feature",
		glue= {"qa.stepDefinitions","qa.hooks","qa.base"}, 
		plugin = {
				"pretty",
				"html:Reports/testcase5.html",
				"json:Reports/cucumber.json",
		        "com.aventstack.extentreports.cucumber.adapter.ExtentCucumberAdapter:"},
		monochrome = true)
public class tc5Runner extends AbstractTestNGCucumberTests {

}
