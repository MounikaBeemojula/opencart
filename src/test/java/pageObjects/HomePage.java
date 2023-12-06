package pageObjects;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class HomePage extends BasePage {
	 public HomePage(WebDriver driver)
	 {
		 super(driver);
	 }
	 
	 //Elements
	 @FindBy(xpath="//span[normalize-space()='My Account']")
	 WebElement lnkMyaccount;
	 
	 
	 @FindBy(xpath="//a[normalize-space()='Register']")
	 WebElement lnkRegister;
	 
	 @FindBy(xpath="//a[@class='dropdown-item'][normalize-space()='Login']") //login link added in step6
	 WebElement lnkLogin;
	 
	 //Action Methods
	 public void clickMyAccount()
	 {
		 lnkMyaccount.click();
	 }
	 
	 public void clickRegister()
	 {
		 lnkRegister.click();
	 }
	 public void clickLogin()
	 {
		 lnkLogin.click();
	 }
	

}
