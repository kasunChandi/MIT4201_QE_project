package Utils;

import com.aventstack.extentreports.ExtentTest;
import org.apache.commons.io.FileUtils;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.testng.ITestResult;
import org.testng.annotations.AfterMethod;

import java.io.File;
import java.text.SimpleDateFormat;
import java.util.Date;

public class takeScreenShort {
    protected WebDriver driver;
    private ExtentTest test;

    public void takeScreenshotResult(ITestResult result) {
        try {
            File screenshotDir = new File("screenshots");
            if (!screenshotDir.exists()) {
                boolean dirCreated = screenshotDir.mkdirs();
                System.out.println("Screenshots directory created: " + dirCreated);
            }

            String timestamp = new SimpleDateFormat("yyyyMMdd_HHmmss").format(new Date());
            String testName = result.getName();
            File source = ((TakesScreenshot) driver).getScreenshotAs(OutputType.FILE);
            String dest = ".screenshots/" + testName + "_" + timestamp + ".png";
            FileUtils.copyFile(source, new File(dest));

            // Add screenshot and status to Extent Report
            if (result.getStatus() == ITestResult.FAILURE) {
                test.fail("Test failed: " + result.getThrowable())
                        .addScreenCaptureFromPath(dest);
            } else if (result.getStatus() == ITestResult.SUCCESS) {
                test.pass("Test passed")
                        .addScreenCaptureFromPath(dest);
            } else if (result.getStatus() == ITestResult.SKIP) {
                test.skip("Test skipped: " + result.getThrowable())
                        .addScreenCaptureFromPath(dest);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
