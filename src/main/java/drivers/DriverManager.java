package drivers;

import org.openqa.selenium.WebDriver;

public class DriverManager {
	
	private static WebDriver driver;

    private DriverManager() {}

    public static WebDriver getWebDriverInstance(String browserName) {
    	if(browserName.equals("Chrome")){
    		BrowserIntializer browserIntializer=new  ChromeBrowserIntializer();
    		driver=browserIntializer.intializeBrowser();
    		}else if (browserName.equals("firefox")){
    			BrowserIntializer browserIntializer=new  FireFoxDriverIntializer();
    			driver=browserIntializer.intializeBrowser();	
    		}
        return driver;
    }

    public static void closeWebBrowser(){
        if (null != driver){
            driver.quit();
        }
        driver = null;
    }  
    
    public static WebDriver getBrowserInstance(){
    	return driver;
    }
}
