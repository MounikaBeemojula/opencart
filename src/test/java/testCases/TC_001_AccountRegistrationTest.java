package testCases;

import org.testng.Assert;
import org.testng.annotations.*;

import pageObjects.AccountRegistrationPage;
import pageObjects.HomePage;
import testBase.BaseClass;

public class TC_001_AccountRegistrationTest extends BaseClass {

	
	
	@Test(groups= {"Regression","Master"})
	void test_accout_Registration()
	{
		logger.debug("Application logs.....");
		logger.info("***  Starting TC_001_AccountRegistrationTest ***");
		try
		{
		HomePage hp=new HomePage(driver);
	
		hp.clickMyAccount();
		logger.info("Clicked on My account link");
		
		hp.clickRegister();
		logger.info("Clicked on register link");
		
		AccountRegistrationPage regpage=new AccountRegistrationPage(driver);
		logger.info("Providing customer data");
		
		regpage.setFirstName(randomString().toUpperCase());
		
		regpage.setLastname(randomString().toUpperCase());
		
		regpage.setEmail(randomString()+"@gmail.com"); //randomly generated the email
		
		regpage.setPassword(randomAlphaNumeric());
		
		regpage.setPrivacyPolicy();
		
		regpage.clickContinue();
		logger.info("Clicked on continue");
		
	String conmsg=regpage.getConfirmationMsg();
	logger.info("Validating expected message");
	Assert.assertEquals(conmsg, "Your Account Has Been Created!","Test Failed");
		}
		catch(Exception e)
		{
			logger.error("test failed");
			Assert.fail();
		}
	
		logger.info("***  Finished TC_001_AccountRegistrationTest ***");
	}
	
	
	
	
}
