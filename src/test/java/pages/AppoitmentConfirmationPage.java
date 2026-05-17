package pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import objectrepository.Locaters;

public class AppoitmentConfirmationPage {
    private WebDriver driver;

    @FindBy(xpath = Locaters.APPOINTMENT_CONFIRMATION)
    private WebElement appointmentConfirmation;

    @FindBy(id = Locaters.CONFIRMATION_DETAILS)
    private WebElement confirmationDetails;

    @FindBy(id = Locaters.MENU_TOGGLE)
    private WebElement menuToggle;

    @FindBy(xpath = Locaters.LOGOUT_LINK)
    private WebElement logoutLink;

    public AppoitmentConfirmationPage(WebDriver driver) {
        this.driver = driver;
        PageFactory.initElements(driver, this);
    }

    public String getConfirmationMessage() {
        return appointmentConfirmation.getText();
    }

    public String getConfirmationDetails() {
        return confirmationDetails.getText();
    }

    public void clickMenu() {
        menuToggle.click();
    }

    public void clickLogout() {
        logoutLink.click();
    }
}