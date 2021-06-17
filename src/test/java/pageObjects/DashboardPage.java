package pageObjects;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class DashboardPage {

	WebDriver driver;

	public DashboardPage (WebDriver driver)
	{
		this .driver=driver;
		PageFactory.initElements(driver, this);
	}
		
		@FindBy(xpath="//div[@class='sc-frame-header']")
		WebElement msgHome;
		
		@FindBy(xpath="//div[@id='linkLogout']")
		WebElement btnLogout;
		
		
		public boolean isHomePageExists()
		{
			
			
				return msgHome.isDisplayed();
				
				
		}
		
		public void clickLogout()
		{
			btnLogout.click();
		}
		

	
}
	