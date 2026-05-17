package utils;

import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;

import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.MediaEntityBuilder;
import com.aventstack.extentreports.Status;

public class Reporter {

    public static void generateReport(WebDriver driver, ExtentTest test, Status status, String stepMessage) {
        if (status.equals(Status.PASS)) {
            test.log(status, stepMessage);
        } else if (status.equals(Status.FAIL)) {
            String screenshotPath = captureScreenshot(driver, stepMessage);
            test.log(status, stepMessage, MediaEntityBuilder.createScreenCaptureFromPath(screenshotPath).build());
        }
    }

    public static String captureScreenshot(WebDriver driver, String stepName) {
        String userDir = System.getProperty("user.dir");
        String timeStamp = new SimpleDateFormat("dd-MM-yyyy_HH-mm-ss").format(new Date());
        String filePath = userDir + "/screenshots/" + stepName.replaceAll("[^a-zA-Z0-9]", "_") + "_" + timeStamp + ".png";

        File srcFile = ((TakesScreenshot) driver).getScreenshotAs(OutputType.FILE);
        File destFile = new File(filePath);

        try {
            FileUtils.copyFile(srcFile, destFile);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return filePath;
    }
}
