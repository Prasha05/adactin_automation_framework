package com.adactinautomation.tests;

import java.util.List;

import org.testng.annotations.Listeners;
import org.testng.annotations.Test;

import com.adactinautomation.utilities.ConfigReader;
import com.adactinautomation.utilities.ExcelUtils;
import com.adactinautomation.utilities.ExtentReportListener;

@Listeners(com.adactinautomation.utilities.ExtentReportListener.class)
public class TestHotelSearch extends BaseTest {
	List<Integer> rowIndices;

	@Test(priority = 4)
	public void search_Hotel_ValidData() {
		ExtentReportListener.setDriver(driver);
		String testCaseID = "TC_04_Search_Hotel_ValidData";
		pages.getLoginPage().loginToApplication(ConfigReader.getProperty("username"),
				ConfigReader.getProperty("password"));
		ExtentReportListener.logStep("Login successful with username: " + ConfigReader.getProperty("username"));
		rowIndices = ExcelUtils.getAllRowIndicesByTestCaseID(testCaseID);
		for (int rowIndex : rowIndices) {
			pages.getSearchHotelPage().fillSearchDetailsAndSearch(rowIndex);
			validatePageTitle(driver.getTitle(), "Adactin.com - Select Hotel");
			pages.getSelectHotelPage().cancel();
		}
	}

	@Test(priority = 5)
	public void search_Hotel_invalidData() {
		ExtentReportListener.setDriver(driver);
		String testCaseID = "TC_05_Search_Hotel_WithoutLocation";
		pages.getLoginPage().loginToApplication(ConfigReader.getProperty("username"),
				ConfigReader.getProperty("password"));
		ExtentReportListener.logStep("Login successful with username: " + ConfigReader.getProperty("username"));
		pages.getSearchHotelPage().clickSearch();
		ExtentReportListener.logStep("Search button clicked without selecting location");
		validateErrorMessage(pages.getSearchHotelPage().getSearchHotelPageErrorMessage(), "Please Select a Location");
	}

	@Test(groups= {"bug"}, priority = 6)
	public void search_Hotel_Backdated() {
		ExtentReportListener.setDriver(driver);
		String testCaseID = "TC_06_Search_Hotel_Backdated";
		pages.getLoginPage().loginToApplication(ConfigReader.getProperty("username"),
				ConfigReader.getProperty("password"));
		ExtentReportListener.logStep("Login successful with username: " + ConfigReader.getProperty("username"));
		rowIndices = ExcelUtils.getAllRowIndicesByTestCaseID(testCaseID);
		int rowIndex = rowIndices.get(0);
		pages.getSearchHotelPage().fillSearchDetailsAndSearch(rowIndex);
		validatePageTitle(driver.getTitle(), "Adactin.com - Search Hotel");
		ExtentReportListener.logStep("Hotel search validation passed. System handled backdated dates correctly.");
	}
}
