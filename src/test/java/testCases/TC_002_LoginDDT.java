package testCases;

import java.io.IOException;

import org.testng.Assert;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import pageObjects.ConsoleLoginPage;
import pageObjects.DashboardPage;
import testBase.BaseClass;
import utilities.XLUtils;

public class TC_002_LoginDDT extends BaseClass
{
	@Test(dataProvider="LoginData")
	// dataprovider is to get the data from the excell and its store into 2-dimentional array and return
	public void test_LoginDDT(String email, String pwd, String exp) throws InterruptedException
	{
		logger.info("Strating TC_002_LoginDDT");
		
		
		try
		{
			driver.get(rb.getString("appURL"));
			
			Thread.sleep(2000);
		
			
			ConsoleLoginPage lp=new ConsoleLoginPage(driver);
			 lp.setUser(email);
			 logger.info("Provided UserName");
			 lp.setPassword(pwd);
			 logger.info("Provided Password");
			 lp.clickLogin();
			 logger.info("User Click on loginbutton");
						 
			 DashboardPage dp=new DashboardPage(driver);
			 boolean targetpage=dp.isHomePageExists();
			 System.out.println(targetpage);
			 System.out.println(exp);
			
					
			if(exp.equals("Valid"))
			{
				 if (targetpage==true)
					{
						 logger.info("Validation User login is success......");
						 dp.clickLogout();
						 logger.info("User click on logout.........");
						 Assert.assertTrue(true);
					}
				else
					{
						logger.error("Login Failed");
						logger.info("Validation User login is not success......");
						Assert.assertTrue(false);
					}
			}
			else if(exp.equals("Invalid"))
			{
				 if (targetpage==true)
				{
					logger.error("Login success for invalid data -Failed");
					dp.clickLogout();
					logger.info("User click on logout.........");
					Assert.assertTrue(false);
				}
				else
				{
					logger.info("Login not Success for invalid data");
					Assert.assertTrue(true);
				}
				
			}
			
				
		}
		catch(Exception e)
		{
			logger.fatal("Login Failed");
			Assert.fail();
			
		}
		 
		logger.info("Finished TC_02_LoginDDT");
	}
	@DataProvider(name="LoginData")
	public String[][] getData() throws IOException
	{
		String path=".\\testData\\DBOS_LoginData.xlsx";
		XLUtils xlutil=new XLUtils(path);
		int totalrows=xlutil.getRowCount("Sheet1");
		int totalcols=xlutil.getCellCount("Sheet1", 1);
	
		String logindata[][]=new String[totalrows][totalcols];
		for(int i=1; i<=totalrows; i++)
		{
			for(int j=0;j<totalcols; j++)
			{
				logindata[i-1][j]=xlutil.getCellData("Sheet1", i, j); // store the whole data into the 2-dimentinal array
			}
		}
		
		return logindata;// return the two dimentional array
	
	
	
	
	}
	

}
