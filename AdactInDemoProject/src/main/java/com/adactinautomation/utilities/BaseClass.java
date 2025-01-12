package com.adactinautomation.utilities;

import java.time.Duration;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;

import io.github.bonigarcia.wdm.WebDriverManager;

public class BaseClass {
	public static WebDriver driver;
	static long timeOut;
	static WebDriverWait wait;
	static long maxWaitTime;

	public static WebDriver setUp() {

		String browser = ConfigReader.getProperty("browser").toLowerCase();
		String testUrl = ConfigReader.getProperty("testUrl");
		timeOut = Long.parseLong(ConfigReader.getProperty("timeOut"));
		maxWaitTime = Long.parseLong(ConfigReader.getProperty("maxWaitTime"));

		switch (browser) {
		case "chrome":
			WebDriverManager.chromedriver().setup();
			driver = new ChromeDriver();
			break;
		case "firefox":
			WebDriverManager.firefoxdriver().setup();
			driver = new FirefoxDriver();
			break;
		case "edge":
			WebDriverManager.edgedriver().setup();
			driver = new EdgeDriver();
			break;

		default:
			System.out.println("Driver is not defined");
			break;
		}
		if (driver == null) {
			throw new RuntimeException("Failed to initialize Webdriver..");
		}
		driver.manage().window().maximize();
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(timeOut));
		driver.get(testUrl);
		wait = new WebDriverWait(driver, Duration.ofSeconds(maxWaitTime));
		return driver;
	}

	public static void tearDown() {
		if (driver != null) {
			driver.quit();
			driver = null;
			System.out.println("Teardown successful");
		}
	}

	public static void validateErrorMessage(String actualMessage, String expectedMessage) {
		try {
			Assert.assertEquals(actualMessage, expectedMessage, "Error message mismatch.");
			ExtentReportListener.logStepPass("Expected error message displayed: " + expectedMessage);
		} catch (AssertionError e) {
			ExtentReportListener.logStepFail(
					"Error message mismatch. Expected: " + expectedMessage + ", but found: " + actualMessage);
			throw e; // Re-throw the exception to ensure the test case still fails.
		}
	}

	public static void validatePageTitle(String actualTitle, String expectedTitle) {
		try {
			Assert.assertEquals(actualTitle, expectedTitle, "Page title mismatch.");
			ExtentReportListener.logStepPass("Page title validated. " + "<br>Navigated to: " + expectedTitle);
		} catch (AssertionError e) {
			ExtentReportListener.logStepFail(
					"Page title validation failed. Expected: " + expectedTitle + ", but got: " + actualTitle);
			throw e;
		}
	}
}
