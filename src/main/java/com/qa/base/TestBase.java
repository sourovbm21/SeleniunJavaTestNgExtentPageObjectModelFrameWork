package com.qa.base;

import java.io.File;
import java.io.IOException;
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
import com.qa.pageUtils.ScreenShotUtilitis;

import io.github.bonigarcia.wdm.WebDriverManager;

public class TestBase {
	
	
	private static ThreadLocal <ExtentTest> extent_local = new ThreadLocal <ExtentTest>();
	public ThreadLocal<WebDriver> driver = new ThreadLocal<>();
	//public static ThreadLocal<ExtentReports> extent = new ThreadLocal<>();
	public static ExtentReports extent;
	public static ExtentTest logger;
	public ExtentHtmlReporter htmlReporter;
	//public ExtentSparkReporter ex;
	
	public void setDriver(WebDriver driver) {
		this.driver.set(driver);
	}
	public WebDriver  getDriver() {
		return this.driver.get();
	}
	
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
	 //   extent_local.set(logger);
		setUpDriver(browserName);
		getDriver().manage().window().maximize();
		getDriver().manage().deleteAllCookies();
		getDriver().get(Constants.url);
		getDriver().manage().timeouts().implicitlyWait(10,TimeUnit.SECONDS);
		
	}
	
	@AfterMethod
	public void afterMethodMethod(ITestResult result ) throws IOException {
		if (result.getStatus()==ITestResult.SUCCESS) {
			String methodName = result.getMethod().getMethodName();
			String logTest = "Test Case" + methodName + "passed";
			Markup m =MarkupHelper.createLabel(logTest, ExtentColor.GREEN);
			logger.log(Status.PASS,m);
			
		}else if (result.getStatus()==ITestResult.FAILURE) {
			String methodName = result.getMethod().getMethodName();
			String logTest = "Test Case" + methodName + "Failed";
			Markup m =MarkupHelper.createLabel(logTest, ExtentColor.RED);
			logger.log(Status.FAIL,m);
			String paht = ScreenShotUtilitis.getScreenShot(getDriver(), result.getName());
			//logger.addScreenCaptureFromPath(methodName +" "+paht);
			logger.addScreenCaptureFromPath(paht);
			
		}else if (result.getStatus()==ITestResult.SKIP) {
			String methodName = result.getMethod().getMethodName();
			String logTest = "Test Case" + methodName + "Skiped";
			Markup m =MarkupHelper.createLabel(logTest, ExtentColor.LIME);
			logger.log(Status.FAIL,m);
			
		}
		
		getDriver().quit();
	}
	
	@AfterTest
	public void afterTestMethod() {
		extent.flush();
		
	}
	
	public void setUpDriver(String browserName) {
		
		if (browserName.contentEquals("chrome")){
			WebDriverManager.chromedriver().setup();
			setDriver( new ChromeDriver());
			
		}else if(browserName.contentEquals("firefox") ){
		WebDriverManager.firefoxdriver().setup();
		setDriver( new FirefoxDriver());
		}
	}

}
