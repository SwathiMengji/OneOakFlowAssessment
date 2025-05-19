package qa.meredithPageObjects;

import java.io.IOException;
import java.time.Duration;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import qa.base.Base;

public class HomePageMeredith extends Base{

	public HomePageMeredith(WebDriver driver) {
		this.driver = driver;
	}
	
	By cookiesAlertBox = By.xpath("//p[contains(text(),'cookies')]");
	By cookiesAcceptButton = By.xpath("//p[contains(text(),'cookies')]//following-sibling::button");
	By socialMediaIcons = By.xpath("//div[@class='flex space-x-6 md:order-2']//a");
	
	By LocationInputBox = By.xpath("//input[@placeholder ='Search Locations']");
	By dateSelectorButton = By.xpath("//button[@aria-label='Select Arrival Date Select Departure Date']");
	By nextMonthButton = By.xpath("//span[text()='Next month']//parent::button");
	By guestsAdultIncrementButton = By.xpath("//span[text()='adults']//parent::div//following-sibling::div//button[2]");
	By guestsChildrenIncrementButton = By.xpath("//span[text()='children']//parent::div//following-sibling::div//button[2]");
	By clearEntryButton = By.xpath("//button[text()='Clear']");
	By searchButton = By.xpath("//button[@aria-label='Search']");
	
	By headerFilterPage = By.xpath("//h2[text()='Lincoln City, Oregon Vacation Rentals by Meredith Lodging']");
	By filterOptionButton = By.xpath("//span[@id='filter']");
	By bedroomsIncrementButton = By.xpath("//p[text()='Bedrooms']//following-sibling::div/button[2]");
	By bathroomsIncrementButton = By.xpath("//p[text()='Bathrooms']//following-sibling::div/button[2]");
	By poolAndSpaDropdownButton = By.xpath("//button[text()='Pool and Spa']");
	By filterOptionsApplyButton = By.xpath("//button[text()='Apply']");
	
	By sortButton = By.xpath("//span[text()='Sort']//parent::div//parent::button");
	By firstPropertyCardName = By.xpath("//div[contains(@id,'list-card')][1]//a/div[3]/div/label");
	By firstProperty = By.xpath("//div[contains(@id,'list-card')][1]//a/div[3]/div");
	By sortName = By.xpath("//span[text()='Sort']//parent::div//parent::button//span[2]");
	
	By propertyNameHeadingDeatils = By.xpath("//h1");
	By bedroomDetails = By.xpath("//li[contains(text(),'Bedrooms')]/strong");
	By bathroomDetails = By.xpath("//li[contains(text(),'Bathrooms')]/strong");
	By maxGuestDetails = By.xpath("//li[contains(text(),'Guests')]/strong");
	By arrivalDateDetails = By.xpath("//span[text()='Arrival']//following-sibling::span");
	By departDateDetails = By.xpath("//span[text()='Depart']//following-sibling::span");
	By numberOfGuestDetails = By.xpath("//span[contains(text(),'guests')]");
	
	
	ArrayList<String> urls_findelemnts = new ArrayList<String>();
	ArrayList<String> urls_windows = new ArrayList<String>();
	
	public void acceptCookies() {
		if(driver.findElement(cookiesAlertBox).isDisplayed()) {
			driver.findElement(cookiesAcceptButton).click();
		}
	}
	
	public void scrollToFooterHomePage() {
		scrollToElement(driver.findElement(socialMediaIcons));
	}

	public void findURLs() {
		List<WebElement> urls = driver.findElements(socialMediaIcons);
		for(WebElement icon : urls) {
			urls_findelemnts.add(icon.getAttribute("href"));
		}	
		//System.out.println(urls_findelemnts.toString());	
	}
	
	public void openAndGetURLs() {
		List<WebElement> urls = driver.findElements(socialMediaIcons);
		String parentWindow = driver.getWindowHandle();
		
		for(WebElement icon : urls) {
			icon.click();
			Set<String> windowHandles = driver.getWindowHandles();

			for(String handle : windowHandles) {
				if(!handle.equals(parentWindow)) {
					driver.switchTo().window(handle);
					urls_windows.add(driver.getCurrentUrl());
					driver.close();
					driver.switchTo().window(parentWindow);
				}
			}
		}	
		//System.out.println(urls_windows.toString());	
	}
	
	public boolean validateURL() {
		boolean assertURL = urls_findelemnts.equals(urls_windows) ? true : false;
		return assertURL;
	}
	
	
	public void selectLocation(String location) throws IOException, InterruptedException {
		Actions actions = new Actions(driver);
		actions.moveToElement(driver.findElement(LocationInputBox))
		       .click()
		       .sendKeys("Lincoln City").perform();
		Thread.sleep(700);
		actions.sendKeys(Keys.ENTER).perform();
	}
	
	

	public void selectDates(String arrival, String depart) throws IOException {
		//driver.findElement(dateSelectorButton).click();
		
		//arrival date
		By arrivalDate = By.xpath("//time[@datetime='"+arrival+"']//parent::button");		
		try{
			scrollToElement(driver.findElement(arrivalDate));
			driver.findElement(arrivalDate).click();
		}catch (Exception e) {
			System.out.println("Arrival date incorrect");
			takeSS("screenshots/tc2ArrivalDate.png");
		}
		
		//select next month
		scrollToElement(driver.findElement(nextMonthButton));
		driver.findElement(nextMonthButton).click();
		
		//depart date
		try{
			driver.findElement(By.xpath("//time[@datetime='"+depart+"']//parent::button")).click();
		}catch (Exception e) {
			System.out.println("Depart date incorrect");
			takeSS("screenshots/tc2DepartDate.png");
		}
	}
	
	
	public void addGuests(int adults, int children) {
		
		for(int i=0;i<adults;i++) {
			driver.findElement(guestsAdultIncrementButton).click();
		}
		
		for(int i=0;i<children;i++) {
			driver.findElement(guestsChildrenIncrementButton).click();
		}
	}
	
	public void clearSelections() {
		driver.findElement(clearEntryButton).click();
	}
	
	public void search() {
		driver.findElement(searchButton).click();
	}
	
	public void filterOptions(int number_bedrooms, int number_bathrooms, String pool_preference) throws IOException {
		scrollByPercentage(5);
		
		waitUntilElementByLocator(filterOptionButton);
		driver.findElement(filterOptionButton).click();
		
		waitUntilElementByLocator(bedroomsIncrementButton);
		for(int i=0;i<number_bedrooms;i++) {
			driver.findElement(bedroomsIncrementButton).click();
		}
		
		for(int i=0;i<number_bathrooms;i++) {
			driver.findElement(bathroomsIncrementButton).click();
		}
		
		//Pool and spa
		try {
			driver.findElement(poolAndSpaDropdownButton).click();
			driver.findElement(By.xpath("//div//label[text()='"+pool_preference+"']//preceding-sibling::input")).click();
		}catch (Exception e) {
			System.out.println("Incorrect pool and spa option");
			takeSS("screenshots/tc2poolandspa.png");
		}
		
		driver.findElement(filterOptionsApplyButton).click();
		waitImplicit(30);
	}
	
	
	public void sortOptions(String sortOption) {
		scrollByPercentage(5);
		for(int i=0;i<10;i++) {
			driver.findElement(sortButton).click();
			//scrollToElement(driver.findElement(By.xpath("//div[text()='"+sortOption+"']")));
			driver.findElement(By.xpath("//div[text()='"+sortOption+"']")).click();
			waitImplicit(5);
		}
	}
	
	public boolean validateFirstPropertyName(String expected) throws InterruptedException {
		waitImplicit(30);
		
		
		
		String found =driver.findElement(firstPropertyCardName).getText();
		System.out.println(found);
		
		if(found.equals(expected)) return true;
		else {
			System.out.println("Name of first property found is: "+found);
			return false;
		}
	}
	
	public boolean validateSortName(String expected) {
		String found =driver.findElement(sortName).getText().trim().replace(": ", "");;
		System.out.println(found);
		
		if(found.equals(expected)) return true;
		else {
			System.out.println("Sort name found is: "+found);
			return false;
		}
	}
	
	public void selectFirstEntry() {
		try {
			waitImplicit(50);
			scrollByPercentage(5.0);
			driver.manage().timeouts().pageLoadTimeout(Duration.ofSeconds(30));
			waitUntilElementByWebElement(driver.findElement(firstProperty));
			scrollByPercentage(2.0);
			waitImplicit(50);
			//scrollToElement(driver.findElement(firstProperty));
			driver.findElement(firstProperty).click();
		}catch(Exception e) {
			waitImplicit(30);
			//scrollToElement(driver.findElement(firstProperty));
			driver.findElement(firstProperty).click();
		}
		
	}
	
	public void printPropertyNameDetailsPage() {
		waitUntilElementByLocator(By.xpath("//h3[text()='About this place']"));
		String name = driver.findElement(propertyNameHeadingDeatils).getText().strip();
		System.out.println("***************************************************");
		System.out.println("Name of property: "+name);
		System.out.println("***************************************************");
	}
	
	public Map<String, String> validateDetails() {
		Map<String, String> guestDetails = new HashMap<>();
		
		scrollToElement(driver.findElement(By.xpath("//h3[text()='About this place']")));
		
		String numberOfBedroomsFound = driver.findElement(bedroomDetails).getText();
		guestDetails.put("bedrooms", numberOfBedroomsFound);
		
		String numberOfBathroomsFound = driver.findElement(bedroomDetails).getText();
		guestDetails.put("bathrooms", numberOfBathroomsFound);
		
		String numberOfMaxGuestsFound = driver.findElement(maxGuestDetails).getText().split(" ")[0];
		guestDetails.put("maxGuests", numberOfMaxGuestsFound);
		
		String arrivalDateFound = driver.findElement(arrivalDateDetails).getText();
		guestDetails.put("arrivalDate", arrivalDateFound);
		
		String departDateFound = driver.findElement(departDateDetails).getText();
		guestDetails.put("departDate", departDateFound);
		
		String numberOfGuestsFound = driver.findElement(numberOfGuestDetails).getText().split(" ")[0];
		guestDetails.put("guests", numberOfGuestsFound);
		
		return guestDetails;
	}
}
