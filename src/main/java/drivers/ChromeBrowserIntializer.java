package drivers;

import org.apache.log4j.Logger;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

import pages.PassengerInformationPage;

public class ChromeBrowserIntializer extends BrowserIntializer {
	final static Logger logger = Logger.getLogger(ChromeBrowserIntializer.class);
	@Override
	public WebDriver intializeBrowser() {
		if (null == driver) {
			BrowserProperties.Builder builder = new BrowserProperties.Builder();
			builder.browserClassPath("webdriver.chrome.driver").browserDriverPath(".\\resources\\chromedriver.exe");
			BrowserProperties chromeProperties = builder.build();
			System.setProperty(chromeProperties.getbrowserClassPath(), chromeProperties.getbrowserDriverPath());
			driver = new ChromeDriver();
		}
		logger.info("Chrome Browser Initiated");
		return driver;
	}

	public static void closeWebBrowser() {
		if (null != driver) {
			driver.quit();
		}
		driver = null;
	}	
}
