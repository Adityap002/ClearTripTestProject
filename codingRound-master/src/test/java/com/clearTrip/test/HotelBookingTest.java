package com.clearTrip.test;

import org.openqa.selenium.By;
import org.openqa.selenium.support.PageFactory;
import org.testng.Assert;
import org.testng.annotations.Test;

import com.cleartrip.bean.baseClass;
import com.cleartrip.config.Configuration.url;
import com.cleartrip.page.CleartripProduct_Search;
import com.cleartrip.page.Search_Hotels_Page;
import com.cleartrip.utility.Util;

public class HotelBookingTest extends baseClass {

	private Util util;
	private CleartripProduct_Search product;
	private Search_Hotels_Page searchHotels;

	@Test
	public void shouldBeAbleToSearchForHotels() {
		util = new Util();
		product = PageFactory.initElements(driver,
				CleartripProduct_Search.class);
		searchHotels = PageFactory.initElements(driver,
				Search_Hotels_Page.class);

		setDriverPath();

		driver.get(url.app_url);
		util.waitFor(2000);

		// Click on hotels Menu
		product.click_On_Hotels(driver);

		// fill hotels entry
		searchHotels.enterLocation_Where(driver, "Indiranagar, Bangalore");

		searchHotels.select_Traveller(driver, "1 room, 2 adults");

		searchHotels.submitBtn(driver);

		String title = driver.getCurrentUrl();

		Assert.assertTrue(title.contains("Bangalore"));
		
		Assert.assertTrue(util.isElementPresent(driver,
				By.className("searchSummary")));

		quitBrowser();

	}

}
