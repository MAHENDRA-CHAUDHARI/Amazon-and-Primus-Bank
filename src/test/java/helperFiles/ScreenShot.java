package helperFiles;

import java.io.File;
import java.io.IOException;
import org.apache.commons.io.FileUtils;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.io.FileHandler;

public class ScreenShot 
{
	
public static String screenCapture(WebDriver driver, String screenshotName) throws IOException
{
//	File screen=((TakesScreenshot)driver).getScreenshotAs(OutputType.FILE);
//	FileHandler.copyFile(source, destination)
//	FileUtils.copyfile
	
	TakesScreenshot ts=(TakesScreenshot)driver;
	File source=ts.getScreenshotAs(OutputType.FILE);
	String dest=System.getProperty("user.dir")+screenshotName +".png";
	File destination=new File(dest);
    FileUtils.copyFile(source, destination);
//    FileHandler.copy(source, destination);
	return dest;
}
}
