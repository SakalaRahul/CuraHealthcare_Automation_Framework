package pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import objectrepository.Locaters;

public class HomePage {
    private WebDriver driver;

    @FindBy(id = Locaters.MAKE_APPOINTMENT_BUTTON)
    private WebElement makeAppointmentBtn;

    @FindBy(id = Locaters.MENU_TOGGLE)
    private WebElement menuToggle;

    @FindBy(xpath = Locaters.LOGOUT_LINK)
    private WebElement logoutLink;

    public HomePage(WebDriver driver) {
        this.driver = driver;
        PageFactory.initElements(driver, this);
    }

    public void navigateToHomePage() {
        driver.get("https://katalon-demo-cura.herokuapp.com/");
    }

    public void clickMakeAppointmentButton() {
        makeAppointmentBtn.click();
    }

    public void clickMenu() {
        menuToggle.click();
    }

    public void clickLogout() {
        logoutLink.click();
    }
}