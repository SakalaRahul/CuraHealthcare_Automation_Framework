# Cura Healthcare Automation Framework

## Project Overview
This project is an automated testing framework developed for the demo healthcare website:
https://katalon-demo-cura.herokuapp.com/

The framework automates important user workflows such as login, appointment booking, validation checks, and logout functionality using Selenium WebDriver with Java.

The project follows the Page Object Model (POM) design pattern and Behavior Driven Development (BDD) approach using Cucumber for better maintainability, scalability, and readability.

---

# Technologies & Tools Used

- Java
- Selenium WebDriver
- Cucumber BDD
- TestNG
- Maven
- Page Object Model (POM)
- Git & GitHub
- Eclipse IDE

---

# Framework Features

✔ BDD Framework using Cucumber  
✔ Page Object Model Design Pattern  
✔ Data-driven test execution support  
✔ Explicit Wait implementation  
✔ Assertion validations using TestNG  
✔ Modular and reusable code structure  
✔ Automated browser execution  
✔ Positive and Negative test scenarios  
✔ Maven dependency management  
✔ GitHub version control integration  

---

# Project Structure


src/test/java
│
├── stepdefinitions
├── pages
├── runners
├── utils
├── hooks
└── objectrepository

src/test/resources

Automated Test Scenarios
Positive Scenario
Successful Login
Appointment Booking
Facility Selection
Healthcare Program Selection
Date Selection
Appointment Confirmation
Logout Validation
Negative Scenario
Appointment Booking with Missing Required Fields
Validation/Error Handling
Design Patterns Used
Page Object Model (POM)

Each webpage is designed as a separate Java class containing:

WebElements
Locators
Reusable methods

This improves:

Code reusability
Maintainability
Readability
Synchronization Techniques

Implemented:

Explicit Waits
ExpectedConditions
Element Visibility Checks
Clickability Checks

To avoid:

Synchronization issues
ElementNotInteractableException
NoSuchElementException
Key Selenium Concepts Used
WebDriver
WebElement
PageFactory
Select Class
XPath & CSS Selectors
Assertions
Waits
Dropdown Handling
Checkbox Handling
Form Validation
How to Run the Project
Prerequisites
Java JDK 8+
Maven
Eclipse IDE
Chrome Browser
│
├── features
└── config
