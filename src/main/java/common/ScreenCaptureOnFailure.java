package common;

import java.io.File;
import java.io.IOException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.apache.commons.io.FileUtils;
import org.openqa.selenium.OutputType;
import org.testng.ITestContext;
import org.testng.ITestListener;
import org.testng.ITestResult;
import drivers.DriverManager;

public class ScreenCaptureOnFailure implements ITestListener {
	
	public WebDriver driver;
	
	public void onTestStart(ITestResult result) {
		deleteAllFiles();
	}

	public void onTestSuccess(ITestResult result) {
	}

	public void onTestFailure( ITestResult result) {
		screenCapture();
	}

	public void onTestSkipped(ITestResult result) {
	}

	public void onTestFailedButWithinSuccessPercentage(ITestResult result) {
	}

	public void onStart(ITestContext context) {
	}

	public void onFinish(ITestContext context) {
	}
		
	public void screenCapture() {		
		driver = DriverManager.getBrowserInstance();
		File scrFile = ((TakesScreenshot) driver).getScreenshotAs(OutputType.FILE);
		try {
			FileUtils.copyFile(scrFile, new File(".\\ScreenCapture\\screenshot_" + appendTimeStampToFile() + ".png"));
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	public String appendTimeStampToFile() {
		DateFormat format = new SimpleDateFormat("yyyyMMddhhmmss");
		String timeStamp = format.format(new Date());
		return timeStamp;
	}
	
	public void deleteAllFiles() {
		File directory = new File(".\\ScreenCapture");
		File[] files = directory.listFiles();
		for (File file : files) {
			if (!file.delete()) {
				System.out.println("Failed to delete " + file);
			}
		}
	}

}
