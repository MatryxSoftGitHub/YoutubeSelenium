package com.matryxsoft.youtube.Pages;

import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;


public class YoutubeSignInPage {
	

    WebDriver driver;
	
	public YoutubeSignInPage(WebDriver driver)
	{
		this.driver=driver;
	}
	
	@FindBy(xpath="//ytd-button-renderer[@class='style-scope ytd-masthead style-suggestive size-small']") WebElement Btn_SignIn;
	
	@FindBy(xpath="//div[contains(text(),'Use another account')]") WebElement Link_AnotherAccount;
	
	@FindBy(id="identifierId") WebElement Txt_Email;
	
	@FindBy(id="identifierNext") WebElement Btn_MailNext;
	
	@FindBy(name="password") WebElement Txt_Password;
	
	@FindBy(id="passwordNext") WebElement Btn_PwdNext;
	
	@FindBy(id="avatar-btn") WebElement Btn_UserAccountIcon;
	
	
	public void SignIn(String Emailid, String Password) throws InterruptedException
	{
		Btn_SignIn.click();
				
		//Choose an account (If the user is already present in the account list)
	    if(driver.findElements(By.xpath("//div[contains(text(),'Use another account')]")).size() > 0==true)
	    {
	    	Link_AnotherAccount.click();
	    }
		
	    WebDriverWait wait = new WebDriverWait(driver,10);
	    wait.until(ExpectedConditions.visibilityOf(Txt_Email));
		Txt_Email.sendKeys(Emailid);
			
		wait.until(ExpectedConditions.elementToBeClickable(Btn_MailNext));
		Btn_MailNext.click();
		
		wait.until(ExpectedConditions.visibilityOf(Txt_Password));
		Txt_Password.sendKeys(Password);
		Btn_PwdNext.click();
		driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
		
		if(Btn_UserAccountIcon.isDisplayed())
		{
			System.out.println("The user is signed into youtube account successfully.");
		
		}
		else
		{
			System.out.println("The user failed to sign into youtube account.");
		}
		
	}

}
