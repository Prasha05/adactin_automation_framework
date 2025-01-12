package com.adactinautomation.utilities;

import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebDriverException;
import org.testng.ITestContext;
import org.testng.ITestListener;
import org.testng.ITestResult;
import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.Status;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;
import com.aventstack.extentreports.reporter.configuration.Theme;

public class ExtentReportListener implements ITestListener {

	private static ExtentReports extent;
	private static ThreadLocal<ExtentTest> test = new ThreadLocal<>();
	private static WebDriver driver;

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
	}

	@Override
	public void onTestStart(ITestResult result) {
		ExtentTest extentTest = extent.createTest(result.getMethod().getMethodName());
		test.set(extentTest);
	}

	public static void logStep(String message) {
		test.get().log(Status.INFO, message);
	}

	public static void logStepPass(String message) {
		test.get().log(Status.PASS, message);
	}

	public static void logStepFail(String message) {
		test.get().log(Status.FAIL, message);
	}

	@Override
	public void onTestSuccess(ITestResult result) {
		test.get().log(Status.PASS, "Test Passed: " + result.getMethod().getMethodName());
	}

	@Override
	public void onTestFailure(ITestResult result) {
		test.get().log(Status.FAIL,
				"Test Failed: " + result.getMethod().getMethodName() + " - " + result.getThrowable());
		takeScreenshot(result.getMethod().getMethodName());
	}

	@Override
	public void onTestSkipped(ITestResult result) {
		test.get().log(Status.SKIP,
				"Test Skipped: " + result.getMethod().getMethodName() + " - " + result.getThrowable());
	}

	@Override
	public void onFinish(ITestContext context) {
		if (extent != null) {
			extent.flush();
		}
	}

	public static void setDriver(WebDriver webDriver) {
		driver = webDriver;
	}

	private void takeScreenshot(String methodName) {
		if (driver != null) {
			try {
				String timestamp = new SimpleDateFormat("yyyyMMdd_HHmmss").format(new Date());
				String screenshotPath = System.getProperty("user.dir") + "\\target\\Screenshots\\" + methodName + "_"
						+ timestamp + ".png";
				File srcFile = ((TakesScreenshot) driver).getScreenshotAs(OutputType.FILE);
				FileUtils.copyFile(srcFile, new File(screenshotPath));
				test.get().addScreenCaptureFromPath(screenshotPath, "Screenshot on Failure");
			} catch (WebDriverException | IOException e) {
				test.get().log(Status.FAIL, "Failed to capture screenshot: " + e.getMessage());
			}
		} else {
			test.get().log(Status.WARNING, "WebDriver is not initialized. Screenshot not taken.");
		}
	}
}
