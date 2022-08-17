package com.qa.TestCases;

import org.testng.annotations.Test;
import org.testng.AssertJUnit;
import org.testng.Assert;
import org.testng.annotations.Test;

import com.qa.base.TestBase;
import com.qa.pageObjects.LandingPageElements;
import com.qa.pageObjects.LoginPageElements;

public class LandingPageTest extends TestBase{
	
	
	
	
	@Test 
	public void clicSign() {
		LandingPageElements Hp = new LandingPageElements(getDriver());
		Hp.clickonSignInButton();
		LoginPageElements lp = new LoginPageElements(getDriver());
		boolean b = lp.virifyLoginPageOpenOrNot();
		Assert.assertTrue(b);
		
	}

	
	
	
	}


