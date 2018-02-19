package Booking_MyAdmin;

import org.apache.log4j.Logger;
import org.apache.log4j.PropertyConfigurator;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
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
import ObjectRepository.MultiAcco;
import ObjectRepository.NewAccoBooking;
import ObjectRepository.Operations;
import ObjectRepository.Others;
import Utility.Configuration;
import lib.DriverAndObjectDetails;
import lib.ExcelDataConfig;
import lib.ExtentManager;
import lib.Takescreenshot;
import lib.DriverAndObjectDetails.DriverName;

public class Prepay_MultiRoom_Book_Within_DeadLine_Flag_Set {
	public WebDriver driverqa;
	Configuration Config = new Configuration();
	Takescreenshot obj = new Takescreenshot();
	ExtentReports rep = ExtentManager.getInstance();
	ExtentTest test;
	LoginPage login = new LoginPage();
	// HomePage home = new HomePage();
	NewAccoBooking acco = new NewAccoBooking();
	Operations opo = new Operations();
	Logger logger = Logger.getLogger("Prepay_MultiRoom_Book_Within_DeadLine_Flag_Set");
	String SearchRateexpected;
	String errorpath;

	@Test
	@Parameters({ "browsername" })
	public void BookPrepayMultiRoomwithinDeadlineFlagset(String browsername) throws Exception {
		test = rep.startTest("PrePay Book MultiRoom within Deadline with Flag set");
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
			// driverqa.get(URL);
			driverqa.get(URL + "/settings/framework/accommodation/booking-channels");
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
			String expectedtitle = "DOTWconnect.com::";
			String atualtitle = driverqa.getTitle();
			Assert.assertEquals(atualtitle, expectedtitle);
			test.log(LogStatus.INFO, "Ending Login");
			test.log(LogStatus.PASS, "PASSED Login");
			logger.info("Login Successful");
			wait.until(ExpectedConditions.visibilityOfElementLocated(Others.SupplierChannel));
			Thread.sleep(2000);
			obj.Takesnap(driverqa,
					Config.SnapShotPath() + "/Book/Accommodation_Book_Prepay_Within_DeadLine_Flag_Set/Log-In.jpg");

		} catch (Throwable e) {
			obj.Takesnap(driverqa, Config.SnapShotPath()
					+ "/Book/Error/Accommodation_Book_Prepay_Within_DeadLine_Flag_Set/Log-In.jpg");
			test.log(LogStatus.FAIL, "Login");
			errorpath = Config.SnapShotPath()
					+ "/Book/Error/Accommodation_Book_Prepay_Within_DeadLine_Flag_Set/Log-In.jpg";
			logger.info(e.getMessage());
			test.log(LogStatus.FAIL, e.getMessage());
			rep.endTest(test);
			rep.flush();
			Assert.assertTrue(false, e.getMessage());
		}

		try {
			driverqa.findElement(Others.SupplierChannel).click();
			wait.until(ExpectedConditions.visibilityOfElementLocated(Others.SetFlag));
			String elementval = driverqa.findElement(Others.SetFlag).getAttribute("class");
			System.out.println(elementval);
			if (elementval.equals("has-switch switch-animate switch-off")) {
				driverqa.findElement(Others.SetFlag).click();
				Thread.sleep(2000);
				obj.Takesnap(driverqa, Config.SnapShotPath()
						+ "/Book/Accommodation_Book_Prepay_Within_DeadLine_Flag_Set/Before-Update-Flag.jpg");
				driverqa.findElement(Others.Updatechannel).click();
				Thread.sleep(2000);
				obj.Takesnap(driverqa, Config.SnapShotPath()
						+ "/Book/Accommodation_Book_Prepay_Within_DeadLine_Flag_Set/Flag-Set.jpg");
				driverqa.findElement(Others.Homebutton).click();
			} else {
				Thread.sleep(2000);
				obj.Takesnap(driverqa, Config.SnapShotPath()
						+ "/Book/Accommodation_Book_Prepay_Within_DeadLine_Flag_Set/Flag-Set.jpg");
				driverqa.findElement(Others.Homebutton).click();
			}
		} catch (Throwable e) {
			obj.Takesnap(driverqa, Config.SnapShotPath()
					+ "/Book/Error/Accommodation_Book_Prepay_Within_DeadLine_Flag_Set/Set-Flag.jpg");
			test.log(LogStatus.FAIL, "Login");
			errorpath = Config.SnapShotPath()
					+ "/Book/Error/Accommodation_Book_Prepay_Within_DeadLine_Flag_Set/Set-Flag.jpg";
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
			obj.Takesnap(driverqa, Config.SnapShotPath()
					+ "/Book/Accommodation_Book_Prepay_Within_DeadLine_Flag_Set/Customer-Search.jpg");

		} catch (Throwable e) {
			obj.Takesnap(driverqa, Config.SnapShotPath()
					+ "/Book/Error/Accommodation_Book_Prepay_Within_DeadLine_Flag_Set/Customer-Search.jpg");
			test.log(LogStatus.FAIL, "Navigation to customer search page");
			errorpath = Config.SnapShotPath()
					+ "/Book/Error/Accommodation_Book_Prepay_Within_DeadLine_Flag_Set/Customer-Search.jpg";
			logger.info(e.getMessage());
			test.log(LogStatus.FAIL, e.getMessage());
			rep.endTest(test);
			rep.flush();

			Assert.assertTrue(false, e.getMessage());

		}
		logger.info("Selecting Customer");
		test.log(LogStatus.INFO, "Selecting Customer");
		try {
			wait.until(ExpectedConditions.visibilityOfElementLocated(Operations.company));

			driverqa.findElement(Operations.company).sendKeys(excel.getData(0, 4, 1));
			Thread.sleep(2000);
			action.sendKeys(Keys.ARROW_DOWN).build().perform();
			action.sendKeys(Keys.ENTER).build().perform();
			Thread.sleep(2000);
			action.sendKeys(Keys.ENTER).build().perform();
			Thread.sleep(2000);

			obj.Takesnap(driverqa, Config.SnapShotPath()
					+ "/Book/Accommodation_Book_Prepay_Within_DeadLine_Flag_Set/Customer-list.jpg");
			wait.until(ExpectedConditions.visibilityOfElementLocated(Operations.chooseCustbook));
			driverqa.findElement(Operations.chooseCustbook).click();
			Thread.sleep(1000);
			String searchpageactualtitle = driverqa.getTitle();
			String searchpageexpectedtitle = "DOTWconnect.com::";
			Assert.assertEquals(searchpageactualtitle, searchpageexpectedtitle);
			logger.info("Customer Selected");
			test.log(LogStatus.PASS, "Customer Selected");

		} catch (Throwable e) {
			obj.Takesnap(driverqa, Config.SnapShotPath()
					+ "/Book/Error/Accommodation_Book_Prepay_Within_DeadLine_Flag_Set/Customer-list.jpg");
			test.log(LogStatus.FAIL, "Customer Selection");
			errorpath = Config.SnapShotPath()
					+ "/Book/Error/Accommodation_Book_Prepay_Within_DeadLine_Flag_Set/Customer-list.jpg";
			logger.info(e.getMessage());
			test.log(LogStatus.FAIL, e.getMessage());
			rep.endTest(test);
			rep.flush();
			Assert.assertTrue(false, e.getMessage());
		}
		logger.info("Applying search Filters");
		logger.info("Starting HotelSearch Prepay MultiRoom Within DeadLine");
		try {
			test.log(LogStatus.INFO, "Starting HotelSearch Prepay");
			wait.until(ExpectedConditions.visibilityOfElementLocated(NewAccoBooking.AccomUnit));
			driverqa.findElement(NewAccoBooking.AccomUnit).sendKeys(excel.getData(0, 12, 1));
			Thread.sleep(2000);
			action.sendKeys(Keys.ARROW_DOWN).build().perform();
			action.sendKeys(Keys.ENTER).build().perform();
			driverqa.findElement(NewAccoBooking.inDate).clear();
			driverqa.findElement(NewAccoBooking.inDate).sendKeys(excel.getData(0, 18, 1));
			driverqa.findElement(NewAccoBooking.outDate).clear();
			driverqa.findElement(NewAccoBooking.outDate).sendKeys(excel.getData(0, 18, 2));
			String expected = excel.getData(0, 12, 1);
			Thread.sleep(3000);
			driverqa.findElement(MultiAcco.Plusroom).click();
			Thread.sleep(2000);
			obj.Takesnap(driverqa, Config.SnapShotPath()
					+ "/Book/Accommodation_Book_Prepay_Within_DeadLine_Flag_Set/Search-Hotel-filters.jpg");
			wait.until(ExpectedConditions.visibilityOfElementLocated(NewAccoBooking.bookChannel));
			driverqa.findElement(NewAccoBooking.bookChannel).click();
			wait.until(ExpectedConditions.visibilityOfElementLocated(NewAccoBooking.thirdPartyChannel));
			driverqa.findElement(NewAccoBooking.thirdPartyChannel).click();
			wait.until(ExpectedConditions.visibilityOfElementLocated(NewAccoBooking.thirdParty));
			driverqa.findElement(NewAccoBooking.thirdParty).click();
			Thread.sleep(2000);
			wait.until(ExpectedConditions.visibilityOfElementLocated(NewAccoBooking.searchButton));
			obj.Takesnap(driverqa, Config.SnapShotPath()
					+ "/Book/Accommodation_Book_Prepay_Within_DeadLine_Flag_Set/Booking-Channel-filters.jpg");
			driverqa.findElement(NewAccoBooking.searchButton).click();
			wait.until(ExpectedConditions.visibilityOfElementLocated(NewAccoBooking.thirdPartyresultHotel));
			String result = driverqa.findElement(NewAccoBooking.thirdPartyresultHotel).getText();
			Thread.sleep(2000);
			wait.until(ExpectedConditions.elementToBeClickable(NewAccoBooking.thirdPartyroomType));
			wait.until(ExpectedConditions.visibilityOfElementLocated(NewAccoBooking.thirdPartyroomType));
			driverqa.findElement(NewAccoBooking.thirdPartyroomType).click();
			Thread.sleep(2000);
			// wait.until(ExpectedConditions.visibilityOfElementLocated(NewAccoBooking.thirdPartyaSaveITNSearchrateoutdead));
			// SearchRateexpected=
			// driverqa.findElement(NewAccoBooking.thirdPartyaSaveITNSearchrateoutdead).getText();
			// System.out.println(SearchRateexpected);
			obj.Takesnap(driverqa, Config.SnapShotPath()
					+ "/Book/Accommodation_Book_Prepay_Within_DeadLine_Flag_Set/Search-Result.jpg");
			wait.until(ExpectedConditions.visibilityOfElementLocated(NewAccoBooking.thirdPartyDeadline));
			String actualdeadline = driverqa.findElement(NewAccoBooking.thirdPartyDeadline).getText();
			String expecteddeadline = excel.getData(0, 30, 1);
			Assert.assertTrue(result.contains(expected));
			Assert.assertTrue(actualdeadline.contains(expecteddeadline));
			test.log(LogStatus.INFO, "Ending HotelSearch Prepay MultiRoom Within DeadLine");
			test.log(LogStatus.PASS, "PASSED HotelSearch Prepay MultiRoom Within DeadLine");
			logger.info("Hotel Search Complete Prepay MultiRoom Within DeadLine");
		} catch (Throwable e) {
			test.log(LogStatus.FAIL, "Hotel Search Prepay MultiRoom Within DeadLine");
			obj.Takesnap(driverqa, Config.SnapShotPath()
					+ "/Book/Error/Accommodation_Book_Prepay_Within_DeadLine_Flag_Set/Search-Result.jpg");
			errorpath = Config.SnapShotPath()
					+ "/Book/Error/Accommodation_Book_Prepay_Within_DeadLine_Flag_Set/Search-Result.jpg";
			logger.info(e.getMessage());
			test.log(LogStatus.FAIL, e.getMessage());
			rep.endTest(test);
			rep.flush();
			Assert.assertTrue(false, e.getMessage());
		}
		try {
			test.log(LogStatus.INFO, "Starting Hotel Book PrePay MultiRoom Within DeadLine");
			logger.info("Starting Hotel Book");
			wait.until(
					ExpectedConditions.visibilityOfElementLocated(NewAccoBooking.thirdPartyroomSelectionbookoutdead));
			driverqa.findElement(NewAccoBooking.thirdPartyroomSelectionbookoutdead).click();
			driverqa.findElement(NewAccoBooking.thirdPartyroom2Selectionbookoutdead).click();
			Thread.sleep(2000);
			obj.Takesnap(driverqa, Config.SnapShotPath()
					+ "/Book/Accommodation_Book_Prepay_Within_DeadLine_Flag_Set/Room-Selection.jpg");
			wait.until(ExpectedConditions.visibilityOfElementLocated(NewAccoBooking.thirdPartyprocedetoBookingoutdead));
			driverqa.findElement(NewAccoBooking.thirdPartyprocedetoBookingoutdead).click();
			wait.until(ExpectedConditions.visibilityOfElementLocated(Others.Unabletobook));
			Thread.sleep(2000);
			obj.Takesnap(driverqa, Config.SnapShotPath()
					+ "/Book/Accommodation_Book_Prepay_Within_DeadLine_Flag_Set/Room-Selection.jpg");
			String expectedunabletobook = driverqa.findElement(Others.Unabletobook).getText();
			String actualunabletobook = "Unable to block all rooms for this booking";
			Assert.assertTrue(actualunabletobook.contains(expectedunabletobook));
			test.log(LogStatus.INFO, "Ending HotelBook Prepay MultiRoom Within DeadLine");
			test.log(LogStatus.PASS, "PASSED HotelBook Prepay MultiRoom Within DeadLine");
			logger.info("Hotel Book Complete Prepay MultiRoom Within DeadLine");
		}

		catch (Throwable e) {
			test.log(LogStatus.FAIL, "Hotel Search Prepay");
			obj.Takesnap(driverqa,
					Config.SnapShotPath() + "/Book/Error/Accommodation_Book_Prepay_Within_DeadLine_Flag_Set/Book.jpg");
			errorpath = Config.SnapShotPath()
					+ "/Book/Error/Accommodation_Book_Prepay_Within_DeadLine_Flag_Set/Book.jpg";
			logger.info(e.getMessage());
			test.log(LogStatus.FAIL, e.getMessage());
			rep.endTest(test);
			rep.flush();
			System.out.println(e.getMessage());
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
