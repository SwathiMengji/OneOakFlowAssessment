package qa.stepDefinitions;

import static org.testng.Assert.assertTrue;

import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import qa.base.TestContext;

public class tc5StepDefinition {

	private TestContext context;

    public tc5StepDefinition(TestContext context) {
        this.context = context;
    }
	
	@When("scroll to {string}")
	public void scroll_to(String string) {
		context.getHomePageOregonBV().acceptCookies();
		context.getHomePageOregonBV().scrollToHeader(string);
	}
	
	@When("select {string} from the ribbon")
	public void select_from_the_ribbon(String string) {
	    context.getHomePageOregonBV().selectFromRibbon(string);
	}
	
	@When("select {string} from the dropdown")
	public void select_from_the_dropdown(String string) {
	    context.getHomePageOregonBV().selectFromRibbonDropdown(string);
	}
	
	@When("enter form details {string}, {string} and {string}")
	public void enter_form_details_and(String name, String email, String phone) {
	    context.getHomePageOregonBV().enterFormDetails(name, email, phone);
	}
	
	@Then("validate error messages {string}, {string} and {string}")
	public void validate_error_messages_and(String name, String email, String phone) {
		assertTrue(context.getHomePageOregonBV().validateErrorMessages(name, email, phone));
	}
	
}
