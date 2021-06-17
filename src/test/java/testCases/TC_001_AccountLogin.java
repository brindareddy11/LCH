package testCases;

import java.time.Duration;

import org.apache.commons.lang3.RandomStringUtils;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import io.github.bonigarcia.wdm.WebDriverManager;
import pageObjects.ConsoleLoginPage;
import pageObjects.DashboardPage;
import testBase.BaseClass;

public class TC_001_AccountLogin extends BaseClass
{
	
	
	
	@Test(groups= {"Regression", "sanity"})
	public void test_account_Login() throws InterruptedException
	{
		logger.info("Starting   ************TC_001_AccountLogin **********");
		
		
		
		driver.get(rb.getString("appURL"));
		
		Thread.sleep(2000);
		try
		{
		ConsoleLoginPage lp=new ConsoleLoginPage(driver);
		 lp.setUser(rb.getString("Uname"));
		 logger.info("Provided UserName");
		 lp.setPassword(rb.getString("Password"));
		 logger.info("Provided Password");
		 lp.clickLogin();
		 logger.info("User Click on loginbutton");
		//lp.setUser(randomestring()+"@Cubic.com");
		 
		 DashboardPage dp=new DashboardPage(driver);
		 boolean title=dp.isHomePageExists();
		 
		 
		 Thread.sleep(2000);
		 if (title==true)
		{
			 logger.info("Validation User login is success......");
			 
			 System.out.println("Test_Sudhakar");
			 		Assert.assertTrue(true);
		}
		 else
		 {
			 captureScreen(driver, "TC_001_AccountLogin");
			 logger.info("Validation User login is not success......");
			 Assert.assertTrue(false);
	
		 }
		 
		 dp.clickLogout();
		 logger.info("User click on logout.........");
		Thread.sleep(2000);
		}
		catch(Exception e)
		{
			logger.fatal("User Login Failed....Exception occured...");
		}
		 
		 logger.info("Finished   ************TC_001_AccountLogin **********");
	}
	
	
}
