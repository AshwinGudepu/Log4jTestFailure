package pages;

import java.util.List;

import org.apache.log4j.Logger;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import common.PageFactoryPage;

public class PassengerInformationPage extends PageFactoryPage {
	final static Logger logger = Logger.getLogger(PassengerInformationPage.class);
	public int adultPassengersCount;

	public PassengerInformationPage(WebDriver driver) {
		super(driver);
	}
	
	@FindBy(xpath = "//h2[@class='title main icon passenger']")
	private WebElement getPassengerInformationPageHeaderText;

	@FindBy(xpath = "//div[@data-type='Child']//div[@class='form-column']//input[contains(@name,'FirstName')]")
	private List<WebElement> getTotalNumbersOfChildren;

	@FindBy(xpath = "//div[@data-type='Adult']//div[@class='form-column']//input[contains(@name,'FirstName')]")
	private List<WebElement> getTotalNumbersOfAdults;

	public Boolean verifyPassengerInformationPageIsOpened() throws Exception {
		getPassengerInformationPageHeaderText.getText();
		return true;
	}
	
	public boolean enterAdultFirstNameAndLastName(String adultPassengerFirstName, String adultPassengerSecondName) {		
		adultPassengersCount = getTotalNumbersOfAdults.size();
		for (int adultPassengerCnt = 0; adultPassengerCnt < adultPassengersCount; adultPassengerCnt++) {
			driver.findElement(By
					.xpath("//div[@data-type='Adult']//div[@class='form-column']//input[contains(@name,'FirstName')][contains(@id,'name_"
							+ adultPassengerCnt + "')]"))
					.sendKeys(adultPassengerFirstName);
			driver.findElement(By
					.xpath("//div[@data-type='Adult']//div[@class='form-column']//input[contains(@name,'Surname')][contains(@id,'surname1_"
							+ adultPassengerCnt + "')]"))
					.sendKeys(adultPassengerSecondName);
		}
		return true;
	}

	public boolean enterChildFirstNameAndLastName(String childPassengerFirstName, String childPassengerSecondName) {
		logger.info("enterChildFirstNameAndLastName******************************************");
		int childPassengersCount = driver
				.findElements(By
						.xpath("//div[@data-type='Child']//div[@class='form-column']//input[contains(@name,'FirstName')]"))
				.size();
		for (int childPassengerCnt = adultPassengersCount; childPassengerCnt < adultPassengersCount
				+ childPassengersCount; childPassengerCnt++) {
			driver.findElement(By
					.xpath("//div[@data-type='Child']//div[@class='form-column']//input[contains(@name,'FirstName')][contains(@id,'name_"
							+ childPassengerCnt + "')]"))
					.sendKeys(childPassengerFirstName);
			driver.findElement(By
					.xpath("//div[@data-type='Child']//div[@class='form-column']//input[contains(@name,'Surname')][contains(@id,'surname11_"
							+ childPassengerCnt + "')]"))
					.sendKeys(childPassengerSecondName);
		}
		
		return true;
	}
}
