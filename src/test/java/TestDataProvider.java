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
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeSuite;
import org.testng.annotations.AfterSuite;

public class TestDataProvider
{
  WebDriver driver;
  
  @BeforeSuite
  public void openbrowser()
	{
		System.out.println("Open Browser");
		System.setProperty("webdriver.chrome.driver", "C:\\Users\\sh\\Downloads\\ChromeDriver\\chromedriver.exe");
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
	public void Github(String user,String pass) throws InterruptedException
	{
		System.out.println("Github Satrts");
		driver.get("https://github.com/");
	    driver.manage().window().maximize();
	    
	    driver.findElement(By.xpath("//div[@class='position-relative mr-3 mb-4 mb-lg-0 d-inline-block']")).click();
	    Thread.sleep(2000);
	    driver.findElement(By.xpath("//input[@id='login_field']")).sendKeys(user);
	    Thread.sleep(2000);
	    driver.findElement(By.xpath("//input[@id='password']")).sendKeys(pass);
	    Thread.sleep(2000);
	    driver.findElement(By.xpath("//input[@type='submit']")).click();
	    Thread.sleep(2000);
	    
	    driver.findElement(By.xpath("(//span[@class='dropdown-caret'])[2]")).click();
	    Thread.sleep(2000);
	    
	    driver.findElement(By.xpath("(//button[@type='submit'])[3]")).click();
	    Thread.sleep(2000);
	    
	    boolean wb=driver.findElement(By.id("js-flash-container")).isDisplayed();
	    System.out.println(wb);
	    if(wb)
	    {
	    	System.out.println("In");
	    	driver.findElement(By.xpath("(//button[@type='button'])[1]")).click();
	    	Thread.sleep(2000);
	    	driver.findElement(By.xpath("//input[@id='login_field']")).clear();
		    Thread.sleep(2000);
		    driver.findElement(By.xpath("//input[@id='password']")).clear();
		    Thread.sleep(2000);
		    driver.findElement(By.xpath("//input[@type='submit']")).click();
		    Thread.sleep(2000);
		    System.out.println("Out");
	    }
	    else
	    {
	    	driver.findElement(By.xpath("(//span[@class='dropdown-caret'])[2]")).click();
		    Thread.sleep(2000);
		    
		    driver.findElement(By.xpath("(//button[@type='submit'])[3]")).click();
		    Thread.sleep(2000);
		    
	    	//WebElement logout=driver.findElement(By.xpath("//button[@type='submit'])[3]\""));
		     //logout.click(); 
	    }
	    
	    
	    
	 }
	
	@DataProvider(name="dp")
	  public Object[][] datap()
	  {
	    return new Object[][] 
	    		{
	      new Object[] { "Yogita0912", "Yogita@0912" },
	      new Object[] { "yogita123", "Yogita@0912"},
	      new Object[] { "yogita456", "Yogita@0912" },
	    };
	  }
	
	@AfterMethod
	public void GetScreenshot() throws IOException
	{
		System.out.println("Take Sceenshot");
		
		File src=((TakesScreenshot)driver).getScreenshotAs(OutputType.FILE);
		FileUtils.copyFileToDirectory(src,new File("C:\\Users\\sh\\eclipse-workspace\\DataProviderProject\\ss"));
	}
  
	@AfterClass
	public void DeleteCookies()
	{
		System.out.println("Delete Coookies");
	}
	
	@AfterTest
	public void DBConnectionclosed()
	{
		System.out.println("Close Database Connection");
	}
	
	@AfterSuite
	public void Close()
	
	{
		System.out.println("Close");
	}
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	

}
