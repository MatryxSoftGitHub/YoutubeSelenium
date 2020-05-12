package com.matryxsoft.youtube.Utilities;

import java.io.FileInputStream;
import java.io.IOException;

import org.apache.poi.openxml4j.exceptions.InvalidFormatException;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.ss.usermodel.WorkbookFactory;

public class ReadExcelDataProvider {
	static Workbook WB;
	static Sheet sheet;
	
	public static Object[][] ReadData(String sheetName) throws IOException
	{
		//Create an object of FileInputStream class to read excel file
		FileInputStream file=null;
		Object[][] data=null;
	try
	{
		 file=new FileInputStream("./DataFile/TestData_Youtube.xlsx");
		
			WB=WorkbookFactory.create(file);
			sheet=WB.getSheet(sheetName);
			data = new Object[sheet.getLastRowNum()][sheet.getRow(0).getLastCellNum()];
			
			 //Create a loop over all the rows of excel file to read it
			for(int i=0; i<sheet.getLastRowNum();i++)
			{
				//Create a loop to print cell values in a row
				for(int j=0; j<sheet.getRow(i).getLastCellNum();j++)
				{
					data[i][j]=sheet.getRow(i+1).getCell(j).getStringCellValue();
					//System.out.println(data[i][j]);
				}
			}
		} 
	catch (InvalidFormatException e)
	   {			
			e.printStackTrace();
		} 
		
	file.close();
	return data;
	
		
	}

	

}
