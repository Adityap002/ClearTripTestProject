package com.cleartrip.page;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class CleartripProduct_Search {

	@FindBy(linkText = "Flights")
	private WebElement flights;
	
	@FindBy(linkText = "Hotels")
	private WebElement hotels;
	
	@FindBy(linkText = "Local")
	private WebElement local;
	
	@FindBy(linkText = "Trains")
	private WebElement trains;
	
	@FindBy(linkText = "Flight Deals")
	private WebElement deals;
	
	public void click_On_Flights(WebDriver driver){
		flights.click();
	}
	
	public void click_On_Hotels(WebDriver driver){
		hotels.click();
	}
	
	public void click_On_Local(WebDriver driver){
		local.click();
	}
	
	public void click_On_Trains(WebDriver driver){
		trains.click();
	}
	
	public void click_On_Deals(WebDriver driver){
		deals.click();
	}

}
