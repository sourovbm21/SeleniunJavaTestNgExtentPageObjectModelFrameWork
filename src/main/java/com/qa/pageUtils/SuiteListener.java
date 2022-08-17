package com.qa.pageUtils;

import java.io.File;
import java.io.IOException;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.testng.ITestContext;
import org.testng.ITestListener;
import org.testng.ITestResult;

import com.qa.base.TestBase;

public class SuiteListener implements ITestListener{
	
	
	
	
	
	
	
	public void onTestStart(ITestResult result) {
		
	}

	public void onTestSuccess(ITestResult result) {
	
	}

	public void onTestFailure(ITestResult result) {
	
	}

	public void onTestSkipped(ITestResult result) {

	}

	public void onTestFailedButWithinSuccessPercentage(ITestResult result) {
		String  fileName=System.getProperty("user.dir")+ File.separator +"screenshots"+File.separator + result.getMethod().getMethodName();
		Object currentInstance =  result.getInstance();
		File file = ((TakesScreenshot)((TestBase)currentInstance).getDriver()).getScreenshotAs(OutputType.FILE);
		try {
			FileUtils.copyFile(file, new File(fileName + ".png"));
		} catch (IOException e) {
						e.printStackTrace();
		}
		
		
		
		
		
	
	}

	public void onTestFailedWithTimeout(ITestResult result) {

	}

	public void onStart(ITestContext context) {

	}

	public void onFinish(ITestContext context) {

	}

	
	
	

}
