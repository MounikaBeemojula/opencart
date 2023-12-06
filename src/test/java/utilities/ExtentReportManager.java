package utilities;


import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;

import org.testng.ITestContext;
import org.testng.ITestListener;
import org.testng.ITestResult;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.Status;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;
import com.aventstack.extentreports.reporter.configuration.Theme;

import testBase.BaseClass;

public class ExtentReportManager implements ITestListener {
	public ExtentSparkReporter sparkReporter;
	public ExtentReports extent;
	public ExtentTest test;

	
	String repName;
	
	public void onStart(ITestContext testContext) {
		String timestamp=new SimpleDateFormat("yyyy.MM.dd.HH.ss").format(new Date()); //time stamp
		repName = "Test-Report-" + timestamp + ".html";
		
		sparkReporter=new ExtentSparkReporter(".\\reports\\" +repName); //specify location of the report
		
		sparkReporter.config().setDocumentTitle("opencart Automation Report"); //title of report
		sparkReporter.config().setReportName("opencart Functional Testing");  //name of report
		sparkReporter.config().setTheme(Theme.DARK);
		
		extent=new ExtentReports();
		extent.attachReporter(sparkReporter);
		extent.setSystemInfo("Application", "opencart");
		extent.setSystemInfo("Module", "Admin");
		extent.setSystemInfo("Sub Module", "Customers");
		extent.setSystemInfo("Operating System", System.getProperty("os.name"));
		extent.setSystemInfo("User Name", System.getProperty("user.name"));
		extent.setSystemInfo("Environment", "QA");
	
		
	}
	
	public void onTestSucess(ITestResult result)
	{
		test=extent.createTest(result.getName());
		test.log(Status.PASS, "Test Passed");
	}
	
	
	public void onTestFailure(ITestResult result)
	{
		test=extent.createTest(result.getName());
		test.log(Status.FAIL, "Test Failed");
		test.log(Status.FAIL, result.getThrowable().getMessage());
		
		try {
			String imgPath = new BaseClass().captureScreen(result.getName());
			test.addScreenCaptureFromPath(imgPath);
		}
		catch (IOException e1)
		{
			e1.printStackTrace();
		}
	}
		
		public void onTestSkipped(ITestResult result)
		{
			test=extent.createTest(result.getName());
			test.log(Status.SKIP, "Test Skipped");
			test.log(Status.SKIP, result.getThrowable().getMessage());
	}
		public void onFinish(ITestContext testContext)
		{
			extent.flush();
			
			/*
			 * try { URL url = new
			 * URL("file://"+System.getProperty("user.dir")+"\\reports\\"+repName);
			 * 
			 * //Create the email message 
			 * ImageHtmlEmail email = new ImageHtmlEmail();
			 * email.setDataSourceResolver(new DataSourceResolver (url));
			 * email.setHostName("smtp.google.com"); email.setSmtPort(465);
			 * email.setAuthenticator(new DefaultAuthenticator("mounika123@gmail.com","password")); 
			 * email.setSSLOnConnect(true);
			 * email.setForm("mounika123@gmail.com"); //sender
			 * email.setSubject("Test Result");
			 * email.setMsg("Please find Attachment Report....");
			 * email.addTo("mounika123@gmail.com"); //Receiver 
			 * email.attach(url, "extent report", "please check report..."); 
			 * email.send(); //send the email
			 *  }
			 * catch(Exception e) {e.printStackTrace();}
			 */
		}
	

	
}
