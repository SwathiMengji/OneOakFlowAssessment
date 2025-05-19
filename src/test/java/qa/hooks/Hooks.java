package qa.hooks;

import java.io.File;

import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;

import io.cucumber.java.After;
import io.cucumber.java.Before;
import io.cucumber.java.Scenario;
import io.cucumber.java.BeforeAll;
import io.cucumber.java.AfterAll;
import qa.base.Base;
import qa.base.TestContext;

public class Hooks {
	
	private TestContext context;
	private static ExtentReports extent;
    private static ExtentTest test;

    public Hooks(TestContext context) {
        this.context = context;
    }

    
    // Initialize ExtentReports once for all scenarios
    @BeforeAll
    public static void beforeAll() {
    	File reportsDir = new File("Reports");
        if (!reportsDir.exists()) {
            reportsDir.mkdirs();
        }
        ExtentSparkReporter spark = new ExtentSparkReporter("Reports/ExtentReport.html");
        extent = new ExtentReports();
        extent.attachReporter(spark);
    }
    
    @Before
    public void setUp(Scenario scenario) {
        WebDriver driver = Base.launchBrowser(); // your method
        context.setDriver(driver);
        
        String scenarioName = scenario.getName().replaceAll("[\\\\/:*?\"<>| ]", "_");
        ExtentTest test = extent.createTest(scenarioName);
        context.setExtentTest(test);
    }

    @After
    public void tearDown(Scenario scenario) {
    	WebDriver driver = context.getDriver();
    	
    	String featureName = scenario.getUri().getPath()
                .replaceAll(".*/", "")
                .replace(".feature", "")
                .replaceAll("[\\\\/:*?\"<>|]", "_");  // sanitize

		String scenarioName = scenario.getName()
		                .replaceAll(" ", "_")
		                .replaceAll("[\\\\/:*?\"<>|]", "_");  // sanitize
		
		String timestamp = String.valueOf(System.currentTimeMillis());
		String screenshotName = featureName + "_" + scenarioName + "_" + timestamp;
		
		ExtentTest test = context.getExtentTest();
		
        if (scenario.isFailed()) {
            
        	// Unique name with timestamp to avoid overwrite
            //Base.takeScreenshotWhenFail(driver, screenshotName);
        	
        	byte[] screenshot = ((TakesScreenshot) driver).getScreenshotAs(OutputType.BYTES);
            scenario.attach(screenshot, "image/png", "Failure Screenshot"+scenarioName);
            
            // Attach screenshot to ExtentReports
            String base64Screenshot = ((TakesScreenshot) driver).getScreenshotAs(OutputType.BASE64);
            test.fail("Scenario Failed").addScreenCaptureFromBase64String(base64Screenshot,"Failure Screenshot: " + scenarioName);
        }
        else {
        	test.pass("Scenario Passed");
        }
    	
        driver.quit();
    }
    
    @AfterAll
    public static void afterAll() {
        if (extent != null) {
            extent.flush();
        }
    }
}
