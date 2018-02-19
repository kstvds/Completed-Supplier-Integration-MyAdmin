package Amend_MyAdmin;

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

import ObjectRepository.Amend;
import ObjectRepository.HomePage;
import ObjectRepository.LoginPage;
import ObjectRepository.MultiAcco;
import ObjectRepository.NewAccoBooking;
import ObjectRepository.Operations;
import ObjectRepository.PaymentPage;
import Utility.Configuration;
import lib.DriverAndObjectDetails;
import lib.ExcelDataConfig;
import lib.ExtentManager;
import lib.Takescreenshot;
import lib.DriverAndObjectDetails.DriverName;

public class Amend_Credit_Card_MultiSupplier {
	public WebDriver driverqa;
	Configuration Config = new Configuration();
	Takescreenshot obj = new Takescreenshot();
	ExtentReports rep = ExtentManager.getInstance();
	ExtentTest test;
	LoginPage login = new LoginPage();
	HomePage home = new HomePage();
	NewAccoBooking acco = new NewAccoBooking();
	Operations opo = new Operations();
	Logger logger = Logger.getLogger("Amend_Credit_Card_MultiSupplier");
	String SearchRateexpected;
	String errorpath;

	@Test
	@Parameters({ "browsername" })
	public void AmendCreditCardMultiSupplier(String browsername) throws Exception {
		test = rep.startTest("Amend Credit Card MultiSupplier");
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
		WebDriverWait wait = new WebDriverWait(driverqa, 40);
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
			obj.Takesnap(driverqa,
					Config.SnapShotPath() + "/Amend/Accommodation_Amend_Credit_Card_MultiSupplier/Log-In.jpg");

		} catch (Exception e) {
			obj.Takesnap(driverqa,
					Config.SnapShotPath() + "/Amend/Error/Accommodation_Amend_Credit_Card_MultiSupplier/Log-In.jpg");
			test.log(LogStatus.FAIL, "Login");
			errorpath = Config.SnapShotPath() + "/Amend/Error/Accommodation_Amend_Credit_Card_MultiSupplier/Log-In.jpg";
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
			Thread.sleep(3000);
			obj.Takesnap(driverqa,
					Config.SnapShotPath() + "/Amend/Accommodation_Amend_Credit_Card_MultiSupplier/Customer-Search.jpg");

		} catch (Exception e) {
			obj.Takesnap(driverqa, Config.SnapShotPath()
					+ "/Amend/Error/Accommodation_Amend_Credit_Card_MultiSupplier/Customer-Search.jpg");
			test.log(LogStatus.FAIL, "Navigation to customer search page");
			errorpath = Config.SnapShotPath()
					+ "/Amend/Error/Accommodation_Amend_Credit_Card_MultiSupplier/Customer-Search.jpg";
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
			driverqa.findElement(Operations.company).sendKeys(excel.getData(0, 6, 1));
			Thread.sleep(2000);
			action.sendKeys(Keys.ARROW_DOWN).build().perform();
			action.sendKeys(Keys.ENTER).build().perform();
			Thread.sleep(2000);
			action.sendKeys(Keys.ENTER).build().perform();
			Thread.sleep(2000);

			obj.Takesnap(driverqa,
					Config.SnapShotPath() + "/Amend/Accommodation_Amend_Credit_Card_MultiSupplier/Customer-list.jpg");
			wait.until(ExpectedConditions.visibilityOfElementLocated(Operations.chooseCustbook));
			driverqa.findElement(Operations.chooseCustbook).click();
			Thread.sleep(1000);
			String searchpageactualtitle = driverqa.getTitle();
			String searchpageexpectedtitle = "DOTWconnect.com::";
			Assert.assertEquals(searchpageactualtitle, searchpageexpectedtitle);
			logger.info("Customer Selected");
			test.log(LogStatus.PASS, "Customer Selected");

		} catch (Exception e) {
			obj.Takesnap(driverqa, Config.SnapShotPath()
					+ "/Amend/Error/Accommodation_Amend_Credit_Card_MultiSupplier/Customer-list.jpg");
			test.log(LogStatus.FAIL, "Customer Selection");
			errorpath = Config.SnapShotPath()
					+ "/Amend/Error/Accommodation_Amend_Credit_Card_MultiSupplier/Customer-list.jpg";
			logger.info(e.getMessage());
			test.log(LogStatus.FAIL, e.getMessage());
			rep.endTest(test);
			rep.flush();
			Assert.assertTrue(false, e.getMessage());

		}
		logger.info("Applying search Filters for Supplier Room");
		logger.info("Starting HotelSearch Credit Card for Supplier Room");
		try {
			test.log(LogStatus.INFO, "Starting HotelSearch Credit Card for Supplier Room");
			wait.until(ExpectedConditions.visibilityOfElementLocated(NewAccoBooking.AccomUnit));
			driverqa.findElement(NewAccoBooking.AccomUnit).sendKeys(excel.getData(0, 9, 1));
			Thread.sleep(2000);
			action.sendKeys(Keys.ARROW_DOWN).build().perform();
			action.sendKeys(Keys.ENTER).build().perform();
			driverqa.findElement(NewAccoBooking.inDate).clear();
			driverqa.findElement(NewAccoBooking.inDate).sendKeys(excel.getData(0, 15, 1));
			driverqa.findElement(NewAccoBooking.outDate).clear();
			driverqa.findElement(NewAccoBooking.outDate).sendKeys(excel.getData(0, 15, 2));
			Select noofadults = new Select(driverqa.findElement(NewAccoBooking.NoofAdults));
			noofadults.selectByIndex(2);
			String expected = excel.getData(0, 9, 1);
			Thread.sleep(2000);
			obj.Takesnap(driverqa, Config.SnapShotPath()
					+ "/Amend/Accommodation_Amend_Credit_Card_MultiSupplier/Search-Hotel-Supplier-filters.jpg");
			wait.until(ExpectedConditions.visibilityOfElementLocated(NewAccoBooking.bookChannel));
			driverqa.findElement(NewAccoBooking.bookChannel).click();
			wait.until(ExpectedConditions.visibilityOfElementLocated(NewAccoBooking.thirdPartyChannel));
			driverqa.findElement(NewAccoBooking.thirdPartyChannel).click();
			wait.until(ExpectedConditions.visibilityOfElementLocated(NewAccoBooking.thirdParty));
			driverqa.findElement(NewAccoBooking.thirdParty).click();
			Thread.sleep(2000);
			wait.until(ExpectedConditions.visibilityOfElementLocated(NewAccoBooking.searchButton));
			obj.Takesnap(driverqa, Config.SnapShotPath()
					+ "/Amend/Accommodation_Amend_Credit_Card_MultiSupplier/Booking-Channel-Supplier-filters.jpg");
			driverqa.findElement(NewAccoBooking.searchButton).click();
			wait.until(ExpectedConditions.visibilityOfElementLocated(NewAccoBooking.thirdPartyresultHotel));
			String result = driverqa.findElement(NewAccoBooking.thirdPartyresultHotel).getText();
			Thread.sleep(2000);
			wait.until(ExpectedConditions.elementToBeClickable(NewAccoBooking.thirdPartyroomType));
			wait.until(ExpectedConditions.visibilityOfElementLocated(NewAccoBooking.thirdPartyroomType));
			driverqa.findElement(NewAccoBooking.thirdPartyroomType).click();
			Thread.sleep(2000);
			obj.Takesnap(driverqa, Config.SnapShotPath()
					+ "/Amend/Accommodation_Amend_Credit_Card_MultiSupplier/Search-Result-Supplier.jpg");
			Assert.assertTrue(result.contains(expected));
			test.log(LogStatus.INFO, "Ending HotelSearch Credit Card for Supplier Room");
			test.log(LogStatus.PASS, "PASSED HotelSearch Credit Card for Supplier Room");
			logger.info("Hotel Search Complete Credit Card for Supplier Room");
		} catch (Exception e) {
			test.log(LogStatus.FAIL, "Hotel Search Credit Card for Supplier Room");
			obj.Takesnap(driverqa, Config.SnapShotPath()
					+ "/Amend/Error/Accommodation_Amend_Credit_Card_MultiSupplier/Search-Result-Supplier.jpg");
			errorpath = Config.SnapShotPath()
					+ "/Amend/Error/Accommodation_Amend_Credit_Card_MultiSupplier/Search-Result-Supplier.jpg";
			logger.info(e.getMessage());
			test.log(LogStatus.FAIL, e.getMessage());
			rep.endTest(test);
			rep.flush();
			Assert.assertTrue(false, e.getMessage());
		}

		try {
			test.log(LogStatus.INFO, "Starting Hotel Save Itenary for Supplier Room");
			logger.info("Starting Hotel Save for Supplier Room");
			wait.until(
					ExpectedConditions.visibilityOfElementLocated(NewAccoBooking.thirdPartyroomSelectionsaveoutdead));
			driverqa.findElement(NewAccoBooking.thirdPartyroomSelectionsaveoutdead).click();
			Thread.sleep(2000);
			obj.Takesnap(driverqa, Config.SnapShotPath()
					+ "/Amend/Accommodation_Amend_Credit_Card_MultiSupplier/Room-Selection-Supplier.jpg");
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
			if (driverqa.findElements(NewAccoBooking.paxFnameCHILD).size() != 0) {
				driverqa.findElement(NewAccoBooking.paxFnameCHILD).sendKeys(excel.getData(0, 23, 1));
				Thread.sleep(1000);
				driverqa.findElement(NewAccoBooking.paxLnameCHILD).sendKeys(excel.getData(0, 23, 2));
				Select passengertitle3 = new Select(driverqa.findElement(NewAccoBooking.paxtitle3));
				passengertitle3.selectByIndex(1);
			}
			driverqa.findElement(NewAccoBooking.acceptChkBX).click();
			Thread.sleep(2000);
			obj.Takesnap(driverqa, Config.SnapShotPath()
					+ "/Amend/Accommodation_Amend_Credit_Card_MultiSupplier/Passenger-Details-Supplier.jpg");
			logger.info("Entered Passenger details for Supplier Room");
			test.log(LogStatus.INFO, "Entered Passenger details for Supplier Room");
			test.log(LogStatus.PASS, "Passenger details for Supplier Room");
			driverqa.findElement(NewAccoBooking.thirdPartsavebooking).click();
			wait.until(ExpectedConditions.visibilityOfElementLocated(NewAccoBooking.thirdPartsaveItncart));
			driverqa.findElement(NewAccoBooking.thirdPartsaveItncart).click();
			Thread.sleep(2000);
			String actualhoteltitle = driverqa.findElement(NewAccoBooking.thirdPartyaftersavehotel).getText();
			String expectedhoteltitle = excel.getData(0, 9, 1);
			Assert.assertTrue(actualhoteltitle.contains(expectedhoteltitle));
			Thread.sleep(2000);
			obj.Takesnap(driverqa, Config.SnapShotPath()
					+ "/Amend/Accommodation_Amend_Credit_Card_MultiSupplier/Itenary-Details-Supplier.jpg");
			test.log(LogStatus.INFO, "Ending HotelSave Credit Card for Supplier Room");
			test.log(LogStatus.PASS, "PASSED HotelSave Credit Card for Supplier Room");
			logger.info("Hotel Save Complete Credit Card for Supplier Room");

		} catch (Exception e) {
			test.log(LogStatus.FAIL, "Hotel Save Itenary Credit Card for Supplier Room");
			obj.Takesnap(driverqa, Config.SnapShotPath()
					+ "/Amend/Error/Accommodation_Amend_Credit_Card_MultiSupplier/Save-Itenary-Supplier.jpg");
			errorpath = Config.SnapShotPath()
					+ "/Amend/Error/Accommodation_Amend_Credit_Card_MultiSupplier/Save-Itenary-Supplier.jpg";
			logger.info(e.getMessage());
			test.log(LogStatus.FAIL, e.getMessage());
			rep.endTest(test);
			rep.flush();
			Assert.assertTrue(false, e.getMessage());

		}
		try {
			driverqa.findElement(NewAccoBooking.thirdPartsaveItncart).click();
			logger.info("Applying search Filters for DOTW Room");
			logger.info("Starting HotelSearch Credit Card for DOTW Room");
			test.log(LogStatus.INFO, "Starting HotelSearch Credit Card for DOTW Room");
			wait.until(ExpectedConditions.visibilityOfElementLocated(NewAccoBooking.AccomUnit));
			driverqa.findElement(NewAccoBooking.AccomUnit).sendKeys(excel.getData(0, 10, 1));
			Thread.sleep(2000);
			action.sendKeys(Keys.ARROW_DOWN).build().perform();
			action.sendKeys(Keys.ENTER).build().perform();
			driverqa.findElement(NewAccoBooking.inDate).clear();
			driverqa.findElement(NewAccoBooking.inDate).sendKeys(excel.getData(0, 16, 1));
			driverqa.findElement(NewAccoBooking.outDate).clear();
			driverqa.findElement(NewAccoBooking.outDate).sendKeys(excel.getData(0, 16, 2));
			String expected = excel.getData(0, 10, 1);
			Select noofchild = new Select(driverqa.findElement(NewAccoBooking.child));
			noofchild.selectByIndex(1);
			wait.until(ExpectedConditions.visibilityOfElementLocated(NewAccoBooking.childage));
			Select childage = new Select(driverqa.findElement(NewAccoBooking.childage));
			childage.selectByIndex(5);
			Thread.sleep(2000);
			obj.Takesnap(driverqa, Config.SnapShotPath()
					+ "/Amend/Accommodation_Amend_Credit_Card_MultiSupplier/Search-Hotel-filters-DOTW.jpg");
			wait.until(ExpectedConditions.visibilityOfElementLocated(NewAccoBooking.bookChannel));
			driverqa.findElement(NewAccoBooking.bookChannel).click();
			wait.until(ExpectedConditions.visibilityOfElementLocated(NewAccoBooking.OfflineContract));
			driverqa.findElement(NewAccoBooking.OfflineContract).click();
			Thread.sleep(2000);
			wait.until(ExpectedConditions.visibilityOfElementLocated(NewAccoBooking.searchButton));
			obj.Takesnap(driverqa, Config.SnapShotPath()
					+ "/Amend/Accommodation_Amend_Credit_Card_MultiSupplier/Booking-Channel-filters-DOTW.jpg");
			driverqa.findElement(NewAccoBooking.searchButton).click();
			wait.until(ExpectedConditions.visibilityOfElementLocated(NewAccoBooking.thirdPartyresultHotel));
			String result = driverqa.findElement(NewAccoBooking.thirdPartyresultHotel).getText();
			Thread.sleep(2000);
			wait.until(ExpectedConditions.elementToBeClickable(NewAccoBooking.thirdPartyroomType));
			wait.until(ExpectedConditions.visibilityOfElementLocated(NewAccoBooking.thirdPartyroomType));
			driverqa.findElement(NewAccoBooking.thirdPartyroomType).click();
			Thread.sleep(2000);
			obj.Takesnap(driverqa, Config.SnapShotPath()
					+ "/Amend/Accommodation_Amend_Credit_Card_MultiSupplier/Search-Result-DOTW.jpg");
			Assert.assertTrue(result.contains(expected));
			test.log(LogStatus.INFO, "Ending HotelSearch Credit Card for DOTW Room");
			test.log(LogStatus.PASS, "PASSED HotelSearch Credit Card for DOTW Room");
			logger.info("Hotel Search Complete Credit Card for DOTW Room");
		} catch (Exception e) {
			test.log(LogStatus.FAIL, "Hotel Search Credit Card for DOTW Room");
			obj.Takesnap(driverqa, Config.SnapShotPath()
					+ "/Amend/Error/Accommodation_Amend_Credit_Card_MultiSupplier/Search-Result-DOTW.jpg");
			errorpath = Config.SnapShotPath()
					+ "/Amend/Error/Accommodation_Amend_Credit_Card_MultiSupplier/Search-Result-DOTW.jpg";
			logger.info(e.getMessage());
			test.log(LogStatus.FAIL, e.getMessage());
			rep.endTest(test);
			rep.flush();
			Assert.assertTrue(false, e.getMessage());
		}
		try {
			test.log(LogStatus.INFO, "Starting Hotel Save Itenary for DOTW Room");
			logger.info("Starting Hotel Save for DOTW Room");
			wait.until(
					ExpectedConditions.visibilityOfElementLocated(NewAccoBooking.thirdPartyroomSelectionsaveoutdead));
			driverqa.findElement(NewAccoBooking.thirdPartyroomSelectionsaveoutdead).click();
			Thread.sleep(2000);
			obj.Takesnap(driverqa, Config.SnapShotPath()
					+ "/Amend/Accommodation_Amend_Credit_Card_MultiSupplier/Room-Selection-DOTW.jpg");
			wait.until(
					ExpectedConditions.visibilityOfElementLocated(NewAccoBooking.thirdPartyprocedetoBooksaveoutdead));
			driverqa.findElement(NewAccoBooking.thirdPartyprocedetoBooksaveoutdead).click();
			logger.info("Entering Passenger details");
			test.log(LogStatus.INFO, "Entering Passenger details");
			wait.until(ExpectedConditions.visibilityOfElementLocated(NewAccoBooking.paxFname));
			driverqa.findElement(NewAccoBooking.paxFname).sendKeys(excel.getData(0, 21, 1));
			Thread.sleep(2000);
			driverqa.findElement(NewAccoBooking.paxLname).sendKeys(excel.getData(0, 22, 2));
			Select passengertitle = new Select(driverqa.findElement(NewAccoBooking.paxtitle));
			passengertitle.selectByIndex(1);
			driverqa.findElement(NewAccoBooking.acceptChkBX).click();
			Thread.sleep(2000);
			obj.Takesnap(driverqa, Config.SnapShotPath()
					+ "/Amend/Accommodation_Amend_Credit_Card_MultiSupplier/Passenger-Details-DOTW.jpg");
			logger.info("Entered Passenger details for DOTW Room");
			test.log(LogStatus.INFO, "Entered Passenger details for DOTW Room");
			test.log(LogStatus.PASS, "Passenger details for DOTW Room");
			driverqa.findElement(NewAccoBooking.thirdPartsavebooking).click();
			Thread.sleep(3000);
			wait.until(ExpectedConditions.visibilityOfElementLocated(NewAccoBooking.thirdPartsaveItncart));
			wait.until(ExpectedConditions.elementToBeClickable(NewAccoBooking.thirdPartsaveItncart));
			driverqa.findElement(NewAccoBooking.thirdPartsaveItncart).click();
			Thread.sleep(2000);
			String actualhoteltitle2 = driverqa.findElement(NewAccoBooking.thirdPartyaftersavehotel2).getText();
			String expectedhoteltitle2 = excel.getData(0, 10, 1);
			Assert.assertTrue(actualhoteltitle2.contains(expectedhoteltitle2));
			Thread.sleep(2000);
			obj.Takesnap(driverqa, Config.SnapShotPath()
					+ "/Amend/Accommodation_Amend_Credit_Card_MultiSupplier/Itenary-Details-DOTW.jpg");
			test.log(LogStatus.INFO, "Ending HotelSave Credit Card for DOTW Room");
			test.log(LogStatus.PASS, "PASSED HotelSave Credit Card for DOTW Room");
			logger.info("Hotel Save Complete Credit Card for DOTW Room");
		} catch (Exception e) {
			test.log(LogStatus.FAIL, "Hotel Save Credit Card for DOTW Room");
			obj.Takesnap(driverqa,
					Config.SnapShotPath() + "/Amend/Error/Accommodation_Amend_Credit_Card_MultiSupplier/Save-DOTW.jpg");
			errorpath = Config.SnapShotPath()
					+ "/Amend/Error/Accommodation_Amend_Credit_Card_MultiSupplier/Save-DOTW.jpg";
			logger.info(e.getMessage());
			test.log(LogStatus.FAIL, e.getMessage());
			rep.endTest(test);
			rep.flush();
			Assert.assertTrue(false, e.getMessage());

		}
		try {
			test.log(LogStatus.INFO, "Starting Hotel Book from Saved Itenary Multi Supplier");
			logger.info("Starting Hotel Book from Saved Itenary Multi Supplier");
			driverqa.findElement(NewAccoBooking.BookSavedITN).click();
			System.out.println("ABC");
			wait.until(ExpectedConditions.visibilityOfElementLocated(NewAccoBooking.ConfirmbuttonafterbookingsavedITN));
			wait.until(ExpectedConditions.elementToBeClickable(NewAccoBooking.ConfirmbuttonafterbookingsavedITN));
			driverqa.findElement(NewAccoBooking.ConfirmbuttonafterbookingsavedITN).click();
			System.out.println("ABCD");
			logger.info("Entering Payment Details");
			test.log(LogStatus.INFO, "Entering Payment Details");
			wait.until(ExpectedConditions.visibilityOfElementLocated(PaymentPage.ccType));
			Select paymentmode = new Select(driverqa.findElement(PaymentPage.ccType));
			paymentmode.selectByIndex(1);
			driverqa.findElement(PaymentPage.ccNum).sendKeys(excel.getData(0, 42, 1));
			driverqa.findElement(PaymentPage.cvv).sendKeys(excel.getData(0, 43, 1));
			driverqa.findElement(PaymentPage.ccName).sendKeys(excel.getData(0, 44, 1));
			Select expiryYear = new Select(driverqa.findElement(PaymentPage.expYear));
			expiryYear.selectByIndex(2);
			Select expirydate = new Select(driverqa.findElement(PaymentPage.expMonth));
			expirydate.selectByIndex(11);
			driverqa.findElement(PaymentPage.zip).sendKeys(excel.getData(0, 41, 1));
			driverqa.findElement(PaymentPage.termsconditions).click();
			Thread.sleep(2000);
			obj.Takesnap(driverqa, Config.SnapShotPath() + "/Amend/Accommodation_Amend_Credit_Card/Card-Details.jpg");
			driverqa.findElement(PaymentPage.confirmPayment).click();
			logger.info("Completed Payment Details");
			test.log(LogStatus.PASS, "Completed Payment Details");
			wait.until(ExpectedConditions.visibilityOfElementLocated(MultiAcco.multisupplierstatus));
			String expectedbookingstatus = "Confirmed";
			String actualbookingstatus = driverqa.findElement(MultiAcco.multisupplierstatus).getText();
			System.out.println(actualbookingstatus);
			String actualhoteltitleDOTW = driverqa.findElement(MultiAcco.multisupplierDOTWHotel).getText();
			System.out.println(actualhoteltitleDOTW);
			String expectedhoteltitleDOTW = excel.getData(0, 10, 1);
			String actualhoteltitleSupplier = driverqa.findElement(MultiAcco.multisupplierSupplierHotel).getText();
			System.out.println(actualhoteltitleSupplier);
			String expectedhoteltitleSupplier = excel.getData(0, 9, 1);
			/*
			 * String actualStartDateDOTW=
			 * driverqa.findElement(MultiAcco.multisupplierSupplierDOTWCheckIn).
			 * getText(); System.out.println(actualStartDateDOTW); String
			 * expectedStartDateDOTW=excel.getData(0, 33, 1); String
			 * actualEndDateDOTW=
			 * driverqa.findElement(MultiAcco.multisupplierSupplierDOTWCheckOut)
			 * .getText(); System.out.println(actualEndDateDOTW); String
			 * expectedEndDateDOTW= excel.getData(, 34, 1); String
			 * actualStartDateSupplier= driverqa.findElement(MultiAcco.
			 * multisupplierSupplierThirdPartyCheckIN).getText();
			 * System.out.println(actualStartDateSupplier); String
			 * expectedStartDateSupplier=excel.getData(3, 28, 1); String
			 * actualEndDateSupplier= driverqa.findElement(MultiAcco.
			 * multisupplierSupplierThirdPartyCheckout).getText();
			 * System.out.println(actualEndDateSupplier); String
			 * expectedEndDateSupplier= excel.getData(3, 29, 1);
			 * //System.out.println(expectedhoteltitle);
			 * //System.out.println(expectedStartDate);
			 * //System.out.println(expectedEndDate);
			 * System.out.println(expectedbookingstatus);
			 * Assert.assertTrue(actualhoteltitleDOTW.contains(
			 * expectedhoteltitleDOTW));
			 * Assert.assertTrue(actualhoteltitleSupplier.contains(
			 * expectedhoteltitleSupplier));
			 * Assert.assertTrue(actualStartDateDOTW.contains(
			 * expectedStartDateDOTW));
			 * Assert.assertTrue(actualEndDateDOTW.contains(expectedEndDateDOTW)
			 * ); Assert.assertTrue(actualEndDateSupplier.contains(
			 * expectedEndDateSupplier));
			 * Assert.assertTrue(actualStartDateSupplier.contains(
			 * expectedStartDateSupplier));
			 * Assert.assertTrue(actualbookingstatus.contains(
			 * expectedbookingstatus));
			 */
			Assert.assertTrue(actualhoteltitleDOTW.contains(expectedhoteltitleDOTW));
			Assert.assertTrue(actualhoteltitleSupplier.contains(expectedhoteltitleSupplier));
			Assert.assertTrue(actualbookingstatus.contains(expectedbookingstatus));
			test.log(LogStatus.INFO, "Ending HotelBook Credit Card from Saved Itenary Multi Supplier");
			test.log(LogStatus.PASS, "PASSED HotelBook Credit Card from Saved Itenary Multi Supplier");
			logger.info("Hotel Book Complete Credit Card Book from Saved Itenary Multi Supplier");
			Thread.sleep(2000);
			obj.Takesnap(driverqa,
					Config.SnapShotPath() + "/Amend/Accommodation_Amend_Credit_Card_MultiSupplier/Booking-Details.jpg");
		} catch (Exception e) {
			test.log(LogStatus.FAIL, "HotelBook Credit Card Book from Saved Itenary Multi Supplier");
			obj.Takesnap(driverqa,
					Config.SnapShotPath() + "/Amend/Error/Accommodation_Amend_Credit_Card_MultiSupplier/Booking.jpg");
			errorpath = Config.SnapShotPath()
					+ "/Amend/Error/Accommodation_Amend_Credit_Card_MultiSupplier/Booking.jpg";
			logger.info(e.getMessage());
			test.log(LogStatus.FAIL, e.getMessage());
			rep.endTest(test);
			rep.flush();
			Assert.assertTrue(false, e.getMessage());

		}
		try {
			driverqa.findElement(Amend.SelectSupplierBooking).click();
			wait.until(ExpectedConditions.visibilityOfElementLocated(NewAccoBooking.thirdPartyconfirmStatus));
			test.log(LogStatus.INFO, "Navigating to Amend Page For DOTW Room");
			logger.info("Navigating to Amend Page For DOTW Room");
			driverqa.findElement(Amend.amendBtn).click();
			// wait.until(ExpectedConditions.visibilityOfElementLocated(Amend.newFname));
			String expectedamendtitle = "DOTWconnect.com::";
			String atualamendtitle = driverqa.getTitle();
			Assert.assertEquals(atualamendtitle, expectedamendtitle);
			test.log(LogStatus.PASS, "Navigating to Amend Page For DOTW Room");
			logger.info("Navigated to Amend Page For DOTW Room");
			Thread.sleep(2000);
			obj.Takesnap(driverqa,
					Config.SnapShotPath() + "/Amend/Accommodation_Amend_Credit_Card_MultiSupplier/booking-Details.jpg");
			test.log(LogStatus.INFO, "Starting to Amend For DOTW Room");
			logger.info("Starting to Amend For DOTW Room");
			test.log(LogStatus.INFO, "Changing No Of Occupancy");
			logger.info("Changing No Of Occupancy");
			Select Adult = new Select(driverqa.findElement(Amend.ChangeNoOfAdults));
			Adult.selectByIndex(1);
			Select Child = new Select(driverqa.findElement(Amend.ChangeNoOfChilds));
			Child.selectByIndex(1);
			test.log(LogStatus.INFO, "Changed No Of Occupancy");
			logger.info("Changed No Of Occupancy");
			Thread.sleep(3000);
			obj.Takesnap(driverqa,
					Config.SnapShotPath() + "/Amend/Accommodation_Amend_Credit_Card_MultiSupplier/AmendPage.jpg");
			driverqa.findElement(Amend.proceedAmend).click();
			Thread.sleep(3000);
			obj.Takesnap(driverqa, Config.SnapShotPath()
					+ "/Amend/Accommodation_Amend_Credit_Card_MultiSupplier/Confirm-AmendPage.jpg");
			driverqa.findElement(Amend.confirmAmend).click();
			wait.until(ExpectedConditions.visibilityOfElementLocated(Amend.StatusAmend));
			test.log(LogStatus.PASS, "Amend For Supplier Room");
			logger.info("Amended For Supplier Room");
			String expectedstatus = "Amended Confirmed";
			String AmendStatus = driverqa.findElement(Amend.StatusAmend).getText();
			Assert.assertTrue(AmendStatus.contains(expectedstatus));
			Thread.sleep(2000);
			obj.Takesnap(driverqa,
					Config.SnapShotPath() + "/Amend/Accommodation_Amend_Credit_Card_MultiSupplier/After-Amend.jpg");
		} catch (Exception e) {
			test.log(LogStatus.FAIL, "Hotel Amend Credit Card for DOTW Room");
			obj.Takesnap(driverqa,
					Config.SnapShotPath() + "/Amend/Error/Accommodation_Amend_Credit_Card_MultiSupplier/Amend.jpg");
			errorpath = Config.SnapShotPath() + "/Amend/Error/Accommodation_Amend_Credit_Card_MultiSupplier/Amend.jpg";

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
