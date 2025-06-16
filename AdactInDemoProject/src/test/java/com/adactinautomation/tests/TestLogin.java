package com.adactinautomation.tests;

import org.testng.annotations.Listeners;
import org.testng.annotations.Test;
import com.adactinautomation.utilities.ConfigReader;
import com.adactinautomation.utilities.ExtentReportListener;

@Listeners(com.adactinautomation.utilities.ExtentReportListener.class)
public class TestLogin extends BaseTest {
	@Test(priority = 1)
	public void blankLoginTest() {
		ExtentReportListener.setDriver(driver);
		pages.getLoginPage().loginToApplication("", "");
		ExtentReportListener.logStep("Username and password left blank and clicked login.");
		validateErrorMessage(pages.getLoginPage().getLoginPageErrorMessage(), "Enter Username");
	}

	@Test(priority = 3)
	public void validLoginTest() {
		ExtentReportListener.setDriver(driver);
		pages.getLoginPage().loginToApplication(ConfigReader.getProperty("username"),
				ConfigReader.getProperty("password"));
		ExtentReportListener.logStep("Entered valid credentials.");
		validatePageTitle(driver.getTitle(), "Adactin.com - Search Hotel");
	}

	@Test(priority = 2)
	public void invalidLoginTest() {
		ExtentReportListener.setDriver(driver);
		pages.getLoginPage().loginToApplication("PrashaXX", "SXRXXX");
		ExtentReportListener.logStep("Entered invalid credentials.");
		validateErrorMessage(pages.getLoginPage().getLoginPageErrorMessage(),
				"Invalid Login details or Your Password might have expired. Click here to reset your password");
	}
}
