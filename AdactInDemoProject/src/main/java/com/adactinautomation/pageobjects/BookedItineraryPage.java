package com.adactinautomation.pageobjects;

import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import com.adactinautomation.utilities.ElementUtils;
import com.adactinautomation.utilities.ExtentReportListener;
import com.adactinautomation.utilities.LoggerHelper;

import org.apache.logging.log4j.Logger;

public class BookedItineraryPage {

	WebDriver driver;
	private static final Logger log = LoggerHelper.getLogger(BookedItineraryPage.class);

	public BookedItineraryPage(WebDriver driver) {
		this.driver = driver;
		PageFactory.initElements(driver, this);
	}

	@FindBy(xpath = "//a[contains(text(),'Booked Itinerary')]")
	private WebElement bookedItineraryButton;

	@FindBy(id = "booked_form")
	private WebElement bookingTable;

	@FindBy(xpath = "(//input[@name='ids[]'])[1]")
	private WebElement firstbookingCheckbox;

	@FindBy(name = "check_all")
	private WebElement allBookingCheckbox;

	@FindBy(xpath = "//input[@name='cancelall']")
	private WebElement cancelButton;

	@FindBy(id = "search_result_error")
	private WebElement successMessage;

	public void goToBookedItineraryPage() {
		bookedItineraryButton.click();
		log.info("Navigated to Booked Itinerary page.");
	}

	public void cancelFirstBooking() {
		try {
			firstbookingCheckbox.click();
			cancelButton.click();
			ElementUtils.acceptAlert(driver);
			ExtentReportListener.logStep("First booking cancelled successfully.");
			log.info("First booking cancelled successfully.");
		} catch (NoSuchElementException e) {
			ExtentReportListener.logStep("No bookings found to cancel.");
			log.warn("No bookings found to cancel - Element not found.");
		}
	}

	public void cancelAllBooking() {
		try {
			firstbookingCheckbox.click();
			allBookingCheckbox.click();
			cancelButton.click();
			ElementUtils.acceptAlert(driver);
			ExtentReportListener.logStep("Selected and cancelled all bookings from the Booked Itinerary Page.");
			log.info("All bookings cancelled successfully.");
		} catch (Exception e) {
			ExtentReportListener.logStep("No bookings found to cancel.");
			log.warn("Failed to cancel all bookings. Possibly no bookings found.");
		}
	}

	public boolean isCancellationSuccessful() {
		boolean visible = successMessage.isDisplayed();
		log.info("Cancellation success message visible: {}", visible);
		return visible;
	}
}
