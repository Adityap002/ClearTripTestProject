package com.cleartrip.page;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.Select;

public class Search_Hotels_Page {

	@FindBy(id = "Tags")
	private WebElement where;

	@FindBy(id = "CheckInDate")
	private WebElement checkInDate;

	@FindBy(id = "CheckOutDate")
	private WebElement checkOutDate;

	@FindBy(id = "travellersOnhome")
	private WebElement traveller;

	@FindBy(id = "SearchHotelsButton")
	private WebElement searchHotels_Btn;

	public void enterLocation_Where(WebDriver driver, String location) {
		where.sendKeys(location);
	}

	public void enterDate_CheckIn(WebDriver driver, String date) {

	}
	
	public void enterDate_CheckOut(WebDriver driver, String date) {

	}
	
	public void select_Traveller(WebDriver driver, String passenger) {
		Select element = new Select(traveller);
		element.selectByVisibleText(passenger);
	}
	
	public void submitBtn(WebDriver driver) {
		searchHotels_Btn.click();
	}

}
