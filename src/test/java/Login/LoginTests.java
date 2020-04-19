package Login;
import java.io.File;
import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.concurrent.TimeUnit;
import org.openqa.selenium.*;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;
import com.relevantcodes.extentreports.ExtentReports;
import com.relevantcodes.extentreports.ExtentTest;
import com.relevantcodes.extentreports.LogStatus;
import Utility_Scripts.Shared_Functions;
import io.appium.java_client.AppiumDriver;
import io.appium.java_client.MobileElement;
import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.remote.MobileCapabilityType;
import PageObjects.SignUpPageObjects;
import PageObjects.LoginPage;

public class LoginTests extends Shared_Functions{
	Shared_Functions SF=new Shared_Functions();
	SignUpPageObjects APO;
	LoginPage SOP;

@BeforeClass
public void setUp() throws Exception  {
	System.out.print("Creating HTML Results File");
	ResultFilepath=SF.CreateHTMLFile("LoginTestResults");
	System.out.print("ResultFilepath is :"+ResultFilepath);
	SF.LaunchApp();
	
	// SF.driver.resetInputState();
		//Create HTML Folder	
}

@Test
public void AmazonLogin() throws Exception {
	try {
		APO=new SignUpPageObjects();
		Test=extent.startTest("User Registration Scenario");
	    Test.log(LogStatus.PASS,"Signin Functionality");	
		//***Register User
		Test.log(LogStatus.PASS,"Enter User Credentials");
		APO.RegisterUser();
		
		//**** LOgin User with registered credentials
		Test=extent.startTest("Login Scenario");	
		SOP=new LoginPage();
		SOP.Login();	
	}
	catch(Exception e)
	{
		SF.getScreenshot(ResultFolderpath+"\\Error.png");
		Test.log(LogStatus.FAIL,e.getMessage()+" Error during Register:"+Test.addScreenCapture(ResultFolderpath+"\\Error.png"));
		extent.endTest(Test);
		extent.flush();	
	}
}

@AfterClass
public void teardown() throws InterruptedException, IOException{
	
}
}
