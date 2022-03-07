import org.testng.annotations.Test;

import org.testng.annotations.BeforeMethod;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.DataProvider;
import org.testng.annotations.BeforeClass;

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
import org.testng.annotations.BeforeTest;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeSuite;
import org.testng.annotations.AfterSuite;

public class Murcury_DataProvider 
{
  WebDriver driver;
  
  @BeforeSuite
  public void openbrowser()
  {
	System.setProperty("webdriver.chrome.driver", "D:\\ChromeDriver\\chromedriver.exe");  
	driver=new ChromeDriver();
  }
  
  @BeforeTest
  public void EnterURL()
  {
	  System.out.println("Enter URL");
  }
	
  
  @BeforeClass
  public void MaxBrowser()
  {
	  System.out.println("Maximize Browser");
	  driver.manage().window().maximize();
  }
  
  @BeforeMethod
  public void GetCookies()
  {
	  System.out.println("Get Cookies");
  }
  
  @Test(dataProvider = "dp")
  public void Murcury(String user,String pass) throws InterruptedException
  {
	  System.out.println("Murcury starts");
	  driver.get("https://www.mercurytravels.co.in/");
	  driver.manage().window().maximize();
	  
	  driver.findElement(By.xpath("/html/body/nav[2]/div/div[2]/ul/li/a")).click();
	  Thread.sleep(2000);
	  driver.findElement(By.xpath("(//a[@data-target='#modalLogin'])[2]")).click();
	  Thread.sleep(2000);
	  driver.findElement(By.id("sign_user_email")).sendKeys(user);
	  Thread.sleep(2000);
	  driver.findElement(By.id("sign_user_password")).sendKeys(pass);
	  Thread.sleep(2000);
	  driver.findElement(By.xpath("(//button[@class='btn btn-lg btn-primary modal-btn ajax-submit'])[1]")).click();
	  Thread.sleep(2000);
	  
	  boolean wb=driver.findElement(By.xpath("(//div[@class='modal-body'])[6]")).isDisplayed();
	  System.out.println(wb);
	  if(wb)
	  {
		  System.out.println("Murcury in");
		  driver.findElement(By.xpath("//button[@class='close open_parent']")).click();
		  Thread.sleep(2000);
		  driver.findElement(By.id("sign_user_email")).clear();
		  Thread.sleep(2000);
		  driver.findElement(By.id("sign_user_password")).clear();
		  Thread.sleep(2000);
	  }
	  
	  else
		 {
			 WebElement welcomeUser=driver.findElement(By.xpath("(//span[@class='caret'])[6]"));
				Actions action = new Actions(driver);
					action.moveToElement(welcomeUser).build().perform();
					Thread.sleep(2000);
					WebElement logout=driver.findElement(By.xpath("(//a[text()=' Log Out'])[2]"));
			     logout.click(); 
		 }
	  
	  
  }
  
  @DataProvider(name="dp")
  public Object[][] datap()
  {
	  return new Object[][]
			  {
		  new Object[] { "yogitaithape4@gmail.com", "yogita@123" },
	      new Object[] { "yogitaithape4@gmail.com", "Yogita@0912"},
	      new Object[] { "yogitaithape4@gmail.com", "yogita@123" },
			  
			  };
  }
  
  @AfterMethod
  public void GetScreenshot() throws IOException
  {
	  System.out.println("Takes Screenshot");
	  File src=((TakesScreenshot)driver).getScreenshotAs(OutputType.FILE);
	  FileUtils.copyFileToDirectory(src, new File("C:\\Users\\sh\\eclipse-workspace\\DataProviderProject\\screenshot"));
	  
  }
 
  @AfterClass
  public void DleteCookies()
  {
	  System.out.println("Delete Cookies");
  }
  
  @AfterTest
  public void DBCoonnectionClose()
  {
	  System.out.println("Close Connection");
  }
  
  @AfterSuite
  public void Close()
  {
	  System.out.println("Close"); 
  }
  
}

