package com.adactinautomation.pageobjects;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import com.adactinautomation.utilities.ElementUtils;
import com.adactinautomation.utilities.ExcelUtils;
import com.adactinautomation.utilities.ExtentReportListener;

public class SearchHotelPage {
	private WebDriver driver;

	public SearchHotelPage(WebDriver driver) {
		this.driver = driver;
		PageFactory.initElements(driver, this);
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
	}

	private void setInputField(WebElement element, String value) {
		ElementUtils.clearAndEnterText(element, value);
	}

	public void fillSearchDetailsAndSearch(int rowIndex) {
		ExtentReportListener
				.logStep("Executing test data for : " + ExcelUtils.getDataByColumnName(rowIndex, "Test Case ID"));
		selectDropdown(locationDropdown, ExcelUtils.getDataByColumnName(rowIndex, "Location"));
		selectDropdown(hotelsDropdown, ExcelUtils.getDataByColumnName(rowIndex, "Hotels"));
		selectDropdown(roomTypeDropdown, ExcelUtils.getDataByColumnName(rowIndex, "Room Type"));
		selectDropdown(numberOfRoomsDropdown, ExcelUtils.getDataByColumnName(rowIndex, "Number of Rooms"));
		setInputField(checkInInput, ExcelUtils.getDataByColumnName(rowIndex, "Check-In Date"));
		setInputField(checkOutInput, ExcelUtils.getDataByColumnName(rowIndex, "Check-Out Date"));
		selectDropdown(adultsDropdown, ExcelUtils.getDataByColumnName(rowIndex, "Adults per Room"));
		selectDropdown(childDropdown, ExcelUtils.getDataByColumnName(rowIndex, "Children per Room"));
		clickSearch();
		ExtentReportListener.logStep("Search details filled for : "
				+ ExcelUtils.getDataByColumnName(rowIndex, "Test Case ID") + " and search initiated.");
	}

	public void clickSearch() {
		searchButton.click();
	}

	public void clickReset() {
		resetButton.click();
	}

	public String getSearchHotelPageErrorMessage() {
		if (ElementUtils.isElementVisible(locationError)) {
			return locationError.getText();
		} else if (ElementUtils.isElementVisible(checkInDateError)) {
			return checkInDateError.getText();
		} else if (ElementUtils.isElementVisible(checkOutDateError)) {
			return checkOutDateError.getText();
		}
		return "No error message displayed";
	}

	public void searchHotelClick() {
		searchHotel.click();

	}
}
