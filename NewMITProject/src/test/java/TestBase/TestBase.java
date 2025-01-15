package TestBase;

import Utils.ErrorScreenCapture;
import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;
import org.apache.commons.io.FileUtils;
import org.openqa.selenium.By;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.ITestResult;
import org.testng.annotations.*;

import java.io.File;
import java.text.SimpleDateFormat;
import java.util.Date;

public class TestBase {
    protected WebDriver driver;
    protected static ExtentReports extent;
    protected static ExtentTest test;
    private String reportFileName;

   /* @BeforeSuite
    public void setUp() {
        // Create a unique file name for the Extent Report
        String timestamp = new SimpleDateFormat("yyyyMMdd_HHmmss").format(new Date());
        reportFileName = "MIT_ProjectQE" + timestamp + ".html"; // Default report name

        // Set up ExtentSparkReporter
        ExtentSparkReporter sparkReporter = new ExtentSparkReporter("./reports/" + reportFileName);
        extent = new ExtentReports();
        extent.attachReporter(sparkReporter);
    }*/

    @BeforeSuite(alwaysRun = true)
    public static ExtentReports getExtentInstance() {
        if (extent == null) {
            String timestamp = new SimpleDateFormat("yyyyMMdd_HHmmss").format(new Date());
            String reportFileName = "ExtentReport_Global_" + timestamp + ".html";

            // Initialize ExtentSparkReporter
            ExtentSparkReporter sparkReporter = new ExtentSparkReporter("./reports/" + reportFileName);
            sparkReporter.config().setReportName("Automation Test Report");
            sparkReporter.config().setDocumentTitle("Test Execution Report");

            // Initialize ExtentReports
            extent = new ExtentReports();
            extent.attachReporter(sparkReporter);

            // Add system/environment info
            extent.setSystemInfo("Tester", "Your Name");
            extent.setSystemInfo("Browser", "Chrome");
            extent.setSystemInfo("OS", System.getProperty("os.name"));
        }
        return extent;
    }

    /**
     * Set a custom name for the Extent Report.
     * @param customName The custom name for the report.
     */
    public void setReportName(String customName) {
        String timestamp = new SimpleDateFormat("yyyyMMdd_HHmmss").format(new Date());
        reportFileName = customName + "_" + timestamp + ".html";

        // Reinitialize ExtentSparkReporter with the new name
        ExtentSparkReporter sparkReporter = new ExtentSparkReporter("./reports/" + reportFileName);
        extent = new ExtentReports();
        extent.attachReporter(sparkReporter);
    }

    /**
     * Start a test in Extent Report with a given name.
     *
     * @param testName Name of the test case.
     */
    public void startTest(String testName) {
        test = extent.createTest(testName);
    }

    @BeforeClass
    public void setUpBrowser() {

        driver = new ChromeDriver();
        driver.manage().window().maximize();
        driver.get("https://www.saucedemo.com/");
    }

    @AfterMethod
    public void captureScreenshotOnFailure(ITestResult result) {
        if (ITestResult.FAILURE == result.getStatus()) {
            ErrorScreenCapture.getScreenshort(driver, result.getName());
        }
    }

    @AfterClass
    public void tearDown() {
        if (driver != null) {
            driver.close();
        }
    }
/*
    @AfterSuite
    public void tearDownReport() {
        extent.flush();

    }
    */

    public void sleep(int timeOutInSeconds) {
        try {
            Thread.sleep((long)(timeOutInSeconds * 1000));
        } catch (InterruptedException var3) {
            var3.printStackTrace();
        }

    }

    @AfterMethod
    public void takeScreenshot(ITestResult result) {
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

    @BeforeMethod
    public void createTestReport(ITestResult result) {
        // Initialize a method-level test in Extent Report
        String testName = result.getMethod().getMethodName();
        String timestamp = new SimpleDateFormat("yyyyMMdd_HHmmss").format(new Date());
        String reportFileName = "ExtentReport_" + testName + "_" + timestamp + ".html";

        ExtentSparkReporter methodSparkReporter = new ExtentSparkReporter("./reports/" + reportFileName);
        methodSparkReporter.config().setReportName("Automation Test Report - " + testName);
        methodSparkReporter.config().setDocumentTitle("Test Execution Report - " + testName);

        extent.attachReporter(methodSparkReporter);
    }

    @AfterSuite
    public void tearDownReport() {
        extent.flush();
    }
}
