package runner;

import io.cucumber.testng.AbstractTestNGCucumberTests;
import io.cucumber.testng.CucumberOptions;

@CucumberOptions(
		features = "src/test/resources/features/AppointmentBooking.feature", 
  glue = {"stepdefinitions"}, 
  plugin = {
    "pretty",
    "html:reports/HTMLReports.html",
	"json:reports/json-report.json",
	"junit:reports/junit_report.xml",
	"io.qameta.allure.cucumber7jvm.AllureCucumber7Jvm"
  }
  
		  

)
public class TestRunner extends AbstractTestNGCucumberTests {
}
