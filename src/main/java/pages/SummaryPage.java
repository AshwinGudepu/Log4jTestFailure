package pages;

import java.util.concurrent.TimeUnit;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

import common.PageFactoryPage;

public class SummaryPage extends PageFactoryPage {

	public SummaryPage(WebDriver driver) {
		super(driver);
	}

	@FindBy(xpath = "//section[@class='booking-sumary']/h1")
	private WebElement summaryPageHeader;

	@FindBy(xpath = "//div[@class='resume-wrapper']/p")
	private WebElement gettotalNumberOfPassengers;

	@FindBy(xpath = "//div[@class='departure']/p/strong[@class='date']")
	private WebElement getDepatureDate;

	@FindBy(xpath = "//div[@class='departure']/p/strong[2]")
	private WebElement getDepatureCity;

	@FindBy(xpath = "//div[@class='return']/p/strong[@class='date']")
	private WebElement getReturnDate;

	@FindBy(xpath = "//div[@class='return']/p/strong[2]")
	private WebElement getReturnCity;
	
	@FindBy(xpath = "//div[@id='formBookingStep01']/a[contains(text(),'NEXT STEP')]")
	private WebElement clickNextStepButton;

	public boolean verifySummaryPageIsOpened() throws Exception {
		summaryPageHeader.getText();
		return true;
	}

	public String getNoOfFromPassengers() throws Exception {
		String noOfPassengers = gettotalNumberOfPassengers.getText();
		return noOfPassengers;
	}

	public String getDepatureDate() throws Exception {
		String depatureDate = getDepatureDate.getText();
		return depatureDate;
	}

	public String getDepatureCity() throws Exception {
		String depatureCity = getDepatureCity.getText();
		return depatureCity;
	}

	public String getReturnDate() throws Exception {
		String returnDate = getReturnDate.getText();
		return returnDate;
	}

	public String getReturnCity() throws Exception {
		String returnCity = getReturnCity.getText();
		return returnCity;
	}

	public PassengerInformationPage clickNextStepButton() {
		clickNextStepButton.click();
		driver.manage().timeouts().implicitlyWait(80, TimeUnit.SECONDS);
		return new PassengerInformationPage(driver);
	}
}
