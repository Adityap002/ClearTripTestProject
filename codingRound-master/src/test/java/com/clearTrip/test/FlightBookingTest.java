package com.clearTrip.test;

import org.apache.log4j.Logger;
import org.openqa.selenium.By;
import org.openqa.selenium.support.PageFactory;
import org.testng.Assert;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Listeners;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

import com.cleartrip.bean.baseClass;
import com.cleartrip.config.Configuration.url;
import com.cleartrip.page.CleartripProduct_Search;
import com.cleartrip.page.Search_Flights_Page;
import com.cleartrip.report.JyperionListener;
import com.cleartrip.utility.ExcelUtility;
import com.cleartrip.utility.Util;


@Listeners(value=JyperionListener.class)
public class FlightBookingTest extends baseClass {

	private Util util;
	private CleartripProduct_Search product;
	private Search_Flights_Page flightSearch;
	private Logger logger = Logger.getLogger(FlightBookingTest.class);
	
	@DataProvider(name="flightbooking")
	public Object[][] dataProvider(){
		Object[][] testData = ExcelUtility.getTestData("FlightBooking");
		return testData;
	}

	@Test(dataProvider="flightbooking")
	public void testThatResultsAppearForAOneWayJourney(String from, String to,
			String departDate, String adults, String children, String infants) {
		util = new Util();
		product = PageFactory.initElements(driver,
				CleartripProduct_Search.class);
		flightSearch = PageFactory.initElements(driver,
				Search_Flights_Page.class);

		logger.debug("Driver got set");

		driver.get(url.app_url);
		logger.debug("open url =="+url.app_url);
		util.waitFor(2000);

		// click on flight tab
		product.click_On_Flights(driver);

		// fill search fields
		flightSearch.clickOnOneWay(driver);

		flightSearch.enter_From(driver, from);

		flightSearch.enter_To(driver, to);

		// wait for the auto complete options to appear for the destination
		String[] depart_Date = util.datePicker(departDate);
		By xpathForMonth = By.xpath("//div[@id='ui-datepicker-div']//span[text()='"
				+ depart_Date[1] + "']");
		By xpathForYear = By.xpath("//div[@id='ui-datepicker-div']//span[text()='"
				+ depart_Date[2] + "']");

		String[] splitDate = util.localDatePicker(departDate);
		int a = Integer.parseInt(splitDate[0]);
		int month = Integer.parseInt(splitDate[1]) - 1;

		String xpathForDateClick = "//td[@data-year='" + depart_Date[2]
				+ "' and @data-month='" + month + "']/a[text()='" + a + "']";

		util.click_On_Date(driver, xpathForYear, xpathForMonth,
				xpathForDateClick);

		// Number of adults
		flightSearch.no_Of_Adults(driver, adults);

		// Number of Children
		flightSearch.no_Of_Childres(driver, children);

		// Number of Infants
		flightSearch.no_Of_Infants(driver, infants);

		// all fields filled in. Now click on search
		flightSearch.submit_Button(driver);
		logger.debug("all valid data entered and click the Serch button");

		util.waitFor(5000);
		// verify that result appears for the provided journey search
		Assert.assertTrue(util.isElementPresent(driver,
				By.className("searchSummary")));

	}
}
