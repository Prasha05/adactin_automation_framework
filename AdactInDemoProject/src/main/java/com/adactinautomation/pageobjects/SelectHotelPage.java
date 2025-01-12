package com.adactinautomation.pageobjects;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import com.adactinautomation.utilities.ExtentReportListener;

public class SelectHotelPage {
	public WebDriver driver;

	public SelectHotelPage(WebDriver driver) {
		this.driver = driver;
		PageFactory.initElements(driver, this);
	}

	@FindBy(id = "radiobutton_0")
	public static WebElement selectRadioButton;
	@FindBy(id = "continue")
	public static WebElement continueButton;
	@FindBy(id = "cancel")
	public WebElement cancelButton;
	
	

	public void Cancel() {
		cancelButton.click();
	}

	public static void selectHotelClick() {
		selectRadioButton.click();
	}

	public static void continueClick() {
		continueButton.click();
	}
	public void selectHotel() {
		selectHotelClick();
		continueClick();
		ExtentReportListener.logStep("Hotel selected successfully and clicked continue button.");
	}

}
