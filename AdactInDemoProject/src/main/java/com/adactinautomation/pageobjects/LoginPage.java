package com.adactinautomation.pageobjects;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import com.adactinautomation.utilities.ElementUtils;
import com.adactinautomation.utilities.ExtentReportListener;

public class LoginPage {
	private static WebDriver driver;

	@FindBy(xpath = "//input[@id='username']")
	private static WebElement usernameField;

	@FindBy(xpath = "//input[@id='password']")
	private static WebElement passwordField;

	@FindBy(xpath = "//input[@id='login']")
	private static WebElement loginbtn;

	@FindBy(xpath = "//span[@id=\"username_span\"]")
	private static WebElement usernameError;

	@FindBy(xpath = "//b[contains(text(), 'Invalid Login details or Your Password might have expired. ')]")
	private static WebElement invalidLoginError;

	public LoginPage(WebDriver driver) {
		this.driver = driver;
		PageFactory.initElements(driver, this);
	}

	public void loginToApplication(String username, String password) {
		usernameField.clear();
		usernameField.sendKeys(username);
		passwordField.clear();
		passwordField.sendKeys(password);
		ExtentReportListener.logStep("Navigated to the Adactin Hotel application login page.");
		loginbtn.click();
	}

	public String getLoginPageErrorMessage() {
		if (ElementUtils.isElementVisible(usernameError)) {
			return usernameError.getText();
		} else if (ElementUtils.isElementVisible(invalidLoginError)) {
			return invalidLoginError.getText();
		}
		return "No error message displayed";
	}

}
