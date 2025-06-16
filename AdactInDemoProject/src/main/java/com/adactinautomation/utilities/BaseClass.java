package com.adactinautomation.utilities;

import java.time.Duration;

import org.apache.logging.log4j.Logger;
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

	private static final Logger log = LoggerHelper.getLogger(BaseClass.class);

	public static WebDriver setUp() {
		String browser = ConfigReader.getProperty("browser").toLowerCase();
		String testUrl = ConfigReader.getProperty("testUrl");
		timeOut = Long.parseLong(ConfigReader.getProperty("timeOut"));
		maxWaitTime = Long.parseLong(ConfigReader.getProperty("maxWaitTime"));

		log.info("Browser selected from config: " + browser);

		switch (browser) {
		case "chrome":
			WebDriverManager.chromedriver().setup();
			driver = new ChromeDriver();
			log.info("ChromeDriver initialized.");
			break;
		case "firefox":
			WebDriverManager.firefoxdriver().setup();
			driver = new FirefoxDriver();
			log.info("FirefoxDriver initialized.");
			break;
		case "edge":
			WebDriverManager.edgedriver().setup();
			driver = new EdgeDriver();
			log.info("EdgeDriver initialized.");
			break;
		default:
			log.error("Unsupported browser specified in config.");
			throw new RuntimeException("Unsupported browser.");
		}

		if (driver == null) {
			log.fatal("WebDriver is null after initialization.");
			throw new RuntimeException("Failed to initialize WebDriver.");
		}

		driver.manage().window().maximize();
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(timeOut));
		driver.get(testUrl);

		log.info("Application launched at URL: " + testUrl);

		wait = new WebDriverWait(driver, Duration.ofSeconds(maxWaitTime));
		return driver;
	}

	public static void tearDown() {
		if (driver != null) {
			driver.quit();
			driver = null;
			log.info("Browser closed and driver instance set to null.");
		}
	}

	public static void validateErrorMessage(String actualMessage, String expectedMessage) {
		try {
			Assert.assertEquals(actualMessage, expectedMessage, "Error message mismatch.");
			log.info("Error message matched: " + expectedMessage);
			ExtentReportListener.logStepPass("Expected error message displayed: " + expectedMessage);
		} catch (AssertionError e) {
			log.error("Error message mismatch. Expected: " + expectedMessage + ", Found: " + actualMessage);
			ExtentReportListener.logStepFail(
					"Error message mismatch. Expected: " + expectedMessage + ", but found: " + actualMessage);
			throw e;
		}
	}

	public static void validatePageTitle(String actualTitle, String expectedTitle) {
		try {
			Assert.assertEquals(actualTitle, expectedTitle, "Page title mismatch.");
			log.info("Page title validated successfully: " + expectedTitle);
			ExtentReportListener.logStepPass("Page title validated. <br>Navigated to: " + expectedTitle);
		} catch (AssertionError e) {
			log.error("Page title mismatch. Expected: " + expectedTitle + ", Found: " + actualTitle);
			ExtentReportListener.logStepFail(
					"Page title validation failed. Expected: " + expectedTitle + ", but got: " + actualTitle);
			throw e;
		}
	}
}
