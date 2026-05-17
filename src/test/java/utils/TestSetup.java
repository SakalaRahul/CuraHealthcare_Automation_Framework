package utils;
import org.openqa.selenium.WebDriver;

import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.testng.annotations.BeforeSuite;

public class TestSetup {
    public static WebDriver driver;
    
    @BeforeSuite
    public static void initDriver() {
        ChromeOptions options = new ChromeOptions();
        options.addArguments("--disable-password-manager-ui");
        driver = new ChromeDriver(options);
    }
}