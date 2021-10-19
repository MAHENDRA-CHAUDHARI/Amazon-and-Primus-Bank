package ngExtentReport;

import java.io.IOException;
import java.util.concurrent.TimeUnit;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.Select;
import org.testng.Assert;
import org.testng.ITestResult;
import org.testng.annotations.AfterClass;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;
import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.Status;
import com.aventstack.extentreports.reporter.ExtentHtmlReporter;
import com.aventstack.extentreports.reporter.configuration.Theme;

import helperFiles.ScreenShot;


public class PrimusBank extends BaseClass
{

	
	@Test
	public void Application()
	{
		test=extent.createTest("Application");
		test.log(Status.PASS, "Application Start successfully");
		driver.get(prop.getProperty("url2"));
		String url2=driver.getCurrentUrl();
		Assert.assertEquals(url2, "http://primusbank.qedgetech.com/");
		System.out.println(url2);
		driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
		test.log(Status.PASS, "Application Running");
	}
	
	//Login Details
	@Test(priority=0)
	public void Login()
	{
	test=extent.createTest("Login by credential ID & Password");
	test.log(Status.INFO, "Login Execution Start");
	driver.findElement(By.id("txtuId")).sendKeys("Admin");
	driver.findElement(By.id("txtPword")).sendKeys("Admin");
	driver.findElement(By.id("login")).click();
	test.log(Status.INFO, "Login Execution End");
	driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
	
	}
	
	//Click on Branch menu
	@Test(priority=1)
	public void bank()
	{
	test=extent.createTest("Click Bank Branch");
	test.log(Status.INFO, "Bank Execution Start");
	WebElement branchLogo=driver.findElement(By.xpath("//img[@src='images/Branches_but.jpg']"));         
	Assert.assertEquals(true, branchLogo.isDisplayed());            //Element is display or not
	System.out.println("Branch Logo clickable is displayed");
	branchLogo.click();
	test.log(Status.INFO, "Bank Execution End");
	driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
	}
	
	//Bank Country Selection
	@Test(priority=2)
	public void Country()
	{
	test=extent.createTest("Select Country");
	test.log(Status.INFO, "Country Selection Execution Start");
	Select Country =new Select (driver.findElement(By.id("lst_countryS")));                //select Country
	Country.selectByIndex(1); 
	test.log(Status.INFO, "Country Selection Execution End");
	driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
	}
	
	//Bank State Selection
	@Test(priority=3)
	public void State()
	{
	test=extent.createTest("Select State");
	test.log(Status.INFO, "State Selection Execution Start");
	Select State =new Select (driver.findElement(By.id("lst_stateS")));                //select State
	State.selectByValue("MAHARASTRA");
	test.log(Status.INFO, "State Selection Execution End");
	driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
	}
	

	//Bank City Selection
	@Test(priority=4)
	public void City()
	{
	test=extent.createTest("Select City");
	test.log(Status.INFO, "City Selection execution Start");
	Select City =new Select (driver.findElement(By.xpath("//*[@name='lst_cityS']")));     //select City
	City.selectByVisibleText("SHIRDI");
	System.out.println("City is selected");
	driver.findElement(By.name("btn_search")).click();         //Click on Search  button
	test.log(Status.INFO, "City Selection Execution End");
	driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
	}
	
	//Print available Bank
	@Test(priority=5)
	public void AvailableBank()
	{
	test=extent.createTest("Check available Bank in this Area");
	test.log(Status.INFO, "Bank available execution Start");
	String Detail=driver.findElement(By.xpath("//*[@id='lab19']")).getText();   //Print Text of bank Details
	System.out.println(Detail);
	
	String Total=driver.findElement(By.xpath("//*[@colspan='8']")).getText();     //Print Total bank available in Regions
	System.out.println("Total Bank Available this Area:  "+Total);
	test.log(Status.INFO, "Bank available execution End");
	driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
	}
	
	@Test(priority=6)
	public void Logout()
	{
		test=extent.createTest("User successfully Logout");
		driver.findElement(By.xpath("//*[@src='images/admin_but_03.jpg']")).click();    //Logout
		test.log(Status.INFO, "User Successfully Logout");
		driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
	}
}
