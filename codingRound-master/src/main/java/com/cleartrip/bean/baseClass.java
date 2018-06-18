package com.cleartrip.bean;

import java.io.File;
import java.util.concurrent.TimeUnit;

import org.apache.commons.io.FileUtils;
import org.apache.log4j.Logger;
import org.openqa.selenium.By;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
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
import com.cleartrip.config.Configuration.excelSetUp;
import com.cleartrip.utility.ExcelUtility;
import com.sun.javafx.PlatformUtil;

@SuppressWarnings("restriction")
public class baseClass {

	public static WebDriver driver;
	private WebDriverWait wait;
	private static Logger logger = Logger.getLogger(baseClass.class.getName());

	@BeforeClass
	public static void setDriverPath() {
		// null check for driver object
		boolean ff_true = false;
		boolean ie_true = false;
		boolean chrome_true = false;
		logger.debug("going to set driver path");
		if (driver == null) {
			logger.debug("drive is not null");
			// getting browse variable from the Configuration class
			if (browser.browse.equalsIgnoreCase("ff")) {
				ff_true = true;
				logger.debug("Browser is firefox");
			} else if (browser.browse.equalsIgnoreCase("ie")) {
				ie_true = true;
				logger.debug("Browser is InternetExplorer");
			} else if (browser.browse.equalsIgnoreCase("chrome")) {
				chrome_true = true;
				logger.debug("Browser is Chrome");
			}
		}
		if (chrome_true == false || ie_true == false || ff_true == false) {
			if (chrome_true == true) {
				if (PlatformUtil.isMac()) {
					logger.debug("System is Mac and browser is Chrome");
					System.setProperty("webdriver.chrome.driver",
							"chromedriver");
				}
				if (PlatformUtil.isWindows()) {
					logger.debug("System is Windows and browser is Chrome");
					System.setProperty("webdriver.chrome.driver",
							"chromedriver.exe");
				}
				if (PlatformUtil.isLinux()) {
					logger.debug("System is Linux and browser is Chrome");
					System.setProperty("webdriver.chrome.driver",
							"chromedriver_linux");
				}
				// instantiate Chrome Driver
				driver = new ChromeDriver();
			} else if (ff_true == true) {
				if (PlatformUtil.isMac()) {
					logger.debug("System is Mac and browser is FF");
					// need to download driver
					System.setProperty("webdriver.gecko.driver", "geckodriver");
				}
				if (PlatformUtil.isWindows()) {
					logger.debug("System is Window and browser is FF");
					// need to download driver
					System.setProperty("webdriver.gecko.driver",
							"geckodriver.exe");
				}
				if (PlatformUtil.isLinux()) {
					logger.debug("System is linux and browser is FF");
					// need to download driver
					System.setProperty("webdriver.gecko.driver",
							"geckodriver_linux");
				}
				// instantiate Firefox Driver
				driver = new FirefoxDriver();
				System.out.println(driver);
			} else if (ie_true == true) {
				if (PlatformUtil.isMac()) {
					logger.debug("System is Mac and browser is IE");
					// need to download driver
					System.setProperty("webdriver.ie.driver", "iexploredriver");
				}
				if (PlatformUtil.isWindows()) {
					logger.debug("System is Window and browser is IE");
					// need to download driver
					System.setProperty("webdriver.ie.driver",
							"iexploredriver.exe");
				}
				if (PlatformUtil.isLinux()) {
					logger.debug("System is linux and browser is IE");
					// need to download driver
					System.setProperty("webdriver.ie.driver",
							"iexploredriver_linux");
				}
				// instantiate Internet Explorer Driver
				driver = new InternetExplorerDriver();
				logger.debug("driver is instantiate");
			}
		}
		// As selenium provided lot of different driver so based on need we can
		// add more also like safari etc.
		// set implicit wait for 5 Sec
		driver.manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS);

		// maximize browser
		driver.manage().window().maximize();

		try {
			ExcelUtility.setExcelFile(excelSetUp.excelLocaation
					+ excelSetUp.workbookName, "LoginTests");
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	public static void closeBrowser() {
		driver.close();
		logger.debug("Closeing the browser");
		driver = null;
	}

	@AfterClass
	public static void quitBrowser() {
		driver.quit();
		logger.debug("Quiting the browser");
		driver = null;
	}

	public static void switchtoIFrame(WebElement arg) {
		driver.switchTo().frame(arg);
		logger.debug("switching window to Iframe");
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

	public void isFrameToBeAvailable_Wait(WebDriver driver, String locator) {
		instantiateWaitObject(driver);
		wait.until(ExpectedConditions.frameToBeAvailableAndSwitchToIt(locator));
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

	// Below is the syntax which is used to check if the element is Selected or
	// not. It returns false when the element is not Selected.
	public boolean isElementSelected(WebDriver driver, By by) {
		WebElement element = driver.findElement(by);
		return element.isSelected();
	}

	public static void takeSnapShot(WebDriver driver, String fileWithPath)
			throws Exception {

		// Convert web driver object to TakeScreenshot

		TakesScreenshot scrShot = ((TakesScreenshot) driver);

		// Call getScreenshotAs method to create image file

		File SrcFile = scrShot.getScreenshotAs(OutputType.FILE);

		// Move image file to new destination

		File DestFile = new File(fileWithPath);

		// Copy file at destination

		FileUtils.copyFile(SrcFile, DestFile);

	}
}
