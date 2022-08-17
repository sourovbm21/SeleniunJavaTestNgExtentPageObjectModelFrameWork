package com.qa.pageObjects;

import org.openqa.selenium.WebDriver;

import com.qa.pageUtils.ElementFetch;

public class LandingPageElements {
	
	WebDriver driver ;
	
	String signInButton = "//span[text()='Hello, Sign in']";
	
	
	public LandingPageElements(WebDriver driver) {
		this.driver = driver;
	}
	
	
	
	public LoginPageElements clickonSignInButton() {
		ElementFetch ef = new ElementFetch();
		ef.getWebElement(driver,"XPATH", signInButton).click();
		return new LoginPageElements(driver);
		
	}
	
}
