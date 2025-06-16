package com.adactinautomation.pageobjects;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.testng.Assert;

import com.adactinautomation.utilities.ExcelUtils;
import com.adactinautomation.utilities.ExtentReportListener;
import com.adactinautomation.utilities.LoggerHelper;
import org.apache.logging.log4j.Logger;

public class BookingConfirmationPage {
    WebDriver driver;
    private static final Logger log = LoggerHelper.getLogger(BookingConfirmationPage.class);

    @FindBy(id = "hotel_name")
    private WebElement hotelName;

    @FindBy(id = "location")
    private WebElement location;

    @FindBy(id = "room_type")
    private WebElement roomType;

    @FindBy(id = "total_price")
    private WebElement totalPrice;

    public BookingConfirmationPage(WebDriver driver) {
        this.driver = driver;
        PageFactory.initElements(driver, this);
    }

    public String getHotelName() {
        return hotelName.getDomAttribute("value");
    }

    public String getLocation() {
        return location.getDomAttribute("value");
    }

    public String getRoomType() {
        return roomType.getDomAttribute("value");
    }

    public String getTotalPrice() {
        return totalPrice.getDomAttribute("value");
    }

    public void bookingConfirmation(int rowIndex, String orderNumber) throws InterruptedException {
        String actualHotelName = getHotelName();
        String actualLocation = getLocation();
        String actualRoomType = getRoomType();

        String expectedHotelName = ExcelUtils.getDataByColumnName(rowIndex, "Hotels");
        String expectedLocation = ExcelUtils.getDataByColumnName(rowIndex, "Location");
        String expectedRoomType = ExcelUtils.getDataByColumnName(rowIndex, "Room Type");

        log.info("Verifying booking confirmation for Order ID: {}", orderNumber);
        log.info("Expected: Hotel = {}, Location = {}, Room Type = {}", expectedHotelName, expectedLocation, expectedRoomType);
        log.info("Actual: Hotel = {}, Location = {}, Room Type = {}", actualHotelName, actualLocation, actualRoomType);

        if (actualHotelName.trim().equalsIgnoreCase(expectedHotelName.trim()) &&
            actualLocation.trim().equalsIgnoreCase(expectedLocation.trim()) &&
            actualRoomType.trim().equalsIgnoreCase(expectedRoomType.trim())) {

            ExtentReportListener.logStep("Booking confirmation details match with input data for the order ID: " + orderNumber);
            log.info("Booking confirmation matched successfully for Order ID: {}", orderNumber);
        } else {
            ExtentReportListener.logStep("Booking confirmation details do not match with input data.");
            ExtentReportListener.logStep("Expected: " + expectedHotelName + ", " + expectedLocation + ", " + expectedRoomType);
            ExtentReportListener.logStep("Actual: " + actualHotelName + ", " + actualLocation + ", " + actualRoomType);
            log.error("Booking confirmation mismatch for Order ID: {}", orderNumber);
            Assert.fail("Validation failed! Expected: " + expectedHotelName + ", " + expectedLocation + ", " + expectedRoomType +
                        " but got: " + actualHotelName + ", " + actualLocation + ", " + actualRoomType);
        }
    }
}
