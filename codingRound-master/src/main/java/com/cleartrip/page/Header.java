package com.cleartrip.page;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class Header {

	@FindBy(id = "userAccountLink")
	private WebElement yr_Trip;

	@FindBy(id = "SignIn")
	private WebElement signIn_BTN;

	public void click_On_YourTrip(WebDriver driver) {
		yr_Trip.click();
	}
	
	public void click_On_SignIn_btn(WebDriver driver) {
		signIn_BTN.click();
	}
}
