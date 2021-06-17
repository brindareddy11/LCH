package utilities;

import java.net.URL;
import java.text.SimpleDateFormat;
import java.util.Date;

import org.apache.commons.mail.DefaultAuthenticator;
import org.apache.commons.mail.ImageHtmlEmail;
import org.apache.commons.mail.resolver.DataSourceUrlResolver;
import org.testng.ITestContext;
import org.testng.ITestListener;
import org.testng.ITestResult;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.Status;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;
import com.aventstack.extentreports.reporter.configuration.Theme;

public class ExtentReportManager implements ITestListener
{
	public ExtentSparkReporter sparkReporter;
	public ExtentReports extent;
	public ExtentTest test;
	String repName;
	public void onStart(ITestContext context)
	{	
		
		String timestamp=new SimpleDateFormat("yyyy.MM.dd.HH.mm.ss").format(new Date());
		repName="Test-Report-"+timestamp+".html";
		sparkReporter=new ExtentSparkReporter(System.getProperty("user.dir")+ "\\reports\\"+repName);// name of the report.html
		
		sparkReporter.config().setDocumentTitle("DBOS console Automation Test Report");// you can set the title
		sparkReporter.config().setReportName("nopCommerce Functional Testing");// set the name of the report
		sparkReporter.config().setTheme(Theme.DARK);  // set theme
		
		extent=new ExtentReports();
		extent.attachReporter(sparkReporter);
		extent.setSystemInfo("Host name", "localhost");//which computer you running this report
		extent.setSystemInfo("Environment", "QA");
		extent.setSystemInfo("user", "Sudhakar");
		extent.setSystemInfo("os", "Windows10");
		extent.setSystemInfo("Browser name", "Chrome,Firefox,Edge");
		
				
	}
	
	public void onTestSuccess(ITestResult result)
	{
		test=extent.createTest(result.getName());// take the name of the report and create the test
		test.log(Status.PASS, "Test case PASSED is:"+result.getName());// this method update the status
	}
	
	public void onTestFailure(ITestResult result)
	{
		test=extent.createTest(result.getName());
		test.log(Status.FAIL, "Test case Failed");// this method update the status
		test.log(Status.FAIL, result.getThrowable().getMessage());
		try
		{
			String screenshotPath=System.getProperty("user.dir")+"\\screenshots\\"+result.getName()+".png";
			test.addScreenCaptureFromPath(screenshotPath);  // add the report to the report
		}
		catch(Exception e)
		{
			e.printStackTrace();
		}

	
		
	}
	
	public void onTestSkipped(ITestResult result)
	{
		test=extent.createTest(result.getName());
		test.log(Status.SKIP, "Test case skipped is:"+result.getName());// this method update the status
		
	}
	

	public void onFinish(ITestContext context)
	{
		extent.flush();// arrange everything properly and  complete the report
	
	/*
	try {
		
		URL url=new URL("file:///"+System.getProperty("user.dir")+"\\reports\\"+repName);
			// create the email message
		ImageHtmlEmail email=new ImageHtmlEmail();
		email.setDataSourceResolver(new DataSourceUrlResolver(url));
		email.setHostName("smtp.googlemail.com");
		email.setSmtpPort(465);
		email.setAuthenticator(new DefaultAuthenticator("abc@gmail.com", "password"));
		email.setSSLOnConnect(true);
		email.setFrom("abc@gmail.com");// sender
		email.setSubject("Test Results");
		email.setMsg("Please find the attached Report....");
		email.addTo("xvz@gmail.com"); //Receiver
		email.attach(url, "extent report", "please check report");
		email.send();		
			
			
		}
		
		catch(Exception e)
		{
			e.printStackTrace();
		}
	*/
}
}
