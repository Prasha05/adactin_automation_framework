package com.adactinautomation.pageobjects;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.testng.Assert;

import com.adactinautomation.utilities.ElementUtils;
import com.adactinautomation.utilities.ExcelUtils;
import com.adactinautomation.utilities.ExtentReportListener;
import com.adactinautomation.utilities.LoggerHelper;
import org.apache.logging.log4j.Logger;

public class BookingPage {
	public WebDriver driver;
	private static final Logger log = LoggerHelper.getLogger(BookingPage.class);

	public BookingPage(WebDriver driver) {
		this.driver = driver;
		PageFactory.initElements(driver, this);
	}

	@FindBy(id = "first_name")
	private WebElement firstName;

	@FindBy(id = "last_name")
	private WebElement lastName;

	@FindBy(id = "address")
	private WebElement billingAddress;

	@FindBy(id = "cc_num")
	private WebElement creditCardNo;

	@FindBy(id = "cc_type")
	private WebElement creditCardType;

	@FindBy(id = "cc_exp_month")
	private WebElement expiryMonth;

	@FindBy(id = "cc_exp_year")
	private WebElement expiryYear;

	@FindBy(id = "cc_cvv")
	private WebElement cvv;

	@FindBy(id = "book_now")
	private WebElement bookNowButton;

	@FindBy(id = "cancel")
	private WebElement cancelButton;

	@FindBy(id = "order_no")
	private WebElement orderNo;

	@FindBy(id = "first_name_span")
	private WebElement firstNameError;

	@FindBy(id = "last_name_span")
	private WebElement lastNameError;

	@FindBy(id = "address_span")
	private WebElement addressError;

	@FindBy(id = "cc_num_span")
	private WebElement creditCardNumberError;

	@FindBy(id = "cc_type_span")
	private WebElement cardTypeError;

	@FindBy(xpath = "//label[contains(text(),'Please Select your Credit Card Expiry Month')]")
	private WebElement cardExpiryMonthError;

	@FindBy(xpath = "//label[contains(text(),'Please Select your Credit Card Expiry Year')]")
	private WebElement cardExpiryYearError;

	@FindBy(id = "cc_cvv_span")
	private WebElement cvvNumberError;

	public void fillBookingDetailsAndBook(int rowIndex) {
		log.info("Filling booking details for TestCaseID: {}", ExcelUtils.getDataByColumnName(rowIndex, "Test Case ID"));
		firstName.sendKeys(ExcelUtils.getDataByColumnName(rowIndex, "First Name"));
		lastName.sendKeys(ExcelUtils.getDataByColumnName(rowIndex, "Last name"));
		billingAddress.sendKeys(ExcelUtils.getDataByColumnName(rowIndex, "Address"));
		creditCardNo.sendKeys(ExcelUtils.getDataByColumnName(rowIndex, "Credit Card no."));
		creditCardType.sendKeys(ExcelUtils.getDataByColumnName(rowIndex, "Credit Card Type"));
		expiryMonth.sendKeys(ExcelUtils.getDataByColumnName(rowIndex, "CC Expiry month"));
		expiryYear.sendKeys(ExcelUtils.getDataByColumnName(rowIndex, "CC Expiry Year"));
		cvv.sendKeys(ExcelUtils.getDataByColumnName(rowIndex, "CVV"));
		clickBookNow();
		ExtentReportListener.logStep("Booking details filled and submitted for Test Case: "
				+ ExcelUtils.getDataByColumnName(rowIndex, "Test Case ID"));
		log.info("Booking form submitted.");
		ElementUtils.setImplicitWait(5, driver);
	}

	public void clickBookNow() {
		bookNowButton.click();
		log.info("Clicked Book Now button.");
	}

	public void clickCancel() {
		cancelButton.click();
		log.info("Clicked Cancel button on Booking Page.");
	}

	public String getAndValidateOrderNumber() {
		String orderNumber = null;
		if (orderNo.isDisplayed()) {
			orderNumber = orderNo.getDomAttribute("value");
			try {
				Assert.assertNotNull(orderNumber, "Order ID should not be null.");
				ExtentReportListener.logStepPass("Order ID generated successfully: " + orderNumber);
				log.info("Order ID retrieved successfully: {}", orderNumber);
			} catch (AssertionError e) {
				ExtentReportListener.logStepFail("Order ID is null.");
				log.error("Order ID validation failed. Expected non-null, but was null.");
				throw e;
			}
		}
		return orderNumber;
	}

	public String getBookingPageErrorMessage() {
		if (ElementUtils.isElementVisible(firstNameError)) {
			log.warn("First name error: {}", firstNameError.getText());
			return firstNameError.getText();
		} else if (ElementUtils.isElementVisible(lastNameError)) {
			log.warn("Last name error: {}", lastNameError.getText());
			return lastNameError.getText();
		} else if (ElementUtils.isElementVisible(addressError)) {
			log.warn("Address error: {}", addressError.getText());
			return addressError.getText();
		} else if (ElementUtils.isElementVisible(creditCardNumberError)) {
			log.warn("Credit Card Number error: {}", creditCardNumberError.getText());
			return creditCardNumberError.getText();
		} else if (ElementUtils.isElementVisible(cardTypeError)) {
			log.warn("Card Type error: {}", cardTypeError.getText());
			return cardTypeError.getText();
		} else if (ElementUtils.isElementVisible(cardExpiryMonthError)) {
			log.warn("Expiry Month error: {}", cardExpiryMonthError.getText());
			return cardExpiryMonthError.getText();
		} else if (ElementUtils.isElementVisible(cardExpiryYearError)) {
			log.warn("Expiry Year error: {}", cardExpiryYearError.getText());
			return cardExpiryYearError.getText();
		} else if (ElementUtils.isElementVisible(cvvNumberError)) {
			log.warn("CVV error: {}", cvvNumberError.getText());
			return cvvNumberError.getText();
		}
		log.info("No error message displayed.");
		return "No error message displayed";
	}
}
