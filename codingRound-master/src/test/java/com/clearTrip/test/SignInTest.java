package com.clearTrip.test;

import org.openqa.selenium.support.PageFactory;
import org.testng.Assert;
import org.testng.annotations.Test;

import com.cleartrip.bean.baseClass;
import com.cleartrip.config.Configuration.url;
import com.cleartrip.page.Header;
import com.cleartrip.page.SignInFrame;
import com.cleartrip.utility.Util;

public class SignInTest extends baseClass {

	private Util util;
	private Header header;
	private SignInFrame signInpage;

	@Test
	public void shouldThrowAnErrorIfSignInDetailsAreMissing() {
		util = new Util();
		header = PageFactory.initElements(driver, Header.class);
		signInpage = PageFactory.initElements(driver, SignInFrame.class);

		setDriverPath();

		driver.get(url.app_url);
		util.waitFor(1000);

		header.click_On_YourTrip(driver);
		header.click_On_SignIn_btn(driver);
		util.waitFor(1000);
		// signInButton is present on iframe
		driver.switchTo().frame("modal_window");
		signInpage.click_SignIn_Btn(driver);

		String errors1 = signInpage.errorMessage(driver);
		Assert.assertTrue(errors1
				.contains("There were errors in your submission"));

		quitBrowser();
	}
}
