package qa.base;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.time.Duration;
import java.util.Properties;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.FluentWait;
import org.openqa.selenium.support.ui.Wait;

import io.github.bonigarcia.wdm.WebDriverManager;

public class Base {
	
		public static WebDriver driver;

		public static Properties properties;
		
		public Base() {	
			try {
				properties = new Properties();
				FileInputStream input = new FileInputStream("src/main/java/qa/base/config.properties");
				properties.load(input);
				input.close();
			}catch (FileNotFoundException error) {
				error.printStackTrace();
			}catch (IOException error) {
				error.printStackTrace();
			}
		}
		
		public static WebDriver launchBrowser() {
			WebDriverManager.chromedriver().setup();
			driver = new ChromeDriver(); 
			driver.manage().window().maximize();
			driver.manage().deleteAllCookies();
			driver.manage().timeouts().pageLoadTimeout(Duration.ofSeconds(30));
			driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(3));
			return driver;
			
		}
		
		public static void launchBrowser(String url) {
			WebDriverManager.chromedriver().setup();
			driver = new ChromeDriver(); 
			
			driver.manage().window().maximize();
			driver.manage().deleteAllCookies();
			driver.manage().timeouts().pageLoadTimeout(Duration.ofSeconds(5));
			driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(3));
			
			driver.get(properties.getProperty(url));
		}
		
		
		public static void waitUntilElementByWebElement(WebElement locator) {
			
			Wait<WebDriver> wait = new FluentWait<WebDriver>(driver)
					.withTimeout(Duration.ofSeconds(10))
					.pollingEvery(Duration.ofMillis(500))
					.ignoring(NoSuchElementException.class);
			wait.until(ExpectedConditions.visibilityOf(locator));
		}
		
		
		public static void waitUntilElementByLocator(By locator) {
			Wait<WebDriver> wait = new FluentWait<WebDriver>(driver)
					.withTimeout(Duration.ofSeconds(15))
					.pollingEvery(Duration.ofMillis(500))
					.ignoring(NoSuchElementException.class);
			wait.until(ExpectedConditions.visibilityOfElementLocated(locator));
		}
		
		public static void waitImplicit(int sec) {
			driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(sec));
		}
		
		
		
		public static void scrollToElement(WebElement locator) {
			((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView(true);", locator);
		}
		
		public static void scrollToTop() {
			((JavascriptExecutor) driver).executeScript("window.scrollTo(0, 0);");
		}
		
		public static void scrollByPercentage(double percentage) {
			if (percentage < 0) {
	            throw new IllegalArgumentException("Percentage cannot be less than 0");
	        }
	        JavascriptExecutor js = (JavascriptExecutor) driver;
	        Long totalHeight = (Long) js.executeScript("return document.body.scrollHeight");
	        long scrollBy = (long) (totalHeight * (percentage / 100.0));

	        js.executeScript("window.scrollBy(0, arguments[0]);", scrollBy);
		}
		
		public static void takeSS(String destPath) throws IOException {
			TakesScreenshot ss = (TakesScreenshot) driver;
			File ssrc = ss.getScreenshotAs(OutputType.FILE);
			File dest = new File(destPath);
			FileUtils.copyFile(ssrc, dest);
		}
		
		public static String takeScreenshotWhenFail(WebDriver driver, String screenshotName) {
	        String path = "Reports/screenshots/" + screenshotName + ".png";
	        File srcFile = ((TakesScreenshot) driver).getScreenshotAs(OutputType.FILE);
	        try {
	            FileUtils.copyFile(srcFile, new File(path));
	        } catch (IOException e) {
	            e.printStackTrace();
	        }
	        return path;
	    }

		
		
		
		public static void teardown() {
			driver.close();
			driver.quit();
		}
		
}
