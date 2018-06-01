package com.cleartrip.bean;

import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.ie.InternetExplorerDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;

import com.cleartrip.config.Configuration.browser;
import com.sun.javafx.PlatformUtil;

@SuppressWarnings("restriction")
public class baseClass {

	public static WebDriver driver;
	private WebDriverWait wait;

	@BeforeClass
	public static void setDriverPath() {
		// null check for driver object
		boolean ff_true = false;
		boolean ie_true = false;
		boolean chrome_true = false;
		if (driver == null) {
			// getting browse variable from the Configuration class
			if (browser.browse.equalsIgnoreCase("ff")) {
				ff_true = true;
			} else if (browser.browse.equalsIgnoreCase("ie")) {
				ie_true = true;
			} else if (browser.browse.equalsIgnoreCase("chrome")) {
				chrome_true = true;
			}
		}
		if (chrome_true == false || ie_true == false || ff_true == false) {
			if (chrome_true == true) {
				if (PlatformUtil.isMac()) {
					System.setProperty("webdriver.chrome.driver",
							"chromedriver");
				}
				if (PlatformUtil.isWindows()) {
					System.setProperty("webdriver.chrome.driver",
							"chromedriver.exe");
				}
				if (PlatformUtil.isLinux()) {
					System.setProperty("webdriver.chrome.driver",
							"chromedriver_linux");
				}
				// instantiate Chrome Driver
				driver = new ChromeDriver();
			} else if (ff_true == true) {
				System.out.println("true ff");
				if (PlatformUtil.isMac()) {
					// need to download driver
					System.setProperty("webdriver.gecko.driver", "geckodriver");
				}
				if (PlatformUtil.isWindows()) {
					// need to download driver
					System.setProperty("webdriver.gecko.driver",
							"geckodriver.exe");
				}
				if (PlatformUtil.isLinux()) {
					// need to download driver
					System.setProperty("webdriver.gecko.driver",
							"geckodriver_linux");
				}
				// instantiate Firefox Driver
				driver = new FirefoxDriver();
				System.out.println(driver);
			} else if (ie_true == true) {
				if (PlatformUtil.isMac()) {
					// need to download driver
					System.setProperty("webdriver.ie.driver", "iexploredriver");
				}
				if (PlatformUtil.isWindows()) {
					// need to download driver
					System.setProperty("webdriver.ie.driver",
							"iexploredriver.exe");
				}
				if (PlatformUtil.isLinux()) {
					// need to download driver
					System.setProperty("webdriver.ie.driver",
							"iexploredriver_linux");
				}
				// instantiate Internet Explorer Driver
				driver = new InternetExplorerDriver();
			}
		}
		// As selenium provided lot of different driver so based on need we can
		// add more also like safari etc.
		// set implicit wait for 5 Sec
		driver.manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS);

		// maximize browser
		driver.manage().window().maximize();
	}

	public static void closeBrowser() {
		driver.close();
		driver = null;
	}

	@AfterClass
	public static void quitBrowser() {
		driver.quit();
		driver = null;
	}

	public static void switchtoIFrame(WebElement arg) {
		driver.switchTo().frame(arg);
	}

	// Instantiate wait object
	private void instantiateWaitObject(WebDriver driver) {
		if (wait == null) {
			wait = new WebDriverWait(driver, 10);
		}
	}

	// Below is the syntax to check for the element presence using
	// WebDriverWait. Here we need to pass locator and wait time as the
	// parameters to the below method. Here it is checking that an element is
	// present on the DOM of a page or not. That does not necessarily mean that
	// the element is visible. ExpectedConditions will return true once the
	// element is found in the DOM.
	public void isElementPresent_Wait(WebDriver driver, By locator) {
		instantiateWaitObject(driver);
		wait.until(ExpectedConditions.presenceOfElementLocated(locator));
	}

	// Below is the syntax for checking an element is visible and enabled such
	// that we can click on the element. We need to pass wait time and the
	// locator as parameters.
	public void isElementClickable_Wait(WebDriver driver, By locator) {
		instantiateWaitObject(driver);
		wait.until(ExpectedConditions.elementToBeClickable(locator));
	}

	// Below is the syntax to check if the element is present on the DOM of a
	// page and visible. Visibility means that the element is not just displayed
	// but also should also has a height and width that is greater than 0.
	public void isElementVisible_Wait(WebDriver driver, By locator) {
		instantiateWaitObject(driver);
		wait.until(ExpectedConditions.visibilityOfElementLocated(locator));
	}

	// Below is the syntax which is used for checking that an element is either
	// invisible or not present on the DOM.
	public void isElementInVisible_Wait(WebDriver driver, By locator) {
		instantiateWaitObject(driver);
		wait.until(ExpectedConditions.invisibilityOfElementLocated(locator));
	}

	// Below is the syntax which is used to check if the element is enabled or
	// not
	public boolean isElementEnabled(WebDriver driver, By by) {
		WebElement element = driver.findElement(by);
		return element.isEnabled();
	}

	// Below is the syntax which is used to check if the element is displayed or
	// not. It returns false when the element is not present in DOM.
	public boolean isElementDisplayed(WebDriver driver, By by) {
		WebElement element = driver.findElement(by);
		return element.isDisplayed();
	}
}
