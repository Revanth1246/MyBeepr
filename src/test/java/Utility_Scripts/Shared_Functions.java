package Utility_Scripts;
import io.appium.java_client.AppiumDriver;
import io.appium.java_client.MobileBy;
import io.appium.java_client.MobileElement;
import io.appium.java_client.TouchAction;
import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.android.AndroidElement;
import io.appium.java_client.touch.offset.PointOption;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;
import java.sql.Array;
import java.text.SimpleDateFormat;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Calendar;
import java.util.Date;
import java.util.Enumeration;
import java.util.HashMap;
import java.util.List;
import java.util.Properties;
import java.util.concurrent.TimeUnit;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.Alert;
import org.openqa.selenium.By;
import org.openqa.selenium.Dimension;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.Point;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.interactions.Locatable;

import org.openqa.selenium.remote.CapabilityType;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.FluentWait;
import org.openqa.selenium.support.ui.Wait;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import com.relevantcodes.extentreports.ExtentReports;
import com.relevantcodes.extentreports.ExtentTest;

import io.appium.java_client.android.AndroidElement;
import io.appium.java_client.MultiTouchAction;
import io.appium.java_client.TouchAction;
//import com.relevantcodes.extentreports.Chart;
import org.openqa.selenium.Dimension;
import java.time.Duration; 
import static io.appium.java_client.touch.TapOptions.tapOptions;
import static io.appium.java_client.touch.WaitOptions.waitOptions;
import static io.appium.java_client.touch.offset.ElementOption.element;
import static io.appium.java_client.touch.offset.PointOption.point;
import static java.time.Duration.ofMillis;
import static java.time.Duration.ofSeconds;


public class Shared_Functions {
	 Process p;
		public static ExtentReports extent;
		public static ExtentTest Test; 
		public static String Datapath=System.getProperty("user.dir");
	 // Set path of your node.exe file.
	 // Progra~1 represents Program Files folder.Android
	 String nodePath = "C:/Progra~1/Appium/node.exe";
	  public static String ResultFilepath,ResultFolderpath;
	 // Set path of your appium.js file.
	 String appiumJSPath = "C:/Progra~1/Appium/node_modules/appium/bin/appium.js";
	 String cmd = nodePath + " " + appiumJSPath;
	 public static AndroidDriver driver;
	 
	 // This method Is responsible for starting appium server.
	 public void appiumStart() throws IOException, InterruptedException {
	  // Execute command string to start appium server.
	  p = Runtime.getRuntime().exec(cmd);
	  Thread.sleep(10000);
	  if (p != null) {
	   System.out.println("Appium server Is started now.");
	  }
	 }

	 // This method Is responsible for stopping appium server.
	 public void appiumStop() throws IOException {
	  if (p != null) {
	   p.destroy();
	  }
	  System.out.println("Appium server Is stopped now.");
	 }

	 

	 public boolean ScrollBottom(String name) throws InterruptedException{
		 Dimension dimensions = driver.manage().window().getSize();	
		 Double screenHeightStart = dimensions.getHeight() * 0.8;
		 boolean found=false;
		 int scrollStart = screenHeightStart.intValue();
		 System.out.println("s="+screenHeightStart);
		 Double screenHeightEnd = dimensions.getHeight() * 0.1;
		 int scrollEnd = screenHeightEnd.intValue();
		 int x=dimensions.width/35;
		 System.out.println("sEnd="+scrollEnd);
		 for (int i=10;i>=1;i--){
			 System.out.println("value:"+i);		 
			 int present=driver.findElements(By.xpath("//*[contains(@text,'"+name+"')]")).size();
			 
			 if(present != 0){
				 
				 String testing=driver.findElementByXPath("//*[contains(@text,'"+name+"')]").getText();
				 System.out.println("Assigments value dislayed are : "+testing);
				 driver.findElementByXPath("//*[contains(@text,'"+name+"')]").click();			 
				 found=true;
				 System.out.println("Element is Present");
				 break;			 
				 
			  }else{
				 
				  System.out.println("Element is Absent");
				 // driver.swipe(x,scrollStart,x,scrollEnd,10000);
			  }

		 }
		 		return found;
		}
	 public boolean Scrollpart(String name,@SuppressWarnings("rawtypes") AndroidDriver driver) throws InterruptedException{
		 boolean found=false;	 	 
		 System.out.println("Executing for: "+name);	 
		 Dimension size = driver.manage().window().getSize();
		    int x = size.width / 2;
		    //int starty = (int) (size.height * 0.60);
		    int starty = (int) (size.height * 0.8);
		    int endy = (int) (size.height * 0.10);
		  
		 System.out.println("sEnd="+endy+ " / x="+x+ "/ starty="+starty);
		 for (int i=10;i>=1;i--){
			 System.out.println("value:"+i);
			  
			try{
			 int present=driver.findElements(By.xpath("//*[contains(@text,'"+name+"')]")).size();
			 System.out.println("Element numer is "+present);	
			 if(present != 0){			 
				 driver.findElementByXPath("//*[contains(@text,'"+name+"')]").click();
				 found=true;
				 System.out.println("Element is Present");
				 break;
				 
				 }else{
				 System.out.println("Element is Absent");
				// driver.swipe(x, starty, x, endy, 5000);
				 }
			}catch(Exception e){
				System.out.println("Error: "+e.getMessage());
				
			}
			
		 }
		 	return found;
		}
	 public static void getScreenshot(String outputlocation) throws IOException
	 {
		 System.out.println("Capturing the snapshot of the page ");
		 File srcFiler=((TakesScreenshot)driver).getScreenshotAs(OutputType.FILE);
		 FileUtils.copyFile(srcFiler, new File(outputlocation));
		
	}
	
public static void scroll(By by)
{
	while(driver.findElements(by).size()== 0)
	{

		}
}
	public boolean element_exists(String property,String value,AndroidDriver driver){
		
		boolean result=false;
			
		int present=driver.findElements(By.xpath("//*[contains(@"+property+",'"+value+"')]")).size();
		
		if(present!=0) {
			result=true;
		}	
		
		return result;
		
		}
    public void tapByElement (AndroidElement androidElement) {
        new TouchAction(driver)
                .tap(tapOptions().withElement(element(androidElement)))
                .waitAction(waitOptions(Duration.ofMillis(250))).perform();
    }
	public static void Webdriver_Wait(By by)
	{
		WebDriverWait wait = new WebDriverWait(driver, 200);
		wait.until((ExpectedConditions.presenceOfElementLocated(by)));
	}

	
	public static void driver_Wait(WebElement element)
	{
		WebDriverWait wait = new WebDriverWait(driver, 200);
		wait.until(ExpectedConditions.visibilityOf(element));
	}
	public static String GetDate(String Format)
	{
		System.out.println("Get Date Shared Function");		
		 DateTimeFormatter dtf = DateTimeFormatter.ofPattern(Format);
		   LocalDateTime now = LocalDateTime.now();
		   System.out.println(dtf.format(now));
		   return dtf.format(now).toString();
	}
	public static String Get_Text(WebElement element,WebDriver driver)
	{
		driver_Wait(element);
		return element.getText();	
		
	}
	public static void Click_Element(WebElement element)
	{
		driver_Wait(element);
		element.click();	
		try {
			Thread.sleep(1000);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	public static void SendKeys(WebElement element,String value)
	{
		driver_Wait(element);
		element.sendKeys(value);
		
	}
	public static void Slide(AndroidElement element) throws InterruptedException{
		System.out.println("SLide function");
		for(int i=0;i<10;i++)
		{
			Thread.sleep(5000);
		
		System.out.println("SLide function valled ");
		Dimension size=driver.manage().window().getSize();
	    System.out.println("Size is "+size);
	    int width=(int)(size.getWidth()/2);
	    System.out.println("width is "+width);
	    int startPoint=(int)(size.getHeight()*0.8);
	    System.out.println("startPoint is "+startPoint);
	    int endPoint=(int)(size.getHeight()*0.1);
	    System.out.println("endPoint is "+endPoint);
	    new TouchAction(driver).press(PointOption.point(width, startPoint)).waitAction().moveTo(PointOption.point(width, endPoint)).release().perform();
	    
	    if(element.isDisplayed())
	    {
	    	//element.click();
	    	 System.out.println("Element dislayed ");
	    	break;
	    }
	}
		
}
	public static void Javascript_Click(WebElement element)
	{
		
		JavascriptExecutor executor = (JavascriptExecutor)driver;
		executor.executeScript("arguments[0].click();", element);
	}
	public static void Javascript_Scroll(WebElement element)
	{
		WebElement element1=element;
		((JavascriptExecutor)driver).executeScript("arguments[0].scrollIntoView();", element1);
	}
	public static void Scroll_down(WebDriver driver) throws InterruptedException
	{
		 JavascriptExecutor js = (JavascriptExecutor) driver;
	      js.executeScript("javascript:window.scrollBy(250,350)");
	      Thread.sleep(1000);
	}
	
	public static void Scroll_To_Element(WebElement element) throws InterruptedException
	{
		//WebElement element = driver.findElement(By.id("my-id"));
		driver_Wait(element);
		Actions actions = new Actions(driver);
		actions.moveToElement(element);
		actions.perform();
		Thread.sleep(200);
	}
	public static String CreateDirectory(String Filename)
	{
		Date now = new Date();
		String dateFormat =  new SimpleDateFormat("yyyyMMdd_HHmmss").format(Calendar.getInstance().getTime());	
		String file = Datapath+"\\Reports\\"+Filename+"_"+dateFormat;
		File dir = new File(file);
		dir.mkdir();
		System.out.println("File Created at "+file);
		return file;
	}
	 public static String CreateHTMLFile(String fileName) throws IOException {
	      
		 ResultFolderpath=CreateDirectory(fileName);      
	      String resultFile = ResultFolderpath+"\\"+fileName +".html";
	      File file = new File(resultFile);
	      file.createNewFile();
	      // if file does exists, then delete and create a new file
	  		extent = new ExtentReports(resultFile, true);
	  		extent.loadConfig(new File(Datapath+"\\extent-config.xml"));
	      return resultFile;
	  }
	 public static String GetFileValue(String Fieldname)
	 {
		 String Fieldvalue = null;
		// System.out.println( "Reached GetFilevalues");
		 File file = new File(Datapath+"\\src\\test\\java\\Configuration.properties");
				 			FileInputStream fileInput = null;
				 			try {
				 				fileInput = new FileInputStream(file);
				 			} catch (FileNotFoundException e) {
				 				e.printStackTrace();
				 			}
				 			Properties prop = new Properties();
				 			try {
				 				prop.load(fileInput);
				 			} catch (IOException e) {
				 				e.printStackTrace();
				 			}
				 			Enumeration KeyValues = prop.keys();
				 			while (KeyValues.hasMoreElements()) {
				 				String key = (String) KeyValues.nextElement();
				 			//	System.out.println(key + " value ");
				 			
				 			    if(Fieldname.toLowerCase().equals(key.toLowerCase()))
				 			    {
				 					Fieldvalue = prop.getProperty(key);
				 					System.out.println(key + " matched value is:  " + Fieldvalue);
				 					//return Fieldvalue; 
				 			    }
				 			}
				 			
				return Fieldvalue; 			
	 }
	
	 public static void LaunchApp() throws Exception
	 {
		 System.out.println("Launching App");
		//*****  Creating the Desired Capabilities*****///
			DesiredCapabilities capabilities = new DesiredCapabilities();
			capabilities.setCapability("VERSION",""+GetFileValue("VERSION")); 
			capabilities.setCapability("deviceName",""+GetFileValue("deviceName"));
			//capabilities.setCapability("deviceName","3201319b830ac5c5");
			//capabilities.setCapability("deviceName","Google Pixel 3 GoogleAPI Emulator");
			capabilities.setCapability("platformName",""+GetFileValue("platformName"));   
		    capabilities.setCapability("appPackage", ""+GetFileValue("appPackage"));
			capabilities.setCapability("appActivity",""+GetFileValue("appActivity"));
			String appvalue=Datapath+ "\\"+GetFileValue("app");
			System.out.println(appvalue);
			capabilities.setCapability("app",appvalue);
			capabilities.setCapability("unicodeKeyboard",""+GetFileValue("unicodeKeyboard"));                                     
			capabilities.setCapability("resetKeyboard", ""+GetFileValue("resetKeyboard"));
			 capabilities.setCapability("fullReset", ""+GetFileValue("fullReset"));
			 capabilities.setCapability("noReset", "true");

		    //Create AppiumDriver instance and connect to the Appium server    
		     URL url=new URL("http://127.0.0.1:4723/wd/hub");
			 driver=new AndroidDriver(url,capabilities);
	 }
	
}
