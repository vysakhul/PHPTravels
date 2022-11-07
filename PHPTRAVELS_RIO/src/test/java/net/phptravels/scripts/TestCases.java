package net.phptravels.scripts;


import java.io.IOException;

import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import net.phptravels.constants.AutomationConstants;
import net.phptravels.pages.AdminBookingsPage;
import net.phptravels.pages.AdminDashboardPage;
import net.phptravels.pages.AgentHotelsPage;
import net.phptravels.pages.HomePage;
import net.phptravels.pages.SuppAdminLoginPage;
import net.phptravels.pages.SuppBookingsPage;
import net.phptravels.pages.SupplierDashboardPage;
import net.phptravels.pages.UserAgentAddFundsPage;
import net.phptravels.pages.UserAgentLoginPage;
import net.phptravels.pages.UserAgentProfilePage;
import net.phptravels.pages.UserBookingsPage;
import net.phptravels.pages.UserAgentDashboardPage;
import net.phptravels.utilities.ExcelUtility;
import net.phptravels.utilities.Waiter;

public class TestCases extends TestBase{
	
	HomePage objHome;
	SuppAdminLoginPage objSuAdLogin;
	UserAgentLoginPage objUsAgLogin;
	Waiter waitfor;
	AdminDashboardPage objAdminDash;
	AdminBookingsPage objAdminBook;
	SupplierDashboardPage objSuppDash;
	SuppBookingsPage objSuppBook;
    UserAgentDashboardPage objUsAgDash;
    UserBookingsPage objUserBook;
	AgentHotelsPage objAgHotel;
	UserAgentAddFundsPage objUsAgFund;
	UserAgentProfilePage objUsAgProfile;
	

	
	
	@BeforeMethod
	public void createObj()
	{
		objHome = new HomePage(driver);
		objSuAdLogin = new SuppAdminLoginPage(driver);
		objUsAgLogin = new UserAgentLoginPage(driver);
		objAdminDash = new AdminDashboardPage(driver);
		objAdminBook = new AdminBookingsPage(driver);
		objSuppDash = new SupplierDashboardPage(driver);
		objSuppBook = new  SuppBookingsPage(driver);
		objUsAgDash = new UserAgentDashboardPage(driver);
		objUserBook = new UserBookingsPage(driver);
		objAgHotel = new AgentHotelsPage(driver);
		objUsAgFund = new UserAgentAddFundsPage(driver);
		objUsAgProfile = new UserAgentProfilePage(driver);
	}
	
	
//////////////////////////////////////////////////////////////////////////////////////////////////////////////////
//////////////////////////////////////USER MODULE - STAGE 1//////////////////////////////////////////////////////
	
	
	@Test(priority=1, description= "Check successful login with correct login credentials", groups="user", enabled=true)
	public void verifyUserAccess_S01_REQ01_T1() throws IOException, InterruptedException
	{
		createObj();
		objUsAgLogin.setUsername(ExcelUtility.getCellData("Logins", 1, 1));
		objUsAgLogin.setPassword(ExcelUtility.getCellData("Logins", 1, 2));
		objUsAgLogin.clickLogin();
		objUsAgLogin.waitForLogin();
		String expUrl = AutomationConstants.USER_DASHBOARD;
		String actUrl = driver.getCurrentUrl();
		Assert.assertEquals(actUrl, expUrl);
	}
	
	
	@Test(priority=2, description= "Login attempt with valid email and blank password", groups="user", enabled=true)
	public void verifyUserAccess_S01_REQ01_T2() throws IOException, InterruptedException
	{
		createObj();
		objUsAgLogin.setUsername(ExcelUtility.getCellData("Logins", 1, 1));
		objUsAgLogin.clickLogin();
		String actUrl = driver.getCurrentUrl();
		String expUrl = ExcelUtility.getCellData("Links", 1, 1);
		Assert.assertEquals(actUrl, expUrl);
	}
	
	
	@Test(priority=3, description= "Login attempt with email field blank and any password", groups="user", enabled=true)
	public void verifyUserAccess_S01_REQ01_T3() throws IOException, InterruptedException
	{
		createObj();
		objUsAgLogin.setPassword(ExcelUtility.getCellData("Logins", 1, 2));
		objUsAgLogin.clickLogin();
		String actUrl = driver.getCurrentUrl();
		String expUrl = ExcelUtility.getCellData("Links", 1, 1);
		Assert.assertEquals(actUrl, expUrl);
	}
	
	
	@Test(priority=4, description= "Login attempt with incorrect login credentials", groups="user", enabled=true)
	public void verifyUserAccess_S01_REQ01_T5() throws IOException, InterruptedException
	{
		createObj();
		objUsAgLogin.setUsername(ExcelUtility.getCellData("Logins", 7, 1));
		objUsAgLogin.setPassword(ExcelUtility.getCellData("Logins", 7, 2));
		objUsAgLogin.clickLogin();
		String actMsg = objUsAgLogin.getInvErrorText();
		String expMsg = ExcelUtility.getCellData("Errors", 14, 1);
		Assert.assertEquals(expMsg, actMsg);
	}
	
	
	@Test(priority=5, description= "Test the link My Bookings", groups="user", enabled=true)
	public void checkLinks_S01_REQ02_T1() throws IOException, InterruptedException
	{
		createObj();
		objUsAgLogin.userLogin();
		objUsAgDash.bookingsMenu();
		objUserBook.waitForPageHead();
		String expUrl = ExcelUtility.getCellData("Links", 2, 1);
		String actUrl = driver.getCurrentUrl();
		Assert.assertEquals(expUrl, actUrl);
	}
	
	@Test(priority=6, description= "Test the link Add Funds", groups="user", enabled=true)
	public void checkLinks_S01_REQ02_T2() throws IOException, InterruptedException
	{
		createObj();
		objUsAgLogin.userLogin();
		objUsAgDash.addFundsMenu();
		objUserBook.waitForPageHead();
		String expUrl = ExcelUtility.getCellData("Links", 3, 1);
		String actUrl = driver.getCurrentUrl();
		Assert.assertEquals(expUrl, actUrl);
	}
	
	
	@Test(priority=7, description= "Test the link My Profile", groups="user", enabled=true)
	public void checkLinks_S01_REQ02_T3() throws IOException, InterruptedException
	{
		createObj();
		objUsAgLogin.userLogin();
		objUsAgDash.myProfileMenu();
		objUserBook.waitForPageHead();
		String expUrl = ExcelUtility.getCellData("Links", 4, 1);
		String actUrl = driver.getCurrentUrl();
		Assert.assertEquals(expUrl, actUrl);	
	}
	
	
	@Test(priority=8, description= "Test logout", groups="user", enabled=true)
	public void checkLinks_S01_REQ02_T4() throws IOException, InterruptedException
	{
		createObj();
		objUsAgLogin.userLogin();
		objUsAgDash.logout();
		objUsAgLogin.waitForPage();
		String expUrl = ExcelUtility.getCellData("Links", 1, 1);
		String actUrl = driver.getCurrentUrl();
		Assert.assertEquals(expUrl, actUrl);
	}
	
	@Test(priority=9, description= "Payment via Paypal", groups="user", enabled=true)
	public void checkLinks_S01_REQ03_T1() throws IOException, InterruptedException
	{
		createObj();
		objUsAgLogin.goHome();
		objUsAgDash.clickCurrencyDropDown();
		objUsAgDash.setCurrency(ExcelUtility.getCellData("FieldInputs", 13, 1));
		objHome.waitForPage();
		objHome.clickAcBtn();
		objHome.selectAccount("Customer Login");
		objUsAgLogin.waitForPage();
		objUsAgLogin.userLogin();
		objUsAgDash.addFundsMenu();	
		objUsAgFund.selectPaypal();
		objUsAgFund.setAmount(ExcelUtility.getCellData("FieldInputs", 14, 1));
		objUsAgFund.clickPayNow();
		Boolean status = objUsAgFund.verifyPaymentSettings(ExcelUtility.getCellData("FieldInputs", 12, 1), 
																ExcelUtility.getCellData("FieldInputs", 13, 1), 
																	ExcelUtility.getCellData("FieldInputs", 14, 1));
		Assert.assertEquals(status, true);
	}
	
	
	@Test(priority=10, description= "View voucher", groups="user", enabled=true)
	public void checkLinks_S01_REQ04_T1() throws IOException, InterruptedException
	{
		createObj();
		objUsAgLogin.userLogin();
		objUsAgDash.bookingsMenu();
		objUserBook.openVoucher();
		Boolean status = objUserBook.getInvoiceStatus();
		Assert.assertEquals(true, status);	
	}
	
	 
	@Test(priority=11, description= "Update Profile information", groups="user", enabled=true)
	public void checkLinks_S01_REQ05_T1() throws IOException, InterruptedException
	{
		createObj();
		objUsAgLogin.userLogin();
		objUsAgDash.myProfileMenu();
		objUsAgProfile.setFirstName(ExcelUtility.getCellData("FieldInputs", 18, 1));
		objUsAgProfile.setLastName(ExcelUtility.getCellData("FieldInputs", 19, 1));
		objUsAgProfile.setPhone(ExcelUtility.getCellData("FieldInputs", 20, 1));
		objUsAgProfile.setEmail(ExcelUtility.getCellData("FieldInputs", 21, 1));
		objUsAgProfile.setPassword(ExcelUtility.getCellData("FieldInputs", 22, 1));
		objUsAgProfile.setCountry(ExcelUtility.getCellData("FieldInputs", 23, 1));
		objUsAgProfile.setState(ExcelUtility.getCellData("FieldInputs", 24, 1));
		objUsAgProfile.setCity(ExcelUtility.getCellData("FieldInputs", 25, 1));
		objUsAgProfile.setFax(ExcelUtility.getCellData("FieldInputs", 26, 1));
		objUsAgProfile.setZip(ExcelUtility.getCellData("FieldInputs", 27, 1));
		objUsAgProfile.setAddressLine1(ExcelUtility.getCellData("FieldInputs", 28, 1));
		objUsAgProfile.setAddressLine2(ExcelUtility.getCellData("FieldInputs", 29, 1));
		objUsAgProfile.saveProfile();
		String actStatus =  objUsAgProfile.getUpdateStatus();
		String expStatus = ExcelUtility.getCellData("Errors", 17, 1);
		Assert.assertEquals(expStatus, actStatus);
	}
	
	
	
	
	
//////////////////////////////////////////////////////////////////////////////////////////////////////////////////
//////////////////////////////////////AGENT MODULE - STAGE 2//////////////////////////////////////////////////////
	
	
	@Test(priority=12, description= "Check successful login with correct login credentials", groups="agent", enabled=true)
	public void verifyUserAccess_S02_REQ01_T1() throws IOException, InterruptedException
	{
		createObj();
		objUsAgLogin.setUsername(ExcelUtility.getCellData("Logins", 2, 1));
		objUsAgLogin.setPassword(ExcelUtility.getCellData("Logins", 2, 2));
		objUsAgLogin.clickLogin();
		objUsAgLogin.waitForLogin();
		String expUrl = AutomationConstants.USER_DASHBOARD;
		String actUrl = driver.getCurrentUrl();
		Assert.assertEquals(actUrl, expUrl);
	}
	
	
	@Test(priority=13, description= "Login attempt with valid email and blank password", groups="agent", enabled=true)
	public void verifyUserAccess_S02_REQ01_T2() throws IOException, InterruptedException
	{
		createObj();
		objUsAgLogin.setUsername(ExcelUtility.getCellData("Logins", 2, 1));
		objUsAgLogin.clickLogin();
		String actUrl = driver.getCurrentUrl();
		String expUrl = ExcelUtility.getCellData("Links", 1, 1);
		Assert.assertEquals(actUrl, expUrl);
	}
	
	
	@Test(priority=14, description= "Login attempt with email field blank and any password", groups="agent", enabled=true)
	public void verifyUserAccess_S02_REQ01_T3() throws IOException, InterruptedException
	{
		createObj();
		objUsAgLogin.setPassword(ExcelUtility.getCellData("Logins", 2, 2));
		objUsAgLogin.clickLogin();
		String actUrl = driver.getCurrentUrl();
		String expUrl = ExcelUtility.getCellData("Links", 1, 1);
		Assert.assertEquals(actUrl, expUrl);
	}
	
	
	
	@Test(priority=15, description= "Login attempt with incorrect login credentials", groups="agent", enabled=true)
	public void verifyUserAccess_S02_REQ01_T5() throws IOException, InterruptedException
	{
		createObj();
		objUsAgLogin.setUsername(ExcelUtility.getCellData("Logins", 7, 1));
		objUsAgLogin.setPassword(ExcelUtility.getCellData("Logins", 7, 2));
		objUsAgLogin.clickLogin();
		String actMsg = objUsAgLogin.getInvErrorText();
		String expMsg = ExcelUtility.getCellData("Errors", 14, 1);
		Assert.assertEquals(expMsg, actMsg);
	}
	
	
	@Test(priority=16, description= "Test the link My Bookings", groups="agent", enabled=true)
	public void checkLinks_S02_REQ02_T1() throws IOException, InterruptedException
	{
		createObj();
		objUsAgLogin.agentLogin();
		objUsAgDash.bookingsMenu();
		objUserBook.waitForPageHead();
		String expUrl = ExcelUtility.getCellData("Links", 2, 1);
		String actUrl = driver.getCurrentUrl();
		Assert.assertEquals(expUrl, actUrl);
	}
	
	@Test(priority=17, description= "Test the link Add Funds", groups="agent", enabled=true)
	public void checkLinks_S02_REQ02_T2() throws IOException, InterruptedException
	{
		createObj();
		objUsAgLogin.agentLogin();
		objUsAgDash.addFundsMenu();
		objUserBook.waitForPageHead();
		String expUrl = ExcelUtility.getCellData("Links", 3, 1);
		String actUrl = driver.getCurrentUrl();
		Assert.assertEquals(expUrl, actUrl);
	}
	
	
	@Test(priority=18, description= "Test the link My Profile", groups="agent", enabled=true)
	public void checkLinks_S02_REQ02_T3() throws IOException, InterruptedException
	{
		createObj();
		objUsAgLogin.agentLogin();
		objUsAgDash.myProfileMenu();
		objUserBook.waitForPageHead();
		String expUrl = ExcelUtility.getCellData("Links", 4, 1);
		String actUrl = driver.getCurrentUrl();
		Assert.assertEquals(expUrl, actUrl);	
	}
	
	
	@Test(priority=19, description= "Test logout", groups="agent", enabled=true)
	public void checkLinks_S02_REQ02_T4() throws IOException, InterruptedException
	{
		createObj();
		objUsAgLogin.agentLogin();
		objUsAgDash.logout();
		objUsAgLogin.waitForPage();
		String expUrl = ExcelUtility.getCellData("Links", 1, 1);
		String actUrl = driver.getCurrentUrl();
		Assert.assertEquals(expUrl, actUrl);
	}
	
	
	@Test(priority=20, description= "Check Home link from navigation menu items", groups="agent", enabled=true)
	public void checkLinks_S02_REQ03_T1() throws IOException, InterruptedException
	{
		createObj();
		objUsAgLogin.agentLogin();
		objUsAgDash.homeNav();
		String expUrl = ExcelUtility.getCellData("Links", 5, 1);
		String actUrl = driver.getCurrentUrl();
		Assert.assertEquals(expUrl, actUrl);
	}
	
	
	@Test(priority=21, description= "Check Hotel link from navigation menu items", groups="agent", enabled=true)
	public void checkLinks_S02_REQ03_T2() throws IOException, InterruptedException
	{
		createObj();
		objUsAgLogin.agentLogin();
		objUsAgDash.hotelsNav();
		String expUrl = ExcelUtility.getCellData("Links", 6, 1);
		String actUrl = driver.getCurrentUrl();
		Assert.assertEquals(expUrl, actUrl);
	}
	
	
	@Test(priority=22, description= "Check Flights link from navigation menu items", groups="agent", enabled=true)
	public void checkLinks_S02_REQ03_T3() throws IOException, InterruptedException
	{
		createObj();
		objUsAgLogin.agentLogin();
		objUsAgDash.flightsNav();
		String expUrl = ExcelUtility.getCellData("Links", 7, 1);
		String actUrl = driver.getCurrentUrl();
		Assert.assertEquals(expUrl, actUrl);
	}
	
	
	@Test(priority=23, description= "Check Tours link from navigation menu items", groups="agent", enabled=true)
	public void checkLinks_S02_REQ03_T4() throws IOException, InterruptedException
	{
		createObj();
		objUsAgLogin.agentLogin();
		objUsAgDash.toursNav();
		String expUrl = ExcelUtility.getCellData("Links", 8, 1);
		String actUrl = driver.getCurrentUrl();
		Assert.assertEquals(expUrl, actUrl);
	}
	
	
	@Test(priority=24, description= "Check Visa link from navigation menu items", groups="agent", enabled=true)
	public void checkLinks_S02_REQ03_T5() throws IOException, InterruptedException
	{
		createObj();
		objUsAgLogin.agentLogin();
		objUsAgDash.visaNav();
		String expUrl = ExcelUtility.getCellData("Links", 10, 1);
		String actUrl = driver.getCurrentUrl();
		Assert.assertEquals(expUrl, actUrl);
	}
	
	
	@Test(priority=25, description= "Check Blog link from navigation menu items", groups="agent", enabled=true)
	public void checkLinks_S02_REQ03_T6() throws IOException, InterruptedException
	{
		createObj();
		objUsAgLogin.agentLogin();
		objUsAgDash.blogNav();
		String expUrl = ExcelUtility.getCellData("Links", 11, 1);
		String actUrl = driver.getCurrentUrl();
		Assert.assertEquals(expUrl, actUrl);
	}
	
	
	@Test(priority=26, description= "Check Offers link from navigation menu items", groups="agent", enabled=true)
	public void checkLinks_S02_REQ03_T7() throws IOException, InterruptedException
	{
		createObj();
		objUsAgLogin.agentLogin();
		objUsAgDash.offersNav();
		String expUrl = ExcelUtility.getCellData("Links", 12, 1);
		String actUrl = driver.getCurrentUrl();
		Assert.assertEquals(expUrl, actUrl);
	}
	
	
	@Test(priority=27, description= "Search for hotel and display result", groups="agent", enabled=true)
	public void checkHotelSearch_S02_REQ04_T1() throws IOException, InterruptedException
	{
		createObj();
		objUsAgLogin.agentLogin();
		objUsAgDash.hotelsNav();
		objAgHotel.setCity(ExcelUtility.getCellData("FieldInputs", 2, 1));
		objAgHotel.setCheckin(ExcelUtility.getCellData("FieldInputs", 3, 1));
		objAgHotel.setCheckout(ExcelUtility.getCellData("FieldInputs", 4, 1));
		objAgHotel.setTravellers(ExcelUtility.getCellData("FieldInputs", 5, 1), 
									ExcelUtility.getCellData("FieldInputs", 6, 1), 
										ExcelUtility.getCellData("FieldInputs", 7, 1),
											ExcelUtility.getCellData("FieldInputs", 8, 1));
		objAgHotel.clickSearch();
		Boolean actResult = objAgHotel.getHotelLocation(ExcelUtility.getCellData("FieldInputs", 2, 1));
		Assert.assertEquals(true, actResult);	
	}
	
	
	@Test(priority=28, description= "Change currency from USD to INR", groups="agent", enabled=true)
	public void changeCurrency_S02_REQ05_T1() throws IOException, InterruptedException
	{
		createObj();
		objUsAgLogin.agentLogin();
		objUsAgDash.clickCurrencyDropDown();
		objUsAgDash.setCurrency("INR");
		objUsAgDash.waitForPage();
		String actCurr = objUsAgDash.getCurrentCurrency();
		String expCurr = "INR";
		Assert.assertEquals(actCurr, expCurr);
	}

	
//////////////////////////////////////////////////////////////////////////////////////////////////////////////////
////////////////////////////////////// ADMIN MODULE - STAGE 3/////////////////////////////////////////////////////
	

	
	@Test(priority=29, description= "Check successful login with correct login credentials", groups="admin", enabled=true)
	public void verifyAdminAccess_S3_REQ01_T1() throws IOException, InterruptedException
	{
		createObj();
		objSuAdLogin.setUsername(ExcelUtility.getCellData("Logins", 3, 1));
		objSuAdLogin.setPassword(ExcelUtility.getCellData("Logins", 3, 2));
		objSuAdLogin.clickLogin();
		objSuAdLogin.waitForLogin();
		String actTitle = driver.getTitle();
		String expTitle = AutomationConstants.ADMIN_HOME_TITLE;
		Assert.assertEquals(expTitle, actTitle);
	}
	
	@Test(priority=30, description = "Login attempt with valid email and blank password", groups="admin", enabled=true)
	public void verifyAdminAccess_S3_REQ01_T2() throws IOException, InterruptedException
	{
		createObj();
		objSuAdLogin.setUsername(ExcelUtility.getCellData("Logins", 3, 1));
		objSuAdLogin.clickLogin();
		objSuAdLogin.waitForErrText();
		String actError = objSuAdLogin.getUserPassErrorMessage();
		String expError = ExcelUtility.getCellData("Errors", 1, 1);
		Assert.assertEquals(actError, expError);
	}
	
	@Test(priority=31, description = "Login attempt with email blank and any password", groups="admin", enabled=true)
	public void verifyAdminAccess_S3_REQ01_T3() throws IOException, InterruptedException
	{
		createObj();
		objSuAdLogin.setPassword(ExcelUtility.getCellData("Logins", 3, 2));
		objSuAdLogin.clickLogin();
		objSuAdLogin.waitForErrText();
		String actError = objSuAdLogin.getUserPassErrorMessage();
		String expError = ExcelUtility.getCellData("Errors", 2, 1);
		Assert.assertEquals(actError, expError);
	}
	
	@Test(priority=32, description = "Login attempt with correct mail and incorrect password", groups="admin", enabled=true)
	public void verifyAdminAccess_S3_REQ01_T4() throws IOException, InterruptedException
	{
		createObj();
		objSuAdLogin.setUsername(ExcelUtility.getCellData("Logins", 3, 1));
		objSuAdLogin.setPassword(ExcelUtility.getCellData("Logins", 7, 2));
		objSuAdLogin.clickLogin();
		objSuAdLogin.waitForInvErrText();
		String actError = objSuAdLogin.getInvalidErrorMessage();
		String expError = ExcelUtility.getCellData("Errors", 3, 1);
		Assert.assertEquals(actError, expError);
	}
	
	
	@Test(priority=33, description = "Login attempt with invalid mail and password", groups="admin", enabled=true)
	public void verifyAdminAccess_S3_REQ01_T5() throws IOException, InterruptedException
	{
		createObj();
		objSuAdLogin.setUsername(ExcelUtility.getCellData("Logins", 8, 1));
		objSuAdLogin.setPassword(ExcelUtility.getCellData("Logins", 7, 2));
		objSuAdLogin.clickLogin();
		objSuAdLogin.waitForInvErrText();
		String actError = objSuAdLogin.getInvalidErrorMessage();
		String expError = ExcelUtility.getCellData("Errors", 4, 1);
		Assert.assertEquals(actError, expError);
	}
	
	
	@Test(priority=34, description= "Verify if the forget password option is functional", groups="admin", enabled=true)
	public void verifyForgetPass_S3_REQ01_T6() throws InterruptedException, IOException
	{
		createObj();
		objSuAdLogin.clickForgetPass();
		//Thread.sleep(1000);
		String expHead = AutomationConstants.ADMIN_FORGOT_PASS;
		String actHead = objSuAdLogin.getPopUpHeading();
		Assert.assertEquals(actHead, expHead);
		objSuAdLogin.clickResetBtn();
		String actPoptext = objSuAdLogin.getAlertText();
		String expPoptext = ExcelUtility.getCellData("Errors", 7, 1);
		Assert.assertEquals(expPoptext, actPoptext);
		driver.switchTo().alert().accept();
		objSuAdLogin.setForgotPassEmail(ExcelUtility.getCellData("Logins", 7, 1));
		objSuAdLogin.clickForgotWinClose();
		expHead = AutomationConstants.ADMIN_LOGIN_HEAD;
		actHead = objSuAdLogin.getHeading();
		Assert.assertEquals(expHead, actHead);	
	}
	
	
	@Test(priority=35, description= "Access booking module and display invoice of paid booking", groups="admin", enabled=true)
	public void displayInvoice_S3_REQ02_T1() throws InterruptedException, IOException
	{
		createObj();
		objSuAdLogin.adminLogin();
		objAdminDash.BookingsNav();
		objAdminBook.waitForBookingTable();
		String expTitle = AutomationConstants.ADMIN_BOOKINGS_TITLE;
		String actTitle = driver.getTitle();
		Assert.assertEquals(actTitle, expTitle);
		Boolean actStatus = objAdminBook.invoiceStatus("Paid");
		Assert.assertEquals(actStatus, true);
	}
	
	@Test(priority=36, description= "Access booking module and delete cancelled booking", groups="admin", enabled=true)
	public void deleteCancelledBooking_S3_REQ03_T1() throws InterruptedException, IOException
	{
		createObj();
		objSuAdLogin.adminLogin();
		objAdminDash.BookingsNav();
		objAdminBook.waitForBookingTable();
		int cancelled_count_before = objAdminBook.getBookingCount("Cancelled");
		if(cancelled_count_before == 0)
		{
			System.out.println("There are no Cancelled Bookings to delete from list");
		}
		else
		{
			objAdminBook.deleteByBookingStatus("Cancelled");
		}
		int cancelled_count_after = objAdminBook.getBookingCount("Cancelled");
		Boolean status = false;
		if(cancelled_count_after == cancelled_count_before - 1)
		{
			status = true;
		}
		Assert.assertEquals(status, true);	
	}
	
	
	@Test(priority=37, description= "Change booking status from Pending to Confirmed and verify in dashboard", groups="admin", enabled=true)
	public void changeBookingStatus_S3_REQ04_T1() throws InterruptedException, IOException
	{
		createObj();
		objSuAdLogin.adminLogin();
		objAdminDash.BookingsNav();
		objAdminBook.waitForBookingTable();
		int pending_count_before = objAdminBook.getBookingCount("Pending");
		int confirmed_count_before = objAdminBook.getBookingCount("Confirmed");
		if(pending_count_before == 0)
		{
			System.out.println("There are no Pending Bookings on the list");
		}
		else
		{
			objAdminBook.changeBookingStatusFromTo("Pending", "Confirmed");
		}
		int pending_count_after = objAdminBook.getBookingCount("Pending");
		int confirmed_count_after = objAdminBook.getBookingCount("Confirmed");
		Boolean status = false;
		if(pending_count_after == pending_count_before-1 && confirmed_count_after == confirmed_count_before+1)
		{
			status = true;
		}
		Assert.assertEquals(status, true);
	}
	
	
	@Test(priority=38, description= "Check website link and redirection", groups="admin", enabled=true)
	public void verifyLink_S3_REQ05_T1() throws IOException, InterruptedException
	{
		createObj();
		objSuAdLogin.adminLogin();
		objAdminDash.WebsiteNavAndSwitch();
		objHome.waitForPage();
		String actUrl = driver.getCurrentUrl();
		String expUrl = AutomationConstants.HOME_URL;
		Assert.assertEquals(expUrl, actUrl);
		
	}
	
	

//////////////////////////////////////////////////////////////////////////////////////////////////////////////////
//////////////////////////////////////SUPPLIER MODULE - STAGE 4/////////////////////////////////////////////////////
		
	
	@Test(priority=39, description= "Check successful login with correct login credentials", groups="supplier", enabled=true)
	public void verifySupplierAccess_S4_REQ01_T1() throws IOException, InterruptedException
	{
		createObj();
		objSuAdLogin.setUsername(ExcelUtility.getCellData("Logins", 4, 1));
		objSuAdLogin.setPassword(ExcelUtility.getCellData("Logins", 4, 2));
		objSuAdLogin.clickLogin();
		objSuAdLogin.waitForLogin();
		String actTitle = driver.getTitle();
		String expTitle = AutomationConstants.SUPPLIER_HOME_TITLE;
		Assert.assertEquals(expTitle, actTitle);
	}
	
	
	@Test(priority=40, description = "Login attempt with valid email and blank password", groups="supplier", enabled=true)
	public void verifySupplierAccess_S4_REQ01_T2() throws IOException, InterruptedException
	{
		createObj();
		objSuAdLogin.setUsername(ExcelUtility.getCellData("Logins", 4, 1));
		objSuAdLogin.clickLogin();
		objSuAdLogin.waitForErrText();
		String actError = objSuAdLogin.getUserPassErrorMessage();
		String expError = ExcelUtility.getCellData("Errors", 1, 1);
		Assert.assertEquals(actError, expError);
	}
	
	@Test(priority=41, description = "Login attempt with email blank and any password", groups="supplier", enabled=true)
	public void verifySupplierAccess_S4_REQ01_T3() throws IOException, InterruptedException
	{
		createObj();
		objSuAdLogin.setPassword(ExcelUtility.getCellData("Logins", 4, 2));
		objSuAdLogin.clickLogin();
		objSuAdLogin.waitForErrText();
		String actError = objSuAdLogin.getUserPassErrorMessage();
		String expError = ExcelUtility.getCellData("Errors", 2, 1);
		Assert.assertEquals(actError, expError);
	}
	
	@Test(priority=42, description = "Login attempt with correct mail and incorrect password", groups="supplier", enabled=true)
	public void verifySupplierAccess_S4_REQ01_T4() throws IOException, InterruptedException
	{
		createObj();
		objSuAdLogin.setUsername(ExcelUtility.getCellData("Logins", 4, 1));
		objSuAdLogin.setPassword(ExcelUtility.getCellData("Logins", 7, 2));
		objSuAdLogin.clickLogin();
		objSuAdLogin.waitForInvErrText();
		String actError = objSuAdLogin.getInvalidErrorMessage();
		String expError = ExcelUtility.getCellData("Errors", 3, 1);
		Assert.assertEquals(actError, expError);
	}
	
	
	@Test(priority=43, description = "Login attempt with invalid mail and password", groups="supplier", enabled=true)
	public void verifySupplierAccess_S4_REQ01_T5() throws IOException, InterruptedException
	{
		createObj();
		objSuAdLogin.setUsername(ExcelUtility.getCellData("Logins", 8, 1));
		objSuAdLogin.setPassword(ExcelUtility.getCellData("Logins", 7, 2));
		objSuAdLogin.clickLogin();
		objSuAdLogin.waitForInvErrText();
		String actError = objSuAdLogin.getInvalidErrorMessage();
		String expError = ExcelUtility.getCellData("Errors", 4, 1);
		Assert.assertEquals(actError, expError);
	}
	
	
	@Test(priority=44, description = "Check the dashboard for the text 'Sales overview and summary'", groups="supplier", enabled=true)
	public void verifyDashboardHead_S4_REQ02_T1() throws IOException, InterruptedException
	{
		createObj();
		objSuAdLogin.supplierLogin();
		String actHead = objSuppDash.getSalesHeading();
		String expHead = AutomationConstants.SUPPLIER_SALES_HEADING;
		Assert.assertEquals(expHead, actHead);
	}
	
	@Test(priority=45, description = "Check the dashboard for Revenue break down", groups="supplier", enabled=true)
	public void checkRevenueBreakdown_S4_REQ03_T1() throws IOException, InterruptedException
	{
		createObj();
		objSuAdLogin.supplierLogin();
		Boolean actStatus = objSuppDash.statusOfRevChart();
		Assert.assertEquals(true, actStatus);
	}
	
	
	@Test(priority=46, description = "Change booking status from Pending to confirmed and verify change", groups="supplier", enabled=true)
	public void changeBookingStatus_S4_REQ04_T1() throws IOException, InterruptedException
	{
		createObj();
		objSuAdLogin.supplierLogin();
		int pending_count_before = objSuppDash.getBookingCount("Pending");
		int confirmed_count_before = objSuppDash.getBookingCount("Confirmed");
		if(pending_count_before == 0)
		{
			System.out.println("There are no Pending Bookings on the list");
		}
		else
		{
			objSuppDash.pendingBookingsNav();
			objSuppBook.waitForBookingTable();
			objSuppBook.changeBookingStatusFromTo("Pending", "Confirmed");
			objSuppBook.dashboardNav();
			objSuppDash.waitforDash();
		}
		int pending_count_after = objSuppDash.getBookingCount("Pending");
		int confirmed_count_after = objSuppDash.getBookingCount("Confirmed");
		Boolean status = false;
		if(pending_count_after == pending_count_before-1 && confirmed_count_after == confirmed_count_before+1)
		{
			status = true;
		}
		Assert.assertEquals(status, true);
		
	}
	
	
	@Test(priority=47, description = "Check if Flights, Visa, Tours, Bookings module is displayed and clickable", groups="supplier", enabled=true)
	public void checkLinks_S4_REQ05_T1() throws IOException, InterruptedException
	{
		createObj();
		objSuAdLogin.supplierLogin();
		Boolean stat1 =objSuppDash.statusOfFlights();
		Boolean stat2 =objSuppDash.statusOfVisa();
		Boolean stat3 =objSuppDash.statusOfTours();
		Boolean stat4 =objSuppDash.statusOfBookings();
		Assert.assertEquals(stat1, true);
		Assert.assertEquals(stat2, true);
		Assert.assertEquals(stat3, true);
		Assert.assertEquals(stat4, true);
	}
	
	
	
	
	
	
	@AfterMethod
	public void close()
	{
		driver.close();
	}

}
