package common;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.pagefactory.AjaxElementLocatorFactory;

public abstract class PageFactoryPage extends BasePage{
	protected WebDriver driver;

	public int timeInSeconds = 30;

	public PageFactoryPage(WebDriver driver) {
		this.driver = driver;
		PageFactory.initElements(new AjaxElementLocatorFactory(driver, timeInSeconds), this);
	}	
}
