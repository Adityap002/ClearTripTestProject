package com.cleartrip.utility;

import java.text.SimpleDateFormat;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Date;

import org.openqa.selenium.By;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class Util {

	public void waitFor(int durationInMilliSeconds) {
		try {
			Thread.sleep(durationInMilliSeconds);
		} catch (InterruptedException e) {
			e.printStackTrace(); // To change body of catch statement use File |
									// Settings | File Templates.
		}
	}

	public boolean isElementPresent(WebDriver driver, By by) {
		try {
			driver.findElement(by);
			return true;
		} catch (NoSuchElementException e) {
			return false;
		}
	}

	// switch frame
	public void switchScreen(WebDriver driver, WebElement element) {
		driver.switchTo().frame(element);
	}

	public void switchScreen(WebDriver driver, String element) {
		driver.switchTo().frame(element);
	}

	public void switchScreen(WebDriver driver, int element) {
		driver.switchTo().frame(element);
	}
	// date

	public String[] datePicker(String date) {

		SimpleDateFormat dt = new SimpleDateFormat("dd-MMMM-yyyy");
		String formatteddate = dt.format(new Date(date));

		String[] date1 = formatteddate.split("-");
		return date1;
	}

	public String[] localDatePicker(String date) {
		
		SimpleDateFormat dt = new SimpleDateFormat("dd-MM-yyyy");
		String formatteddate = dt.format(new Date(date));
		
		String[] date1 = formatteddate.split("-");
		return date1;
	}
	
	public void clickRightClickArrow(WebDriver driver) {
		driver.findElement(By.xpath("//a[contains(@class,'nextMonth')]")).click();
	}
	
	
	public void click_On_Date(WebDriver driver, String xpathForYear, String xpathForMonth,
			String xpathForDateClick) {
		while (true) {
			try {
				driver.findElement(By.xpath(xpathForYear)).isDisplayed();
				driver.findElement(By.xpath(xpathForMonth)).isDisplayed();
				driver.findElement(By.xpath(xpathForDateClick)).click();
				break;
			} catch (Exception e) {
				clickRightClickArrow(driver);
			}
		}
	}

}
