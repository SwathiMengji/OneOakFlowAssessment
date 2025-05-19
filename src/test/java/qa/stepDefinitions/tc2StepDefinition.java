package qa.stepDefinitions;

import static org.testng.Assert.assertTrue;

import java.io.IOException;

import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import qa.base.Base;
import qa.base.TestContext;
import qa.meredithPageObjects.HomePageMeredith;

public class tc2StepDefinition extends Base{
	
	private TestContext context;

    public tc2StepDefinition(TestContext context) {
        this.context = context;
    }
	
	@When("location is selected as {string}")
	public void location_is_selected_as(String string) throws IOException, InterruptedException {
		context.getHomePageMeredith().acceptCookies();
		context.getHomePageMeredith().selectLocation(string);
	}
	
	@When("arrival date is selected {string} and depart date is selected {string}")
	public void arrival_date_is_selected_and_depart_date_is_selected(String string1, String string2) throws IOException {
		context.getHomePageMeredith().selectDates(string1, string2);
	}
	
	
	@When("add {int} adults and {int} children")
	public void add_adults_and_children(Integer int1, Integer int2) {
		context.getHomePageMeredith().addGuests(int1, int2);
	}
	
	@When("clear people selection")
	public void clear_people_selection() {
		context.getHomePageMeredith().clearSelections();
	}
	
	@When("click on search")
	public void click_on_search() {
		context.getHomePageMeredith().search();
	}
	
	@When("add filter options {int} bedrooms, {int} bathrooms and  {string} pool")
	public void add_filter_options_bedrooms_bathrooms_and_pool(Integer bedrooms, Integer bathrooms, String pool) throws IOException {
	    waitImplicit(2);
	    context.getHomePageMeredith().filterOptions(bedrooms, bathrooms, pool);
	}
	
	@When("sort by {string}")
	public void sort_by(String sortOption) {
		context.getHomePageMeredith().sortOptions(sortOption);
	}
	
	@Then("validate first entry name is {string}")
	public void validate_first_entry_name_is(String string) throws IOException {
		try {
    		assertTrue(context.getHomePageMeredith().validateFirstPropertyName(string));
    	}catch(Exception e) {
    		takeSS("screenshots/tc2IncorrectFirstProperty.png");
    		assertTrue(false);
    	}
	}
	
	@Then("validate the text after sort is {string}")
	public void validate_the_text_after_sort_is(String string) throws IOException {
		try {
    		assertTrue(context.getHomePageMeredith().validateSortName(string));
    	}catch(Exception e) {
    		takeSS("screenshots/tc2IncorrectSort.png");
    		assertTrue(false);
    	}
	}
	
}
