Feature: Cura Healthcare - Appointment Booking

  Scenario Outline: Successful Appointment Booking
    Given User is on the Cura login page
    When User enters username "<username>" and password "<password>"
    And clicks on the Login button
    Then User should be redirected to the Make Appointment page
    When User selects facility "<facility>"
    And checks "<readmission>"
    And selects healthcare program "<program>"
    And picks appointment date "<date>"
    And adds comment "<comment>"
    And clicks the Book Appointment button
    Then Appointment confirmation should be displayed
    And User clicks on the Menu and selects Logout
    Then User should be redirected to the homepage

    Examples: 
      | username   | password           | facility                        | readmission                      | program  | date       | comment             |
      | John Doe   | ThisIsNotAPassword | Tokyo CURA Healthcare Center    | Apply for hospital readmission   | Medicaid | 30/05/2025 | Checkup appointment |
      
  Scenario Outline: Failed Appointment Booking - Missing Required Fields
    Given User is on the Cura login page
    When User enters username "<username>" and password "<password>"
    And clicks on the Login button
    Then User should be redirected to the Make Appointment page
    When User selects facility "<facility>"
    And checks "<readmission>"
    And leaves healthcare program unselected
    And picks appointment date "<date>"
    And clicks the Book Appointment button
    Then Error message should be displayed indicating required fields are missing

    Examples: 
      | username   | password           | facility                     | readmission                      | date       |
      | John Doe   | ThisIsNotAPassword | Tokyo CURA Healthcare Center | Apply for hospital readmission   | 30/05/2025 |
      