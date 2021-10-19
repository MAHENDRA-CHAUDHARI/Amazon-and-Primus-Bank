package ngExtentReport;

import java.util.concurrent.TimeUnit;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.testng.Assert;
import org.testng.annotations.Test;
import com.aventstack.extentreports.Status;


public class Amazon extends BaseClass
{

	
	@Test (priority=0)
public void Application()
{
	test=extent.createTest("Application");
	test.log(Status.PASS, "Application Start successfully");
	driver.get(prop.getProperty("url"));
//	driver.get("https://www.amazon.in/");
	String url=driver.getCurrentUrl();
	Assert.assertEquals(url, "https://www.amazon.in/");
	System.out.println(url);
	driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
	test.log(Status.PASS, "Application Running");
}
	
	@Test  (priority=1)
	public void validation()
	{
		test=extent.createTest("validation");
		test.log(Status.INFO, "Validation Process Start");
		String name=driver.findElement(By.xpath("//*[@id='glow-ingress-line1']")).getText();
		Assert.assertEquals(name, "Hello");
		driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
		test.log(Status.INFO, "Validation Process End");
	}
	
	@Test (priority=2)
	public void verifyTitle() 
	{
		test=extent.createTest("verifyTitle");
		test.log(Status.INFO, "VerifcyTitle Process Start");
		String title=driver.getTitle();
		String verify="Online Shopping site in India: Shop Online for Mobiles, Books, Watches, Shoes and More - Amazon.in";
		Assert.assertEquals(title, verify);
		System.out.println("Title: "+title);
		test.log(Status.INFO, "VerifcyTitle Process End");
		driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
	}
	
	@Test(dependsOnMethods= {"verifyTitle"})
	public void SearchMobile()
	{
		test=extent.createTest("SearchMobile");
		test.log(Status.INFO, "SeacrhMobile Process Start");
		driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
		driver.findElement(By.id("twotabsearchtextbox")).sendKeys("Mobile");
		driver.findElement(By.cssSelector("#nav-search-submit-button")).click();
		
		Boolean display=driver.findElement(By.xpath("//*[text()='Redmi']")).isDisplayed();
		System.out.println("Product name:   " +display);
		test.log(Status.INFO, "Search Mobile Process Start");
	}
	
	@Test(dependsOnMethods= {"SearchMobile"})
	public void clickonMobile()
	{
		test=extent.createTest("clickonMobile");
		test.log(Status.INFO, "ClickOnMobile Process Start");
		driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
		driver.findElement(By.id("twotabsearchtextbox")).click();
		Assert.assertTrue(true);
		test.log(Status.INFO, "ClickOnMobile Process End");
	}
	
	@Test (priority=3)
	public void BrandName()
	{
		test=extent.createTest("Mobile Brand Name");
		test.log(Status.INFO, "BrandName mobile Process is Started");
		WebElement brand=driver.findElement(By.xpath("//*[text()='Redmi']"));
		Assert.assertEquals(true, brand.isDisplayed());
		brand.click();
		test.log(Status.INFO, "BrandName mobile Process is Started");
		driver.manage().timeouts().pageLoadTimeout(10, TimeUnit.SECONDS);
	}
	
	@Test (priority=4)
	public void LowPriceRange()
	{
		driver.manage().timeouts().pageLoadTimeout(10, TimeUnit.SECONDS);
		test=extent.createTest("Mobile PriceRange");
		test.log(Status.INFO, "LowPriceRange Process is Started");
		WebElement Lprice=driver.findElement(By.xpath("//*[@id='low-price']"));
		Assert.assertEquals(true, Lprice.isDisplayed());
		Lprice.sendKeys("2000");
		test.log(Status.INFO, "LowPriceRange Process is Ended");
	}
	
	@Test (priority=5)
	public void HighPriceRange()
	{
		driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
		test=extent.createTest("Mobile PriceRange");
		test.log(Status.INFO, "HighPriceRange Process is Started");
		WebElement Hprice=driver.findElement(By.cssSelector("#high-price"));
		Assert.assertEquals(true, Hprice.isDisplayed());
		Hprice.sendKeys("20000");
		test.log(Status.INFO, "HighPriceRange Process is Ended");
	}
	
	@Test (priority=6)
	public void GoButton()
	{
		test=extent.createTest("ClickOnGoButton");
		test.log(Status.FAIL, "If Fail Show Message");
		driver.findElement(By.cssSelector(".a-button-input")).click();
		test.log(Status.WARNING, "All Process is done will you check anyone remaining");
	}
	
	
} 
