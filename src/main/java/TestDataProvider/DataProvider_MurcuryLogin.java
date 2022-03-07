
package TestDataProvider;

import java.io.File;
import java.io.IOException;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.By;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.interactions.Actions;
import org.testng.annotations.AfterClass;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.BeforeSuite;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

public class DataProvider_MurcuryLogin 
{
	

	

	public class Basic_demo 
	{
		WebDriver driver;
		
		@BeforeSuite
		public void openbrowser()
		{
			
			System.setProperty("webdriver.chrome.driver", "C:\\Users\\Ganesh\\Downloads\\Testing Batch dec 2021\\chromedriver_win32 (3)\\chromedriver.exe");
			 driver= new ChromeDriver();
		}
		
		@BeforeTest
		public void enterURL()
		{
			System.out.println("enter url");
			driver.get("https://www.mercurytravels.co.in/");
		}
		
		@BeforeClass()
		public void maxbrowser()
		{
			System.out.println("maximize browser");	
			driver.manage().window().maximize();
		}
		
		@BeforeMethod()
		public void getcookies()
		{
			System.out.println("get cookies");
		}
		
		@Test (dataProvider = "dp")
		public void ABC(String user,String pass) throws InterruptedException
		{
			System.out.println("ABC");
			  Thread.sleep(3);
			  Actions a =new Actions(driver);
			  a.moveToElement(driver.findElement(By.xpath("/html/body/nav[2]/div/div[2]/ul/li[1]/a"))).build().perform();
			  Thread.sleep(2000);
			  driver.findElement(By.xpath("(//a[contains(text(),'User Login')])[2]")).click ();
			  Thread.sleep(2000);
			  driver.findElement(By.id("sign_user_email")).sendKeys(user);//code to enter emailID
			 Thread.sleep(2000);
			 driver.findElement(By.id("sign_user_password")).sendKeys(pass);//code to enter password
			 Thread.sleep(2000);
			 driver.findElement(By.xpath("(//button[@class='btn btn-lg btn-primary modal-btn ajax-submit'])[1]")).click();
			 Thread.sleep(3000);
			 
			boolean wb= driver.findElement(By.xpath("(//div[@class='modal-header']/h3)[6]")).isDisplayed();
			System.out.println(wb);
			 if(wb)
			 {
				 System.out.println("in");
				 driver.findElement(By.xpath("//button[@class='close open_parent']")).click();
				 Thread.sleep(2000);
				 driver.findElement(By.id("sign_user_email")).clear();
				 Thread.sleep(2000);
				 driver.findElement(By.id("sign_user_password")).clear();
				 Thread.sleep(2000);
				 driver.findElement(By.id("modalLogin")).click();
				 Thread.sleep(2000);
				 System.out.println("out");
			 }
			 else
			 {
				 WebElement welcomeUser=driver.findElement(By.xpath("(//span[@class='caret'])[6]"));
					Actions action = new Actions(driver);
						action.moveToElement(welcomeUser).build().perform();
						Thread.sleep(2000);
						WebElement logout=driver.findElement(By.xpath("(//a[text()=' Log Out'])[2]"));
				     logout.click(); 
				 System.out.println("Logout Successfully");
			 }
			 
			 
			 
			 
		}
		
		 @DataProvider(name="dp")
		  public Object[][] datap()
		  {
		    return new Object[][] 
		    		{
		      new Object[] { "sonawanesushil57@gmail.com", "Sushil@123" },
		      new Object[] { "sonawanesushil999@gmail.com", "Sushil@123" },
		      new Object[] { "sonawanesushil57@gmail.com", "Sushil@123" },
		    };
		  }
		
		
		
		@AfterMethod
		public void getscreenshot() throws IOException
		{
			System.out.println("get ss");
			
			File src=((TakesScreenshot)driver).getScreenshotAs(OutputType.FILE);
			FileUtils.copyFileToDirectory(src, new File("C:\\Users\\Ganesh\\eclipse-workspace\\AUTOMATION_TESTING_DEC_2021\\ss"));
		}
		
		@AfterClass
		public void deletecookies()
		{
		System.out.println("delete cookies");	
		}
		@AfterTest
		public void dbconnectionclosed()
		{
			System.out.println("db connection closed");
		}
		
		@AfterSuite
		public void close()
		{
			System.out.println("close the broser");
			driver.close();
		}
		
	}

}
