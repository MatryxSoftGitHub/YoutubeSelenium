package com.matryxsoft.youtube.Utilities;

import java.io.File;
import java.io.IOException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.io.FileHandler;

public class CaptureScreenshots {


	//*************************Capture Snapshot Start**********************************************************
	public static String Screenshot(WebDriver driver)
	{   
		File src=((TakesScreenshot)driver).getScreenshotAs(OutputType.FILE);
		String ScreenshotPath=System.getProperty("./ErrorScreenshots/Youtube_"+ GetCurrentDateTime() + ".png");
		
		try {
			
			FileHandler.copy(src,new File(ScreenshotPath));
			
			System.out.println("Screenshot captured");
			
		} catch (IOException e) {
			
			System.out.println("Unable to capture screenshot"+e.getMessage());
			
		}
		return ScreenshotPath;
	}
	//To get current date and time to the snapshot
	public static String GetCurrentDateTime() 
	{
		DateFormat CustomFormat=new SimpleDateFormat("MM_dd_yyyy_hh_mm_ss");
		
		Date CurrentDate=new Date();
		
		return CustomFormat.format(CurrentDate);
	}
	
}

