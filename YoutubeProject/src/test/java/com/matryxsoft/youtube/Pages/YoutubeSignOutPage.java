package com.matryxsoft.youtube.Pages;

import java.util.concurrent.TimeUnit;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;


public class YoutubeSignOutPage {
	
WebDriver driver;
	
	public YoutubeSignOutPage(WebDriver driver)
	{
		this.driver=driver;
	}
	
	@FindBy(id="avatar-btn") WebElement Btn_UserAccountIcon;
	
	@FindBy(xpath="//yt-formatted-string[contains(text(),'Sign out')]") WebElement MenuItem_SignOut;
	
	@FindBy(xpath="//ytd-button-renderer[@class='style-scope ytd-masthead style-suggestive size-small']") WebElement Btn_SignIn;

	
	public void SignOut()
	{
		Btn_UserAccountIcon.click();
		MenuItem_SignOut.click();
		driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
		
		if(Btn_SignIn.isDisplayed())
		{
			System.out.println("The user is signed out from youtube account successfully.");
			
		}
		else
		{
			System.out.println("The user failed to sign out from youtube account.");
		}
	
}
}