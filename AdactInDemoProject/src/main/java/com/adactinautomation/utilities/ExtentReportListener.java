package com.adactinautomation.utilities;

import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;

import org.apache.commons.io.FileUtils;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebDriverException;
import org.testng.ITestContext;
import org.testng.ITestListener;
import org.testng.ITestResult;

import com.aventstack.extentreports.*;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;
import com.aventstack.extentreports.reporter.configuration.Theme;

public class ExtentReportListener implements ITestListener {

	private static ExtentReports extent;
	private static ThreadLocal<ExtentTest> test = new ThreadLocal<>();
	private static WebDriver driver;
	private static final Logger log = LoggerHelper.getLogger(ExtentReportListener.class);

	@Override
	public void onStart(ITestContext context) {
		ExtentSparkReporter spark = new ExtentSparkReporter("test-output/ExtentReport.html");
		spark.config().setDocumentTitle("Adactin Hotel Automation Framework Report");
		spark.config().setReportName("Adactin Automation Suite");
		spark.config().setTheme(Theme.STANDARD);

		extent = new ExtentReports();
		extent.attachReporter(spark);
		extent.setSystemInfo("OS", System.getProperty("os.name"));
		extent.setSystemInfo("Environment", "QA");
		extent.setSystemInfo("User", System.getProperty("user.name"));
		
		log.info("Extent report initialized.");
	}

	@Override
	public void onTestStart(ITestResult result) {
		ExtentTest extentTest = extent.createTest(result.getMethod().getMethodName());
		test.set(extentTest);
		log.info("Test started: " + result.getMethod().getMethodName());
	}

	public static void logStep(String message) {
		test.get().log(Status.INFO, message);
		log.info(message);
	}

	public static void logStepPass(String message) {
		test.get().log(Status.PASS, message);
		log.info("PASS: " + message);
	}

	public static void logStepFail(String message) {
		test.get().log(Status.FAIL, message);
		log.error("FAIL: " + message);
	}

	@Override
	public void onTestSuccess(ITestResult result) {
		test.get().log(Status.PASS, "Test Passed: " + result.getMethod().getMethodName());
		log.info("Test passed: " + result.getMethod().getMethodName());
	}

	@Override
	public void onTestFailure(ITestResult result) {
		String methodName = result.getMethod().getMethodName();
		Throwable error = result.getThrowable();
		test.get().log(Status.FAIL, "Test Failed: " + methodName + " - " + error);
		log.error("Test failed: " + methodName, error);
		takeScreenshot(methodName);
	}

	@Override
	public void onTestSkipped(ITestResult result) {
		String methodName = result.getMethod().getMethodName();
		test.get().log(Status.SKIP, "Test Skipped: " + methodName + " - " + result.getThrowable());
		log.warn("Test skipped: " + methodName);
	}

	@Override
	public void onFinish(ITestContext context) {
		if (extent != null) {
			extent.flush();
			log.info("Extent report flushed successfully.");
		}
	}

	public static void setDriver(WebDriver webDriver) {
		driver = webDriver;
		log.debug("Driver set in ExtentReportListener.");
	}

	private void takeScreenshot(String methodName) {
		if (driver != null) {
			try {
				String timestamp = new SimpleDateFormat("yyyyMMdd_HHmmss").format(new Date());
				String screenshotPath = System.getProperty("user.dir") + "/target/Screenshots/" + methodName + "_"
						+ timestamp + ".png";
				File srcFile = ((TakesScreenshot) driver).getScreenshotAs(OutputType.FILE);
				FileUtils.copyFile(srcFile, new File(screenshotPath));
				test.get().addScreenCaptureFromPath(screenshotPath, "Screenshot on Failure");
				log.info("Screenshot captured for failure: " + screenshotPath);
			} catch (WebDriverException | IOException e) {
				test.get().log(Status.FAIL, "Failed to capture screenshot: " + e.getMessage());
				log.error("Screenshot capture failed: " + e.getMessage());
			}
		} else {
			test.get().log(Status.WARNING, "WebDriver is not initialized. Screenshot not taken.");
			log.warn("WebDriver not available for screenshot.");
		}
	}
}
