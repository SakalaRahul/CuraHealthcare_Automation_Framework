package pages;

import java.time.Duration;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;

import objectrepository.Locaters;
import utils.Base;

public class AppointmentPage {

    private WebDriver driver;
    private WebDriverWait wait;

    @FindBy(id = Locaters.FACILITY_DROPDOWN)
    private WebElement facilityDropdown;

    @FindBy(id = Locaters.READMISSION_CHECKBOX)
    private WebElement readmissionCheckbox;

    @FindBy(id = Locaters.VISIT_DATE_FIELD)
    private WebElement visitDateField;

    @FindBy(id = Locaters.COMMENT_FIELD)
    private WebElement commentField;

    @FindBy(id = Locaters.BOOK_APPOINTMENT_BUTTON)
    private WebElement bookAppointmentButton;

    public AppointmentPage(WebDriver driver) {

        this.driver = driver;
        this.wait = new WebDriverWait(driver, Duration.ofSeconds(10));

        PageFactory.initElements(driver, this);
    }

    public String getPageTitle() {
        return driver.getTitle();
    }

    // Select Facility
    public void selectFacility(String facility) {

        wait.until(ExpectedConditions.visibilityOf(facilityDropdown));

        Select select = new Select(facilityDropdown);

        select.selectByVisibleText(facility);

        String selectedFacility =
                select.getFirstSelectedOption().getText();

        Assert.assertEquals(
                selectedFacility,
                facility,
                "Facility selection failed");

        System.out.println("Selected Facility: " + selectedFacility);

        Base.sleep(1000);
    }

    // Check Readmission Checkbox
    public void checkReadmission() {

        wait.until(ExpectedConditions.elementToBeClickable(readmissionCheckbox));

        if (!readmissionCheckbox.isSelected()) {

            readmissionCheckbox.click();
        }

        Assert.assertTrue(
                readmissionCheckbox.isSelected(),
                "Readmission checkbox not selected");

        System.out.println("Readmission checkbox selected");

        Base.sleep(500);
    }

    // Select Healthcare Program
    public void selectHealthcareProgram(String program) {

        String cssSelector =
                String.format(Locaters.HEALTHCARE_PROGRAM, program);

        WebElement healthcareProgram =
                wait.until(ExpectedConditions.elementToBeClickable(
                        By.cssSelector(cssSelector)));

        healthcareProgram.click();

        Assert.assertTrue(
                healthcareProgram.isSelected(),
                "Healthcare program selection failed");

        System.out.println("Selected Healthcare Program: " + program);

        Base.sleep(500);
    }

    // Enter Appointment Date
    public void enterAppointmentDate(String date) {

        wait.until(ExpectedConditions.elementToBeClickable(visitDateField));

        visitDateField.clear();

        visitDateField.sendKeys(date);

        String enteredDate =
                visitDateField.getAttribute("value");

        Assert.assertEquals(
                enteredDate,
                date,
                "Date entry failed");

        System.out.println("Appointment Date Entered: " + enteredDate);

        Base.sleep(1000);
    }

    // Select Date From Calendar
    public void selectDateFromPicker(
            String day,
            String month,
            String year) {

        wait.until(ExpectedConditions.elementToBeClickable(visitDateField))
                .click();

        Base.sleep(1000);

        // Select Month
        Select monthDropdown =
                new Select(driver.findElement(
                        By.className("ui-datepicker-month")));

        monthDropdown.selectByVisibleText(month);

        // Select Year
        Select yearDropdown =
                new Select(driver.findElement(
                        By.className("ui-datepicker-year")));

        yearDropdown.selectByVisibleText(year);

        // Select Day
        String dayXpath =
                "//a[text()='" + day +
                "' and not(contains(@class,'ui-state-disabled'))]";

        wait.until(ExpectedConditions.elementToBeClickable(
                By.xpath(dayXpath))).click();

        String selectedDate =
                visitDateField.getAttribute("value");

        Assert.assertFalse(
                selectedDate.isEmpty(),
                "Date selection failed");

        System.out.println("Selected Date: " + selectedDate);

        Base.sleep(1000);
    }

    // Enter Comment
    public void enterComment(String comment) {

        wait.until(ExpectedConditions.elementToBeClickable(commentField));

        commentField.clear();

        commentField.sendKeys(comment);

        Assert.assertEquals(
                commentField.getAttribute("value"),
                comment,
                "Comment entry failed");

        System.out.println("Comment Added");

        Base.sleep(500);
    }

    // Click Book Appointment
    public void clickBookAppointment() {

        wait.until(ExpectedConditions.elementToBeClickable(
                bookAppointmentButton)).click();

        System.out.println("Clicked Book Appointment Button");

        Base.sleep(2000);
    }

    // Verify Appointment Confirmation
    public boolean isAppointmentConfirmed() {

        return driver.getPageSource()
                .contains("Appointment Confirmation");
    }
}