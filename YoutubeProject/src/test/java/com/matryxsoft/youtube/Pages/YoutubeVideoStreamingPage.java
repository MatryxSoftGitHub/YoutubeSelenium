package com.matryxsoft.youtube.Pages;
	
import java.util.List;
import java.util.concurrent.TimeUnit;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Action;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindBy;


	public class YoutubeVideoStreamingPage {
	WebDriver driver;
		
		public YoutubeVideoStreamingPage(WebDriver driver)
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
		 
		 
	    public void PlayListLooping() throws InterruptedException
		{
	    	
			driver.manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS);
			
			WebLink_Videos.click();
			Thread.sleep(5000);
			
			//To Fetch Video Count
			List<WebElement> VideoCount= driver.findElements(By.id("metadata-container"));
				
			for (int i = 1; i <=VideoCount.size(); i++)
			{
			    driver.findElement(By.xpath("//body//ytd-grid-video-renderer["+i+"]")).click();
			    driver.manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS);
			    
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
			    Txt_ChannelName.click();
			    System.out.println("Channel name is clicked.");
			    Thread.sleep(5000);
			   			    
			    WebLink_Videos.click();
			    driver.manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS);
			    
		   }
									
			System.out.println("All the videos in the Playlist are played.");
			
	}
	  	
	    //To Verify Video is Played Completely 
	    public void VideoStream() throws InterruptedException
	   	{
	           String time = Txt_VideoDurationTime.getText(); //mm:ss
	           String[] units = time.split(":"); //will break the string up into an array
	           int minutes = Integer.parseInt(units[0]); //first element
	           int seconds = Integer.parseInt(units[1]); //second element
	           int finalduration = 60 * minutes + seconds; //add up our values
	           for(int i=0;i<=finalduration;i++)
	           {
	        	   System.out.println("Loop: Enter into VideoStream Functinality.");
	        	   
	        	   //Mouse Hover Action on Video Slider(to fetch the time duration)
	           	   Actions builder = new Actions(driver);
	               WebElement Scrollbar = Scrollbar_Indicator;
	               System.out.println("Mouse over on Duration Bar");
	               Action mouseMovement=builder.moveToElement(Scrollbar).build();
	               mouseMovement.perform();
	               //System.out.println("Final Duration: " +Txt_VideoDurationTime.getText());
	               Thread.sleep(3000);
	               //System.out.println("Current Duration: " +Txt_VideoCurrentTime.getText());
	            
	            //Comparing video duration time with current video time
	           	if(Txt_VideoCurrentTime.getText().equals(Txt_VideoDurationTime.getText()))
	              {
	           		System.out.println("The video streaming is completed.");
	           		LikeAndSubscribeChannel();
	           		break;
	              }
	           }
	           
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




