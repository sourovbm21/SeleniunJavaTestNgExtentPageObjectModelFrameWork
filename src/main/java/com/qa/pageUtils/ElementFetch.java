package com.qa.pageUtils;

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import com.qa.base.TestBase;

public class ElementFetch {
	
	
	
	
	
	
	public WebElement getWebElement(WebDriver driver, String identifierType, String identifierValue) {
		
		switch (identifierType) {
		case "ID":
			return driver.findElement(By.id(identifierValue));
		case "CSS":
			return driver.findElement(By.cssSelector(identifierValue));
			
		case "TAGNAME":
			return driver.findElement(By.tagName(identifierValue));
		case "XPATH":
			return driver.findElement(By.xpath(identifierValue));
		case "NAME":
			return driver.findElement(By.name(identifierValue));
		case "CLASSNAME":
			return driver.findElement(By.className(identifierValue));
		default:
			return null;
		}
		
		
	}
	
	
	
	public List<WebElement> getWebElements(WebDriver driver,String identifierType, String identifierValue) {
		
		switch (identifierType) {
		case "ID":
			return driver.findElements(By.id(identifierValue));
		case "CSS":
			return driver.findElements(By.cssSelector(identifierValue));
			
		case "TAGNAME":
			return driver.findElements(By.tagName(identifierValue));
		case "XPATH":
			return driver.findElements(By.xpath(identifierValue));
		case "NAME":
			return driver.findElements(By.name(identifierValue));
		case "CLASSNAME":
			return driver.findElements(By.className(identifierValue));
		default:
			return null;
		}
		
		
	}

}
