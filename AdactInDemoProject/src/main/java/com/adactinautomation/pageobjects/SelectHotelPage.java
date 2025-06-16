package com.adactinautomation.pageobjects;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import com.adactinautomation.utilities.ExtentReportListener;
import com.adactinautomation.utilities.LoggerHelper;
import org.apache.logging.log4j.Logger;

public class SelectHotelPage {
	public WebDriver driver;
	private static final Logger log = LoggerHelper.getLogger(SelectHotelPage.class);

	public SelectHotelPage(WebDriver driver) {
		this.driver = driver;
		PageFactory.initElements(driver, this);
	}

	@FindBy(id = "radiobutton_0")
	private WebElement selectRadioButton;

	@FindBy(id = "continue")
	private WebElement continueButton;

	@FindBy(id = "cancel")
	private WebElement cancelButton;

	public void cancel() {
		cancelButton.click();
		log.info("Clicked on Cancel button in SelectHotelPage");
	}

	public void selectHotelClick() {
		selectRadioButton.click();
		log.info("Selected hotel radio button.");
	}

	public void continueClick() {
		continueButton.click();
		log.info("Clicked on Continue button.");
	}

	public void selectHotel() {
		selectHotelClick();
		continueClick();
		ExtentReportListener.logStep("Hotel selected successfully and clicked continue button.");
		log.info("Hotel selection process completed.");
	}
}
