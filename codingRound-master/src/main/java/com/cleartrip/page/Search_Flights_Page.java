package com.cleartrip.page;

import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.Select;

public class Search_Flights_Page {
	@FindBy(id="OneWay")
	private WebElement one_Way_Radiobtn;
	
	@FindBy(id="RoundTrip")
	private WebElement round_Trip_Radiobtn;
	
	@FindBy(id="MultiCity")
	private WebElement multi_City_Radiobtn;
	
	@FindBy(id="FromTag")
	private WebElement from_City_Airport;
	
	@FindBy(id="ToTag")
	private WebElement to_City_Airport;
	
	@FindBy(id="DepartDate")
	private WebElement depart_On;
	
	@FindBy(id="html/body/div[1]/div[2]/table/tbody/tr[4]/td[4]/a")
	private WebElement depart_Date;
	
	@FindBy(id="Adults")
	private WebElement adults;
	
	@FindBy(id="Childrens")
	private WebElement child;
	
	@FindBy(id="Infants")
	private WebElement infant;
	
	@FindBy(id="SearchBtn")
	private WebElement searchBtn;
	
	public void clickOnOneWay(WebDriver driver){
		one_Way_Radiobtn.click();
	}
	
	public void clickOnRoundTrip(WebDriver driver){
		round_Trip_Radiobtn.click();
	}
	
	public void clickOnMultiCity(WebDriver driver){
		multi_City_Radiobtn.click();
	}
	
	public void enter_From(WebDriver driver, String from){
		from_City_Airport.clear();
		from_City_Airport.sendKeys(from);
		
		String fromFlights = "//ul[@id='ui-id-1']/li[1]//a[contains(text(),'"+from+"')]";
		driver.findElement(By.xpath(fromFlights)).click();
	}
	
	public void enter_To(WebDriver driver, String to){
		to_City_Airport.clear();
		to_City_Airport.sendKeys(to);
		
		String toFlights = "//ul[@id='ui-id-2']/li[1]//a[contains(text(),'"+to+"')]";
		driver.findElement(By.xpath(toFlights)).click();
		
	}
	
	public void no_Of_Adults(WebDriver driver, String adult) {
		Select element = new Select(adults);
		element.selectByVisibleText(adult);
	}
	
	public void no_Of_Childres(WebDriver driver, String children) {
		Select element = new Select(child);
		element.selectByVisibleText(children);
	}
	
	public void no_Of_Infants(WebDriver driver, String infants) {
		Select element = new Select(infant);
		element.selectByVisibleText(infants);
	}
	
	public void submit_Button(WebDriver driver) {
		searchBtn.submit();;
	}
	
	public void enter_Depart_On(WebDriver driver, String date){
		depart_On.clear();
		depart_On.sendKeys(date,Keys.ENTER);
	}
}
