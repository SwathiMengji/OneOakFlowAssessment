package qa.stepDefinitions;

import java.io.IOException;
import java.util.Map;

import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import qa.base.Base;
import qa.base.TestContext;
import org.testng.asserts.SoftAssert;

public class tc3StepDefinition extends Base{

	private TestContext context;

    public tc3StepDefinition(TestContext context) {
        this.context = context;
    }
	
	@When("select first entry")
	public void select_first_entry() {
		context.getHomePageMeredith().selectFirstEntry();
	}
	
	@Then("print the name of property")
	public void print_the_name_of_property() {
		context.getHomePageMeredith().printPropertyNameDetailsPage();
	}
	
	@Then("validate details on checkout page")
	public void validate_details_on_checkout_page() throws IOException {
	    Map<String, String> detailsFound = context.getHomePageMeredith().validateDetails();
	    
	    SoftAssert softAssert = new SoftAssert();
	    
	    softAssert.assertTrue(Integer.parseInt(detailsFound.get("bedrooms"))>=2);
	    softAssert.assertTrue(Integer.parseInt(detailsFound.get("bathrooms"))>=2);
	    softAssert.assertTrue(Integer.parseInt(detailsFound.get("maxGuests"))>=3);
	    softAssert.assertTrue(detailsFound.get("arrivalDate").equals("2025-06-25"));
	    softAssert.assertTrue(detailsFound.get("departDate").equals("2025-07-01"));
	    softAssert.assertTrue(Integer.parseInt(detailsFound.get("guests"))==3);
	    
	    try {
	    	softAssert.assertAll();
	    }catch(Exception e) {
	    	System.out.println("Incorrect details");
	    	takeSS("screenshots/tc3incorrectdetails.png");
	    }
	    
	}
	
}
