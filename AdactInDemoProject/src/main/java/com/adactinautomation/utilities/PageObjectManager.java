package com.adactinautomation.utilities;

import org.apache.logging.log4j.Logger;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.PageFactory;

import com.adactinautomation.pageobjects.*;

public class PageObjectManager {

	public WebDriver driver;
	private static PageObjectManager pageObjectManager;

	private LoginPage loginPage;
	private SearchHotelPage searchHotelPage;
	private SelectHotelPage selectHotelPage;
	private BookingPage bookingPage;
	private BookedItineraryPage bookedItineraryPage;
	private BookingConfirmationPage bookingConfirmationPage;

	private static final Logger log = LoggerHelper.getLogger(PageObjectManager.class);

	private PageObjectManager(WebDriver driver) {
		this.driver = driver;
		PageFactory.initElements(driver, this);
		log.info("PageObjectManager initialized with WebDriver.");
	}

	public static PageObjectManager getInstance(WebDriver driver) {
		if (pageObjectManager == null) {
			pageObjectManager = new PageObjectManager(driver);
			log.info("New PageObjectManager instance created.");
		}
		return pageObjectManager;
	}

	public LoginPage getLoginPage() {
		if (loginPage == null) {
			loginPage = new LoginPage(driver);
			log.info("LoginPage object created.");
		}
		return loginPage;
	}

	public SearchHotelPage getSearchHotelPage() {
		if (searchHotelPage == null) {
			searchHotelPage = new SearchHotelPage(driver);
			log.info("SearchHotelPage object created.");
		}
		return searchHotelPage;
	}

	public SelectHotelPage getSelectHotelPage() {
		if (selectHotelPage == null) {
			selectHotelPage = new SelectHotelPage(driver);
			log.info("SelectHotelPage object created.");
		}
		return selectHotelPage;
	}

	public BookingPage getBookingPage() {
		if (bookingPage == null) {
			bookingPage = new BookingPage(driver);
			log.info("BookingPage object created.");
		}
		return bookingPage;
	}

	public BookedItineraryPage getBookedItineraryPage() {
		if (bookedItineraryPage == null) {
			bookedItineraryPage = new BookedItineraryPage(driver);
			log.info("BookedItineraryPage object created.");
		}
		return bookedItineraryPage;
	}

	public BookingConfirmationPage getBookingConfirmationPage() {
		if (bookingConfirmationPage == null) {
			bookingConfirmationPage = new BookingConfirmationPage(driver);
			log.info("BookingConfirmationPage object created.");
		}
		return bookingConfirmationPage;
	}
}
