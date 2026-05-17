package objectrepository;

import org.openqa.selenium.By;

public class Locaters {
    // Login Page
    public static final String USERNAME_FIELD = "txt-username";
    public static final String PASSWORD_FIELD = "txt-password";
    public static final String LOGIN_BUTTON = "btn-login";
    
    // Home Page
    public static final String MAKE_APPOINTMENT_BUTTON = "btn-make-appointment";
    public static final String MENU_TOGGLE = "menu-toggle";
    public static final String LOGOUT_LINK = "//a[contains(text(),'Logout')]";
    
    // Appointment Page
   
    public static final String READMISSION_CHECKBOX = "chk_hospotal_readmission";
    public static final String HEALTHCARE_PROGRAM = "input[name='programs'][value='%s']";
    public static final String VISIT_DATE_FIELD = "txt_visit_date";
    public static final String COMMENT_FIELD = "txt_comment";
    public static final String BOOK_APPOINTMENT_BUTTON = "btn-book-appointment";
    public static final String APPOINTMENT_ERROR = ".alert-danger";
    public static final String FACILITY_DROPDOWN = "combo_facility";
  
    
    // Confirmation Page
    public static final String APPOINTMENT_CONFIRMATION = "//h2[contains(text(),'Appointment Confirmation')]";
    public static final String CONFIRMATION_DETAILS = "summary";
}