package SaveItenary_MyAdmin;

import org.apache.log4j.Logger;
import org.apache.log4j.PropertyConfigurator;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.ITestResult;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.AfterTest;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

import com.relevantcodes.extentreports.ExtentReports;
import com.relevantcodes.extentreports.ExtentTest;
import com.relevantcodes.extentreports.LogStatus;

import ObjectRepository.HomePage;
import ObjectRepository.LoginPage;
import ObjectRepository.NewAccoBooking;
import ObjectRepository.Operations;
import Utility.Configuration;
import lib.DriverAndObjectDetails;
import lib.ExcelDataConfig;
import lib.ExtentManager;
import lib.Takescreenshot;
import lib.DriverAndObjectDetails.DriverName;

public class Save_Credit_Card_NFR {

	public WebDriver driverqa;
	Configuration Config = new Configuration();
	Takescreenshot obj = new Takescreenshot();
	ExtentReports rep = ExtentManager.getInstance();
	ExtentTest test;
	LoginPage login = new LoginPage();
	HomePage home = new HomePage();
	NewAccoBooking acco = new NewAccoBooking();
	Operations opo = new Operations();
	Logger logger = Logger.getLogger("Save_Credit_Card_NFR");
	String errorpath;

	@Test
	@Parameters({ "browsername" })
	public void SaveCreditCardNFR(String browsername) throws Exception {
		test = rep.startTest("Credit Card Save NFR");
		ExcelDataConfig excel;
		excel = new ExcelDataConfig(Config.getExcelPathBook());
		PropertyConfigurator.configure("Log4j.properties");
		logger.info("Test Case Started");
		if (browsername.equalsIgnoreCase("CH")) {
			driverqa = new DriverAndObjectDetails(DriverName.CH).CreateDriver();
		} else if (browsername.equalsIgnoreCase("IE")) {
			driverqa = new DriverAndObjectDetails(DriverName.IE).CreateDriver();
		} else {
			driverqa = new DriverAndObjectDetails(DriverName.FF).CreateDriver();
		}
		WebDriverWait wait = new WebDriverWait(driverqa, 30);
		Actions action = new Actions(driverqa);
		try {
			logger.info("Browser Opened");
			String URL = excel.getData(0, 1, 5) + "/_myadmin";
			driverqa.get(URL);
			logger.info("Test Case Started");
			test.log(LogStatus.INFO, "Starting Login");
			WebElement username = driverqa.findElement(LoginPage.uname);
			username.clear();
			username.sendKeys(excel.getData(0, 1, 1));
			WebElement password = driverqa.findElement(LoginPage.pwd);
			password.clear();
			password.sendKeys(excel.getData(0, 1, 2));
			driverqa.findElement(LoginPage.submit).click();
			Thread.sleep(1000);
			String expectedtitle = "DOTWconnect.com::DOTWconnect.com: My Admin";
			String atualtitle = driverqa.getTitle();
			Assert.assertEquals(atualtitle, expectedtitle);
			test.log(LogStatus.INFO, "Ending Login");
			test.log(LogStatus.PASS, "PASSED Login");
			logger.info("Login Successful");
			wait.until(ExpectedConditions.visibilityOfElementLocated(HomePage.operation));
			Thread.sleep(2000);
			obj.Takesnap(driverqa, Config.SnapShotPath() + "/Save/Accommodation_Save_Credit_Card_NFR/Log-In.jpg");

		} catch (Throwable e) {
			obj.Takesnap(driverqa, Config.SnapShotPath() + "/Save/Error/Accommodation_Save_Credit_Card_NFR/Log-In.jpg");
			test.log(LogStatus.FAIL, "Login");
			errorpath = Config.SnapShotPath() + "/Save/Error/Accommodation_Save_Credit_Card_NFR/Log-In.jpg";

			logger.info(e.getMessage());
			test.log(LogStatus.FAIL, e.getMessage());
			rep.endTest(test);
			rep.flush();
			Assert.assertTrue(false, e.getMessage());
		}
		logger.info("Searching Customer");

		try {
			test.log(LogStatus.INFO, "Navigating to customer search page");
			logger.info("Navigating to customer search page");
			driverqa.findElement(HomePage.operation).click();
			wait.until(ExpectedConditions.visibilityOfElementLocated(Operations.newBooking));
			driverqa.findElement(Operations.newBooking).click();
			wait.until(ExpectedConditions.visibilityOfElementLocated(Operations.AccomBook));
			driverqa.findElement(Operations.AccomBook).click();
			Thread.sleep(2000);
			String searchcustatualtitle = driverqa.getTitle();
			String searchcustexpectedtitle = "DOTWconnect.com::New Accommodation Booking";
			Assert.assertEquals(searchcustatualtitle, searchcustexpectedtitle);
			logger.info("Navigated to customer search page");
			test.log(LogStatus.PASS, "Navigated to customer search page");
			Thread.sleep(2000);
			obj.Takesnap(driverqa,
					Config.SnapShotPath() + "/Save/Accommodation_Save_Credit_Card_NFR/Customer-Search.jpg");

		} catch (Throwable e) {
			logger.info(e.getMessage());
			test.log(LogStatus.FAIL, e.getMessage());
			rep.endTest(test);
			rep.flush();
			obj.Takesnap(driverqa,
					Config.SnapShotPath() + "/Save/Error/Accommodation_Save_Credit_Card_NFR/Customer-Search.jpg");
			Assert.assertTrue(false, e.getMessage());
			test.log(LogStatus.FAIL, "Navigation to customer search page");
		}
		logger.info("Selecting Customer");
		test.log(LogStatus.INFO, "Selecting Customer");
		try {
			wait.until(ExpectedConditions.visibilityOfElementLocated(Operations.company));
			driverqa.findElement(Operations.company).sendKeys(excel.getData(0, 6, 1));
			Thread.sleep(2000);
			action.sendKeys(Keys.ARROW_DOWN).build().perform();
			action.sendKeys(Keys.ENTER).build().perform();
			Thread.sleep(2000);
			action.sendKeys(Keys.ENTER).build().perform();
			Thread.sleep(2000);

			obj.Takesnap(driverqa,
					Config.SnapShotPath() + "/Save/Accommodation_Save_Credit_Card_NFR/Customer-list.jpg");
			wait.until(ExpectedConditions.visibilityOfElementLocated(Operations.chooseCustbook));
			driverqa.findElement(Operations.chooseCustbook).click();
			Thread.sleep(1000);
			String searchpageactualtitle = driverqa.getTitle();
			String searchpageexpectedtitle = "DOTWconnect.com::";
			Assert.assertEquals(searchpageactualtitle, searchpageexpectedtitle);
			logger.info("Customer Selected");
			test.log(LogStatus.PASS, "Customer Selected");

		} catch (Throwable e) {
			obj.Takesnap(driverqa,
					Config.SnapShotPath() + "/Save/Error/Accommodation_Save_Credit_Card_NFR/Customer-list.jpg");
			test.log(LogStatus.FAIL, "Customer Selection");
			errorpath = Config.SnapShotPath() + "/Save/Error/Accommodation_Save_Credit_Card_NFR/Customer-list.jpg";

			logger.info(e.getMessage());
			test.log(LogStatus.FAIL, e.getMessage());
			rep.endTest(test);
			rep.flush();
			Assert.assertTrue(false, e.getMessage());
		}
		logger.info("Applying search Filters");
		logger.info("Starting HotelSearch Credit Card NFR");
		try {
			test.log(LogStatus.INFO, "Starting HotelSearch Credit Card NFR");
			wait.until(ExpectedConditions.visibilityOfElementLocated(NewAccoBooking.AccomUnit));
			driverqa.findElement(NewAccoBooking.AccomUnit).sendKeys(excel.getData(0, 9, 1));
			Thread.sleep(2000);
			action.sendKeys(Keys.ARROW_DOWN).build().perform();
			action.sendKeys(Keys.ENTER).build().perform();
			driverqa.findElement(NewAccoBooking.inDate).clear();
			driverqa.findElement(NewAccoBooking.inDate).sendKeys(excel.getData(0, 17, 1));
			driverqa.findElement(NewAccoBooking.outDate).clear();
			driverqa.findElement(NewAccoBooking.outDate).sendKeys(excel.getData(0, 17, 2));
			String expected = excel.getData(0, 9, 1);
			Thread.sleep(2000);
			obj.Takesnap(driverqa,
					Config.SnapShotPath() + "/Save/Accommodation_Save_Credit_Card_NFR/Search-Hotel-filters.jpg");
			wait.until(ExpectedConditions.visibilityOfElementLocated(NewAccoBooking.bookChannel));
			driverqa.findElement(NewAccoBooking.bookChannel).click();
			wait.until(ExpectedConditions.visibilityOfElementLocated(NewAccoBooking.thirdPartyChannel));
			driverqa.findElement(NewAccoBooking.thirdPartyChannel).click();
			wait.until(ExpectedConditions.visibilityOfElementLocated(NewAccoBooking.thirdParty));
			driverqa.findElement(NewAccoBooking.thirdParty).click();
			Thread.sleep(2000);
			wait.until(ExpectedConditions.visibilityOfElementLocated(NewAccoBooking.searchButton));
			obj.Takesnap(driverqa,
					Config.SnapShotPath() + "/Save/Accommodation_Save_Credit_Card_NFR/Booking-Channel-filters.jpg");
			driverqa.findElement(NewAccoBooking.searchButton).click();
			wait.until(ExpectedConditions.visibilityOfElementLocated(NewAccoBooking.thirdPartyresultHotel));
			String result = driverqa.findElement(NewAccoBooking.thirdPartyresultHotel).getText();
			Thread.sleep(2000);
			wait.until(ExpectedConditions.elementToBeClickable(NewAccoBooking.thirdPartyroomType));
			wait.until(ExpectedConditions.visibilityOfElementLocated(NewAccoBooking.thirdPartyroomType));
			driverqa.findElement(NewAccoBooking.thirdPartyroomType).click();
			Thread.sleep(2000);
			obj.Takesnap(driverqa,
					Config.SnapShotPath() + "/Save/Accommodation_Save_Credit_Card_NFR/Search-Result.jpg");
			wait.until(ExpectedConditions.visibilityOfElementLocated(NewAccoBooking.thirdPartyDeadline));
			String actualdeadline = driverqa.findElement(NewAccoBooking.thirdPartyDeadline).getText();
			String actualNFR = driverqa.findElement(NewAccoBooking.thirdPartyNFR).getText();
			String expecteddeadline = excel.getData(0, 30, 1);
			String expectedNFR = excel.getData(0, 32, 1);
			Assert.assertTrue(result.contains(expected));
			Assert.assertTrue(actualdeadline.contains(expecteddeadline));
			Assert.assertTrue(actualNFR.contains(expectedNFR));
			test.log(LogStatus.INFO, "Ending HotelSearch Credit Card NFR");
			test.log(LogStatus.PASS, "PASSED HotelSearch Credit Card NFR");
			logger.info("Hotel Search Complete Credit Card NFR");
		} catch (Throwable e) {
			test.log(LogStatus.FAIL, "Hotel Search Credit Card NFR");
			obj.Takesnap(driverqa,
					Config.SnapShotPath() + "/Save/Error/Accommodation_Save_Credit_Card_NFR/Search-Result.jpg");
			errorpath = Config.SnapShotPath() + "/Save/Error/Accommodation_Save_Credit_Card_NFR/Search-Result.jpg";

			logger.info(e.getMessage());
			test.log(LogStatus.FAIL, e.getMessage());
			rep.endTest(test);
			rep.flush();
			Assert.assertTrue(false, e.getMessage());
		}

		try {
			test.log(LogStatus.INFO, "Starting Hotel Save Itenary");
			logger.info("Starting Hotel Save");
			wait.until(
					ExpectedConditions.visibilityOfElementLocated(NewAccoBooking.thirdPartyroomSelectionsaveoutdead));
			driverqa.findElement(NewAccoBooking.thirdPartyroomSelectionsaveoutdead).click();
			Thread.sleep(2000);
			obj.Takesnap(driverqa,
					Config.SnapShotPath() + "/Save/Accommodation_Save_Credit_Card_NFR/Room-Selection.jpg");
			wait.until(
					ExpectedConditions.visibilityOfElementLocated(NewAccoBooking.thirdPartyprocedetoBooksaveoutdead));
			driverqa.findElement(NewAccoBooking.thirdPartyprocedetoBooksaveoutdead).click();
			logger.info("Entering Passenger details");
			test.log(LogStatus.INFO, "Entering Passenger details");
			wait.until(ExpectedConditions.visibilityOfElementLocated(NewAccoBooking.paxFname));
			driverqa.findElement(NewAccoBooking.paxFname).sendKeys(excel.getData(0, 21, 1));
			Thread.sleep(2000);
			driverqa.findElement(NewAccoBooking.paxLname).sendKeys(excel.getData(0, 21, 2));
			Select passengertitle = new Select(driverqa.findElement(NewAccoBooking.paxtitle));
			passengertitle.selectByIndex(1);
			if (driverqa.findElements(NewAccoBooking.paxFname2).size() != 0) {
				driverqa.findElement(NewAccoBooking.paxFname2).sendKeys(excel.getData(0, 22, 1));
				Thread.sleep(1000);
				driverqa.findElement(NewAccoBooking.paxLname2).sendKeys(excel.getData(0, 22, 2));
				Select passengertitle2 = new Select(driverqa.findElement(NewAccoBooking.paxtitle2));
				passengertitle2.selectByIndex(1);
			}
			driverqa.findElement(NewAccoBooking.acceptChkBX).click();
			Thread.sleep(2000);
			obj.Takesnap(driverqa,
					Config.SnapShotPath() + "/Save/Accommodation_Save_Credit_Card_NFR/Passenger-Details.jpg");
			logger.info("Entered Passenger details");
			test.log(LogStatus.INFO, "Entered Passenger details");
			test.log(LogStatus.PASS, "Passenger details");
			driverqa.findElement(NewAccoBooking.thirdPartsavebooking).click();
			wait.until(ExpectedConditions.visibilityOfElementLocated(NewAccoBooking.thirdPartsaveItncart));
			driverqa.findElement(NewAccoBooking.thirdPartsaveItncart).click();
			Thread.sleep(2000);
			String actualhoteltitle = driverqa.findElement(NewAccoBooking.thirdPartyaftersavehotel).getText();
			String expectedhoteltitle = excel.getData(0, 9, 1);
			String actualStartDate = driverqa.findElement(NewAccoBooking.thirdPartyaftersaveCheckin).getText();
			String expectedStartDate = excel.getData(0, 36, 2);
			String actualEndDate = driverqa.findElement(NewAccoBooking.thirdPartyaftersaveCheckout).getText();
			String expectedEndDate = excel.getData(0, 37, 2);
			Assert.assertTrue(actualhoteltitle.contains(expectedhoteltitle));
			Assert.assertTrue(actualStartDate.contains(expectedStartDate));
			Assert.assertTrue(actualEndDate.contains(expectedEndDate));
			// Assert.assertTrue(actualRate.contains(SearchRateexpected));
			Thread.sleep(2000);
			obj.Takesnap(driverqa,
					Config.SnapShotPath() + "/Save/Accommodation_Save_Credit_Card_NFR/Itenary-Details.jpg");
			test.log(LogStatus.INFO, "Ending HotelSave Credit Card NFR");
			test.log(LogStatus.PASS, "PASSED HotelSave Credit Card NFR");
			logger.info("Hotel Save Complete Credit Card NFR");
		} catch (Throwable e) {
			test.log(LogStatus.FAIL, "Hotel Save Itenary Credit Card NFR");
			obj.Takesnap(driverqa,
					Config.SnapShotPath() + "/Save/Error/Accommodation_Save_Credit_Card_NFR/Save-Itenary.jpg");
			errorpath = Config.SnapShotPath() + "/Save/Error/Accommodation_Save_Credit_Card_NFR/Save-Itenary.jpg";

			logger.info(e.getMessage());
			test.log(LogStatus.FAIL, e.getMessage());
			rep.endTest(test);
			rep.flush();
			Assert.assertTrue(false, e.getMessage());
		}
		try {
			test.log(LogStatus.INFO, "Starting Delete from Itenary");
			logger.info("Starting Delete from Itenary");
			wait.until(ExpectedConditions.visibilityOfElementLocated(NewAccoBooking.thirdPartyremovefromcart));
			driverqa.findElement(NewAccoBooking.thirdPartyremovefromcart).click();
			Thread.sleep(2000);
			obj.Takesnap(driverqa, Config.SnapShotPath()
					+ "/Save/Accommodation_Save_Credit_Card_NFR/Confirm-Delete-Itenary-Details.jpg");
			wait.until(ExpectedConditions.visibilityOfElementLocated(NewAccoBooking.thirdPartyconfirmremovefromcart));
			driverqa.findElement(NewAccoBooking.thirdPartyconfirmremovefromcart).click();
			wait.until(
					ExpectedConditions.visibilityOfElementLocated(NewAccoBooking.thirdPartyremovefromcartconfirmtext));
			Thread.sleep(2000);
			obj.Takesnap(driverqa,
					Config.SnapShotPath() + "/Save/Accommodation_Save_Credit_Card_NFR/Deleted-Itenary-Details.jpg");
			String Deletesuccesstext = driverqa.findElement(NewAccoBooking.thirdPartyremovefromcartconfirmtext)
					.getText();
			String expectedDeletesuccesstext = excel.getData(0, 31, 1);
			System.out.println(Deletesuccesstext);
			System.out.println(expectedDeletesuccesstext);
			Assert.assertTrue(Deletesuccesstext.contains(expectedDeletesuccesstext));
			test.log(LogStatus.INFO, "Ending Deleting from Itenary");
			test.log(LogStatus.PASS, "Deleting from Itenary");
			logger.info("Deleted from Itenary");
		} catch (Throwable e) {
			test.log(LogStatus.FAIL, "Hotel Delete from Itenary Credit Card NFR");
			obj.Takesnap(driverqa,
					Config.SnapShotPath() + "/Save/Error/Accommodation_Save_Credit_Card_NFR/Delete-Itenary.jpg");
			errorpath = Config.SnapShotPath() + "/Save/Error/Accommodation_Save_Credit_Card_NFR/Delete-Itenary.jpg";
			logger.info(e.getMessage());
			test.log(LogStatus.FAIL, e.getMessage());
			rep.endTest(test);
			rep.flush();
			Assert.assertTrue(false, e.getMessage());

		}

	}

	@AfterMethod
	public void getResult(ITestResult result) {
		if (result.getStatus() == ITestResult.FAILURE) {

			test.log(LogStatus.FAIL, test.addScreenCapture(errorpath));
			test.log(LogStatus.FAIL, result.getThrowable());
		}
		rep.endTest(test);
	}

	@AfterTest
	public void afterTest() {

		rep.endTest(test);
		rep.flush();
		driverqa.close();
	}
}
