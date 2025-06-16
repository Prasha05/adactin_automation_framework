package com.adactinautomation.utilities;

import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.Alert;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.Select;

import org.apache.logging.log4j.Logger;

public class ElementUtils {

	private static final Logger log = LoggerHelper.getLogger(ElementUtils.class);

	public static boolean isElementVisible(WebElement element) {
		try {
			return element.isDisplayed();
		} catch (Exception e) {
			log.warn("Element not visible or not found.");
			return false;
		}
	}

	public static void acceptAlert(WebDriver driver) {
		Alert alert = driver.switchTo().alert();
		alert.accept();
		log.info("Accepted alert.");
	}

	public static void dismissAlert(WebDriver driver) {
		Alert alert = driver.switchTo().alert();
		alert.dismiss();
		log.info("Dismissed alert.");
	}

	public static String getAlertText(WebDriver driver) {
		Alert alert = driver.switchTo().alert();
		String text = alert.getText();
		log.info("Alert text: " + text);
		return text;
	}

	public static void selectDropdownByVisibleText(WebElement element, String text) {
		new Select(element).selectByVisibleText(text);
		log.debug("Dropdown selected: " + text);
	}

	public static void clearAndEnterText(WebElement element, String text) {
		element.clear();
		element.sendKeys(text);
		log.debug("Entered text: " + text);
	}

	public static void takeScreenshot(String fileName, WebDriver driver) throws IOException {
		String timestamp = new SimpleDateFormat("yyyyMMdd_HHmmss").format(new Date());
		fileName = fileName + "_" + timestamp + ".png";
		TakesScreenshot ts = (TakesScreenshot) driver;
		File source = ts.getScreenshotAs(OutputType.FILE);
		File target = new File("D:\\java\\Selenium\\Material\\screenshots\\" + fileName);
		FileUtils.copyFile(source, target);
		log.info("Screenshot saved: " + target.getAbsolutePath());
	}
}
