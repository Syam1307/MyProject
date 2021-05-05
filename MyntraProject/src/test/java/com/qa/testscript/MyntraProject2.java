package com.qa.testscript;

import java.io.IOException;
import java.util.Iterator;
import java.util.Set;

import org.openqa.selenium.WebDriver;
import org.testng.Reporter;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import com.qa.utility.ExcelUtility;

public class MyntraProject2  extends TestBase{
 @Test(dataProvider="getData")
	
	public void Bag (String Input)throws InterruptedException,IOException{
	    
	    captureScreenshot(driver,"Bag");
		driver.manage().window().maximize();
		myntrapages.getsearchbar().sendKeys(Input);
		myntrapages.getselect().click();
		myntrapages.getshirt().click();///on click will open new window
			Set<String> windowHandles =driver.getWindowHandles();
		Iterator<String> iter =windowHandles.iterator();
		while(iter.hasNext()) {
			String winId =iter.next();
			WebDriver window =driver.switchTo().window(winId);
			Reporter.log(window.getTitle(),true);
		}
			
		//myntrapages.getsize().click();
		myntrapages.getbag().click();
			Thread.sleep(5000);
			myntrapages.getgotobag().click();
			myntrapages.getpincode().click();
			Thread.sleep(5000);
			myntrapages.getcheckpin().sendKeys("1");
			
			
			Thread.sleep(5000);
			Reporter.log("Invalid pin code Enter the Validpin code");
			myntrapages.getenterpin().click();
			Thread.sleep(5000);
			Reporter.log("Iteam is not deliverable to current adress");
		
			myntrapages.getplaceorder().click();
			 String pageTitle=driver.getTitle();
			 String Excepted= "Myntra";
			System.out.println(pageTitle);
		 if (pageTitle.equals(Excepted)) {
			 Reporter.log("User is able to place order");
		 }
		 else {
			System.out.println("User is  not able to place order");
		 }
		
	}
	@DataProvider
	public String[][]getData()throws IOException{
		String x1Path="D:\\VirtusaWorkspace\\MyntraProject\\src\\test\\java\\com\\qa\\testdata\\Testdata.xlsx";
		String x1Sheet="Sheet3";
		int rowCount=ExcelUtility.getRowCount(x1Path,x1Sheet);
		int cellCount=ExcelUtility.getCellCount(x1Path,x1Sheet,rowCount);
		String[][]data=new String[rowCount][cellCount];
		for(int i=1;i<=rowCount;i++) {
			for (int j=0;j<cellCount;j++) {
				data[i-1][j]=ExcelUtility.getCellData(x1Path,x1Sheet,i,j);
			}
		}
		return data;
				}

}


