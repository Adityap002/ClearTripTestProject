package com.clearTrip.test;

import org.apache.log4j.Logger;
import org.openqa.selenium.By;
import org.openqa.selenium.support.PageFactory;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Listeners;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;

import com.cleartrip.bean.baseClass;
import com.cleartrip.config.Configuration.url;
import com.cleartrip.page.CleartripProduct_Search;
import com.cleartrip.page.Search_Hotels_Page;
import com.cleartrip.report.JyperionListener;
import com.cleartrip.utility.ExcelUtility;
import com.cleartrip.utility.Util;

@Listeners(value=JyperionListener.class)
public class HotelBookingTest extends baseClass {

	private Util util;
	private CleartripProduct_Search product;
	private Search_Hotels_Page searchHotels;
	private Logger logger = Logger.getLogger(HotelBookingTest.class);
	private SoftAssert assertion = new SoftAssert();

	
	@DataProvider(name="hotelbook")
	public Object[][] dataProvider(){
		Object[][] testData = ExcelUtility.getTestData("HotelsBookingTest");
		return testData;
	}

	
	@Test(dataProvider="hotelbook")
	public void shouldBeAbleToSearchForHotels(String where, String checkInDate, String checkOutDate) {
		util = new Util();
		product = PageFactory.initElements(driver,
				CleartripProduct_Search.class);
		searchHotels = PageFactory.initElements(driver,
				Search_Hotels_Page.class);

		logger.debug("Driver got set");

		driver.get(url.app_url);
		logger.debug("open url =="+url.app_url);

		// Click on hotels Menu
		product.click_On_Hotels(driver);

		// fill hotels entry
		searchHotels.enterLocation_Where(driver, where);
		util.waitFor(1000);

		// Click on check-in date
		String[] check_In_Date = util.datePicker(checkInDate);
		By xpathForMonth = By.xpath("//div[@id='ui-datepicker-div']//span[text()='"
				+ check_In_Date[1] + "']");
		By xpathForYear = By.xpath("//div[@id='ui-datepicker-div']//span[text()='"
				+ check_In_Date[2] + "']");
		logger.debug("Check-In Month ==> "+xpathForMonth);
		logger.debug("Check-In Year ==> "+xpathForYear);

		String[] splitDate = util.localDatePicker(checkInDate);
		int a = Integer.parseInt(splitDate[0]);
		int month = Integer.parseInt(splitDate[1]) - 1;

		String xpathForDateClick = "//td[@data-year='" + check_In_Date[2]
				+ "' and @data-month='" + month + "']/a[text()='"
				+ a + "']";
		logger.debug("date => "+xpathForDateClick);
		
		util.click_On_Date(driver, xpathForYear, xpathForMonth, xpathForDateClick);

		util.waitFor(1000);
		// Click on check-out date
		String[] departDate_CheckOut = util.datePicker(checkOutDate);
		
		By xpathForMonth_CheckOut = By.xpath("//div[@id='ui-datepicker-div']//span[text()='"
				+ departDate_CheckOut[1] + "']");

		String[] splitDate_CheckOut = util.localDatePicker(checkOutDate);
		int b = Integer.parseInt(splitDate_CheckOut[0]);
		int month_CheckOut = Integer.parseInt(splitDate_CheckOut[1]) - 1;
		
		logger.debug("Check-Out Month ==> "+xpathForMonth_CheckOut);
		
		
		String xpathForDateClick_CheckOut = "//td[@data-year='"
				+ splitDate_CheckOut[2] + "' and @data-month='"
				+ month_CheckOut + "']/a[text()='" + b
				+ "']";
		logger.debug("Check-Out Date ==> "+xpathForDateClick_CheckOut);
		
		By xpathForYear_CheckOut = By.xpath("//div[@id='ui-datepicker-div']//span[text()='"
				+ departDate_CheckOut[2] + "']");
		logger.debug("Check-Out Year ==> "+xpathForYear_CheckOut);

		util.click_On_Date(driver, xpathForYear_CheckOut, xpathForMonth_CheckOut, xpathForDateClick_CheckOut);

		searchHotels.select_Traveller(driver, "1 room, 2 adults");

		searchHotels.submitBtn(driver);

		String title = driver.getCurrentUrl();

		util.waitFor(2000);

		assertion.assertTrue(title.contains("Bangalore"));

		assertion.assertTrue(util.isElementPresent(driver,
				By.className("searchSummary")));
		
		assertion.assertAll();
	}

}
