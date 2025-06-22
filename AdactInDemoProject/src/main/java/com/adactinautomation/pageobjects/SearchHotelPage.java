package com.adactinautomation.pageobjects;

import org.apache.logging.log4j.Logger;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import com.adactinautomation.utilities.ElementUtils;
import com.adactinautomation.utilities.ExcelUtils;
import com.adactinautomation.utilities.ExtentReportListener;
import com.adactinautomation.utilities.LoggerHelper;

public class SearchHotelPage {
	private WebDriver driver;
	private static final Logger log = LoggerHelper.getLogger(SearchHotelPage.class);

	public SearchHotelPage(WebDriver driver) {
		this.driver = driver;
		PageFactory.initElements(driver, this);
		log.info("SearchHotelPage initialized.");
	}

	@FindBy(id = "location")
	private WebElement locationDropdown;

	@FindBy(id = "hotels")
	private WebElement hotelsDropdown;

	@FindBy(id = "room_type")
	private WebElement roomTypeDropdown;

	@FindBy(id = "room_nos")
	private WebElement numberOfRoomsDropdown;

	@FindBy(id = "datepick_in")
	private WebElement checkInInput;

	@FindBy(id = "datepick_out")
	private WebElement checkOutInput;

	@FindBy(id = "adult_room")
	private WebElement adultsDropdown;

	@FindBy(id = "child_room")
	private WebElement childDropdown;

	@FindBy(id = "Submit")
	private WebElement searchButton;

	@FindBy(id = "Reset")
	private WebElement resetButton;

	@FindBy(id = "location_span")
	private static WebElement locationError;

	@FindBy(id = "checkin_span")
	private static WebElement checkInDateError;

	@FindBy(id = "checkout_span")
	private static WebElement checkOutDateError;

	@FindBy(xpath = "//a[contains(text(),'Search Hotel')]")
	private static WebElement searchHotel;

	private void selectDropdown(WebElement element, String value) {
		ElementUtils.selectDropdownByVisibleText(element, value);
		log.info("Dropdown selected - {} : {}", element, value);
	}

	private void setInputField(WebElement element, String value) {
		ElementUtils.clearAndEnterText(element, value);
		log.info("Input field set - {} : {}", element, value);
	}

	public void fillSearchDetailsAndSearch(int rowIndex) {
		String testCaseId = ExcelUtils.getDataByColumnName(rowIndex, "Test Case ID");
		ExtentReportListener.logStep("Executing test data for : " + testCaseId);
		log.info("Filling search hotel form for Test Case ID: {}", testCaseId);

		selectDropdown(locationDropdown, ExcelUtils.getDataByColumnName(rowIndex, "Location"));
		selectDropdown(hotelsDropdown, ExcelUtils.getDataByColumnName(rowIndex, "Hotels"));
		selectDropdown(roomTypeDropdown, ExcelUtils.getDataByColumnName(rowIndex, "Room Type"));
		selectDropdown(numberOfRoomsDropdown, ExcelUtils.getDataByColumnName(rowIndex, "Number of Rooms"));
		setInputField(checkInInput, ExcelUtils.getDataByColumnName(rowIndex, "Check-In Date"));
		setInputField(checkOutInput, ExcelUtils.getDataByColumnName(rowIndex, "Check-Out Date"));
		selectDropdown(adultsDropdown, ExcelUtils.getDataByColumnName(rowIndex, "Adults per Room"));
		selectDropdown(childDropdown, ExcelUtils.getDataByColumnName(rowIndex, "Children per Room"));
		clickSearch();
		ElementUtils.waitForElementVisible(driver,"//td[contains(text(), \"Select Hotel \")]");//wait for select hotel page visibility
		ExtentReportListener.logStep("Search details filled for : " + testCaseId + " and search initiated.");
		log.info("Search initiated for Test Case ID: {}", testCaseId);
	}

	public void clickSearch() {
		searchButton.click();
		log.info("Search button clicked.");
	}

	public void clickReset() {
		resetButton.click();
		log.info("Reset button clicked.");
	}

	public String getSearchHotelPageErrorMessage() {
		if (ElementUtils.isElementVisible(locationError)) {
			log.warn("Validation error - Location: {}", locationError.getText());
			return locationError.getText();
		} else if (ElementUtils.isElementVisible(checkInDateError)) {
			log.warn("Validation error - Check-in Date: {}", checkInDateError.getText());
			return checkInDateError.getText();
		} else if (ElementUtils.isElementVisible(checkOutDateError)) {
			log.warn("Validation error - Check-out Date: {}", checkOutDateError.getText());
			return checkOutDateError.getText();
		}
		log.info("No error message displayed on hotel search page.");
		return "No error message displayed";
	}

	public void searchHotelClick() {
		searchHotel.click();
		log.info("Search Hotel link clicked.");
	}
}
