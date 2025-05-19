package qa.base;

import org.openqa.selenium.WebDriver;

import com.aventstack.extentreports.ExtentTest;

import qa.meredithPageObjects.HomePageMeredith;
import qa.oregonBVPageObjects.HomePageOregonBV;
import qa.reblPageObjects.HomePageRebl;

public class TestContext {
	private WebDriver driver;
    private HomePageMeredith homePageM;
    private HomePageRebl homePageR;
    private HomePageOregonBV homePageO;
    private ExtentTest test;

    public WebDriver getDriver() {
        return driver;
    }

    public void setDriver(WebDriver driver) {
        this.driver = driver;
    }

    
    public void setExtentTest(ExtentTest test) {
        this.test = test;
    }

    public ExtentTest getExtentTest() {
        return test;
    }
    
    
    public HomePageMeredith getHomePageMeredith() {
        if (homePageM == null) {
            homePageM = new HomePageMeredith(driver);
        }
        return homePageM;
    }
    
    
    public HomePageRebl getHomePageRebl() {
        if (homePageR == null) {
            homePageR = new HomePageRebl(driver);
        }
        return homePageR;
    }
    
    
    public HomePageOregonBV getHomePageOregonBV() {
        if (homePageO == null) {
        	homePageO = new HomePageOregonBV(driver);
        }
        return homePageO;
    }

}
