package pageObjects;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class ConsoleLoginPage {

	
	WebDriver driver;

	public ConsoleLoginPage(WebDriver driver)
	{
		this .driver=driver;
		PageFactory.initElements(driver, this);
	}
		@FindBy(xpath="//input[@id='LogingForm_userName_field']")
		
		
		WebElement txtUserName;
		@FindBy(xpath="//input[@id='LogingForm_password_field']")
		WebElement txtPassword;
		
		@FindBy(xpath="//span[normalize-space()='Log in']")
		WebElement btnLogin;
		@FindBy(xpath="//div[@class='sc-frame-header']")
		WebElement msgHome;
		public void setUser(String username)
		{
			
			txtUserName.sendKeys(username);
		}
		public void setPassword(String pwd)
		{
			
			txtPassword.sendKeys(pwd);
		}
		
		public void clickLogin()
		{
			btnLogin.click();
		}
		
	

	
}
	
	
	
	
	
	
	
