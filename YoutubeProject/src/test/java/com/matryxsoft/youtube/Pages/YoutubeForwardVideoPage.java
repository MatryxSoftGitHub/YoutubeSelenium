/*Description: Play random videos by forwarding, Like and Subscribe the channel.

 Parameters:String UserId,String UserPwd, String ChannelName */

package com.matryxsoft.youtube.Pages;

import java.util.List;
import java.util.Random;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindBy;

public class YoutubeForwardVideoPage {
	
	WebDriver driver;
	
	public YoutubeForwardVideoPage(WebDriver driver)
	{
		this.driver=driver;
	}

    @FindBy(xpath="//ytd-subscribe-button-renderer[@class='style-scope ytd-video-secondary-info-renderer']//paper-button[@class='style-scope ytd-subscribe-button-renderer']") WebElement Btn_Subscribe;
	
  	@FindBy(xpath="//div[@id='info']//ytd-toggle-button-renderer[1]//a[1]//yt-icon-button[1]//button[1]") WebElement Btn_Like;
	
    @FindBy(xpath="//div[@class='ytp-left-controls']//span[@class='ytp-time-duration']") WebElement Txt_VideoDurationTime;
	
	@FindBy(xpath="//div[@class='ytp-left-controls']//span[@class='ytp-time-current']") WebElement Txt_VideoCurrentTime;
	
	@FindBy(xpath="//div[@class='ytp-scrubber-pull-indicator']") WebElement Scrollbar_Indicator;
	
	@FindBy(xpath="//body//paper-tab[2]") WebElement WebLink_Videos;
				
	@FindBy(xpath="//paper-toggle-button[@id='toggle']") WebElement Btn_AutoPlay;
	
	@FindBy(xpath="//div[@id='upload-info']//ytd-channel-name[@id='channel-name']") WebElement Txt_ChannelName;
	 
	@FindBy(className="ytp-progress-bar") WebElement Slider;
	
	
    public void PlayListLooping() throws InterruptedException
	{
    	
		driver.manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS);
		
		WebLink_Videos.click();
		Thread.sleep(5000);
		
		//To Fetch Video Count
		List<WebElement> VideoCount= driver.findElements(By.id("metadata-container"));
		
		
		for (int i= 0; i< 4; i++)
		{
			Random objGenerator = new Random();
	         int randomNumber = objGenerator.nextInt(VideoCount.size());
	              
		    driver.findElement(By.xpath("//body//ytd-grid-video-renderer["+randomNumber+"]")).click();
		    Thread.sleep(5000);
		    String sVideoName=driver.getTitle();
		    System.out.println("The title of the video playing is: "+sVideoName);
		    
		    
		    //Disable Autoplay if Enabled
		    if(Btn_AutoPlay.getAttribute("aria-pressed").equals("true"))
			{
		    	Btn_AutoPlay.click();
		    	driver.manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS);
				System.out.println("AutoPlay was disabled Successfully.");
			}
			else
			{
				System.out.println("AutoPlay is already disabled.");
			}
		    VideoStream();
		    Thread.sleep(5000);
		    Txt_ChannelName.click();
		    System.out.println("Channel name is clicked.");
		    Thread.sleep(5000);
		   			    
		    WebLink_Videos.click();
		    Thread.sleep(5000);
		    
	   }
								
		System.out.println("All the videos in the Playlist are played.");
}
  	
    //To Verify Video is Played Completely 
    public void VideoStream() throws InterruptedException
   	{
    
        Double MaxVal=Double.parseDouble(Slider.getAttribute("aria-valuemax"));
         
       	int PixelsToMove = GetPixelsToMove(Slider, MaxVal/6, MaxVal, 0);
       	Actions SliderAction = new Actions(driver);
       	SliderAction.clickAndHold(Slider).moveByOffset((-(int)Slider.getSize().getWidth() / 2),0).moveByOffset(PixelsToMove, 0).release().perform();
       	Thread.sleep(5000);
       	            		
       	int PixelsToMove1 = GetPixelsToMove(Slider, MaxVal/2, MaxVal, 0);
       	SliderAction.clickAndHold(Slider).moveByOffset((-(int)Slider.getSize().getWidth() / 2),0).moveByOffset(PixelsToMove1, 0).release().perform();
       	Thread.sleep(5000);
       	
       	int PixelsToMove2 = GetPixelsToMove(Slider, MaxVal, MaxVal, 0);
       	SliderAction.clickAndHold(Slider).moveByOffset((-(int)Slider.getSize().getWidth() / 2),0).moveByOffset(PixelsToMove2, 0).release().perform();
       	Thread.sleep(2000);
       	
        //Comparing video duration time with current video time
       	if(Txt_VideoCurrentTime.getText().equals(Txt_VideoDurationTime.getText()))
        {
     		System.out.println("The video streaming is completed.");
     		LikeAndSubscribeChannel();
        }
      }    

       	public static int GetPixelsToMove(WebElement Slider, double Amount, double SliderMax, double SliderMin)
           {
               int pixels = 0;
               double tempPixels = Slider.getSize().getWidth();
               tempPixels = tempPixels / (SliderMax - SliderMin);
               tempPixels = tempPixels * (Amount - SliderMin);
               pixels = (int) (tempPixels);
       	        return pixels;
           }
    //To Like and Subscribe the Channel
    public void LikeAndSubscribeChannel() throws InterruptedException
	{
		//Click on Like button if not liked
		if(Btn_Like.getAttribute("aria-pressed").equals("false"))
		{
			Btn_Like.click();
			driver.manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS);
			System.out.println("Video is liked Successfully.");
		}
		else
		{
			System.out.println("Video was already Liked.");
		}
		
		driver.manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS);
		
		//Click on Subscribe button if not Subscribed
		if (Btn_Subscribe.getText().equals("SUBSCRIBE"))
		{   
			driver.manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS);
			Btn_Subscribe.click();
			System.out.println("Channel is Subscribed Successfully.");
		}
		else
		{
			System.out.println("Channel was already Subscribed.");
		}
		
		
	}

}
