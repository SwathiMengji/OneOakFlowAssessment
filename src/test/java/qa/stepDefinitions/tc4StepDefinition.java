package qa.stepDefinitions;

import static org.testng.Assert.assertTrue;

import java.io.IOException;

import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import qa.base.Base;
import qa.base.TestContext;

public class tc4StepDefinition extends Base {
	private TestContext context;

    public tc4StepDefinition(TestContext context) {
        this.context = context;
    }
	
    
    @When("hover over {string} and validate color change")
    public void hover_over_and_validate_color_change(String name) throws InterruptedException {
    	assertTrue(context.getHomePageRebl().hoverOverAndValidateColorChange(name));
    }
    
    
    @Then("validate {string} under {string}")
    public void validate_under(String options, String name) throws IOException {
    	try {
    		assertTrue(context.getHomePageRebl().validateOptionsUnder(options, name));
    	}catch(Exception e) {
    		takeSS("screenshot/tc4IncorrectOptions.png");
    		assertTrue(false);
    	}
    	
    }
    
    @When("hover over and click on {string}")
    public void hover_over_and_click_on(String string) throws InterruptedException {
        context.getHomePageRebl().hoverOverAndClick(string);
    }
    
    @Then("validate {string} on home page")
    public void validate_on_home_page(String string) throws IOException {
    	try {
    		assertTrue(context.getHomePageRebl().validateHeader(string));
    	}catch(Exception e) {
    		takeSS("screenshots/tc4IncorrectHeader.png");
    		assertTrue(false);
    	}
    	
    }
    
    @Then("validate property name {string} and location {string}")
    public void validate_property_name_and_location(String name, String location) throws IOException {
    	try {
    		assertTrue(context.getHomePageRebl().validateProperty(name, location));    
    	}catch(Exception e) {
    		takeSS("screenshots/tc4IncorrectProperty.png");
    		assertTrue(false);
    	}
    	
    }
    
    
}
