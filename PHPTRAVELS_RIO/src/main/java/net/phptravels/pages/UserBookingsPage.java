package net.phptravels.pages;

import java.util.Set;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import net.phptravels.utilities.Waiter;

public class UserBookingsPage {
	
	WebDriver driver;
	
	@FindBy(xpath="//h3[@class = 'title']")
	WebElement pageHead;
	
	@FindBy(xpath="//tbody/tr[1]/td[4]/div[1]/a[1]")
	WebElement voucher;
	
	@FindBy(xpath="//h3[contains(text(),'Invoice')]")
	WebElement invoiceHead;
	
	
	public UserBookingsPage(WebDriver driver)
	{
		this.driver = driver;
		PageFactory.initElements(driver, this);
	}
	
	public void waitForPageHead()
	{
		Waiter.waitForEle(driver, pageHead, 1500);
	}
	
	public void openVoucher()
	{
		voucher.click();
		String current = driver.getWindowHandle();
		Set<String> handles = driver.getWindowHandles();
		driver.close();
		for(String handle: handles)
		{
			if(!current.equals(handle))
			{
				driver.switchTo().window(handle);
			}
		}
		
	}
	
	public Boolean getInvoiceStatus()
	{
		Waiter.waitForEle(driver, invoiceHead, 1000);
		String head = invoiceHead.getText().substring(0,15);
		//System.out.println("Text found: "+invoiceHead.getText().substring(0,15));
		if(head.equals("Booking Invoice"))
		{
			return true;
		}
		else
		{
			return false;
		}
	}

}
