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

public class ElementUtils {

	// Common method to check visibility of any element
	public static boolean isElementVisible(WebElement element) {
		try {
			return element.isDisplayed();
		} catch (Exception e) {
			return false;
		}
	}

	public static void acceptAlert(WebDriver driver) {
		Alert alert = driver.switchTo().alert();
		alert.accept();
	}

	public static void dismissAlert(WebDriver driver) {
		Alert alert = driver.switchTo().alert();
		alert.dismiss();
	}

	public static String getAlertText(WebDriver driver) {
		Alert alert = driver.switchTo().alert();
		return alert.getText();
	}

	public static void selectDropdownByVisibleText(WebElement element, String text) {
		new Select(element).selectByVisibleText(text);
	}

	public static void clearAndEnterText(WebElement element, String text) {
		element.clear();
		element.sendKeys(text);
	}

	public static void takeScreenshot(String fileName, WebDriver driver) throws IOException {

		String timestamp = new SimpleDateFormat("yyyyMMdd_HHmmss").format(new Date());
		fileName = fileName + "_" + timestamp + ".png";
		TakesScreenshot ts = (TakesScreenshot) driver;
		File source = ts.getScreenshotAs(OutputType.FILE);
		File target = new File("D:\\java\\Selenium\\Material\\screenshots\\" + fileName);
		FileUtils.copyFile(source, target);
	}
}
