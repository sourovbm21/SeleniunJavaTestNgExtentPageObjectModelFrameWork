package com.qa.base;

import java.io.File;
import java.lang.reflect.Method;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.ITestResult;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Parameters;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.Status;
import com.aventstack.extentreports.markuputils.ExtentColor;
import com.aventstack.extentreports.markuputils.Markup;
import com.aventstack.extentreports.markuputils.MarkupHelper;
import com.aventstack.extentreports.reporter.ExtentHtmlReporter;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;
import com.aventstack.extentreports.reporter.configuration.Theme;
import com.qa.pageUtils.Constants;

import io.github.bonigarcia.wdm.WebDriverManager;

public class TestBase {
	
	public static WebDriver driver;
	public static ExtentReports extent;
	public static ExtentTest logger;
	public ExtentHtmlReporter htmlReporter;
	//public ExtentSparkReporter ex;
	
	
	
	@BeforeTest
	public void beforeTestMethod() {
	 htmlReporter = new ExtentHtmlReporter(System.getProperty("user.dir")+ File.separator + "reports" +File.separator + "AutomationReport.html");
	 htmlReporter.config().setEncoding("utf-8");
	 htmlReporter.config().setDocumentTitle("Automation Report");
	 htmlReporter.config().setReportName("Automation test result");
	 htmlReporter.config().setTheme(Theme.DARK);	 
	 extent = new ExtentReports();
	 extent.attachReporter(htmlReporter);
	 extent.setSystemInfo("Automation Tester","Tohidur Rahman");
	 
	}
	
	@BeforeMethod
	@Parameters(value= {"browserName"})
	public void beforeMethodMethod(String browserName,Method testMethod) {
	    logger = extent.createTest(testMethod.getName());
		setUpDriver(browserName);
		driver.manage().window().maximize();
		driver.get(Constants.url);
		driver.manage().timeouts().implicitlyWait(10,TimeUnit.SECONDS);
		
	}
	
	@AfterMethod
	public void afterMethodMethod(ITestResult result ) {
		if (result.getStatus()==ITestResult.SUCCESS) {
			String methodName = result.getMethod().getMethodName();
			String logTest = "Test Case" + methodName + "passed";
			Markup m =MarkupHelper.createLabel(logTest, ExtentColor.RED);
			logger.log(Status.PASS,m);
			
		}else if (result.getStatus()==ITestResult.SUCCESS) {
			String methodName = result.getMethod().getMethodName();
			String logTest = "Test Case" + methodName + "Failed";
			Markup m =MarkupHelper.createLabel(logTest, ExtentColor.RED);
			logger.log(Status.FAIL,m);
			
		}
		driver.quit();
	}
	
	@AfterTest
	public void afterTestMethod() {
		extent.flush();
		
	}
	
	public void setUpDriver(String browserName) {
		
		if (browserName.contentEquals("chrome")){
			WebDriverManager.chromedriver().setup();
			driver = new ChromeDriver();
			
		}else if(browserName.contentEquals("firefox") ){
		WebDriverManager.chromedriver().setup();
			driver= new FirefoxDriver();
		}
	}

}
