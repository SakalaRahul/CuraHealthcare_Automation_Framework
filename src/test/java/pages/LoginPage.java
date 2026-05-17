package pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import io.cucumber.java.Before;
import objectrepository.Locaters;

public class LoginPage {
    private WebDriver driver;

    @FindBy(id = Locaters.USERNAME_FIELD)
    private WebElement usernameField;

    @FindBy(id = Locaters.PASSWORD_FIELD)
    private WebElement passwordField;

    @FindBy(id = Locaters.LOGIN_BUTTON)
    private WebElement loginButton;

    public LoginPage(WebDriver driver) {
        this.driver = driver;
        PageFactory.initElements(driver, this);
    }
    public void login(String username, String password) {
        usernameField.clear();
        usernameField.sendKeys(username);
        passwordField.clear();
        passwordField.sendKeys(password);
        loginButton.click();
    }

    public void enterCredentials(String username, String password) {
        usernameField.sendKeys(username);
        passwordField.sendKeys(password);
    }

    public void clickLoginButton() {
        loginButton.click();
    }
    @Before
    public void disablePasswordLeakCheck() {
        ChromeOptions options = new ChromeOptions();
        options.addArguments("--disable-features=PasswordLeakDetection");
        driver = new ChromeDriver(options); 
    }
}