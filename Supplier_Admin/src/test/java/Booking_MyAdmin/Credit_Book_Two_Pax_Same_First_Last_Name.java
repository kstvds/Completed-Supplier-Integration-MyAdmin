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
import ObjectRepository.NewAccoBooking;
import ObjectRepository.Operations;
import Utility.Configuration;
import lib.DriverAndObjectDetails;
import lib.ExcelDataConfig;
import lib.ExtentManager;
import lib.Takescreenshot;
import lib.DriverAndObjectDetails.DriverName;

public class Credit_Book_Two_Pax_Same_First_Last_Name {
	public WebDriver driverqa;
	Configuration Config = new Configuration();
	Takescreenshot obj= new Takescreenshot();
	ExtentReports rep = ExtentManager.getInstance();
	ExtentTest test;
	LoginPage login = new LoginPage();
	HomePage home = new HomePage();
	NewAccoBooking acco = new NewAccoBooking();
	Operations opo = new Operations();
	Logger logger = Logger.getLogger("Credit_Book_Two_Pax_Same_First_Last_Name");
	String SearchRateexpected;
	 @Test
	 @Parameters({ "browsername" })
	  public void BookCreditSameFirstLastNameTwoPax(String browsername) throws Exception {
		  test = rep.startTest("Credit Book Two PAX with Same First and Last Name");
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
			    String URL = excel.getData(0, 1, 5);
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
				obj.Takesnap(driverqa, Config.SnapShotPath() + "/Book/Accommodation_Book_Credit_Two_Pax_Same_First_Last_Name/Log-In.jpg");

		} catch (Exception e) {
			obj.Takesnap(driverqa, Config.SnapShotPath() + "/Book/Error/Accommodation_Book_Credit_Two_Pax_Same_First_Last_Name/Log-In.jpg");
			test.log(LogStatus.FAIL, "Login");
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
				 obj.Takesnap(driverqa, Config.SnapShotPath() + "/Book/Accommodation_Book_Credit_Two_Pax_Same_First_Last_Name/Customer-Search.jpg");
			
			} catch (Exception e) {
				logger.info(e.getMessage());
				test.log(LogStatus.FAIL, e.getMessage());
				rep.endTest(test);
				rep.flush();
				obj.Takesnap(driverqa, Config.SnapShotPath() + "/Book/Error/Accommodation_Book_Credit_Two_Pax_Same_First_Last_Name/Customer-Search.jpg");
				test.log(LogStatus.FAIL, "Navigation to customer search page");
				Assert.assertTrue(false, e.getMessage());
				
			}
		     logger.info("Selecting Customer");
		     test.log(LogStatus.INFO, "Selecting Customer");
			 try {
				 wait.until(ExpectedConditions.visibilityOfElementLocated(Operations.company));
				 driverqa.findElement(Operations.company).sendKeys(excel.getData(0, 5, 1));
				 Thread.sleep(2000);
				 action.sendKeys(Keys.ARROW_DOWN).build().perform();
				 action.sendKeys(Keys.ENTER).build().perform();
				 Thread.sleep(2000);
				 action.sendKeys(Keys.ENTER).build().perform();
				 Thread.sleep(2000);
				 
				obj.Takesnap(driverqa, Config.SnapShotPath() + "/Book/Accommodation_Book_Credit_Two_Pax_Same_First_Last_Name/Customer-list.jpg");
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
				 obj.Takesnap(driverqa, Config.SnapShotPath() + "/Book/Error/Accommodation_Book_Credit_Two_Pax_Same_First_Last_Name/Customer-list.jpg");
					test.log(LogStatus.FAIL, "Customer Selection");
					logger.info(e.getMessage());
					test.log(LogStatus.FAIL, e.getMessage());
					rep.endTest(test);
					rep.flush();
					Assert.assertTrue(false, e.getMessage());
					
				}
			 logger.info("Applying search Filters");
			 logger.info("Starting HotelSearch Credit");
			 try{
				 test.log(LogStatus.INFO, "Starting HotelSearch Credit");
				 wait.until(ExpectedConditions.visibilityOfElementLocated(NewAccoBooking.AccomUnit));
				 driverqa.findElement(NewAccoBooking.AccomUnit).sendKeys(excel.getData(0, 9, 1));
				 Thread.sleep(2000);
				 action.sendKeys(Keys.ARROW_DOWN).build().perform();
				 action.sendKeys(Keys.ENTER).build().perform();
				 driverqa.findElement(NewAccoBooking.inDate).clear();
				 driverqa.findElement(NewAccoBooking.inDate).sendKeys(excel.getData(0, 15, 1));
				 driverqa.findElement(NewAccoBooking.outDate).clear();
				 driverqa.findElement(NewAccoBooking.outDate).sendKeys(excel.getData(0, 15, 2));
				 String expected=excel.getData(0, 9, 1);
				 /*wait.until(ExpectedConditions.visibilityOfElementLocated(MultiAcco.adult1));
		         Select adult1 = new Select(driverqa.findElement(MultiAcco.adult1));
			   	 adult1.selectByIndex(0);*/
				 Thread.sleep(2000);
				 obj.Takesnap(driverqa, Config.SnapShotPath() + "/Book/Accommodation_Book_Credit_Two_Pax_Same_First_Last_Name/Search-Hotel-filters.jpg");
				 wait.until(ExpectedConditions.visibilityOfElementLocated(NewAccoBooking.bookChannel));
				 driverqa.findElement(NewAccoBooking.bookChannel).click();
				 wait.until(ExpectedConditions.visibilityOfElementLocated(NewAccoBooking.thirdPartyChannel));
				 driverqa.findElement(NewAccoBooking.thirdPartyChannel).click();
				 wait.until(ExpectedConditions.visibilityOfElementLocated(NewAccoBooking.thirdParty));
				 driverqa.findElement(NewAccoBooking.thirdParty).click();
				 Thread.sleep(2000);
				 wait.until(ExpectedConditions.visibilityOfElementLocated(NewAccoBooking.searchButton));
				 obj.Takesnap(driverqa, Config.SnapShotPath() + "/Book/Accommodation_Book_Credit_Two_Pax_Same_First_Last_Name/Booking-Channel-filters.jpg");
				 driverqa.findElement(NewAccoBooking.searchButton).click();
				 wait.until(ExpectedConditions.visibilityOfElementLocated(NewAccoBooking.thirdPartyresultHotel));
				 String result= driverqa.findElement(NewAccoBooking.thirdPartyresultHotel).getText();
				 Thread.sleep(2000);
				 wait.until(ExpectedConditions.elementToBeClickable(NewAccoBooking.thirdPartyroomType));
				 wait.until(ExpectedConditions.visibilityOfElementLocated(NewAccoBooking.thirdPartyroomType));
				 driverqa.findElement(NewAccoBooking.thirdPartyroomType).click();
				 Thread.sleep(2000);
				 //wait.until(ExpectedConditions.visibilityOfElementLocated(NewAccoBooking.thirdPartyaSaveITNSearchrateoutdead));
				 //SearchRateexpected= driverqa.findElement(NewAccoBooking.thirdPartyaSaveITNSearchrateoutdead).getText(); 
				 //System.out.println(SearchRateexpected);
				 obj.Takesnap(driverqa, Config.SnapShotPath() + "/Book/Accommodation_Book_Credit_Two_Pax_Same_First_Last_Name/Search-Result.jpg");
				 Assert.assertTrue(result.contains(expected));
				 test.log(LogStatus.INFO, "Ending HotelSearch Credit");
				 test.log(LogStatus.PASS, "PASSED HotelSearch Credit");
				 logger.info("Hotel Search Complete Credit");
			} catch (Exception e) {
				test.log(LogStatus.FAIL, "Hotel Search Credit");
				obj.Takesnap(driverqa, Config.SnapShotPath() + "/Book/Error/Accommodation_Book_Credit_Two_Pax_Same_First_Last_Name/Search-Result.jpg");
				logger.info(e.getMessage());
				test.log(LogStatus.FAIL, e.getMessage());
				rep.endTest(test);
				rep.flush();
				Assert.assertTrue(false, e.getMessage());
							}
			
				try {
					test.log(LogStatus.INFO, "Starting Hotel Book Credit Two PAX with Same First and Last Name");
					 logger.info("Starting Hotel Book Credit Two PAX with Same First and Last Name");
					 wait.until(ExpectedConditions.visibilityOfElementLocated(NewAccoBooking.thirdPartyroomSelectionbookoutdead));
					 driverqa.findElement(NewAccoBooking.thirdPartyroomSelectionbookoutdead).click();
					Thread.sleep(2000);
					 obj.Takesnap(driverqa, Config.SnapShotPath() + "/Book/Accommodation_Book_Credit_Two_Pax_Same_First_Last_Name/Room-Selection.jpg");
					 wait.until(ExpectedConditions.visibilityOfElementLocated(NewAccoBooking.thirdPartyprocedetoBookingoutdead));
					 driverqa.findElement(NewAccoBooking.thirdPartyprocedetoBookingoutdead).click();
					 logger.info("Entering Passenger details");
					 test.log(LogStatus.INFO, "Entering Passenger details");
					Thread.sleep(1000);
					wait.until(ExpectedConditions.visibilityOfElementLocated(NewAccoBooking.paxFname));
					driverqa.findElement(NewAccoBooking.paxFname).sendKeys(excel.getData(0, 21, 1));
					Thread.sleep(2000);
					driverqa.findElement(NewAccoBooking.paxLname).sendKeys(excel.getData(0, 21, 2));
					Select passengertitle = new Select(driverqa.findElement(NewAccoBooking.paxtitle));
					passengertitle.selectByIndex(1);
					driverqa.findElement(NewAccoBooking.paxFname2).sendKeys(excel.getData(0, 21, 1));
					driverqa.findElement(NewAccoBooking.paxLname2).sendKeys(excel.getData(0, 21, 2));
					Select passengertitle2 = new Select(driverqa.findElement(NewAccoBooking.paxtitle2));
					passengertitle2.selectByIndex(1);
					driverqa.findElement(NewAccoBooking.acceptChkBX).click();
					Thread.sleep(2000);
					obj.Takesnap(driverqa, Config.SnapShotPath() + "/Book/Accommodation_Book_Credit_Two_Pax_Same_First_Last_Name/Passenger-Details.jpg");
			    	logger.info("Entered Passenger details");
					test.log(LogStatus.INFO, "Entered Passenger details");
					test.log(LogStatus.PASS, "Passenger details");
					driverqa.findElement(NewAccoBooking.bookBtn).click();
					wait.until(ExpectedConditions.visibilityOfElementLocated(NewAccoBooking.SameNameConfirm));
					Thread.sleep(2000);
					obj.Takesnap(driverqa, Config.SnapShotPath() + "/Book/Accommodation_Book_Credit_Two_Pax_Same_First_Last_Name/Confirm-Same-Name.jpg");
					driverqa.findElement(NewAccoBooking.SameNameConfirm).click();
				    wait.until(ExpectedConditions.visibilityOfElementLocated(NewAccoBooking.thirdPartyconfirmStatus));
					Thread.sleep(2000);
					obj.Takesnap(driverqa, Config.SnapShotPath() + "/Book/Accommodation_Book_Credit_Two_Pax_Same_First_Last_Name/booking-Details.jpg");
					String expectedbookingstatus = "Confirmed";
					String actualbookingstatus = driverqa.findElement(NewAccoBooking.thirdPartyconfirmStatus).getText();
					System.out.println(actualbookingstatus);
					String actualhoteltitle= driverqa.findElement(NewAccoBooking.thirdPartyafterbookhotelcredit).getText();
					System.out.println(actualhoteltitle);
					String expectedhoteltitle=excel.getData(0, 9, 1);
					String actualStartDate= driverqa.findElement(NewAccoBooking.thirdPartyafterbookCheckInCreditOutDead).getText();
					System.out.println(actualStartDate);
					String expectedStartDate=excel.getData(0, 28, 1);
					String actualEndDate= driverqa.findElement(NewAccoBooking.thirdPartyafterbookCheckOutCreditOutDead).getText();
					System.out.println(actualEndDate);
					String expectedEndDate= excel.getData(0, 29, 1);
					String actualnames= driverqa.findElement(NewAccoBooking.SameLastNameFirstNameTwoPax).getText();
					String Expectednames = "Kaustav Das";
					Assert.assertTrue(actualhoteltitle.contains(expectedhoteltitle));
					Assert.assertTrue(actualStartDate.contains(expectedStartDate));
					Assert.assertTrue(actualEndDate.contains(expectedEndDate));
					Assert.assertTrue(actualbookingstatus.contains(expectedbookingstatus));
					Assert.assertTrue(actualnames.contains(Expectednames));
					test.log(LogStatus.INFO, "Ending HotelBook Credit Two PAX with Same First and Last Name");
				    test.log(LogStatus.PASS, "PASSED HotelBook Credit Two PAX with Same First and Last Name");
				    logger.info("Hotel Book Complete Credit Two PAX with Same First and Last Name");


				} catch (Exception e) {
					test.log(LogStatus.FAIL, "Hotel Book Credit Two PAX with Same First and Last Name");
					obj.Takesnap(driverqa, Config.SnapShotPath() + "/Book/Error/Accommodation_Book_Credit_Two_Pax_Same_First_Last_Name/Save-Itenary.jpg");
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








