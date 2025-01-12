package com.adactinautomation.utilities;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.PageFactory;

import com.adactinautomation.pageobjects.BookedItineraryPage;
import com.adactinautomation.pageobjects.BookingConfirmationPage;
import com.adactinautomation.pageobjects.BookingPage;
import com.adactinautomation.pageobjects.LoginPage;
import com.adactinautomation.pageobjects.SearchHotelPage;
import com.adactinautomation.pageobjects.SelectHotelPage;

public class PageObjectManager {
	public WebDriver driver;
	private static PageObjectManager pageObjectManager;
	private LoginPage loginPage;
	private SearchHotelPage searchHotelPage;
	private SelectHotelPage selectHotelPage;
	private BookingPage bookingPage;
	private BookedItineraryPage bookedItineraryPage;
	private BookingConfirmationPage bookingConfirmationPage;

	private PageObjectManager(WebDriver driver) {
		this.driver = driver;
		PageFactory.initElements(driver, this);
	}

	public static PageObjectManager getInstance(WebDriver driver) {
		if (pageObjectManager == null) {
			pageObjectManager = new PageObjectManager(driver);
		}
		return pageObjectManager;
	}

	public LoginPage getLoginPage() {

		if (loginPage == null) {
			loginPage = new LoginPage(driver);
		}
		return loginPage;
	}

	public SearchHotelPage getSearchHotelPage() {
		if (searchHotelPage == null) {
			searchHotelPage = new SearchHotelPage(driver);
		}
		return searchHotelPage;
	}

	public SelectHotelPage getSelectHotelPage() {
		if (selectHotelPage == null) {
			selectHotelPage = new SelectHotelPage(driver);
		}
		return selectHotelPage;
	}

	public BookingPage getBookingPage() {
		if (bookingPage == null) {
			bookingPage = new BookingPage(driver);
		}
		return bookingPage;
	}

	public BookedItineraryPage getBookedItineraryPage() {
		if(bookedItineraryPage==null) {
		bookedItineraryPage = new BookedItineraryPage(driver);
		}
		return bookedItineraryPage;
	}

	public BookingConfirmationPage getBookingConfirmationPage() {
		if(bookingConfirmationPage==null) {
		bookingConfirmationPage = new BookingConfirmationPage(driver);
		}
		return bookingConfirmationPage;
	}

}
