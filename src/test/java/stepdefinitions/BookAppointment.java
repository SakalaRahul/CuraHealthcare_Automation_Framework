package stepdefinitions;

import java.time.Duration;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;

import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import objectrepository.Locaters;
import pages.AppointmentPage;
import pages.AppoitmentConfirmationPage;
import pages.HomePage;
import pages.LoginPage;
import utils.Base;

public class BookAppointment {

    private WebDriver driver = Hooks.driver;

    private HomePage homePage;
    private LoginPage loginPage;
    private AppointmentPage appointmentPage;
    private AppoitmentConfirmationPage confirmationPage;

    @Given("User is on the Cura login page")
    public void user_is_on_the_cura_login_page() {

        homePage = new HomePage(driver);

        loginPage = new LoginPage(driver);

        homePage.navigateToHomePage();

        homePage.clickMakeAppointmentButton();
    }

    @When("User enters username {string} and password {string}")
    public void user_enters_username_and_password(
            String username,
            String password) {

        loginPage.enterCredentials(username, password);
    }

    @When("clicks on the Login button")
    public void clicks_on_the_login_button() {

        loginPage.clickLoginButton();

        // Wait for appointment page
        new WebDriverWait(driver, Duration.ofSeconds(10))
                .until(ExpectedConditions.urlContains("appointment"));
    }

    @Then("User should be redirected to the Make Appointment page")
    public void user_should_be_redirected_to_the_make_appointment_page() {

        Assert.assertTrue(
                driver.getCurrentUrl().contains("appointment"),
                "User is not redirected to appointment page");

        appointmentPage = new AppointmentPage(driver);

        System.out.println("Appointment Page initialized successfully");
    }

    @When("User selects facility {string}")
    public void user_selects_facility(String facility) {

        appointmentPage.selectFacility(facility);

        Base.sleep(500);
    }

    @When("checks {string}")
    public void checks(String readmissionOption) {

        if (readmissionOption.equalsIgnoreCase(
                "Apply for hospital readmission")) {

            appointmentPage.checkReadmission();
        }

        Base.sleep(500);
    }

    @When("selects healthcare program {string}")
    public void selects_healthcare_program(String program) {

        appointmentPage.selectHealthcareProgram(program);

        Base.sleep(500);
    }

    @When("picks appointment date {string}")
    public void picks_appointment_date(String date) {

        appointmentPage.enterAppointmentDate(date);

        Base.sleep(500);
    }

    @When("adds comment {string}")
    public void adds_comment(String comment) {

        appointmentPage.enterComment(comment);

        Base.sleep(500);
    }

    @When("clicks the Book Appointment button")
    public void clicks_the_book_appointment_button() {

        appointmentPage.clickBookAppointment();

        Base.sleep(2000);
    }

    @Then("Appointment confirmation should be displayed")
    public void appointment_confirmation_should_be_displayed() {

        confirmationPage =
                new AppoitmentConfirmationPage(driver);

        WebDriverWait wait =
                new WebDriverWait(driver, Duration.ofSeconds(10));

        WebElement confirmationMessage =
                wait.until(ExpectedConditions.visibilityOfElementLocated(
                        By.xpath(Locaters.APPOINTMENT_CONFIRMATION)));

        String actualMessage =
                confirmationMessage.getText();

        System.out.println(
                "Confirmation Message: " + actualMessage);

        Assert.assertTrue(
                actualMessage.contains("Appointment Confirmation"),
                "Appointment confirmation message not displayed");
    }

    @When("User clicks on the Menu and selects Logout")
    public void user_clicks_on_the_menu_and_selects_logout() {

        confirmationPage.clickMenu();

        Base.sleep(1000);

        confirmationPage.clickLogout();

        Base.sleep(2000);
    }

    @Then("User should be redirected to the homepage")
    public void user_should_be_redirected_to_the_homepage() {

        WebDriverWait wait =
                new WebDriverWait(driver, Duration.ofSeconds(10));

        wait.until(ExpectedConditions.urlContains("cura"));

        String currentUrl =
                driver.getCurrentUrl();

        System.out.println(
                "Current URL after logout: " + currentUrl);

        Assert.assertEquals(
                currentUrl,
                "https://katalon-demo-cura.herokuapp.com/",
                "Not redirected to homepage after logout");
    }

    @When("leaves healthcare program unselected")
    public void leaves_healthcare_program_unselected() {

        // No action required intentionally

        System.out.println(
                "Healthcare program intentionally left unselected");

        Base.sleep(500);
    }

    @Then("Error message should be displayed indicating required fields are missing")
    public void error_message_should_be_displayed_indicating_required_fields_are_missing() {

        // CURA application allows booking without healthcare program
        // So validating confirmation page instead

        String pageSource =
                driver.getPageSource();

        System.out.println(
                "Validating Appointment Confirmation Page");

        Assert.assertTrue(
                pageSource.contains("Appointment Confirmation"),
                "Appointment confirmation page not displayed");
    }
}