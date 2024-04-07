package com.Policy.PageObject;

import static com.Policy.Generic.BaseLib.log;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import com.Policy.Generic.CommonUtility;

public class loginPage {
	protected WebDriver driver;

	public loginPage(WebDriver driver) {
		PageFactory.initElements(driver, this);
		this.driver = driver;
	}

	@FindBy(xpath = "//input[@aria-label='Email or phone']")
	private WebElement userName;

	public WebElement getUserName(int timeOut)
	{
		return CommonUtility.elementVisibility(driver, userName, "user name", timeOut);	
	}

	@FindBy(xpath = "//span[text()='Next']")
	private WebElement nextBtn;

	@FindBy(xpath = "//input[@aria-label='Enter your password']")
	private WebElement password;
	
	public WebElement getPassword(int timeOut)
	{
		return CommonUtility.elementVisibility(driver, password, "password", timeOut);	
	}

	@FindBy(xpath = "//img[@role='presentation']")
	private WebElement loginGmailIcon;
	
	public WebElement getLoginGmailIcon(int timeOut)
	{
		return CommonUtility.elementVisibility(driver, loginGmailIcon, "gmail icon", timeOut);	
	}
	
	
	public boolean gmailLogIn(String userName, String password)
	{
		boolean flag=false;
		
		if(CommonUtility.type(getUserName(30), userName, "user name"))
		{
			log.info("userame "+userName+" has been enter");
			if(CommonUtility.clickOnElement(driver, nextBtn, "next button", 30))
			{
				log.info("Clicked on next button");
				if(CommonUtility.type(getPassword(30), password, "password"))
				{
					log.info("password "+password+" has been enter");
					if(CommonUtility.clickOnElement(driver, nextBtn, "next button", 30))
					{
						log.info("Clicked on next button");
						if(getLoginGmailIcon(50)!=null)
						{
							log.info("Logged in Successfull");
							return true;
						}
						else
						{
							log.error("LoggedIn Unsuccessfull");
						}
					}
					else
					{
						log.error("Not able to click on next button");
					}
				}
				else
				{
					log.error("Not able to enter "+password+ " in password field");
				}
			}
			else
			{
				log.error("Not able to click on next button");
			}
		}
		else
		{
			log.error("Not able to enter "+userName+ " in username field");
		}
		
		return flag;
	}

}
