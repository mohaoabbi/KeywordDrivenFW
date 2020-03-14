package com.qa.keyword.engine;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Properties;

import org.apache.poi.EncryptedDocumentException;
import org.apache.poi.sl.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.ss.usermodel.WorkbookFactory;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import com.qa.hs.keyword.base.Base;

public class keyworkEngine extends Base {
	

	public final String SCENARIO_SHEET_PATHString = "C:\\Users\\Abdel\\eclipse-workspace\\keyWordPro\\src\\main\\java\\com.qa.hs.keayword.scenarios\\hubspot_scenarios.xlsx";
	public static XSSFWorkbook book;
	public static XSSFSheet sheet;
	public static Base base;
	public static WebElement element;
	
	
	public void startExecution(String sheetName) throws EncryptedDocumentException, IOException {
		 
		String locatorName = null   ;
		String locatorValue = null;
		FileInputStream file  = new FileInputStream("C:\\Users\\Abdel\\eclipse-workspace\\keyWordPro\\src\\main\\java\\com\\qa\\hs\\keayword\\scenarios\\hubspot_scenarios.xlsx");
		book = new XSSFWorkbook(file);
		sheet = book.getSheet(sheetName);
		int k = 0;
		System.out.println(sheet);
		for (int i=0;i< sheet.getLastRowNum();i++) {
			try {
			System.out.println("im here");
			String locatorColValue = sheet.getRow(i+1).getCell(k+2).toString().trim(); // id=username
			if(!locatorColValue.equalsIgnoreCase("na")) {
				 locatorName = locatorColValue.split("=")[0].trim();//id
				locatorValue = locatorColValue.split("=")[1].trim();//id
			}
			String action = sheet.getRow(i+1).getCell(k+3).toString().trim();
			String value =     sheet.getRow(i+1).getCell(k+4).toString().trim();
			switch (action) {
			case "open browser":
				base = new Base();
				base.init_properties();
				if (value.isEmpty() || value.equalsIgnoreCase("na")) {
				base.in_driver(prop.getProperty("browser"));
				}else {
					driver  = base.in_driver(value);
				}
				System.out.println( value);
				System.out.println( locatorColValue);
			System.out.println( locatorName);
			System.out.println( locatorValue);
				break;
			case "enter url":
				if (value.isEmpty() || value.equalsIgnoreCase("na")) {
					base.in_driver(prop.getProperty("url"));
				}else {
					driver  = base.in_driver(value);
				}
				
			case "quit":
				
					driver.close();
				
			default:
				break;
			}
			switch (locatorName) {
			case "id":
				 element = driver.findElement(By.id(locatorColValue));
				if(action.equalsIgnoreCase("sendkeys")) {
					element.sendKeys(value);
				}else if(action.equalsIgnoreCase("click")){
					element.click();
						}
				locatorName = null;
				break;
			case "linkText":
				 element = driver.findElement(By.linkText(locatorColValue));
					element.click();
						
				locatorName = null;
				break;

			default:
				break;
			}
			
		}catch (Exception e) {
			// TODO: handle exception
		}
		
	}
	
	}}

