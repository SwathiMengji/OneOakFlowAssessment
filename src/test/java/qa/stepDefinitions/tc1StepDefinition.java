package qa.stepDefinitions;

import static org.testng.Assert.assertTrue;

import java.io.IOException;

import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import qa.base.Base;
import qa.base.TestContext;


public class tc1StepDefinition extends Base{
	
	private TestContext context;

    public tc1StepDefinition(TestContext context) {
        this.context = context;
    }
	
	@Given("the user is on {string}")
	public void the_user_is_on_url_in_properties_file(String string) {
		context.getDriver().get(properties.getProperty(string));
	}
	
	@When("user scolls down to footer")
	public void user_scolls_down_to_footer() {
		context.getHomePageMeredith().acceptCookies();
		context.getHomePageMeredith().scrollToFooterHomePage();
	}
	
	@When("finds all urls of social media icons using findelements")
	public void finds_all_urls_of_social_media_icons_using_findelements() {
		context.getHomePageMeredith().findURLs();
	}
	
	@When("open all social media handles in new windows")
	public void open_all_social_media_handles_in_new_windows() {
		context.getHomePageMeredith().openAndGetURLs();
	}
	
	@Then("validate the urls found match the urls opened")
	public void validate_the_urls_found_match_the_urls_opened() throws IOException {
		try {
			assertTrue(context.getHomePageMeredith().validateURL(), "URLs do not match!");
		}
		catch (Exception e){
			takeSS("screenshots/tc1.png");
		}
	}
	
}
