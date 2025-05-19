package qa.reblPageObjects;

import java.io.IOException;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;

import qa.base.Base;

public class HomePageRebl extends Base{

	public HomePageRebl(WebDriver driver){
		this.driver = driver;
	}
	
	By propertyItem = By.xpath("//div[@class='abe-item']");
	
	
	public boolean hoverOverAndValidateColorChange(String name) throws InterruptedException {
		WebElement element = driver.findElement(By.xpath("//span[text()='"+name+"']//ancestor::li[1]"));
		
		String colorValueBefore = element.getCssValue("background-color");
		System.out.println(colorValueBefore);
		
		Thread.sleep(1000);
		
		Actions action=new Actions(driver);
		action.moveToElement(element).build().perform(); 
		
		String colorValueAfter = element.getCssValue("background-color");
		System.out.println(colorValueAfter);
		if(colorValueBefore.equals(colorValueAfter)) {
			return false;
		}else {return true;}
		
	}
	
	
	public boolean validateOptionsUnder(String options, String name) {
		WebElement element = driver.findElement(By.xpath("//span[text()='"+name+"']//ancestor::li"));
		Actions action=new Actions(driver);
		action.moveToElement(element).build().perform(); 
		
		List<WebElement> optionsList= driver.findElements(By.xpath("//span[text()='"+name+"']//parent::a//following-sibling::ul/li/a/span"));
		
		StringBuilder optionsFound = new StringBuilder();

		for (WebElement op : optionsList) {
		    if (optionsFound.length() > 0) {
		        optionsFound.append(", ");
		    }
		    optionsFound.append(op.getText());
		}

		String finalResult = optionsFound.toString();
		
		if(finalResult.equals(options)) return true;
		else return false;
	}
	
	
	public void hoverOverAndClick(String name) throws InterruptedException {
		WebElement element = driver.findElement(By.xpath("//span[text()='"+name+"']//ancestor::li"));
		Actions action=new Actions(driver);
		action.moveToElement(element).build().perform(); 
		Thread.sleep(500);
		action.click().build().perform();
	}
	
	public boolean validateHeader(String header) throws IOException {
		String headerFound = driver.findElement(By.xpath("//h1")).getText().strip().toLowerCase();
		System.out.println(header.toLowerCase());
		System.out.println(headerFound);
		if(header.toLowerCase().equals(headerFound)) return true;
		else {
			//takeSS("screenshots/tc4IncorrectHeader.png");
			return false;
		}
	}
	
	public boolean validateProperty(String name, String location) throws IOException {
		WebElement property = driver.findElement(propertyItem);
		String propertyNameFound = property.getAttribute("data-vrp-name").toLowerCase();
		String propertyAddressFound = property.getAttribute("data-vrp-address").toLowerCase();
		
		if((name.toLowerCase().equals(propertyNameFound)) && propertyAddressFound.contains(location.toLowerCase())) return true;
		else {
			//takeSS("screenshots/tc4IncorrectProperty.png");
			return false;
		}
	}
}
