package com.adactinautomation.tests;

import org.testng.Assert;
import org.testng.annotations.Listeners;
import org.testng.annotations.Test;

import com.adactinautomation.utilities.ConfigReader;
import com.adactinautomation.utilities.ExtentReportListener;

@Listeners(com.adactinautomation.utilities.ExtentReportListener.class)
public class TestBookingCancel extends BaseTest {
	@Test(priority = 17)
	public void cancelBooking() throws InterruptedException {
		ExtentReportListener.setDriver(driver);
		pages.getLoginPage().loginToApplication(ConfigReader.getProperty("username"),
				ConfigReader.getProperty("password"));
		ExtentReportListener.logStep("Login successful with username: " + ConfigReader.getProperty("username"));
		pages.getBookedItineraryPage().goToBookedItineraryPage();
		pages.getBookedItineraryPage().cancelAllBooking();
		Assert.assertTrue(pages.getBookedItineraryPage().isCancellationSuccessful(),
				"Booking cancellation verification failed.");
	}
}
