package com.clearTrip.test;

import org.openqa.selenium.By;
import org.openqa.selenium.support.PageFactory;
import org.testng.Assert;
import org.testng.annotations.Test;

import com.cleartrip.bean.baseClass;
import com.cleartrip.config.Configuration.url;
import com.cleartrip.page.CleartripProduct_Search;
import com.cleartrip.page.Search_Flights_Page;
import com.cleartrip.utility.Util;

public class FlightBookingTest extends baseClass {

	private Util util;
	private CleartripProduct_Search product;
	private Search_Flights_Page flightSearch;

	@Test
	public void testThatResultsAppearForAOneWayJourney() {
		util = new Util();
		product = PageFactory.initElements(driver,
				CleartripProduct_Search.class);
		flightSearch = PageFactory.initElements(driver,
				Search_Flights_Page.class);

		setDriverPath();

		driver.get(url.app_url);
		util.waitFor(2000);

		// click on flight tab
		product.click_On_Flights(driver);

		// fill search fields
		flightSearch.clickOnOneWay(driver);

		flightSearch.enter_From(driver, "Bangalore");

		flightSearch.enter_To(driver, "Delhi");

		// wait for the auto complete options to appear for the destination
		flightSearch.enter_Depart_On(driver, "30/07/2018");

		// Number of adults
		flightSearch.no_Of_Adults(driver, "2");

		// Number of Children
		flightSearch.no_Of_Childres(driver, "1");

		// Number of Infants
		flightSearch.no_Of_Infants(driver, "1");

		// all fields filled in. Now click on search
		flightSearch.submit_Button(driver);

		util.waitFor(5000);
		// verify that result appears for the provided journey search
		Assert.assertTrue(util.isElementPresent(driver,
				By.className("searchSummary")));

		// close the browser
		quitBrowser();

	}
}
