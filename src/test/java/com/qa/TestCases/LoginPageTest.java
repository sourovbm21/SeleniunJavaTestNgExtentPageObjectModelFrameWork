package com.qa.TestCases;

import org.testng.Assert;
import org.testng.annotations.Test;
import org.testng.internal.BaseTestMethod;

import com.qa.base.TestBase;
import com.qa.pageObjects.LandingPageElements;
import com.qa.pageObjects.LoginPageElements;

public class LoginPageTest extends TestBase{
	
	
	
	
	
	
	
	@Test 
	public void checkemaiIdField () {
		LandingPageElements Hp = new LandingPageElements(getDriver());
		Hp.clickonSignInButton();
		LoginPageElements lp = new LoginPageElements(getDriver());
		TestBase.logger.info("entering email in email field");
		lp.enterEmailid();
		
	}
	@Test
	public void clickonContinueBtn() {
		LandingPageElements Hp = new LandingPageElements(getDriver());
		Hp.clickonSignInButton();
		LoginPageElements lp = new LoginPageElements(getDriver());
		lp.enterEmailid();
		boolean b = lp.clickContinueButton();
		TestBase.logger.info("Clicking on continue button");
		Assert.assertTrue(b);
		
	}

}
