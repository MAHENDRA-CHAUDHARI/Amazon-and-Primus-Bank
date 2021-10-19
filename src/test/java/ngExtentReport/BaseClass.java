package ngExtentReport;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.ie.InternetExplorerDriver;
import org.testng.ITestResult;
import org.testng.annotations.AfterClass;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeSuite;
import org.testng.annotations.BeforeTest;
import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.Status;
import com.aventstack.extentreports.markuputils.ExtentColor;
import com.aventstack.extentreports.markuputils.MarkupHelper;
import com.aventstack.extentreports.reporter.ExtentHtmlReporter;
import com.aventstack.extentreports.reporter.configuration.Theme;

import helperFiles.ScreenShot;

public class BaseClass 
{
	public static WebDriver driver=null;
	public static Properties prop;
	public static ExtentReports extent;
	public static ExtentTest test;
	public static ExtentHtmlReporter htmlReporter;
	
	@BeforeSuite
public void BrowserLaunch() throws IOException
{ 
		htmlReporter=new ExtentHtmlReporter("./MyReport.html");
		extent=new ExtentReports();
		extent.attachReporter(htmlReporter);
		
		extent.setSystemInfo("OS", "WINDOW 10");
		extent.setSystemInfo("HostName", "Selenium");
		extent.setSystemInfo("Environment", "QA");
		extent.setSystemInfo("Author", "MAHENDRA");
		
		htmlReporter.config().setReportName("Amazon Report");
		htmlReporter.config().setDocumentTitle("Automation Report");
		htmlReporter.config().setTheme(Theme.DARK); 

		/*System.setProperty("webdriver.chrome.driver", "F:\\Selenium+Webdriver+java\\Softwares\\chromedriver_94.exe");
		driver=new ChromeDriver();
//		driver.get("https://www.amazon.in/"); */

		prop=new Properties();
		FileInputStream fis=new FileInputStream("C:\\Users\\DELL\\eclipse-workspace\\TestNg_Extent\\Jenkins\\src\\test\\java\\helperFiles\\data.properties");
		
		prop.load(fis);
		String urlName=prop.getProperty("url");
		String browserName=prop.getProperty("browser");

		if(browserName.equals("chrome"))
		{
//			test.log(Status.INFO, "Browser is Started...");
			System.setProperty("webdriver.chrome.driver", "F:\\Selenium+Webdriver+java\\Softwares\\chromedriver_94.exe");
			driver=new ChromeDriver();
			driver.manage().window().maximize();	
		}
		else if(browserName.equals("firefox"))
		{
			System.setProperty("webdriver.gecko.driver", "F:\\Selenium+Webdriver+java\\Softwares\\geckodriver_29.exe");
			driver=new FirefoxDriver();
			driver.manage().window().maximize();
		}
		else if(browserName.equals("IE"))
		{
			System.setProperty("webdriver.ie.driver", "F:\\Selenium+Webdriver+java\\Softwares\\IEDriverServer.exe");
			driver=new InternetExplorerDriver();
			driver.manage().window().maximize();
			driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS); 
		} 
}
		
	@AfterMethod
	 public void Teardown(ITestResult result) 
	 {
		 if(result.getStatus() == ITestResult.FAILURE)
		 {
			 test.log(Status.FAIL, MarkupHelper.createLabel(result.getName() + " - Test Case Failed", ExtentColor.RED));
			 test.log(Status.FAIL, "TESTCASE IS FAILED - " + result.getName());  //return the name of the method
			 test.log(Status.FAIL, "TESTCASE IS FAILED - " + result.getThrowable());  // throw exception to the report
		 }
		 else if(result.getStatus() == ITestResult.SKIP)
		 {
			 test.log(Status.SKIP, "TESTCASE IS SKIPPED - " + result.getName());
		 }
		 else if(result.getStatus() == ITestResult.SUCCESS)
		 {
			 test.log(Status.PASS, "TESTCASE IS PASSED - " + result.getName());
		 }
	 } 
	
	//Status of Pass & Fail TestCases
		@AfterMethod
		public void getResults(ITestResult result) throws IOException
		{
			if(result.getStatus()==ITestResult.FAILURE)
			{
				String screenShotPath=ScreenShot.screenCapture(driver, "This fail TestCase screen is given Below");
				test.fail(result.getThrowable()  +" Test is FAIL");
				test.addScreenCaptureFromPath(screenShotPath);
				
			} else
			{
				test.pass(result.getName() + " Test is Passed");
			}
		} 
		
	
	@AfterSuite
	public void endsReport() throws InterruptedException
	{
	Thread.sleep(5000);
	test.log(Status.INFO, "All TestCases is Done");
	driver.close();
	extent.flush();
	}

}
