package PageObjects;
import java.awt.AWTException;
import java.io.IOException;
import java.util.List;
import java.util.concurrent.TimeUnit;
import org.openqa.selenium.By;
import org.openqa.selenium.Dimension;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.interactions.internal.TouchAction;
import org.openqa.selenium.interactions.touch.TouchActions;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.FindBys;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;

import com.relevantcodes.extentreports.LogStatus;

import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.android.AndroidElement;
import io.appium.java_client.android.AndroidTouchAction;
import io.appium.java_client.pagefactory.AndroidBy;
//import io.appium.java_client.android.AndroidKeyCode;
import io.appium.java_client.pagefactory.AndroidFindBy;
import io.appium.java_client.pagefactory.AndroidFindBys;
import io.appium.java_client.pagefactory.AppiumFieldDecorator;
import io.appium.java_client.touch.LongPressOptions;
import io.appium.java_client.touch.offset.ElementOption;
import org.openqa.selenium.support.PageFactory;
import Utility_Scripts.Shared_Functions;
import io.appium.java_client.android.nativekey.AndroidKey;
import PageObjects.SignUpPageObjects;

public class LoginPage extends Shared_Functions {
	
	Shared_Functions SF=new Shared_Functions();
	SignUpPageObjects APO=new SignUpPageObjects();
	
	//**** Login Page ****/
	
		// Username Editbox Button
		
		 @AndroidFindBy(id="com.appsgallery.sagar.loginregistrationexample:id/editLogin")
		 AndroidElement Username_Editbox;
		 
		 //// Password Textbox
		
		 @AndroidFindBy(id="com.appsgallery.sagar.loginregistrationexample:id/editPasswordLogin")
		 AndroidElement Password_Editbox;
		 
		 //// Login button 
			
		 @AndroidFindBy(id="com.appsgallery.sagar.loginregistrationexample:id/login")
		 AndroidElement Login_btn;
		 
		 @AndroidFindBy(id="com.appsgallery.sagar.loginregistrationexample:id/textView8")
		 AndroidElement Successful_Login;
		
		//************************************
			//*****	Page Factory Initialization *****
			//*************************************
			    public LoginPage(){

			        this.driver = SF.driver;
			        //This initElements method will create all WebElements

			        PageFactory.initElements(new AppiumFieldDecorator(driver), this);
			        System.out.println("Completed Init");
			        

			    }
			    
			    public void Login() throws IOException
			    {
			    	//Get username and password from configuration file
			    	String username=SF.GetFileValue("username");
			    	String password=SF.GetFileValue("password");	    
			    	//Thread.sleep(10000);
			    	SF.driver_Wait(Username_Editbox);
			    	//SF.Webdriver_Wait(By.id("com.amazon.mShop.android.shopping:id/sign_in_button"));
			    	System.out.println("Reached Login page");			    	
			    	SF.SendKeys(Username_Editbox,username);
			    	Test.log(LogStatus.PASS,"Entered username as "+username);
					System.out.println("Entered Username");
					SF.driver_Wait(Password_Editbox);
			    	SF.SendKeys(Password_Editbox,password);
			    	Test.log(LogStatus.PASS,"Entered password as "+password);
			    	SF.getScreenshot(ResultFolderpath+"\\LoginPage1.png");
					Test.log(LogStatus.PASS,"Login Page:"+Test.addScreenCapture(ResultFolderpath+"\\LoginPage1.png"));
					extent.flush();			    		    	
			    	SF.Click_Element(Login_btn);
			    	//SF.Click_Element(Login_btn);
			    	SF.driver.manage().timeouts().implicitlyWait(5000, TimeUnit.SECONDS);
			    	SF.driver_Wait(Successful_Login);
			    	SF.getScreenshot(ResultFolderpath+"\\WelcomePage.png");
					Test.log(LogStatus.PASS,"Welcome Page displayed successfully:"+Test.addScreenCapture(ResultFolderpath+"\\WelcomePage.png"));
					extent.flush();
			    }
			    
			    
}
