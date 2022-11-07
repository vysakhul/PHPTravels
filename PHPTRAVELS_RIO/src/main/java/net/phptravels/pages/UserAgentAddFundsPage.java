package net.phptravels.pages;

import java.util.Set;

import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import net.phptravels.utilities.Waiter;

public class UserAgentAddFundsPage {
	
	WebDriver driver;
	
	@FindBy(id="gateway_razorpay")
	WebElement razor;
	
	@FindBy(id="gateway_stripe")
	WebElement stripe;
	
	@FindBy(id="gateway_paypal")
	WebElement paypal;
	
	@FindBy(id="gateway_bank-transfer")
	WebElement banktf;
	
	@FindBy(xpath="//input[@name='price']")
	WebElement amountBox;
	
	@FindBy(xpath="//button[contains(text(), 'Pay Now')]")
	WebElement payNowBtn;
	
	@FindBy(xpath="//*[@id=\"jsx-iframe-d8097297f5\"]")
	WebElement paypalBtn;
	
	@FindBy(xpath="//h1[@id='headerText']")
	WebElement paypalHead;
	
	@FindBy(xpath="//strong[contains(text(),'paypal')]")
	WebElement paymentSetting;
	
	public UserAgentAddFundsPage(WebDriver driver)
	{
		this.driver = driver;
		PageFactory.initElements(driver, this);
	}
	
	public void selectPaypal()
	{
		paypal.click();
	}
	
	public void selectRazorPay()
	{
		razor.click();
	}
	
	public void selectStripe()
	{
		stripe.click();
	}
	
	public void selectBankTf()
	{
		banktf.click();
	}
	
	public void setAmount(String amt)
	{
		amountBox.clear();
		amountBox.sendKeys(amt);
	}
	
	public void clickPayNow()
	{
		payNowBtn.click();
	}
	
	public void clickPayPal()
	{
		JavascriptExecutor executor = (JavascriptExecutor)driver;
		executor.executeScript("arguments[0].click();", paypalBtn);
		//paypalBtn.click();
	}
	
	public void closeAndSwitch()
	{
		String parent = driver.getWindowHandle();
		Set<String> wins = driver.getWindowHandles();
		//driver.close();
		for(String win: wins)
		{
			if(win!=parent)
			{
				driver.switchTo().window(win);
			}
		}
	}
	
	public void getPaymentHomeLink()
	{
		Waiter.waitForEle(driver, paypalHead, 1500);
		String url = driver.getCurrentUrl();
		String[] homeUrl = url.split("/", 22);
		for(String a: homeUrl)
		{
			System.out.println(a);
		}
		
		
	}
	
	public Boolean verifyPaymentSettings(String mod, String cu, String am)
	{
		String[] setting = paymentSetting.getText().split(" ");
		String payMode = setting[0];
		String curr = setting[1];
		String amt = setting[2];
		if(payMode.equals(mod) && curr.equals(cu) && amt.equals(am))
		{
			return true;
		}
		else
		{
			return false;
		}
	}

}
