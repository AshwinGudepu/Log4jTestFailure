package pages;

import java.util.concurrent.TimeUnit;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.Select;
import org.apache.log4j.Logger;

import common.PageFactoryPage;

public class FindFlightsPage extends PageFactoryPage {
	
	final static Logger logger = Logger.getLogger(FindFlightsPage.class);

	public int adultPassengersCount = 0;
	public int childPassengersCount = 0;

	@FindBy(xpath = "//a[@class='button brand md block']")
	private WebElement getTitleOfHomePage;

	@FindBy(xpath = "//ul[@id='navBar']//h2[@itemprop='name'][contains(text(),'FLIGHTS')]")
	private By clickFlightsButton;

	@FindBy(xpath = "//fieldset[@class='origin']")
	private WebElement clickOriginInputBox;

	@FindBy(xpath = "//fieldset[@class='destination']/input")
	private WebElement clickDestinationInputBox;

	public final String originCountry = "Germany";
	public final String originState = "Munich (MUC)";

	@FindBy(xpath = "//h2[@class='title'][contains(text(),'" + originCountry + "')]//following::a[contains(text(),'"
			+ originState + "')]")
	private WebElement selectOriginCountryAndState;

	public final String destinationCountry = "Greece";
	public final String destinationState = "Mykonos (JMK)";

	@FindBy(xpath = "//h2[@class='title'][contains(text(),'" + destinationCountry
			+ "')]//following::a[contains(text(),'" + destinationState + "')]")
	private WebElement selectDestinationCountryAndState;

	@FindBy(xpath = "//select[@name='adults']/optgroup/option")
	private WebElement adultDropDown;

	@FindBy(xpath = "//select[@name='adults']")
	private WebElement selectingAdultNumber;

	@FindBy(xpath = "//select[@name='children']/optgroup/option")
	private WebElement childDropDown;

	@FindBy(xpath = "//select[@name='children']")
	private WebElement selectingChildNumber;

	@FindBy(xpath = "//form[@name='vm.searchSubmit']/a[@role='button'][contains(text(),'FIND FLIGHTS')]")
	private WebElement clickFindFLightsButton;

	public final String travelStartDate = "28";
	@FindBy(xpath = "//div[@class='ui-datepicker-group ui-datepicker-group-first']//following::a[@class='ui-state-default'][contains(text(),'"
			+ travelStartDate + "')]")
	private WebElement selectTravelStartDate;

	public final String travelReturnDate = "29";
	@FindBy(xpath = "//div[@class='ui-datepicker-group ui-datepicker-group-first']//following::a[@class='ui-state-default'][contains(text(),'"
			+ travelReturnDate + "')]")
	private WebElement selectTravelReturnDate;	

	public FindFlightsPage(WebDriver driver) {
		super(driver);
	}

	public String verifyLandingPage() throws Exception {
		driver.manage().timeouts().implicitlyWait(20, TimeUnit.SECONDS);
		String textName = getTitleOfHomePage.getText();
		return textName;
	}

	public void selectOriginAndDestinationState(String originCountry, String originState, String destinationCountry,
			String destinationState) throws Exception {
		clickOriginInputBox.click();
		selectOriginCountryAndState.click();
		Thread.sleep(2000);
		clickDestinationInputBox.click();
		selectDestinationCountryAndState.click();
		driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
		waitForPageToLoad(driver);
		logger.info("Selected Origin And Destination State");
	}

	public void selectStartDateAndReturnDate(String travelStartDate, String travelReturnDate) throws Exception {
		selectTravelStartDate.click();
		driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
		selectTravelReturnDate.click();
		driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
	}

	public String getSelectedOriginStateText() throws Exception {
		JavascriptExecutor js = (JavascriptExecutor) driver;
		String origin = (String) js.executeScript("return document.getElementsByName('origin')[0].value");
		driver.manage().timeouts().implicitlyWait(20, TimeUnit.SECONDS);
		return origin;
	}

	public String getSelectedDestinationStateText() throws Exception {
		JavascriptExecutor js = (JavascriptExecutor) driver;
		String destination = (String) js.executeScript("return document.getElementsByName('destination')[0].value");
		return destination;
	}

	public void selectNumberOfAdultAndChildPassengers() throws Exception {
		adultDropDown.click();
		Select selectAdults = new Select(selectingAdultNumber);
		selectAdults.selectByValue("number:2");
		childDropDown.click();
		Select selectChild = new Select(selectingChildNumber);
		selectChild.selectByValue("number:2");
		driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
	}

	public SummaryPage clickFindFlightsButton() throws Exception {
		Thread.sleep(2000);
		clickFindFLightsButton.click();
		return new SummaryPage(driver);
	}
}
