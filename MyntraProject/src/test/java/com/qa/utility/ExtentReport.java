package com.qa.utility;

import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;

import org.testng.ITestContext;
import org.testng.ITestResult;
import org.testng.TestListenerAdapter;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.Status;
import com.aventstack.extentreports.markuputils.ExtentColor;
import com.aventstack.extentreports.markuputils.MarkupHelper;
import com.aventstack.extentreports.reporter.ExtentHtmlReporter;
import com.aventstack.extentreports.reporter.configuration.Theme;

public class ExtentReport  extends TestListenerAdapter{
	
	public ExtentHtmlReporter htmlReporter;
	public ExtentReports extent;
	public ExtentTest xTest;
	
	 
	  public void onStart(ITestContext testContext) {
		  String timeStamp=new SimpleDateFormat("yyyy.MM.dd.HH.ss").format(new Date());
		  String repName="Test-Report"+timeStamp+".html";
		  htmlReporter =new ExtentHtmlReporter(System.getProperty("user.dir")+ "/Reports/" +repName);
		  htmlReporter.config().setDocumentTitle("Automation Testing");
		 // htmlReporter.config().setReportName("Automation Testing");
		   htmlReporter.config().setTheme(Theme.DARK);
		   htmlReporter.config().setAutoCreateRelativePathMedia(true);
		   extent =new ExtentReports();
		   extent.attachReporter(htmlReporter);
		   extent.setSystemInfo("host","Syam");
	  }

	  public void onFinish(ITestContext testContext) {
		  extent.flush();// to capture extent report
		  
	  }
	  
	  
	  public void onTestSuccess(ITestResult tr) {
		  xTest =extent.createTest(tr.getName());
		  xTest.log(Status.PASS,MarkupHelper.createLabel(tr.getName(),ExtentColor.RED));
		  xTest.log(Status.PASS,"TEST IS PASSED");
		  
		   
		  }

		  @Override
		  public void onTestFailure(ITestResult tr) {  
			  xTest =extent.createTest(tr.getName());
			  xTest.log(Status.FAIL,MarkupHelper.createLabel(tr.getName(),ExtentColor.YELLOW));
			  xTest.log(Status.FAIL,"TEST IS FAILED");
			  String screenshotpath=System.getProperty("user.dir")+"/+.png";
			  File file = new File(screenshotpath);
			  if(file.exists()) {
				  try {
					  xTest.fail("SCREENSHOOT FOR THE TEST FAILED IS :"+xTest.addScreenCaptureFromPath(screenshotpath));
				  }catch (IOException e) {
					  e.printStackTrace();
					  
				  }
			  }
			  
		    
		  }

		  @Override
		  public void onTestSkipped(ITestResult tr) {
			  xTest =extent.createTest(tr.getName());
			  xTest.log(Status.SKIP,MarkupHelper.createLabel(tr.getName(),ExtentColor.BLUE));
			  xTest.log(Status.SKIP,"TEST IS SKIPED");
			  


		  }
	  

}
