package com.qa.hs.keyword.base;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Properties;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.edge.EdgeOptions;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxOptions;
import org.openqa.selenium.ie.InternetExplorerDriver;
import org.openqa.selenium.ie.InternetExplorerOptions;

import io.github.bonigarcia.wdm.WebDriverManager;

public class Base {
	
	public  WebDriver driver;
	public  Properties prop;
	
	public WebDriver in_driver(String browserName) {
		
		switch (browserName) {
		case "chrome":
			WebDriverManager.chromedriver().setup();
			if(prop.getProperty("headless").equalsIgnoreCase("yes")) {
			ChromeOptions options = new ChromeOptions();
			options.addArguments("--headless");
			driver = new ChromeDriver(options);
			}else {
				driver = new ChromeDriver();
			}
			break;
		case "firefox":
			WebDriverManager.firefoxdriver().setup();
			if(prop.getProperty("headless").equalsIgnoreCase("yes")) {
				FirefoxOptions options = new FirefoxOptions();
				options.addArguments("--headless");
				driver = new FirefoxDriver(options);
				}else {
					driver = new FirefoxDriver();
				}
			break;
		case "edge":
			WebDriverManager.edgedriver().setup();
			if(prop.getProperty("headless").equalsIgnoreCase("yes")) {
				EdgeOptions options = new EdgeOptions();
				driver = new EdgeDriver(options);
				}else {
					driver = new EdgeDriver();
				}
			break;
		case "ie":
			WebDriverManager.iedriver().setup();
			if(prop.getProperty("headless").equalsIgnoreCase("yes")) {
				InternetExplorerOptions options = new InternetExplorerOptions()
						   .requireWindowFocus();
				 new InternetExplorerOptions(options);
				}else {
					driver = new InternetExplorerDriver();
				}
		
			break;

		default:
			System.out.println("Enter browser as : chrome, firefox,edge, or ie");
			break;
		}
		
		
		return driver;
		
	}
	public Properties init_properties() throws IOException {
		
		prop = new Properties();
		FileInputStream ip = new FileInputStream(
				"C:\\Users\\Abdel\\eclipse-workspace\\keyWordPro\\src\\main\\java\\com\\qa\\hs\\keyWordPro\\config.properties");
		prop.load(ip);
		return prop;
	}

}
