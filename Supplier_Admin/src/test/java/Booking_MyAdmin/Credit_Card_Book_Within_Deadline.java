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
import ObjectRepository.PaymentPage;
import Utility.Configuration;
import lib.DriverAndObjectDetails;
import lib.ExcelDataConfig;
import lib.ExtentManager;
import lib.Takescreenshot;
import lib.DriverAndObjectDetails.DriverName;

public class Credit_Card_Book_Within_Deadline {
	public WebDriver driverqa;
	Configuration Config = new Configuration();
	Takescreenshot obj= new Takescreenshot();
	ExtentReports rep = ExtentManager.getInstance();
	ExtentTest test;
	LoginPage login = new LoginPage();
	HomePage home = new HomePage();
	NewAccoBooking acco = new NewAccoBooking();
	Operations opo = new Operations();
	Logger logger = Logger.getLogger("Credit_Card_Book_Within_Deadline");
	String SearchRateexpected;
	 @Test
	 @Parameters({ "browsername" })
	  public void BookCreditCardWithinDeadLine(String browsername) throws Exception {
		  test = rep.startTest("Credit Card Book Within Deadline");
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
				obj.Takesnap(driverqa, Config.SnapShotPath() + "/Book/Accommodation_Book_Credit_Card_Within_DeadLine/Log-In.jpg");

		} catch (Exception e) {
			logger.info(e.getMessage());
			test.log(LogStatus.FAIL, e.getMessage());
			rep.endTest(test);
			rep.flush();
			Assert.assertTrue(false, e.getMessage());
			obj.Takesnap(driverqa, Config.SnapShotPath() + "/Book/Error/Accommodation_Book_Credit_Card_Within_DeadLine/Log-In.jpg");
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
				 Thread.sleep(2000);
				 obj.Takesnap(driverqa, Config.SnapShotPath() + "/Book/Accommodation_Book_Credit_Card_Within_DeadLine/Customer-Search.jpg");
			
			} catch (Exception e) {
				logger.info(e.getMessage());
				test.log(LogStatus.FAIL, e.getMessage());
				rep.endTest(test);
				rep.flush();
				obj.Takesnap(driverqa, Config.SnapShotPath() + "/Book/Error/Accommodation_Book_Credit_Card_Within_DeadLine/Customer-Search.jpg");
				Assert.assertTrue(false, e.getMessage());
				test.log(LogStatus.FAIL, "Navigation to customer search page");
			}
		     logger.info("Selecting Customer");
		     test.log(LogStatus.INFO, "Selecting Customer");
			 try {
				 wait.until(ExpectedConditions.visibilityOfElementLocated(Operations.company));
				 driverqa.findElement(Operations.company).sendKeys(excel.getData(3, 6, 1));
				 Thread.sleep(2000);
				 action.sendKeys(Keys.ARROW_DOWN).build().perform();
				 action.sendKeys(Keys.ENTER).build().perform();
				 Thread.sleep(2000);
				 action.sendKeys(Keys.ENTER).build().perform();
				 Thread.sleep(2000);
				 
				obj.Takesnap(driverqa, Config.SnapShotPath() + "/Book/Accommodation_Book_Credit_Card_Within_DeadLine/Customer-list.jpg");
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
					obj.Takesnap(driverqa, Config.SnapShotPath() + "/Book/Error/Accommodation_Book_Credit_Card_Within_DeadLine/Customer-list.jpg");
					test.log(LogStatus.FAIL, "Customer Selection");
				}
			 logger.info("Applying search Filters");
			 logger.info("Starting HotelSearch Credit Card Within Deadline");
			 try{
				 test.log(LogStatus.INFO, "Starting HotelSearch Credit Card Within Deadline");
				 wait.until(ExpectedConditions.visibilityOfElementLocated(NewAccoBooking.AccomUnit));
				 driverqa.findElement(NewAccoBooking.AccomUnit).sendKeys(excel.getData(3, 12, 1));
				 Thread.sleep(2000);
				 action.sendKeys(Keys.ARROW_DOWN).build().perform();
				 action.sendKeys(Keys.ENTER).build().perform();
				 driverqa.findElement(NewAccoBooking.inDate).clear();
				 driverqa.findElement(NewAccoBooking.inDate).sendKeys(excel.getData(3, 18, 1));
				 driverqa.findElement(NewAccoBooking.outDate).clear();
				 driverqa.findElement(NewAccoBooking.outDate).sendKeys(excel.getData(3, 18, 2));
				 String expected=excel.getData(3, 12, 1);
				 driverqa.findElement(MultiAcco.Plusroom).click();
				 wait.until(ExpectedConditions.visibilityOfElementLocated(MultiAcco.adult1));
		         /*Select adult1 = new Select(driverqa.findElement(MultiAcco.adult1));
			   	 adult1.selectByIndex(0);*/
			   	 Select noofchild = new Select(driverqa.findElement(MultiAcco.child1));
				 noofchild.selectByIndex(1);
				 /*Thread.sleep(2000);
				 Select adult2 = new Select(driverqa.findElement(MultiAcco.adult2));
				 adult2.selectByIndex(0);*/
				 Thread.sleep(2000);
				 obj.Takesnap(driverqa, Config.SnapShotPath() + "/Book/Accommodation_Book_Credit_Card_Within_DeadLine/Search-Hotel-filters.jpg");
				 wait.until(ExpectedConditions.visibilityOfElementLocated(NewAccoBooking.bookChannel));
				 driverqa.findElement(NewAccoBooking.bookChannel).click();
				 wait.until(ExpectedConditions.visibilityOfElementLocated(NewAccoBooking.thirdPartyChannel));
				 driverqa.findElement(NewAccoBooking.thirdPartyChannel).click();
				 wait.until(ExpectedConditions.visibilityOfElementLocated(NewAccoBooking.thirdParty));
				 driverqa.findElement(NewAccoBooking.thirdParty).click();
				 Thread.sleep(2000);
				 wait.until(ExpectedConditions.visibilityOfElementLocated(NewAccoBooking.searchButton));
				 obj.Takesnap(driverqa, Config.SnapShotPath() + "/Book/Accommodation_Book_Credit_Card_Within_DeadLine/Booking-Channel-filters.jpg");
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
				 obj.Takesnap(driverqa, Config.SnapShotPath() + "/Book/Accommodation_Book_Credit_Card_Within_DeadLine/Search-Result.jpg");
				 wait.until(ExpectedConditions.visibilityOfElementLocated(NewAccoBooking.thirdPartyDeadline));
				 String actualdeadline= driverqa.findElement(NewAccoBooking.thirdPartyDeadline).getText();
				 String expecteddeadline=excel.getData(3, 30, 1);
				 Assert.assertTrue(result.contains(expected));
				 Assert.assertTrue(actualdeadline.contains(expecteddeadline));
				 test.log(LogStatus.INFO, "Ending HotelSearch Credit Card Within Deadline");
				 test.log(LogStatus.PASS, "PASSED HotelSearch Credit Card Within Deadline");
				 logger.info("Hotel Search Complete Credit Card Within Deadline");
			} catch (Exception e) {
				logger.info(e.getMessage());
				test.log(LogStatus.FAIL, e.getMessage());
				rep.endTest(test);
				rep.flush();
				Assert.assertTrue(false, e.getMessage());
				test.log(LogStatus.FAIL, "Hotel Search Credit Within Deadline");
				obj.Takesnap(driverqa, Config.SnapShotPath() + "/Book/Error/Accommodation_Book_Credit_Card_Within_DeadLine/Search-Result.jpg");
			}
			
				try {
					test.log(LogStatus.INFO, "Starting Hotel Book Credit Card Within Deadline");
					 logger.info("Starting Hotel Book");
					 wait.until(ExpectedConditions.visibilityOfElementLocated(NewAccoBooking.thirdPartyroomSelectionbookoutdead));
					 driverqa.findElement(NewAccoBooking.thirdPartyroomSelectionbookoutdead).click();
					 driverqa.findElement(NewAccoBooking.thirdPartyroom2Selectionbookoutdead).click();
					 Thread.sleep(2000);
					 obj.Takesnap(driverqa, Config.SnapShotPath() + "/Book/Accommodation_Book_Credit_Card_Within_DeadLine/Room-Selection.jpg");
					 wait.until(ExpectedConditions.visibilityOfElementLocated(NewAccoBooking.thirdPartyprocedetoBookingoutdead));
					 driverqa.findElement(NewAccoBooking.thirdPartyprocedetoBookingoutdead).click();
					 logger.info("Entering Passenger details");
					 test.log(LogStatus.INFO, "Entering Passenger details");
					 wait.until(ExpectedConditions.visibilityOfElementLocated(MultiAcco.paxFname1room1));
				     driverqa.findElement(MultiAcco.paxFname1room1).sendKeys(excel.getData(3, 21, 1));
					Thread.sleep(2000);
					driverqa.findElement(MultiAcco.paxLname1room1).sendKeys(excel.getData(3, 21, 2));
					Select passengertitle = new Select(driverqa.findElement(MultiAcco.paxtitle1room1));
					passengertitle.selectByIndex(1);
					driverqa.findElement(MultiAcco.paxFname2room1).sendKeys(excel.getData(3, 22, 1));
					Thread.sleep(1000);
					driverqa.findElement(MultiAcco.paxLname2room1).sendKeys(excel.getData(3, 22, 2));
					Select passengertitle2 = new Select(driverqa.findElement(MultiAcco.paxtitle2room1));
					passengertitle2.selectByIndex(1);
					driverqa.findElement(MultiAcco.paxFname3room1).sendKeys(excel.getData(3, 23, 1));
					Thread.sleep(1000);
					driverqa.findElement(MultiAcco.paxLname3room1).sendKeys(excel.getData(3, 23, 2));
					Select passengertitle3 = new Select(driverqa.findElement(MultiAcco.paxtitle3room1));
					passengertitle3.selectByIndex(1);
					driverqa.findElement(MultiAcco.paxFname1room2).sendKeys(excel.getData(3, 25, 1));
					Thread.sleep(1000);
					driverqa.findElement(MultiAcco.paxLname1room2).sendKeys(excel.getData(3, 25, 2));
					Select passengertitle4 = new Select(driverqa.findElement(MultiAcco.paxtitle1room2));
					passengertitle4.selectByIndex(1);
					driverqa.findElement(MultiAcco.paxFname2room2).sendKeys(excel.getData(3, 26, 1));
					Thread.sleep(1000);
					driverqa.findElement(MultiAcco.paxLname2room2).sendKeys(excel.getData(3, 26, 2));
					Select passengertitle5 = new Select(driverqa.findElement(MultiAcco.paxtitle2room2));
					passengertitle5.selectByIndex(1);
					driverqa.findElement(NewAccoBooking.acceptChkBX).click();
					driverqa.findElement(MultiAcco.chkboxroom2).click();
					Thread.sleep(2000);
					obj.Takesnap(driverqa, Config.SnapShotPath() + "/Book/Accommodation_Book_Credit_Card_Within_DeadLine/Passenger-Details.jpg");
					logger.info("Entered Passenger details");
					test.log(LogStatus.INFO, "Entered Passenger details");
					test.log(LogStatus.PASS, "Passenger details");
					driverqa.findElement(NewAccoBooking.bookBtn).click();	
					wait.until(ExpectedConditions.visibilityOfElementLocated(NewAccoBooking.confirmBook));
					Thread.sleep(2000);
					obj.Takesnap(driverqa, Config.SnapShotPath() + "/Book/Accommodation_Book_Credit_Card_Within_DeadLine/Proceed-To-Booking.jpg");
					driverqa.findElement(NewAccoBooking.confirmBook).click();
					logger.info("Entering Payment Details");
					test.log(LogStatus.INFO, "Entering Payment Details");
					wait.until(ExpectedConditions.visibilityOfElementLocated(PaymentPage.ccType));
					Select paymentmode = new Select(driverqa.findElement(PaymentPage.ccType));
					paymentmode.selectByIndex(1);
					driverqa.findElement(PaymentPage.ccNum).sendKeys(excel.getData(3, 42, 1));
					driverqa.findElement(PaymentPage.cvv).sendKeys(excel.getData(3, 43, 1));
					driverqa.findElement(PaymentPage.ccName).sendKeys(excel.getData(3, 44, 1));
					Select expiryYear = new Select(driverqa.findElement(PaymentPage.expYear));
					expiryYear.selectByIndex(2);
					Select expirydate = new Select(driverqa.findElement(PaymentPage.expMonth));
					expirydate.selectByIndex(11);
					driverqa.findElement(PaymentPage.zip).sendKeys(excel.getData(3, 41, 1));
					driverqa.findElement(PaymentPage.termsconditions).click();
					Thread.sleep(2000);
					obj.Takesnap(driverqa, Config.SnapShotPath() + "/Book/Accommodation_Book_Credit_Card_Card_Within_DeadLine/Card-Details.jpg");
				    driverqa.findElement(PaymentPage.confirmPayment).click();
				    logger.info("Completed Payment Details");
				    test.log(LogStatus.PASS, "Completed Payment Details");
				    Thread.sleep(2000);
					wait.until(ExpectedConditions.visibilityOfElementLocated(MultiAcco.Hotel));
					Thread.sleep(2000);
					obj.Takesnap(driverqa, Config.SnapShotPath() + "/Book/Accommodation_Book_Credit_Card_Within_DeadLine/booking-Details.jpg");
					String expectedbookingstatus = "Confirmed";
					String actualbookingstatus1 = driverqa.findElement(MultiAcco.roomstatus1).getText();
					System.out.println(actualbookingstatus1);
					String actualbookingstatus2 = driverqa.findElement(MultiAcco.roomstatus2).getText();
					System.out.println(actualbookingstatus2);
					String actualhoteltitle= driverqa.findElement(MultiAcco.Hotel).getText();
					System.out.println(actualhoteltitle);
					String expectedhoteltitle=excel.getData(3, 12, 1);
					String actualStartDateroom1= driverqa.findElement(MultiAcco.Room1CheckIn).getText();
					System.out.println(actualStartDateroom1);
					String expectedStartDate=excel.getData(3, 38, 1);
					String actualEndDateroom1= driverqa.findElement(MultiAcco.Room1CheckOut).getText();
					System.out.println(actualEndDateroom1);
					String actualStartDateroom2= driverqa.findElement(MultiAcco.Room2CheckIn).getText();
					System.out.println(actualStartDateroom2);
					String actualEndDateroom2= driverqa.findElement(MultiAcco.Room2CheckOut).getText();
					System.out.println(actualEndDateroom2);
					String expectedEndDate= excel.getData(3, 39, 1);
					Assert.assertTrue(actualhoteltitle.contains(expectedhoteltitle));
					Assert.assertTrue(actualStartDateroom1.contains(expectedStartDate));
					Assert.assertTrue(actualEndDateroom1.contains(expectedEndDate));
					Assert.assertTrue(actualStartDateroom2.contains(expectedStartDate));
					Assert.assertTrue(actualEndDateroom2.contains(expectedEndDate));
					Assert.assertTrue(actualbookingstatus1.contains(expectedbookingstatus));
					Assert.assertTrue(actualbookingstatus2.contains(expectedbookingstatus));
					test.log(LogStatus.INFO, "Ending HotelBook Credit Card Within Deadline");
				    test.log(LogStatus.PASS, "PASSED HotelBook Credit Card Within Deadline");
				    logger.info("Hotel Book Complete Credit Card Within Deadline");


				} catch (Exception e) {
					logger.info(e.getMessage());
					test.log(LogStatus.FAIL, e.getMessage());
					rep.endTest(test);
					rep.flush();
					Assert.assertTrue(false, e.getMessage());
					test.log(LogStatus.FAIL, "Hotel Book Credit Card Within Deadline");
					obj.Takesnap(driverqa, Config.SnapShotPath() + "/Book/Error/Accommodation_Book_Credit_Card_Within_DeadLine/Save-Itenary.jpg");

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
			//driverqa.close();
		}
	 }
