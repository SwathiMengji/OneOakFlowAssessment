package qa.oregonBVPageObjects;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

import qa.base.Base;

public class HomePageOregonBV extends Base{

	public HomePageOregonBV(WebDriver driver){
		this.driver = driver;
	}
	
	By cookiesAlertBox = By.xpath("//p[contains(text(),'cookies')]");
	By cookiesAcceptButton = By.xpath("//p[contains(text(),'cookies')]//following-sibling::button");
	By fullNameForm = By.xpath("//div[text()='Full Name']//following-sibling::input");
	By nameError = By.xpath("//div[text()='Full Name']//following-sibling::p");
	By emailForm = By.xpath("//div[text()='Email']//following-sibling::input");
	By emailError = By.xpath("//div[text()='Email']//following-sibling::p");
	By phoneNumberForm = By.xpath("//div[@class='PhoneInputCountry']//following-sibling::input");
	By phoneNumberError = By.xpath("//div[@class='PhoneInputCountry']//parent::div//parent::div//following-sibling::p");
	By addressInputBox = By.xpath("//div[text()='Full Address of the Rental']//following-sibling::input");
	
	public void acceptCookies() {
		try {
			if(driver.findElement(cookiesAlertBox).isDisplayed()) {
				driver.findElement(cookiesAcceptButton).click();
			}
		}catch (Exception e) {
			System.out.println("");
		}
	}
	
	public void scrollToHeader(String text) {	
		scrollToElement(driver.findElement(By.xpath("//h2[text()='"+text+"']")));
	}
	
	public void selectFromRibbon(String option) {
		scrollToTop();
		waitUntilElementByLocator(By.xpath("//button[text()='"+option+"']"));
		driver.findElement(By.xpath("//button[text()='"+option+"']")).click();
	}
	
	public void selectFromRibbonDropdown(String option) {
		driver.findElement(By.xpath("//div[@class='header-nav-items']//a[text()='"+option+"']")).click();
	}
	
	public void enterFormDetails(String name, String email, String phone) {
		driver.findElement(fullNameForm).sendKeys(name);
		driver.findElement(emailForm).sendKeys(email);
		driver.findElement(phoneNumberForm).sendKeys(phone);
		driver.findElement(addressInputBox).click();
	}
	
	public boolean validateErrorMessages(String name, String email, String phone) {
		String nameErrorString = driver.findElement(nameError).getText().strip();
		String emailErrorString = driver.findElement(emailError).getText().strip();
		String phoneErrorString = driver.findElement(phoneNumberError).getText().strip();
		
		if(nameErrorString.equals(name) && emailErrorString.equals(email) && phoneErrorString.equals(phone)) {
			return true;
		}else return false;
			
	}
	
}
