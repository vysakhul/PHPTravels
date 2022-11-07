package net.phptravels.pages;

import java.io.IOException;
import java.time.Duration;

import org.openqa.selenium.Alert;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import net.phptravels.utilities.ExcelUtility;
import net.phptravels.utilities.Waiter;

public class UserAgentLoginPage {
	WebDriver driver;
	
	HomePage objHome;
	
	@FindBy(id="ACCOUNT")
	WebElement accountBtn;
	
	@FindBy(xpath="//div[@class='logo']//img[@alt='logo']")
	WebElement home;
	
	@FindBy(xpath="//span[@class='author__meta']")
	WebElement welcomeMsg;
	
	@FindBy(xpath="//input[@placeholder='Email']")
	WebElement username;
	
	@FindBy(xpath="//input[@placeholder='Password']")
	WebElement password;
	
	@FindBy(id="rememberchb")
	WebElement rememberMe;
	
	@FindBy(xpath="//label[normalize-space()='Reset Password']")
	WebElement resetPass;
	
	@FindBy(xpath="//span[normalize-space()='Login']")
	WebElement loginBtn;
	
	@FindBy(xpath="//div[@class='alert alert-danger failed']")
	WebElement invError;
	
	@FindBy(xpath="//a[contains(@class,'btn btn-outline-primary btn-block form-group effect ladda-sm ladda-button waves-effect')]")
	WebElement signupBtn;
	
	@FindBy(xpath="//span[normalize-space()='Reset']")
	WebElement resetPassBtn;
	
	@FindBy(xpath="//input[contains(@placeholder,'your@email.com')]")
	WebElement resetPassEmail;
	
	@FindBy(xpath="//h5[@id='modal']")
	WebElement resetPassHead;
	
	@FindBy(xpath="//button[@aria-label='Close']")
	WebElement resetPassClose;
	
	public UserAgentLoginPage(WebDriver driver)
	{
		this.driver = driver;
		PageFactory.initElements(driver, this);
	}
	
	public void goHome()
	{
		home.click();
	}
	
	public void setUsername(String str)
	{
		username.sendKeys(str);
	}
	
	public void setPassword(String pass)
	{
		password.sendKeys(pass);
	}
	
	public void clickRememberMe()
	{
		rememberMe.click();
	}
	
	public void clickResetPassLink()
	{
		resetPass.click();
	}
	
	public void clickLogin()
	{
		loginBtn.click();
	}
	
	public void clickSignup()
	{
		signupBtn.click();
	}
	
	public void clickResetPassBtn()
	{
		resetPassBtn.click();
	}
	
	public void setResetPassEmail(String em)
	{
		resetPassEmail.sendKeys(em);
	}
	
	public void clickResetPassWinClose()
	{
		resetPassClose.click();
	}
	
	public String getResetPassHeading()
	{
		return resetPassHead.getText();
	}
	
	public String getAlertText()
	{
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(5));
		Alert alert = wait.until(ExpectedConditions.alertIsPresent());
		String text = alert.getText();
		return text;
	}
	
	public String getInvErrorText()
	{
		Waiter.waitForEle(driver, invError, 1500);
		return invError.getText();
	}
	
	public void userLogin() throws IOException, InterruptedException
	{
		Waiter.waitForEle(driver, loginBtn, 500);
		username.sendKeys(ExcelUtility.getCellData("Logins", 1, 1));
		password.sendKeys(ExcelUtility.getCellData("Logins", 1, 2));
		loginBtn.click();
		Waiter.waitForEle(driver, welcomeMsg, 500);
	}
	
	public void agentLogin() throws IOException, InterruptedException
	{
		Waiter.waitForEle(driver, loginBtn, 500);
		username.sendKeys(ExcelUtility.getCellData("Logins", 2, 1));
		password.sendKeys(ExcelUtility.getCellData("Logins", 2, 2));
		loginBtn.click();
		Waiter.waitForEle(driver, welcomeMsg, 500);
	}
	
	public void waitForLogin()
	{
		Waiter.waitForEle(driver, welcomeMsg, 500);
	}
	
	public void waitForPage()
	{
		Waiter.waitForEle(driver, loginBtn, 1500);
	}

}
