package com.qa.testscript;


import java.io.File;
import java.io.IOException;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Parameters;

import com.qa.pages.MyntraPages;



public class TestBase

{
	WebDriver driver;
	
	JavascriptExecutor js;
	MyntraPages myntrapages;
	
	
	@Parameters({"browser","url"})
	@BeforeClass
	public void setup(String browser,String url)
	{
		if(browser.equalsIgnoreCase("chrome"))
			{
			     System.setProperty("webdriver.chrome.driver", "D:\\Selenium softwares\\chromedriver_win32\\chromedriver.exe");
			     driver=new ChromeDriver();
			     driver.manage().window().maximize();
			     js=(JavascriptExecutor) driver;
			}
		else if(browser.equalsIgnoreCase("edge"))
		{
			
			System.setProperty("webdriver.edge.driver", "D:\\Selenium softwares\\edgedriver_win64\\msedgedriver.exe");
		    driver=new EdgeDriver();
	    }
		
        
		
		myntrapages=new MyntraPages(driver);
      	driver.get(url);
	}
	
	@AfterClass
	public void tearDown()
	{
		driver.close();
		driver.quit();
	}
	
	public void captureScreenshot(WebDriver driver ,String tname) throws IOException{
		TakesScreenshot ts =(TakesScreenshot)driver;
		File Source =ts.getScreenshotAs(OutputType.FILE);
		File target =new File(System.getProperty("user.dir")+"/Screenshots/"+tname+".png");
		FileUtils.copyFile(Source,target);
		System.out.println("SS is Taken");
		
	}

	
}
