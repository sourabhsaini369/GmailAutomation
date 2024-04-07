package com.Policy.PageObject;

import java.util.ArrayList;
import java.util.List;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import com.Policy.Generic.CommonUtility;
import com.Policy.Generic.BaseLib.*;

public class gmailHomePage {

	protected WebDriver driver;

	public gmailHomePage(WebDriver driver) {
		PageFactory.initElements(driver, this);
		this.driver = driver;
	}

	@FindBy(xpath = "//div[@data-tooltip='Inbox']")
	private WebElement inboxBtnFromSideMenu;

	public WebElement getInboxfromSidebar(int timeOut)
	{
		return CommonUtility.elementVisibility(driver, inboxBtnFromSideMenu, "inbox btn", timeOut);	
	}

	public WebElement getEmail(String subjectName, int timeOut)
	{
		String xPath="//div[@role='tabpanel']//tr//span[@class='bog']/span[text()='"+subjectName+"']";
		return CommonUtility.FindElement(driver, xPath, "subject: "+subjectName, timeOut);	
	}

	public WebElement getemailBodyContect(int timeOut)
	{
		String xPath = "//div[@class='a3s aiL ']";
		return CommonUtility.FindElement(driver, xPath, "email content", timeOut);	
	}

	public WebElement emailForwordDisableArowBtn(int timeOut)
	{
		String xPath="//span[@class='Di']/div[3][@aria-disabled='true']/img";
		return CommonUtility.FindElement(driver, xPath, "ero btn", timeOut);	
	}
	
	public WebElement emailForwordArowBtn(int timeOut)
	{
		String xPath="//span[@class='Di']/div[3]";
		return CommonUtility.FindElement(driver, xPath, "ero btn", timeOut);	
	}
	
	
	
	
	@FindBy(xpath = "//div[@aria-label='Show more messages']/span")
	private WebElement page;

	public WebElement getPage(int timeOut)
	{
		return CommonUtility.elementVisibility(driver, page, "page btn", timeOut);	
	}
	
	
	


	public List<String> printEmailContent(String subjectName)
	{
		
		List<String> negativeResult = new ArrayList<String>();
//		if(CommonUtility.clickOnElement(driver, inboxBtnFromSideMenu, "inbox", 30))
//		{
//			System.out.println("Clicked on inbox button");
			
	
			while(true)
			{
				
				if(emailForwordDisableArowBtn(2)!=null)
				{
					
					
					
					if(!emailBodyOpenAndFetchData( subjectName))
					{
						
						negativeResult.add("Email with Subject: "+subjectName+" not found till the last page");
						
					}
					else
					{
						
						System.out.println("Email Found: "+subjectName);
					}
						
						
						
					
					break;
					
					
				}
				else
				{
					
					
					if(emailBodyOpenAndFetchData( subjectName))
					{
						System.out.println("Email Found: "+subjectName);
					break;
						
					}	
					
					String valueee=CommonUtility.getText(driver, getPage(20), "cheking pagination", 30);
					CommonUtility.clickOnElement(driver, emailForwordArowBtn(25), "inbox", 30);
					if(!CommonUtility.verifyPageUpdated(driver, "//div[@aria-label='Show more messages']/span", valueee, 30))
					{
						
						negativeResult.add("After Click on Forward Link, New Page of Email not Load");
						break;
					}
					
					
					
					
					
					
					
					
					
				}
				
				
				
				
				
				
				
				
				
				
				
				
//				
//				if(i>0)
//				{				
//					String valueee=CommonUtility.getText(driver, getPage(20), "cheking pagination", 30);
//					CommonUtility.clickOnElement(driver, emailForwordArowBtn(25), "inbox", 30);
//					if(!CommonUtility.verifyPageUpdated(driver, "//div[@aria-label='Show more messages']/span", valueee, 30))
//					{
//						break;
//					}
//					
//				}
//				
//				if(emailForwordDisableArowBtn(20)==null)
//				{
//					if(getEmail(subjectName,20)!=null)
//					{
//						if(CommonUtility.clickOnElement(driver, getEmail(subjectName,20), subjectName+" email", 30))
//						{
//							System.out.println("Clicked on email "+subjectName);
//							if(getemailBodyContect(20)!=null)
//							{
//								String vallll=CommonUtility.getText(driver, getemailBodyContect(20), subjectName, 30);
//								System.out.println("Email Content    --- "+vallll);
//							}
//							else
//							{
//								System.out.println("The email is blank");
//							}
//						}
//						else
//						{
//							System.out.println("Not able to click on email "+subjectName);
//						}
//					}
//					break;
//				}
//				else
//				{
//					if(getEmail(subjectName,20)==null)
//					{
//						if(CommonUtility.clickOnElement(driver, getEmail(subjectName,20), subjectName+" email", 30))
//						{
//							System.out.println("Clicked on email "+subjectName);
//							if(getemailBodyContect(20)!=null)
//							{
//								String vallll=CommonUtility.getText(driver, getemailBodyContect(20), subjectName, 30);
//								System.out.println("Email Content    --- "+vallll);
//								
//
//							}
//							else
//							{
//								System.out.println("The email is blank");
//							}
//						}
//						else
//						{
//							System.out.println("Not able to click on email "+subjectName);
//						}
//					}
//					else
//					{
//						System.out.println("Could not get the email");
//					}
//					
//				}
//				
//				i++;
			}
			
//		}
//		else
//		{
//			System.out.println("Not able to Click on inbox button");
//			negativeResult.add("Not able to Click on inbox button");
//		}
		
		return negativeResult;

	}
	
	
	
	public boolean emailBodyOpenAndFetchData(String subjectName)
	{
		
		boolean flag = false;
		
		if(getEmail(subjectName,3)!=null)
		{
			if(CommonUtility.clickOnElement(driver, getEmail(subjectName,3), subjectName+" email", 10))
			{
				System.out.println("Clicked on email "+subjectName);
				if(getemailBodyContect(10)!=null)
				{
					String vallll=CommonUtility.getText(driver, getemailBodyContect(5), subjectName, 5);
					System.out.println("Email Content    --- "+vallll);
					flag = true;
				}
				else
				{
					System.out.println("The email is blank");
					
					flag =true;
				}
			}
			else
			{
				System.out.println("Not able to click on email "+subjectName);
			}
		}
		
	
		
		
		return flag;
	}


}
