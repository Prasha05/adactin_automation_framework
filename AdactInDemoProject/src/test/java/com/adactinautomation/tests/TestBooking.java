package com.adactinautomation.tests;

import org.testng.annotations.Listeners;
import org.testng.annotations.Test;
import com.adactinautomation.utilities.*;

import java.io.IOException;
import java.util.List;

@Listeners(com.adactinautomation.utilities.ExtentReportListener.class)
public class TestBooking extends BaseTest {

	@Test(priority = 7)
	public void bookingTestWithValidDetails() throws InterruptedException {
		ExtentReportListener.setDriver(driver);
		String testCaseID = "TC_07_Book_Hotel_ValidData";
		pages.getLoginPage().loginToApplication(ConfigReader.getProperty("username"),
				ConfigReader.getProperty("password"));
		ExtentReportListener.logStep("Login successful with username: " + ConfigReader.getProperty("username"));
		List<Integer> rowIndices = ExcelUtils.getAllRowIndicesByTestCaseID(testCaseID);
		int rowIndex = rowIndices.get(0);
		pages.getSearchHotelPage().fillSearchDetailsAndSearch(rowIndex);
		pages.getSelectHotelPage().selectHotel();
		pages.getBookingPage().fillBookingDetailsAndBook(rowIndex);
		Thread.sleep(10000);
		pages.getBookingPage().getAndValidateOrderNumber();
	}

	public void bookingTestWithMissingDetails(String testCaseID, String expectedMessage) {
		ExtentReportListener.setDriver(driver);
		pages.getLoginPage().loginToApplication(ConfigReader.getProperty("username"),
				ConfigReader.getProperty("password"));
		ExtentReportListener.logStep("Login successful with username: " + ConfigReader.getProperty("username"));
		List<Integer> rowIndices = ExcelUtils.getAllRowIndicesByTestCaseID(testCaseID);
		int rowIndex = rowIndices.get(0);
		pages.getSearchHotelPage().fillSearchDetailsAndSearch(rowIndex);
		pages.getSelectHotelPage().selectHotel();
		pages.getBookingPage().fillBookingDetailsAndBook(rowIndex);
		validateErrorMessage(pages.getBookingPage().getBookingPageErrorMessage(), expectedMessage);
	}

	@Test(priority = 8)
	public void bookingTestMissingFirstName() {
		bookingTestWithMissingDetails("TC_08_Book_Hotel_MissingFirstName", "Please Enter your First Name");
	}

	@Test(priority = 9)
	public void bookingTestMissingLastName() {
		bookingTestWithMissingDetails("TC_09_Book_Hotel_MissingLastName", "Please Enter you Last Name");
	}

	@Test(priority = 10)
	public void bookingTestMissingAddress() {
		bookingTestWithMissingDetails("TC_10_Book_Hotel_MissingAddress", "Please Enter your Address");
	}

	@Test(priority = 11)
	public void bookingTestMissingCardNumber() {
		bookingTestWithMissingDetails("TC_11_Book_Hotel_MissingCardNumber",
				"Please Enter your 16 Digit Credit Card Number");
	}

	@Test(priority = 12)
	public void bookingTestMissingCardType() {
		bookingTestWithMissingDetails("TC_12_Book_Hotel_MissingCardType", "Please Select your Credit Card Type");
	}

	@Test(priority = 13)
	public void bookingTestMissingCardExpiryMonth() {
		bookingTestWithMissingDetails("TC_13_Book_Hotel_MissingCardExpiryMonth",
				"Please Select your Credit Card Expiry Month");
	}

	@Test(priority = 14)
	public void bookingTestMissingCardExpiryYear() {
		bookingTestWithMissingDetails("TC_14_Book_Hotel_MissingCardExpiryYear",
				"Please Select your Credit Card Expiry Year");
	}

	@Test(priority = 15)
	public void bookingTestMissingCardCVVNumber() {
		bookingTestWithMissingDetails("TC_15_Book_Hotel_MissingCardCVVNumber",
				"Please Enter your Credit Card CVV Number");
	}

	@Test(priority = 16)
	public void verifyBookingConfirmation() throws InterruptedException, IOException {
		ExtentReportListener.setDriver(driver);
		String testCaseID = "TC_16_Verify_Booking_Confirmation";
		pages.getLoginPage().loginToApplication(ConfigReader.getProperty("username"),
				ConfigReader.getProperty("password"));
		ExtentReportListener.logStep("Login successful with username: " + ConfigReader.getProperty("username"));
		List<Integer> rowIndices = ExcelUtils.getAllRowIndicesByTestCaseID(testCaseID);
		for (int rowIndex : rowIndices) {
			pages.getSearchHotelPage().fillSearchDetailsAndSearch(rowIndex);
			pages.getSelectHotelPage().selectHotel();
			pages.getBookingPage().fillBookingDetailsAndBook(rowIndex);
			Thread.sleep(10000);
			String orderNumber = pages.getBookingPage().getAndValidateOrderNumber();
			pages.getBookingConfirmationPage().bookingConfirmation(rowIndex, orderNumber);
			pages.getSearchHotelPage().searchHotelClick();
		}
	}
}
