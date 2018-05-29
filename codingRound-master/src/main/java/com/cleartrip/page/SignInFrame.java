package com.cleartrip.page;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class SignInFrame {
	
	@FindBy(id="email")
	private WebElement usr_name;
	
	@FindBy(id="password")
	private WebElement pwd;
	
	@FindBy(id="signInButton")
	private WebElement signInBtn;
	
	@FindBy(id = "errors1")
	private WebElement error; 
	
	public void enter_Username(WebDriver driver, String u_name) {
		usr_name.sendKeys(u_name);
	}
	
	public void enter_Pwd(WebDriver driver,String password) {
		pwd.sendKeys(password);
	}
	
	public void click_SignIn_Btn(WebDriver driver) {
		signInBtn.click();
	}
	
	public String errorMessage(WebDriver driver){
		return error.getText();
	}

}
