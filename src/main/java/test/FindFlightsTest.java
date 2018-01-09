package test;

import static org.testng.Assert.assertEquals;
import static org.testng.Assert.assertTrue;
import org.openqa.selenium.WebDriver;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeSuite;
import org.testng.annotations.Listeners;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;
import drivers.DriverManager;
import pages.FindFlightsPage;
import pages.PassengerInformationPage;
import pages.SummaryPage;


@Listeners(common.ScreenCaptureOnFailure.class)
public class FindFlightsTest {
	private String START_URL = "http://www.volotea.com/en";
	public FindFlightsPage findFlightsPage;
	public SummaryPage summaryPage;
	public PassengerInformationPage passengerInformationPage;
	public WebDriver driver;
	
	@BeforeClass
	@Parameters({ "browserName" })
	public void openBrowser(String browserName) {
		driver = DriverManager.getWebDriverInstance(browserName);
		driver.manage().window().maximize();
		driver.get(START_URL);
		findFlightsPage = new FindFlightsPage(driver);
	}

	@Test
	public void applicationPage() throws Exception {
		String attributeValue = findFlightsPage.verifyLandingPage();
		if (attributeValue.contains("FIND FLIGHTS")) {
			assertTrue(true);
		}
	}

	@Test
	@Parameters({ "originCountry", "originState", "destinationCountry", "destinationState" })
	public void selectOriginDestinationStateAndCountry(String originCountry, String originState,
			String destinationCountry, String destinationState) throws Exception {
		findFlightsPage.selectOriginAndDestinationState(originCountry, originState, destinationCountry,
				destinationState);
	}

	@Test
	@Parameters({ "travelStartDate", "travelReturnDate" })
	public void selectTravelStartAndReturnDate(String travelStartDate, String travelReturnDate) throws Exception {
		findFlightsPage.selectStartDateAndReturnDate(travelStartDate, travelReturnDate);
	}

	@Test
	public void selectNumberOfAdultAndChildPassengers() throws Exception {
		findFlightsPage.selectNumberOfAdultAndChildPassengers();
	}

	@Test
	public void verifySelectedOriginState() throws Exception {
		assertTrue(findFlightsPage.getSelectedOriginStateText().contains("Munich · MUC"));
	}

	@Test
	public void verifySelectedDestinationState() throws Exception {
		assertTrue(findFlightsPage.getSelectedDestinationStateText().contains("Mykonos · JMK"));
	}

	@Test
	public void clickFindFlightsBtn() throws Exception {
		summaryPage = findFlightsPage.clickFindFlightsButton();
	}

	@Test(dependsOnMethods = "clickFindFlightsBtn")
	public void summaryPage() throws Exception {
		assertTrue(summaryPage.verifySummaryPageIsOpened());
	}

	@Test(dependsOnMethods = "clickFindFlightsBtn")
	public void verifyNoOfFromPassengers() throws Exception {
		assertTrue(summaryPage.getNoOfFromPassengers().contains("4 passengers"));
	}

	@Test(dependsOnMethods = "clickFindFlightsBtn")
	public void verifyDepatureDate() throws Exception {
		assertEquals(summaryPage.getDepatureDate(), "Mon 28 May 2018");
	}

	@Test(dependsOnMethods = "clickFindFlightsBtn")
	public void verifyDepatureCity() throws Exception {
		assertEquals(summaryPage.getDepatureCity(), "Munich · Mykonos");
	}

	@Test(dependsOnMethods = "clickFindFlightsBtn")
	public void verifyReturnDate() throws Exception {
		assertEquals(summaryPage.getReturnDate(), "Fri 29 Jun 2018");
	}

	@Test(dependsOnMethods = "clickFindFlightsBtn")
	public void verifyReturnCity() throws Exception {
		assertEquals(summaryPage.getReturnCity(), "Mykonos · Munich");
	}

	@Test
	public void clickNextStepButton() throws Exception {
		passengerInformationPage = summaryPage.clickNextStepButton();
	}

	@Test(dependsOnMethods = "clickNextStepButton")
	public void verifyPassengerInformationPageIsOpened() throws Exception {
		assertTrue(passengerInformationPage.verifyPassengerInformationPageIsOpened());
	}

	@Test(dependsOnMethods = "clickNextStepButton")
	@Parameters({ "adultPassengerFirstName", "adultPassengerSecondName" })
	public void enterAdultFirstNameAndLastName(String adultPassengerFirstName, String adultPassengerSecondName)
			throws Exception {
		assertTrue(passengerInformationPage.enterAdultFirstNameAndLastName(adultPassengerFirstName,
				adultPassengerSecondName));
	}

	@Test(dependsOnMethods = "clickNextStepButton")
	@Parameters({ "childPassengerFirstName", "childPassengerSecondName" })
	public void enterChildFirstNameAndLastName(String childPassengerFirstName, String childPassengerSecondName)
			throws Exception {
		assertTrue(passengerInformationPage.enterChildFirstNameAndLastName(childPassengerFirstName,
				childPassengerSecondName));
	}

	@AfterClass
	public void afterClass() {
		driver.close();
		driver.quit();
	}
}
