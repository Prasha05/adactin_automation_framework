package com.adactinautomation.pageobjects;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.testng.Assert;

import com.adactinautomation.utilities.ExcelUtils;
import com.adactinautomation.utilities.ExtentReportListener;

public class BookingConfirmationPage {
    WebDriver driver;

    @FindBy(id = "hotel_name")
    WebElement hotelName;

    @FindBy(id = "location")
    WebElement location;

    @FindBy(id = "room_type")
    WebElement roomType;

    @FindBy(id = "total_price")
    WebElement totalPrice;

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
//        String actualTotalPrice = getTotalPrice();
        String expectedHotelName = ExcelUtils.getDataByColumnName(rowIndex, "Hotels");
        String expectedLocation = ExcelUtils.getDataByColumnName(rowIndex, "Location");
        String expectedRoomType = ExcelUtils.getDataByColumnName(rowIndex, "Room Type");

//        String expectedTotalPrice = ExcelUtils.getDataByColumnName(rowIndex, "TotalPrice");
        if (actualHotelName.trim().equalsIgnoreCase(expectedHotelName.trim()) &&
        	    actualLocation.trim().equalsIgnoreCase(expectedLocation.trim()) &&
        	    actualRoomType.trim().equalsIgnoreCase(expectedRoomType.trim())) {

        	ExtentReportListener.logStep("Booking confirmation details match with input data for the order ID: " + orderNumber);
        	} else {
        		ExtentReportListener.logStep("Booking confirmation details do not match with input data.");
        		ExtentReportListener.logStep("Expected: " + expectedHotelName + ", " + expectedLocation + ", " + expectedRoomType);
        		ExtentReportListener.logStep("Actual: " + actualHotelName + ", " + actualLocation + ", " + actualRoomType);
        		Assert.fail("Validation failed! Expected: " + expectedHotelName + ", " + expectedLocation + ", " + expectedRoomType +
                        " but got: " + actualHotelName + ", " + actualLocation + ", " + actualRoomType);
        }
        	}

    }

