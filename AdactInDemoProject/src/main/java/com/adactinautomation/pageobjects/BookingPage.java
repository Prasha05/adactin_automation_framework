package com.adactinautomation.pageobjects;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.testng.Assert;

import com.adactinautomation.utilities.ElementUtils;
import com.adactinautomation.utilities.ExcelUtils;
import com.adactinautomation.utilities.ExtentReportListener;

public class BookingPage {
	public WebDriver driver;

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
	private static WebElement firstNameError;

	@FindBy(id = "last_name_span")
	private static WebElement lastNameError;

	@FindBy(id = "address_span")
	private static WebElement addressError;

	@FindBy(id = "cc_num_span")
	private static WebElement creditCardNumberError;

	@FindBy(id = "cc_type_span")
	private static WebElement cardTypeError;

	@FindBy(xpath = "//label[contains(text(),'Please Select your Credit Card Expiry Month')]")
	private static WebElement cardExpiryMonthError;

	@FindBy(xpath = "//label[contains(text(),'Please Select your Credit Card Expiry Year')]")
	private static WebElement cardExpiryYearError;

	@FindBy(id = "cc_cvv_span")
	private static WebElement cvvNumberError;

	public void fillBookingDetailsAndBook(int rowIndex) {

		firstName.sendKeys(ExcelUtils.getDataByColumnName(rowIndex, "First Name"));
		lastName.sendKeys(ExcelUtils.getDataByColumnName(rowIndex, "Last name"));
		billingAddress.sendKeys(ExcelUtils.getDataByColumnName(rowIndex, "Address"));
		creditCardNo.sendKeys(ExcelUtils.getDataByColumnName(rowIndex, "Credit Card no."));
		creditCardType.sendKeys(ExcelUtils.getDataByColumnName(rowIndex, "Credit Card Type"));
		expiryMonth.sendKeys(ExcelUtils.getDataByColumnName(rowIndex, "CC Expiry month"));
		expiryYear.sendKeys(ExcelUtils.getDataByColumnName(rowIndex, "CC Expiry Year"));
		cvv.sendKeys(ExcelUtils.getDataByColumnName(rowIndex, "CVV"));
		clickBookNow();
		ExtentReportListener.logStep("Booking details filled and booking submitted for : "
				+ ExcelUtils.getDataByColumnName(rowIndex, "Test Case ID"));
	}

	public void clickBookNow() {
		bookNowButton.click();
	}

	public void clickCancel() {
		cancelButton.click();
	}

	public String getAndValidateOrderNumber() {
		String orderNumber = null;
		if (orderNo.isDisplayed()) {
			orderNumber = orderNo.getDomAttribute("value");
			try {
				Assert.assertNotNull(orderNumber, "Order ID should not be null.");
				ExtentReportListener.logStepPass("Order ID generated successfully: " + orderNumber);
			} catch (AssertionError e) {
				ExtentReportListener
						.logStepFail("Order ID validation failed. Expected an order number, but it was null.");
				throw e;
			}
		}
		return orderNumber;
	}

	public static String getBookingPageErrorMessage() {
		if (ElementUtils.isElementVisible(firstNameError)) {
			return firstNameError.getText();
		} else if (ElementUtils.isElementVisible(lastNameError)) {
			return lastNameError.getText();
		} else if (ElementUtils.isElementVisible(addressError)) {
			return addressError.getText();
		} else if (ElementUtils.isElementVisible(creditCardNumberError)) {
			return creditCardNumberError.getText();
		} else if (ElementUtils.isElementVisible(cardTypeError)) {
			return cardTypeError.getText();
		} else if (ElementUtils.isElementVisible(cardExpiryMonthError)) {
			return cardExpiryMonthError.getText();
		} else if (ElementUtils.isElementVisible(cardExpiryYearError)) {
			return cardExpiryYearError.getText();
		} else if (ElementUtils.isElementVisible(cvvNumberError)) {
			return cvvNumberError.getText();
		}
		return "No error message displayed";
	}
}
