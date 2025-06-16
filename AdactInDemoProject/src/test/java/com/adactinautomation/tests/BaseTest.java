package com.adactinautomation.tests;

import java.lang.reflect.Method;

import org.apache.logging.log4j.Logger;
import org.openqa.selenium.WebDriver;
import org.testng.annotations.*;

import com.adactinautomation.utilities.BaseClass;
import com.adactinautomation.utilities.ConfigReader;
import com.adactinautomation.utilities.ExcelUtils;
import com.adactinautomation.utilities.LoggerHelper;
import com.adactinautomation.utilities.PageObjectManager;

public class BaseTest extends BaseClass {
	public static WebDriver driver;
	protected static PageObjectManager pages;
	public static ExcelUtils excel;

	private static final Logger log = LoggerHelper.getLogger(BaseTest.class);

	@BeforeSuite
	public static void startApplication() {
		log.info("Starting test suite and initializing Excel & WebDriver setup.");
		excel = new ExcelUtils();
		driver = BaseClass.setUp();
	}

	@BeforeClass
	public void startClass() {
		pages = PageObjectManager.getInstance(driver);
		log.info("PageObjectManager initialized for the test class.");
	}

	@BeforeMethod
	public void beforeMethod(Method method) {
		driver.get(ConfigReader.getProperty("testUrl"));
	}

	@AfterMethod
	public void afterMethod() {
		log.info("Test method execution completed.");
	}

	@AfterClass
	public void cleanup() {
		driver.manage().deleteAllCookies();
		log.info("All cookies deleted after class execution.");
	}

	@AfterSuite
	public void closeApplication() {
		BaseClass.tearDown();
		log.info("WebDriver teardown completed. Test suite execution finished.");
	}
}
