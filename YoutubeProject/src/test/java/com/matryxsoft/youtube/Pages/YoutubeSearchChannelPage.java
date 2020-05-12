package com.matryxsoft.youtube.Pages;

import java.util.concurrent.TimeUnit;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;



public class YoutubeSearchChannelPage {
	
	 WebDriver driver;
		
		public YoutubeSearchChannelPage(WebDriver driver)
		{
			this.driver=driver;
		}
		
		@FindBy(xpath="//input[@id='search']") WebElement Txt_SearchBox;
		
		
		@FindBy(xpath="//yt-icon[@class='style-scope ytd-searchbox']") WebElement Btn_Search;
		
		
		
		@FindBy(xpath="//yt-icon[@class='style-scope ytd-toggle-button-renderer']") WebElement Btn_Filters;
		
		
		@FindBy(xpath="//yt-formatted-string[contains(text(),'Type')]") WebElement Btn_Type;
		
		
		@FindBy(xpath="//yt-formatted-string[contains(text(),'Channel')]") WebElement Btn_Channel;
		
		@FindBy(xpath="//body//paper-tab[2]") WebElement WebLink_Videos;
		
		
		   	
		   
		public void SearchString(String ChannelName) throws InterruptedException
		{
			
			Txt_SearchBox.click();
			Txt_SearchBox.sendKeys(ChannelName);
			Btn_Search.click();
			driver.manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS);
			
			
			Btn_Filters.click();
			Thread.sleep(5000);
			
			
			Btn_Channel.click();
			Thread.sleep(5000);		

			WebElement Channel = driver.findElement(By.xpath("//yt-formatted-string[contains(text(), '"+ChannelName+"')]"));
			Channel.click();
			driver.manage().timeouts().implicitlyWait(25, TimeUnit.SECONDS);
			
			if(WebLink_Videos.isDisplayed())
			{
				System.out.println("Navigated to "+ ChannelName+" Channel Home Page.");
			
			}
			else
			{
				System.out.println("Failed to navaigate to "+ ChannelName+" Channel Home Page.");
			}
			
		}


}
