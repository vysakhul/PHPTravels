package net.phptravels.pages;


import java.io.IOException;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import net.phptravels.utilities.ExcelUtility;
import net.phptravels.utilities.Waiter;

public class SuppAdminLoginPage {
	WebDriver driver;
	
	HomePage objHome;
	
	@FindBy(xpath="//h1")
	WebElement heading;
	
	@FindBy(xpath="//h1[normalize-space()='Dashboard']")
	WebElement dashboardHeading;
	
	@FindBy(xpath="//input[@name='email']")
	WebElement username;
	
	@FindBy(xpath="//input[@name='password']")
	WebElement password;
	
	@FindBy(xpath="//ins[@class='iCheck-helper']")
	WebElement rememberMe;
	
	@FindBy(xpath="//span[@class='ladda-label']")
	WebElement loginBtn;
	
	@FindBy(xpath="//div[@class='resultlogin']/div/p")
	WebElement errUserPass;
	
	@FindBy(xpath="//div[@class='alert alert-danger loading wow fadeIn animated animated']")
	WebElement errInvalid;
	
	@FindBy(id="link-forgot")
	WebElement forgetPass;     // forgot pass link under username pass field
	
	@FindBy(id="ForgetPasswordLabel")
	WebElement forgotWin;    // pop up alert for entering email for getting reset pass
	
	@FindBy(id="resetemail")
	WebElement forgotWinEmail;
	
	@FindBy(xpath="//span[normalize-space()='Close']")
	WebElement forgotWinCloseBtn;
	
	@FindBy(xpath="//span[normalize-space()='Reset Password']")
	WebElement forgotWinResetBtn;
	
	public SuppAdminLoginPage(WebDriver driver)
	{
		this.driver = driver;
		PageFactory.initElements(driver, this);
	}
	
	public String getHeading()
	{
		return heading.getText();
	}
	
	public void setUsername(String str)
	{
		username.sendKeys(str);
	}
	
	public void setPassword(String pass)
	{
		password.sendKeys(pass);
	}
	
	public void clickRemember()
	{
		rememberMe.click();
	}
	
	public void clickLogin()
	{
		loginBtn.click();
	}
	
	public void clickForgetPass()
	{
		forgetPass.click();
	}
	
	public String getPopUpHeading()
	{
		Waiter.waitForEle(driver, forgotWin, 1000);
		return forgotWin.getText();
	}
	
	public void setForgotPassEmail(String repass)
	{
		forgotWinEmail.sendKeys(repass);
	}
	
	public void clickForgotWinClose()
	{
		forgotWinCloseBtn.click();
	}
	
	public void clickResetBtn()
	{
		forgotWinResetBtn.click();
	}
	
	public String getAlertText()
	{
		return driver.switchTo().alert().getText();
	}
	
	public String getUserPassErrorMessage()
	{
		return errUserPass.getText();
	}
	
	public String getInvalidErrorMessage()
	{
		return errInvalid.getText();
	}
	
	public void adminLogin() throws IOException, InterruptedException
	{
		Waiter.waitForEle(driver, loginBtn, 500);
		username.sendKeys(ExcelUtility.getCellData("Logins", 3, 1));
		password.sendKeys(ExcelUtility.getCellData("Logins", 3, 2));
		loginBtn.click();
		Waiter.waitForEle(driver, dashboardHeading, 500);
	}
	
	public void supplierLogin() throws IOException, InterruptedException
	{
		Waiter.waitForEle(driver, loginBtn, 500);
		username.sendKeys(ExcelUtility.getCellData("Logins", 4, 1));
		password.sendKeys(ExcelUtility.getCellData("Logins", 4, 2));
		loginBtn.click();
		Waiter.waitForEle(driver, dashboardHeading, 500);
	}
	
	public void waitForLogin()
	{
		Waiter.waitForEle(driver, dashboardHeading, 500);
	}
	
	public void waitForInvErrText()
	{
		Waiter.waitForEle(driver, errInvalid, 500);
	}
	
	public void waitForErrText()
	{
		Waiter.waitForEle(driver, errUserPass, 500);
	}
}
