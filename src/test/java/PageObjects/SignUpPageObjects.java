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
public class SignUpPageObjects extends Shared_Functions{
Shared_Functions SF;
	
	//**** Register User Page ****/
	
	// Register Button
	
	 @AndroidFindBy(id="com.appsgallery.sagar.loginregistrationexample:id/register")
	 AndroidElement Register_btn;
	 
	 //// Username Textbox
	
	 @AndroidFindBy(id="com.appsgallery.sagar.loginregistrationexample:id/editTextUserName")
	 AndroidElement Username_Editbox;
	 
	 //// Password on Register page
		
	 @AndroidFindBy(id="com.appsgallery.sagar.loginregistrationexample:id/editTextPassword")
	 AndroidElement Password_Editbox;
	 
	 //// Confirm Password Editbox on Email page
		
	 @AndroidFindBy(id="com.appsgallery.sagar.loginregistrationexample:id/editTextConfirmPassword")
	 AndroidElement ConfirmPassword_Editbox;
	 
	 //// Create button on Register page
		
	 @AndroidFindBy(id="com.appsgallery.sagar.loginregistrationexample:id/buttonCreateAccount")
	 AndroidElement Create_UserBtn;
	 
	 @AndroidFindBy(xpath="//*[contains(text(),'Account')]")
	 AndroidElement Account_Created;
	
	
	
	//************************************
	//*****	Page Factory Initialization *****
	//*************************************
	    public SignUpPageObjects(){

	        this.driver = SF.driver;
	        //This initElements method will create all WebElements

	        PageFactory.initElements(new AppiumFieldDecorator(driver), this);
	        System.out.println("Completed Init");	        

	    }
	    
	  
	    
	    public void RegisterUser() throws IOException
	    {
	    	//Get username and password from configuration file
	    	String username=SF.GetFileValue("username");
	    	String password=SF.GetFileValue("password");	    
	    	//Thread.sleep(10000);
	    	SF.driver_Wait(Register_btn);
	    	//SF.Webdriver_Wait(By.id("com.amazon.mShop.android.shopping:id/sign_in_button"));
	    	System.out.println("Reached HOme page");	    	
	    	SF.Click_Element(Register_btn);	    
	    	SF.driver_Wait(Username_Editbox);
	    	SF.SendKeys(Username_Editbox,username);	   
	    	//SF.Click_Element(Continue_EmailBtn);
	    	Test.log(LogStatus.PASS,"Entered Username as: "+username);
			extent.flush();
			System.out.println("Entered Username");			
	    	SF.driver_Wait(Password_Editbox);
	    	SF.SendKeys(Password_Editbox,password);
	    	Test.log(LogStatus.PASS,"Entered password as: "+password);
	    	SF.driver_Wait(ConfirmPassword_Editbox);
	    	SF.SendKeys(ConfirmPassword_Editbox,password);
	    	SF.getScreenshot(ResultFolderpath+"\\RegisterPage.png");
			Test.log(LogStatus.PASS,"Register Page:"+Test.addScreenCapture(ResultFolderpath+"\\RegisterPage.png"));
			extent.flush();
	    	SF.driver_Wait(Create_UserBtn);	    	
	    	SF.Click_Element(Create_UserBtn);
	    	//SF.driver_Wait(Search_Editbox);
	    	SF.driver.manage().timeouts().implicitlyWait(5000, TimeUnit.SECONDS);
	    	//SF.driver_Wait(Account_Created);
	    	SF.getScreenshot(ResultFolderpath+"\\UserCreated.png");
			Test.log(LogStatus.PASS,"Welcome Page displayed successfully:"+Test.addScreenCapture(ResultFolderpath+"\\UserCreated.png"));
			extent.flush();
			SF.driver.navigate().back();
			SF.driver_Wait(Register_btn);
	    }
}
