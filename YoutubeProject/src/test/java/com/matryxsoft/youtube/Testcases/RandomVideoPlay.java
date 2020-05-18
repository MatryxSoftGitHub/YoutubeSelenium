/*Description: Play random videos, Like and Subscribe the channel.

 Parameters:String UserId,String UserPwd, String ChannelName */

package com.matryxsoft.youtube.Testcases;

import java.util.concurrent.TimeUnit;

import org.openqa.selenium.support.PageFactory;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;
import com.matryxsoft.youtube.Pages.*;
import com.matryxsoft.youtube.Utilities.*;

public class RandomVideoPlay extends BaseClass{
	
	//Marks a method as supplying data for a test method
		@DataProvider(name="ReadData")
		public Object[][] getDetails() 
		{
			Object data[][] = null;
			try {
				data = ReadExcelDataProvider.ReadData("AccountDetails");
				 System.out.println(data);
			} 
			catch (Exception e) {
				e.printStackTrace();
			}
			return data;
		}
		
		
		@Test(dataProvider="ReadData")		
		public void PlayRandomly(String UserId,String UserPwd, String ChannelName) throws InterruptedException
			{
			//Navigate to Youtube User Account
			YoutubeSignInPage SignInElements= PageFactory.initElements(driver, YoutubeSignInPage.class);
			SignInElements.SignIn(UserId,UserPwd);
			driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
			
			//Search and Select the Channel
			YoutubeSearchChannelPage ToSearchChannel= PageFactory.initElements(driver, YoutubeSearchChannelPage.class);
			ToSearchChannel.SearchString(ChannelName);
			driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);

			//YoutubeRandomVideoStreamingPage
			YoutubeRandomVideoStreamingPage RandomPlayList = PageFactory.initElements(driver, YoutubeRandomVideoStreamingPage.class);
			RandomPlayList.PlayListLooping();
			driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
			
			//SignOut from the User Account
		    YoutubeSignOutPage SignOutElements= PageFactory.initElements(driver, YoutubeSignOutPage.class);
			SignOutElements.SignOut();
			driver.manage().timeouts().implicitlyWait(25, TimeUnit.SECONDS);
			}

}
