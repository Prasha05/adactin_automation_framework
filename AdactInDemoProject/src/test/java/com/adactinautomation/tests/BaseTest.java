package com.adactinautomation.tests;

import java.lang.reflect.Method;

import org.openqa.selenium.WebDriver;
import org.testng.annotations.AfterClass;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.BeforeSuite;

import com.adactinautomation.utilities.BaseClass;
import com.adactinautomation.utilities.ConfigReader;
import com.adactinautomation.utilities.ExcelUtils;
import com.adactinautomation.utilities.PageObjectManager;

public class BaseTest extends BaseClass {
	public static WebDriver driver;
	protected static PageObjectManager pages;
	public static ExcelUtils excel;

	@BeforeSuite
	public static void startApplication() {
		excel = new ExcelUtils();
		driver = BaseClass.setUp();
	}

	@BeforeClass
	public void startClass() {
		pages = PageObjectManager.getInstance(driver);
	}

	@BeforeMethod
	public void beforeMethod(Method method) {
		driver.get(ConfigReader.getProperty("testUrl"));
		System.out.println("Starting test: " + method.getName());
	}

	@AfterMethod
	public void afterMethod() {
	}

	@AfterClass
	public void cleanup() {
		driver.manage().deleteAllCookies();
	}

	@AfterSuite
	public void closeApplication() {
		BaseClass.tearDown();
	}
}
