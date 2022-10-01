package com.qa.TestCases;

import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;
import org.testng.AssertJUnit;

import java.io.IOException;
import java.util.HashMap;
import java.util.List;

import org.testng.Assert;
import org.testng.annotations.Test;

import com.qa.base.TestBase;
import com.qa.pageObjects.LandingPageElements;
import com.qa.pageObjects.LoginPageElements;
import com.qa.pageUtils.DataReader;

public class LandingPageTest extends TestBase{
	
	
	
	
	@Test 
	public void clicSign() {
		LandingPageElements Hp = new LandingPageElements(getDriver());
		Hp.clickonSignInButton();
		LoginPageElements lp = new LoginPageElements(getDriver());
		boolean b = lp.virifyLoginPageOpenOrNot();
		Assert.assertTrue(b);
		
	}
	
	//This data provider is an frameWork of data driven approach from Json file.
	//We can drive data using excel file as well.
	@Test(dataProvider = "getData")
	public void invalidLoginTest(HashMap<String ,String >input) {
	//	userName.sendKeys(input.get("email"));
	//	passWord.sendKeys(input.get("password"));
	//	loginBtn.click();
		
		
	}
	@DataProvider
	public Object[][] getData() throws IOException {
		
		DataReader reader = new DataReader();
		List<HashMap<String,String>> data = reader.getDataToMap(System.getProperty("user.dir")+"/src/main/java/com/qa/TestData/LoginData.json");
		return new Object[][] {{data.get(0)},{data.get(1)}};
	}

	
	
	
	}


