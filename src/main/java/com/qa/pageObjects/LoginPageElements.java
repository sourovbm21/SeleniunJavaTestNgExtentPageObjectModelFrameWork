package com.qa.pageObjects;

import org.openqa.selenium.WebDriver;

import com.qa.pageUtils.ElementFetch;

public class LoginPageElements {
	
	
	WebDriver driver ;
	
	String loginText = "//h1[contains(text(),'Login')]";
	String emailAddress = "ap_email";
	String  comtinueBtn =  "continue";
	String errorMsg = "#auth-error-message-box";

	
	public LoginPageElements(WebDriver driver) {
		this.driver = driver;
	}
	
	public boolean  virifyLoginPageOpenOrNot() {
		ElementFetch ef = new ElementFetch();
		return ef.getWebElement(driver,"XPATH",loginText).isDisplayed();
	}
	
	public void enterEmailid() {
		ElementFetch ef = new ElementFetch();
		ef.getWebElement(driver,"ID",emailAddress ).sendKeys("HELLO@gmail.com");
	}
	
	public boolean clickContinueButton () {
		ElementFetch ef = new ElementFetch();
		ef.getWebElement(driver,"ID", comtinueBtn).click();
		return ef.getWebElement(driver,"CSS", comtinueBtn).isDisplayed();
		
		
	}
	
}
