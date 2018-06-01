package com.clearTrip.test;

import org.openqa.selenium.By;
import org.openqa.selenium.support.PageFactory;
import org.testng.Assert;
import org.testng.annotations.Listeners;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

import com.cleartrip.bean.baseClass;
import com.cleartrip.config.Configuration.url;
import com.cleartrip.page.Header;
import com.cleartrip.page.SignInFrame;
import com.cleartrip.report.JyperionListener;
import com.cleartrip.utility.Util;

@Listeners(value = JyperionListener.class)
public class SignInTest extends baseClass {

	private Util util;
	private Header header;
	private SignInFrame signInpage;

	@Parameters({ "username", "password", "scenario" })
	@Test
	public void shouldThrowAnErrorIfSignInDetailsAreMissing(String username,
			String password, String scenario) {
		util = new Util();
		header = PageFactory.initElements(driver, Header.class);
		signInpage = PageFactory.initElements(driver, SignInFrame.class);

		setDriverPath();

		driver.get(url.app_url);

		header.click_On_YourTrip(driver);
		header.click_On_SignIn_btn(driver);
		util.waitFor(1000);
		// signInButton is present on iframe
		util.switchScreen(driver, "modal_window");

		// Enter user credentials
		signInpage.enter_Username(driver, username);
		signInpage.enter_Pwd(driver, password);

		signInpage.click_SignIn_Btn(driver);
		if (scenario.contains("negative")) {
			String errors1 = signInpage.errorMessage(driver);
			Assert.assertTrue(errors1
					.contains("There were errors in your submission"));

		} else {
			header.click_On_YourTrip(driver);
			String signOut = driver.findElement(By.id("global_signout"))
					.getText();
			Assert.assertTrue(signOut.contains("Sign out12"));
		}
	}
}
