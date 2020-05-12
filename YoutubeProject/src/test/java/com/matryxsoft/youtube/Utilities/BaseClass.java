package com.matryxsoft.youtube.Utilities;
import java.io.IOException;
import org.openqa.selenium.WebDriver;
import org.testng.ITestResult;
import org.testng.Reporter;
import org.testng.annotations.*;


public class BaseClass {
	
			public WebDriver driver;
			
			public ConfigDataProvider config;
			
			//**************Fetching data from config file and excel**********************************
			@BeforeSuite
			public void SetUpSuite()
			{
					config=new ConfigDataProvider();
				
			}
			
			//***************************************APP STATE ONSTART***************************************
			@BeforeClass
			public void AppOnStart()
			{
		        driver=BrowserFactory.OnStart(driver, config.GetBrowser(), config.GetURL());
			}

			//***************************************APP STATE ONFINISH***************************************
			@AfterTest
			public void AppOnFinish()
			{
				BrowserFactory.OnFinish(driver);
			}
			
			//*******************Capture Snapshot if testcase is Failed***********************************
			@AfterMethod
			public void CaptureSnapshot(ITestResult result) throws IOException
			{
							
				if(result.getStatus()==ITestResult.FAILURE)
				{
					Reporter.log("Testcase failed", true);
					CaptureScreenshots.Screenshot(driver);//Capture Snapshot 
					
				}

			}
			
			
}
