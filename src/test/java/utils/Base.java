package utils;

import java.util.Properties;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;

public class Base {

	static public WebDriver driver;

	public void launchBrowser() {

		Properties prop = PropertyReader.readProperty();

		if (prop.getProperty("Browser").equalsIgnoreCase("chrome")) {
			ChromeOptions options = new ChromeOptions();

			//  Disable Save Address Popup
			options.addArguments("--disable-popup-blocking");
			options.addArguments("--disable-notifications");

			// Disable Chrome Autofill (for Save Address)
			options.addArguments("--disable-save-password-bubble");
			options.addArguments("--disable-autofill-keyboard-accessory-view");
			options.addArguments("--disable-prompt-on-repost");

			//  Disable Save Passwords Popup
			options.setExperimentalOption("prefs", prefs());
			driver = new ChromeDriver(options);
		} else if (prop.getProperty("Browser").equalsIgnoreCase("firefox")) {
			driver = new FirefoxDriver();
		} else if (prop.getProperty("Browser").equalsIgnoreCase("edge")) {
			driver = new EdgeDriver();
		} else
			System.out.println("Enter only eirther chrome or firefox or edge");
	
		driver.manage().window().maximize();
		driver.get(prop.getProperty("URL"));
		
	
	}
	private static java.util.Map<String, Object> prefs() {
		java.util.Map<String, Object> prefs = new java.util.HashMap<>();
		prefs.put("credentials_enable_service", false);  
		prefs.put("profile.password_manager_enabled", false);  
		prefs.put("autofill.profile_enabled", false);  
		prefs.put("autofill.credit_card_enabled", false); 
		return prefs;
	}

	// Method to pause execution for a specified time
	public static void sleep(int msec) {
		try {
			Thread.sleep(msec);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}
	 public static void tearDown() {
	        if (driver != null) {
	            driver.quit();
	            driver = null;
	        }
	    }

}
