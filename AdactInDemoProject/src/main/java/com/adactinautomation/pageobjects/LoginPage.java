package com.adactinautomation.pageobjects;

import org.apache.logging.log4j.Logger;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import com.adactinautomation.utilities.ElementUtils;
import com.adactinautomation.utilities.ExtentReportListener;
import com.adactinautomation.utilities.LoggerHelper;

public class LoginPage {

	private WebDriver driver;
	private final Logger log = LoggerHelper.getLogger(LoginPage.class);

	@FindBy(id = "username")
	private WebElement usernameField;

	@FindBy(id = "password")
	private WebElement passwordField;

	@FindBy(id = "login")
	private WebElement loginbtn;

	@FindBy(id = "username_span")
	private WebElement usernameError;

	@FindBy(xpath = "//b[contains(text(), 'Invalid Login details or Your Password might have expired. ')]")
	private WebElement invalidLoginError;

	public LoginPage(WebDriver driver) {
		this.driver = driver;
		PageFactory.initElements(driver, this);
		log.info("LoginPage initialized with WebDriver.");
	}

	public void loginToApplication(String username, String password) {
		usernameField.clear();
		usernameField.sendKeys(username);
		passwordField.clear();
		passwordField.sendKeys(password);
		ExtentReportListener.logStep("Navigated to the Adactin Hotel application login page.");
		log.info("Login attempt with username: {}", username);
		loginbtn.click();
	}

	public String getLoginPageErrorMessage() {
		if (ElementUtils.isElementVisible(usernameError)) {
			log.warn("Username field error: {}", usernameError.getText());
			return usernameError.getText();
		} else if (ElementUtils.isElementVisible(invalidLoginError)) {
			log.warn("Invalid login error: {}", invalidLoginError.getText());
			return invalidLoginError.getText();
		}
		log.info("No login error message displayed.");
		return "No error message displayed";
	}
}
