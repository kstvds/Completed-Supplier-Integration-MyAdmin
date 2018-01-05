package Booking_MyAdmin;

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
import ObjectRepository.MultiAcco;
import ObjectRepository.NewAccoBooking;
import ObjectRepository.Operations;
import Utility.Configuration;
import lib.DriverAndObjectDetails;
import lib.ExcelDataConfig;
import lib.ExtentManager;
import lib.Takescreenshot;
import lib.DriverAndObjectDetails.DriverName;

public class Book_Credit_MultiSupplier {
	public WebDriver driverqa;
	Configuration Config = new Configuration();
	Takescreenshot obj= new Takescreenshot();
	ExtentReports rep = ExtentManager.getInstance();
	ExtentTest test;
	LoginPage login = new LoginPage();
	HomePage home = new HomePage();
	NewAccoBooking acco = new NewAccoBooking();
	Operations opo = new Operations();
	Logger logger = Logger.getLogger("Book_Credit_MultiSupplier");
	String SearchRateexpected;
	 @Test
	 @Parameters({ "browsername" })
	  public void BookCreditMultiSupplier(String browsername) throws Exception {
		  test = rep.startTest("Book Credit MultiSupplier");
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
			    WebDriverWait wait= new WebDriverWait(driverqa, 30);
			    Actions action = new Actions(driverqa);
	           try{
			    logger.info("Browser Opened");
				driverqa.get(Config.getApplicationUrl());
				logger.info("Test Case Started");
				test.log(LogStatus.INFO, "Starting Login");
				WebElement username = driverqa.findElement(LoginPage.uname);
				username.clear();
				username.sendKeys(excel.getData(3, 1, 1));
				WebElement password = driverqa.findElement(LoginPage.pwd);
				password.clear();
				password.sendKeys(excel.getData(3, 1, 2));
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
				obj.Takesnap(driverqa, Config.SnapShotPath() + "/Book/Accommodation_Book_Credit_MultiSupplier/Log-In.jpg");

		} catch (Exception e) {
			logger.info(e.getMessage());
			test.log(LogStatus.FAIL, e.getMessage());
			rep.endTest(test);
			rep.flush();
			Assert.assertTrue(false, e.getMessage());
			obj.Takesnap(driverqa, Config.SnapShotPath() + "/Book/Error/Accommodation_Book_Credit_MultiSupplier/Log-In.jpg");
			test.log(LogStatus.FAIL, "Login");
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
				 obj.Takesnap(driverqa, Config.SnapShotPath() + "/Book/Accommodation_Book_Credit_MultiSupplier/Customer-Search.jpg");
			
			} catch (Exception e) {
				logger.info(e.getMessage());
				test.log(LogStatus.FAIL, e.getMessage());
				rep.endTest(test);
				rep.flush();
				obj.Takesnap(driverqa, Config.SnapShotPath() + "/Book/Error/Accommodation_Book_Credit_MultiSupplier/Customer-Search.jpg");
				Assert.assertTrue(false, e.getMessage());
				test.log(LogStatus.FAIL, "Navigation to customer search page");
			}
		     logger.info("Selecting Customer");
		     test.log(LogStatus.INFO, "Selecting Customer");
			 try {
				 wait.until(ExpectedConditions.visibilityOfElementLocated(Operations.company));
				 driverqa.findElement(Operations.company).sendKeys(excel.getData(3, 5, 1));
				 Thread.sleep(2000);
				 action.sendKeys(Keys.ARROW_DOWN).build().perform();
				 action.sendKeys(Keys.ENTER).build().perform();
				 Thread.sleep(2000);
				 action.sendKeys(Keys.ENTER).build().perform();
				 Thread.sleep(2000);
				 
				obj.Takesnap(driverqa, Config.SnapShotPath() + "/Book/Accommodation_Book_Credit_MultiSupplier/Customer-list.jpg");
				wait.until(ExpectedConditions.visibilityOfElementLocated(Operations.chooseCustbook));
				driverqa.findElement(Operations.chooseCustbook).click();
				Thread.sleep(1000);
				String searchpageactualtitle = driverqa.getTitle();
				String searchpageexpectedtitle = "DOTWconnect.com::";
				Assert.assertEquals(searchpageactualtitle, searchpageexpectedtitle);
				logger.info("Customer Selected");
				test.log(LogStatus.PASS, "Customer Selected");
				
			 }
			 catch (Exception e) {
					logger.info(e.getMessage());
					test.log(LogStatus.FAIL, e.getMessage());
					rep.endTest(test);
					rep.flush();
					Assert.assertTrue(false, e.getMessage());
					obj.Takesnap(driverqa, Config.SnapShotPath() + "/Book/Error/Accommodation_Book_Credit_MultiSupplier/Customer-list.jpg");
					test.log(LogStatus.FAIL, "Customer Selection");
				}
			 logger.info("Applying search Filters for Supplier Room");
			 logger.info("Starting HotelSearch Credit for Supplier Room");
			 try{
				 test.log(LogStatus.INFO, "Starting HotelSearch Credit for Supplier Room");
				 wait.until(ExpectedConditions.visibilityOfElementLocated(NewAccoBooking.AccomUnit));
				 driverqa.findElement(NewAccoBooking.AccomUnit).sendKeys(excel.getData(3, 9, 1));
				 Thread.sleep(2000);
				 action.sendKeys(Keys.ARROW_DOWN).build().perform();
				 action.sendKeys(Keys.ENTER).build().perform();
				 driverqa.findElement(NewAccoBooking.inDate).clear();
				 driverqa.findElement(NewAccoBooking.inDate).sendKeys(excel.getData(3, 15, 1));
				 driverqa.findElement(NewAccoBooking.outDate).clear();
				 driverqa.findElement(NewAccoBooking.outDate).sendKeys(excel.getData(3, 15, 2));
				 String expected=excel.getData(3, 9, 1);
				 Thread.sleep(2000);
				 obj.Takesnap(driverqa, Config.SnapShotPath() + "/Book/Accommodation_Book_Credit_MultiSupplier/Search-Hotel-Supplier-filters.jpg");
				 wait.until(ExpectedConditions.visibilityOfElementLocated(NewAccoBooking.bookChannel));
				 driverqa.findElement(NewAccoBooking.bookChannel).click();
				 wait.until(ExpectedConditions.visibilityOfElementLocated(NewAccoBooking.thirdPartyChannel));
				 driverqa.findElement(NewAccoBooking.thirdPartyChannel).click();
				 wait.until(ExpectedConditions.visibilityOfElementLocated(NewAccoBooking.thirdParty));
				 driverqa.findElement(NewAccoBooking.thirdParty).click();
				 Thread.sleep(2000);
				 wait.until(ExpectedConditions.visibilityOfElementLocated(NewAccoBooking.searchButton));
				 obj.Takesnap(driverqa, Config.SnapShotPath() + "/Book/Accommodation_Book_Credit_MultiSupplier/Booking-Channel-Supplier-filters.jpg");
				 driverqa.findElement(NewAccoBooking.searchButton).click();
				 wait.until(ExpectedConditions.visibilityOfElementLocated(NewAccoBooking.thirdPartyresultHotel));
				 String result= driverqa.findElement(NewAccoBooking.thirdPartyresultHotel).getText();
				 Thread.sleep(2000);
				 wait.until(ExpectedConditions.elementToBeClickable(NewAccoBooking.thirdPartyroomType));
				 wait.until(ExpectedConditions.visibilityOfElementLocated(NewAccoBooking.thirdPartyroomType));
				 driverqa.findElement(NewAccoBooking.thirdPartyroomType).click();
				 Thread.sleep(2000);
				 obj.Takesnap(driverqa, Config.SnapShotPath() + "/Book/Accommodation_Book_Credit_MultiSupplier/Search-Result-Supplier.jpg");
				 Assert.assertTrue(result.contains(expected));
				 test.log(LogStatus.INFO, "Ending HotelSearch Credit for Supplier Room");
				 test.log(LogStatus.PASS, "PASSED HotelSearch Credit for Supplier Room");
				 logger.info("Hotel Search Complete Credit for Supplier Room");
			} catch (Exception e) {
				logger.info(e.getMessage());
				test.log(LogStatus.FAIL, e.getMessage());
				rep.endTest(test);
				rep.flush();
				Assert.assertTrue(false, e.getMessage());
				test.log(LogStatus.FAIL, "Hotel Search Credit for Supplier Room");
				obj.Takesnap(driverqa, Config.SnapShotPath() + "/Book/Error/Accommodation_Book_Credit_MultiSupplier/Search-Result-Supplier.jpg");
			}
			
				try {
					test.log(LogStatus.INFO, "Starting Hotel Save Itenary for Supplier Room");
					 logger.info("Starting Hotel Save for Supplier Room");
					 wait.until(ExpectedConditions.visibilityOfElementLocated(NewAccoBooking.thirdPartyroomSelectionsaveoutdead));
					 driverqa.findElement(NewAccoBooking.thirdPartyroomSelectionsaveoutdead).click();
					 Thread.sleep(2000);
					 obj.Takesnap(driverqa, Config.SnapShotPath() + "/Book/Accommodation_Book_Credit_MultiSupplier/Room-Selection-Supplier.jpg");
					 wait.until(ExpectedConditions.visibilityOfElementLocated(NewAccoBooking.thirdPartyprocedetoBooksaveoutdead));
					 driverqa.findElement(NewAccoBooking.thirdPartyprocedetoBooksaveoutdead).click();
					 logger.info("Entering Passenger details");
					 test.log(LogStatus.INFO, "Entering Passenger details");
					 wait.until(ExpectedConditions.visibilityOfElementLocated(NewAccoBooking.paxFname));
					driverqa.findElement(NewAccoBooking.paxFname).sendKeys(excel.getData(3, 21, 1));
					Thread.sleep(2000);
					driverqa.findElement(NewAccoBooking.paxLname).sendKeys(excel.getData(3, 21, 2));
					Select passengertitle = new Select(driverqa.findElement(NewAccoBooking.paxtitle));
					passengertitle.selectByIndex(1);
					driverqa.findElement(NewAccoBooking.paxFname2).sendKeys(excel.getData(3, 22, 1));
					Thread.sleep(1000);
					driverqa.findElement(NewAccoBooking.paxLname2).sendKeys(excel.getData(3, 22, 2));
					Select passengertitle2 = new Select(driverqa.findElement(NewAccoBooking.paxtitle2));
					passengertitle2.selectByIndex(1);
					driverqa.findElement(NewAccoBooking.acceptChkBX).click();
					Thread.sleep(2000);
					 obj.Takesnap(driverqa, Config.SnapShotPath() + "/Book/Accommodation_Book_Credit_MultiSupplier/Passenger-Details-Supplier.jpg");
					logger.info("Entered Passenger details for Supplier Room");
					test.log(LogStatus.INFO, "Entered Passenger details for Supplier Room");
					test.log(LogStatus.PASS, "Passenger details for Supplier Room");
					driverqa.findElement(NewAccoBooking.thirdPartsavebooking).click();
					wait.until(ExpectedConditions.visibilityOfElementLocated(NewAccoBooking.thirdPartsaveItncart));
					driverqa.findElement(NewAccoBooking.thirdPartsaveItncart).click();
					Thread.sleep(2000);
					String actualhoteltitle= driverqa.findElement(NewAccoBooking.thirdPartyaftersavehotel).getText();
					String expectedhoteltitle=excel.getData(3, 9, 1);
					Assert.assertTrue(actualhoteltitle.contains(expectedhoteltitle));
					Thread.sleep(2000);
					obj.Takesnap(driverqa, Config.SnapShotPath() + "/Book/Accommodation_Book_Credit_MultiSupplier/Itenary-Details-Supplier.jpg");
					test.log(LogStatus.INFO, "Ending HotelSave Credit for Supplier Room");
				    test.log(LogStatus.PASS, "PASSED HotelSave Credit for Supplier Room");
				    logger.info("Hotel Save Complete Credit for Supplier Room");


				} catch (Exception e) {
					logger.info(e.getMessage());
					test.log(LogStatus.FAIL, e.getMessage());
					rep.endTest(test);
					rep.flush();
					Assert.assertTrue(false, e.getMessage());
					test.log(LogStatus.FAIL, "Hotel Save Itenary Credit for Supplier Room");
					obj.Takesnap(driverqa, Config.SnapShotPath() + "/Book/Error/Accommodation_Book_Credit_MultiSupplier/Save-Itenary-Supplier.jpg");

				}
				try {
					driverqa.findElement(NewAccoBooking.thirdPartsaveItncart).click();
					 logger.info("Applying search Filters for DOTW Room");
					 logger.info("Starting HotelSearch Credit for DOTW Room");
					 test.log(LogStatus.INFO, "Starting HotelSearch Credit for DOTW Room");
					 wait.until(ExpectedConditions.visibilityOfElementLocated(NewAccoBooking.AccomUnit));
					 driverqa.findElement(NewAccoBooking.AccomUnit).sendKeys(excel.getData(3, 10, 1));
					 Thread.sleep(2000);
					 action.sendKeys(Keys.ARROW_DOWN).build().perform();
					 action.sendKeys(Keys.ENTER).build().perform();
					 driverqa.findElement(NewAccoBooking.inDate).clear();
					 driverqa.findElement(NewAccoBooking.inDate).sendKeys(excel.getData(3, 16, 1));
					 driverqa.findElement(NewAccoBooking.outDate).clear();
					 driverqa.findElement(NewAccoBooking.outDate).sendKeys(excel.getData(3, 16, 2));
					String expected=excel.getData(3, 10, 1);
					Select noofchild = new Select(driverqa.findElement(NewAccoBooking.child));
					noofchild.selectByIndex(1);
					wait.until(ExpectedConditions.visibilityOfElementLocated(NewAccoBooking.childage));
					Select childage = new Select(driverqa.findElement(NewAccoBooking.childage));
					childage.selectByIndex(5);
					 Thread.sleep(2000);
					 obj.Takesnap(driverqa, Config.SnapShotPath() + "/Book/Accommodation_Book_Credit_MultiSupplier/Search-Hotel-filters-DOTW.jpg");
					 wait.until(ExpectedConditions.visibilityOfElementLocated(NewAccoBooking.bookChannel));
					 driverqa.findElement(NewAccoBooking.bookChannel).click();
					 wait.until(ExpectedConditions.visibilityOfElementLocated(NewAccoBooking.OfflineContract));
					 driverqa.findElement(NewAccoBooking.OfflineContract).click();
					 Thread.sleep(2000);
					 wait.until(ExpectedConditions.visibilityOfElementLocated(NewAccoBooking.searchButton));
					 obj.Takesnap(driverqa, Config.SnapShotPath() + "/Book/Accommodation_Book_Credit_MultiSupplier/Booking-Channel-filters-DOTW.jpg");
					 driverqa.findElement(NewAccoBooking.searchButton).click();
					 wait.until(ExpectedConditions.visibilityOfElementLocated(NewAccoBooking.thirdPartyresultHotel));
					 String result= driverqa.findElement(NewAccoBooking.thirdPartyresultHotel).getText();
					 Thread.sleep(2000);
					 wait.until(ExpectedConditions.elementToBeClickable(NewAccoBooking.thirdPartyroomType));
					 wait.until(ExpectedConditions.visibilityOfElementLocated(NewAccoBooking.thirdPartyroomType));
					 driverqa.findElement(NewAccoBooking.thirdPartyroomType).click();
					 Thread.sleep(2000);
					 obj.Takesnap(driverqa, Config.SnapShotPath() + "/Book/Accommodation_Book_Credit_MultiSupplier/Search-Result-DOTW.jpg");
					 Assert.assertTrue(result.contains(expected));
					 test.log(LogStatus.INFO, "Ending HotelSearch Credit for DOTW Room");
					 test.log(LogStatus.PASS, "PASSED HotelSearch Credit for DOTW Room");
					 logger.info("Hotel Search Complete Credit for DOTW Room");
				} 
					catch (Exception e) {
						logger.info(e.getMessage());
						test.log(LogStatus.FAIL, e.getMessage());
						rep.endTest(test);
						rep.flush();
						Assert.assertTrue(false, e.getMessage());
						test.log(LogStatus.FAIL, "Hotel Search Credit for DOTW Room");
						obj.Takesnap(driverqa, Config.SnapShotPath() + "/Book/Error/Accommodation_Book_Credit_MultiSupplier/Search-Result-DOTW.jpg");
					}
				try {
					test.log(LogStatus.INFO, "Starting Hotel Save Itenary for DOTW Room");
					 logger.info("Starting Hotel Save for DOTW Room");
					 wait.until(ExpectedConditions.visibilityOfElementLocated(NewAccoBooking.thirdPartyroomSelectionsaveoutdead));
					 driverqa.findElement(NewAccoBooking.thirdPartyroomSelectionsaveoutdead).click();
					 Thread.sleep(2000);
					 obj.Takesnap(driverqa, Config.SnapShotPath() + "/Book/Accommodation_Book_Credit_MultiSupplier/Room-Selection-DOTW.jpg");
					 wait.until(ExpectedConditions.visibilityOfElementLocated(NewAccoBooking.thirdPartyprocedetoBooksaveoutdead));
					 driverqa.findElement(NewAccoBooking.thirdPartyprocedetoBooksaveoutdead).click();
					 logger.info("Entering Passenger details");
					 test.log(LogStatus.INFO, "Entering Passenger details");
					 wait.until(ExpectedConditions.visibilityOfElementLocated(NewAccoBooking.paxFname));
					driverqa.findElement(NewAccoBooking.paxFname).sendKeys(excel.getData(3, 21, 1));
					Thread.sleep(2000);
					driverqa.findElement(NewAccoBooking.paxLname).sendKeys(excel.getData(3, 22, 2));
					Select passengertitle = new Select(driverqa.findElement(NewAccoBooking.paxtitle));
					passengertitle.selectByIndex(1);
					driverqa.findElement(NewAccoBooking.acceptChkBX).click();
					Thread.sleep(2000);
					obj.Takesnap(driverqa, Config.SnapShotPath() + "/Book/Accommodation_Book_Credit_MultiSupplier/Passenger-Details-DOTW.jpg");
					logger.info("Entered Passenger details for DOTW Room");
					test.log(LogStatus.INFO, "Entered Passenger details for DOTW Room");
					test.log(LogStatus.PASS, "Passenger details for DOTW Room");
					driverqa.findElement(NewAccoBooking.thirdPartsavebooking).click();
					Thread.sleep(3000);
					wait.until(ExpectedConditions.visibilityOfElementLocated(NewAccoBooking.thirdPartsaveItncart));
					wait.until(ExpectedConditions.elementToBeClickable(NewAccoBooking.thirdPartsaveItncart));
					driverqa.findElement(NewAccoBooking.thirdPartsaveItncart).click();
					Thread.sleep(2000);
					String actualhoteltitle2= driverqa.findElement(NewAccoBooking.thirdPartyaftersavehotel2).getText();
					String expectedhoteltitle2=excel.getData(3, 10, 1);
					Assert.assertTrue(actualhoteltitle2.contains(expectedhoteltitle2));
					Thread.sleep(2000);
					obj.Takesnap(driverqa, Config.SnapShotPath() + "/Book/Accommodation_Book_Credit_MultiSupplier/Itenary-Details-DOTW.jpg");
					test.log(LogStatus.INFO, "Ending HotelSave Credit for DOTW Room");
					test.log(LogStatus.PASS, "PASSED HotelSave Credit for DOTW Room");
					logger.info("Hotel Save Complete Credit for DOTW Room");
				} catch (Exception e) {
					logger.info(e.getMessage());
					test.log(LogStatus.FAIL, e.getMessage());
					rep.endTest(test);
					rep.flush();
					Assert.assertTrue(false, e.getMessage());
					test.log(LogStatus.FAIL, "Hotel Save Credit for DOTW Room");
					obj.Takesnap(driverqa, Config.SnapShotPath() + "/Book/Error/Accommodation_Book_Credit_MultiSupplier/Search-Result-DOTW.jpg");
		
				}
				try {
					test.log(LogStatus.INFO, "Starting Hotel Book from Saved Itenary Multi Supplier");
					 logger.info("Starting Hotel Book from Saved Itenary Multi Supplier");
					 driverqa.findElement(NewAccoBooking.BookSavedITN).click();
					 wait.until(ExpectedConditions.visibilityOfElementLocated(NewAccoBooking.ConfirmbuttonafterbookingsavedITN));
					 wait.until(ExpectedConditions.elementToBeClickable(NewAccoBooking.ConfirmbuttonafterbookingsavedITN));
					 driverqa.findElement(NewAccoBooking.ConfirmbuttonafterbookingsavedITN).click();
					 wait.until(ExpectedConditions.visibilityOfElementLocated(MultiAcco.multisupplierstatus));
					 String expectedbookingstatus = "Confirmed";
					 String actualbookingstatus = driverqa.findElement(MultiAcco.multisupplierstatus).getText();
					 System.out.println(actualbookingstatus);
					 String actualhoteltitleDOTW= driverqa.findElement(MultiAcco.multisupplierDOTWHotel).getText();
					 System.out.println(actualhoteltitleDOTW);
					 String expectedhoteltitleDOTW=excel.getData(3, 10, 1);
					 String actualhoteltitleSupplier= driverqa.findElement(MultiAcco.multisupplierSupplierHotel).getText();
					 System.out.println(actualhoteltitleSupplier);
					 String expectedhoteltitleSupplier=excel.getData(3, 9, 1);
					 String actualStartDateDOTW= driverqa.findElement(MultiAcco.multisupplierSupplierDOTWCheckIn).getText();
					 System.out.println(actualStartDateDOTW);
					 String expectedStartDateDOTW=excel.getData(3, 33, 1);
					 String actualEndDateDOTW= driverqa.findElement(MultiAcco.multisupplierSupplierDOTWCheckOut).getText();
					 System.out.println(actualEndDateDOTW);
					 String expectedEndDateDOTW= excel.getData(3, 34, 1);
					 String actualStartDateSupplier= driverqa.findElement(MultiAcco.multisupplierSupplierThirdPartyCheckIN).getText();
					 System.out.println(actualStartDateSupplier);
					 String expectedStartDateSupplier=excel.getData(3, 28, 1);
					 String actualEndDateSupplier= driverqa.findElement(MultiAcco.multisupplierSupplierThirdPartyCheckout).getText();
					 System.out.println(actualEndDateSupplier);
					 String expectedEndDateSupplier= excel.getData(3, 29, 1);
					 //System.out.println(expectedhoteltitle);
					 //System.out.println(expectedStartDate);
					 //System.out.println(expectedEndDate);
					 System.out.println(expectedbookingstatus);
					 Assert.assertTrue(actualhoteltitleDOTW.contains(expectedhoteltitleDOTW));
					 Assert.assertTrue(actualhoteltitleSupplier.contains(expectedhoteltitleSupplier));
					 Assert.assertTrue(actualStartDateDOTW.contains(expectedStartDateDOTW));
					 Assert.assertTrue(actualEndDateDOTW.contains(expectedEndDateDOTW));
					 Assert.assertTrue(actualEndDateSupplier.contains(expectedEndDateSupplier));
					 Assert.assertTrue(actualStartDateSupplier.contains(expectedStartDateSupplier));
					 Assert.assertTrue(actualbookingstatus.contains(expectedbookingstatus));
					 test.log(LogStatus.INFO, "Ending HotelBook Credit from Saved Itenary Multi Supplier");
					 test.log(LogStatus.PASS, "PASSED HotelBook Credit from Saved Itenary Multi Supplier");
					 logger.info("Hotel Book Complete Credit Book from Saved Itenary Multi Supplier");
					 Thread.sleep(2000);
					 obj.Takesnap(driverqa, Config.SnapShotPath() + "/Book/Accommodation_Book_Credit_MultiSupplier/Booking-Details.jpg");
				} catch (Exception e) {
					logger.info(e.getMessage());
					test.log(LogStatus.FAIL, e.getMessage());
					rep.endTest(test);
					rep.flush();
					Assert.assertTrue(false, e.getMessage());
					test.log(LogStatus.FAIL, "HotelBook Credit Book from Saved Itenary Multi Supplier");
					obj.Takesnap(driverqa, Config.SnapShotPath() + "/Book/Error/Accommodation_Book_Credit_MultiSupplier/Booking-Details.jpg");

				}

			}
	 
	 
	  @AfterMethod
		public void getResult(ITestResult result) {
			if (result.getStatus() == ITestResult.FAILURE) {
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




