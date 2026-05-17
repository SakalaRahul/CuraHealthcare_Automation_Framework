package stepdefinitions;

import java.io.ByteArrayInputStream;
import java.util.HashMap;
import java.util.Map;

import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;

import io.cucumber.java.After;
import io.cucumber.java.AfterAll;
import io.cucumber.java.AfterStep;
import io.cucumber.java.Before;
import io.cucumber.java.BeforeAll;
import io.cucumber.java.Scenario;
import io.qameta.allure.Allure;
import utils.Base;
import utils.Reporter;

public class Hooks extends Base {

    static ExtentSparkReporter spark;
    static ExtentReports extent;
    static ExtentTest test;

    @BeforeAll
    public static void setUpReports() {
        spark = new ExtentSparkReporter(System.getProperty("user.dir") + "/reports/ExtentReport.html");
        extent = new ExtentReports();
        extent.attachReporter(spark);
    }

    @AfterAll
    public static void afterAll() {
        extent.flush();
    }

    @Before
    public void before(Scenario scenario) {
        test = extent.createTest(scenario.getName()); 
        System.out.println("Launching the browser...");
        
        // Configure Chrome options to disable password manager warnings
        ChromeOptions options = new ChromeOptions();
        Map<String, Object> prefs = new HashMap<>();
        prefs.put("credentials_enable_service", false);
        prefs.put("profile.password_manager_enabled", false);
        prefs.put("profile.password_manager_leak_detection", false);
        options.setExperimentalOption("prefs", prefs);
        options.addArguments(
            "--disable-blink-features=PasswordLeakDetection",
            "--disable-popup-blocking",
            "--disable-infobars"
        );
        
        // Initialize driver with these options
        driver = new ChromeDriver(options);
        driver.manage().window().maximize();
    }

    @After
    public void after(Scenario scenario) {
        if (scenario.isFailed()) {
            // Capture screenshot and attach to Allure report
            byte[] screenshot = ((TakesScreenshot) driver).getScreenshotAs(OutputType.BYTES);
            Allure.addAttachment("Failed Scenario Screenshot", new ByteArrayInputStream(screenshot));
            Reporter.generateReport(driver, test, com.aventstack.extentreports.Status.FAIL, 
                "Scenario Failed: " + scenario.getName());
        } else {
            Reporter.generateReport(driver, test, com.aventstack.extentreports.Status.PASS, 
                "Scenario Passed: " + scenario.getName());
        }

        // Add brief delay to ensure all operations complete
        try {
            Thread.sleep(2000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        // Close the browser
        if (driver != null) {
            System.out.println("Closing the browser...");
            driver.quit();
        }
    }

    @AfterStep
    public void afterStep(Scenario scenario) {
        if (scenario.isFailed()) {
            byte[] screenshot = ((TakesScreenshot) driver).getScreenshotAs(OutputType.BYTES);
            Allure.addAttachment("Failed Step Screenshot", new ByteArrayInputStream(screenshot));
            test.log(com.aventstack.extentreports.Status.FAIL, "Step Failed: " + scenario.getName());
        } else {
            test.log(com.aventstack.extentreports.Status.PASS, "Step Passed: " + scenario.getName());
        }
    }
}