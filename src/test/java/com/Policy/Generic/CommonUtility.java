package com.Policy.Generic;

import org.openqa.selenium.By;
import static com.Policy.Generic.BaseLib.log;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.StaleElementReferenceException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;


public class CommonUtility {

	
	
	public WebElement explicitWaitForElementClickable(WebDriver driver, WebElement element, long waitTimeInSeconds) {
		WebDriverWait wait = new WebDriverWait(driver, waitTimeInSeconds);
		return wait.until(ExpectedConditions.elementToBeClickable(element));
	}

	public WebElement explicitWaitForElementVisibility(WebDriver driver, WebElement element, long waitTimeInSeconds) {
		WebDriverWait wait = new WebDriverWait(driver, waitTimeInSeconds);
		return wait.until(ExpectedConditions.visibilityOf(element));
	}
	
	public static boolean type(WebElement ele, String value, String elementName) {
		boolean flag = false;
		
		try {
			ele.clear();
			ele.sendKeys(value);
			System.out.println("Successfully Entered Value:-" + value);
			flag = true;
		} catch (Exception e) {
			System.out.println("Not able to enter the Value:-" + value);
			e.printStackTrace();
			flag = false;
		}
		return flag;
	}
	
	public static boolean clickOnElement(WebDriver driver, WebElement ele, String elementName, int timeOut) {
		if (elementVisibility(driver, ele, timeOut) != null) {
			try {
				ele.click();
				return true;
			} catch (Exception e) {
				System.out.println("Not able to click on element " + elementName);
				e.printStackTrace();
				e.getMessage();

				return false;
			}
		} else {
			System.out.println("Not able to click on element " + elementName);
			return false;
		}

	}

	public static WebElement elementVisibility(WebDriver driver, WebElement element, String elementName,int timeOut) {
		WebDriverWait wait = new WebDriverWait(driver, timeOut);
		try {
			return wait.until(ExpectedConditions.visibilityOf(element));

		} catch (Exception e) {
			
			log.info(elementName+" is not visible");
			e.printStackTrace();
			e.getMessage();
			return null;
		}
	}
	
	
	public static WebElement elementVisibility(WebDriver driver, WebElement element,int timeOut) {
		WebDriverWait wait = new WebDriverWait(driver, timeOut);
		try {
			return wait.until(ExpectedConditions.visibilityOf(element));

		} catch (Exception e) {
			
			e.printStackTrace();
			e.getMessage();
			return null;
		}
	}
	
	public static String getText(WebDriver driver, WebElement ele, String elementName, int timeOut) {
		if (elementVisibility(driver, ele, timeOut) != null) {
			try {
				String value=ele.getText();
				 System.out.println(elementName+ " text "+value);
				return value;
			} catch (Exception e) {
				System.out.println("Not able to get the text from " + elementName);
				e.printStackTrace();
				e.getMessage();

				return null;
			}
		} else {
			System.out.println(elementName+" is not visible");
			return null;
		}

	}
	
	public static WebElement FindElement(WebDriver driver, String xpath, String elementName, int timeOut) {

		try {
			int timeout = 0;
			WebElement ele = null;
			while (true) {
				try {
					ele = driver.findElement(By.xpath(xpath));
					break;
				} 
				catch(StaleElementReferenceException e)
				{
					Thread.sleep(250);
					ele = driver.findElement(By.xpath(xpath));
					break;	
				}
				catch (Exception e) {

					Thread.sleep(250);
					timeout++;
					if (timeout > timeOut * 4) {
						ele = driver.findElement(By.xpath(xpath));
						break;
					}
				}
			}
			if (elementName != null && !elementName.isEmpty() && ele != null) {
				scrollDownThroughWebelement(driver, ele, elementName);

			}
			return ele;
		} catch (Exception e) {
			

		}
		return null;
	}
	
	public static boolean scrollDownThroughWebelement(WebDriver driver, WebElement Element, String elementName) {
		try {
			((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView(true);", Element);
			if (elementName != "")
			//	LogsManager.getLogger().info("Window Scrolled to " + elementName);
				System.out.println("Window Scrolled to " + elementName);

			return true;
		} catch (Exception e) {
			if (elementName != "")
			//	LogsManager.getLogger().error("Can not scrolled Window to " + elementName);
				System.out.println("Can not scrolled Window to " + elementName);

			return false;
		}
	}
	
	public static boolean verifyPageUpdated(WebDriver driver, String xPath, String value, int timeOut)
	{
		WebDriverWait wait=new WebDriverWait(driver,timeOut);
		try
		{
			return wait.until(ExpectedConditions.not(ExpectedConditions.textToBe(By.xpath(xPath), value)));
		}
		catch(Exception ex)
		{
			return false;
		}
		
	}
	
}
