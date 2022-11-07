package net.phptravels.pages;

import java.util.Set;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class AdminDashboardPage {
	
	WebDriver driver;
	
	@FindBy(xpath="//a[normalize-space()='']")
	WebElement dashboard;
	
	@FindBy(xpath="//a[normalize-space()='Website']")
	WebElement website; 
	
	@FindBy(xpath="//a[@class='nav-link loadeffect']")
	WebElement bookings;
	
	@FindBy(xpath="//a[normalize-space()='Docs']")
	WebElement docs;
	
	@FindBy(xpath="//h1")
	WebElement dashboardHeading;
	
	@FindBy(xpath="//div[@class='text-muted']")
	WebElement salesHeading;
	
	@FindBy(xpath="//div[contains(text(),'Confrimed Bookings')]//preceding-sibling::div")
	WebElement confirmedBookingCount;
	
	@FindBy(xpath="//div[contains(text(),'Pending Bookings')]//preceding-sibling::div")
	WebElement pendingBookingCount;
	
	@FindBy(xpath="//div[contains(text(),'Cancelled Bookings')]//preceding-sibling::div")
	WebElement cancelledBookingCount;
	
	@FindBy(xpath="//div[contains(text(),'Paid Bookings')]//preceding-sibling::div")
	WebElement paidBookingCount;
	
	@FindBy(xpath="//div[contains(text(),'Unpaid Bookings')]//preceding-sibling::div")
	WebElement unpaidBookingCount;
	
	@FindBy(xpath="//div[contains(text(),'Refunded Bookings')]//preceding-sibling::div")
	WebElement refundedBookingCount;
	
	@FindBy(id="dashboardBarChart")
	WebElement revenueChart;
	
	public AdminDashboardPage(WebDriver driver)
	{
		this.driver = driver;
		PageFactory.initElements(driver, this);
	}
	
	public void DashboardNav()
	{
		dashboard.click();
	}
	
	public void WebsiteNavAndSwitch()
	{
		website.click();
		String parentWinHandle = driver.getWindowHandle();
		Set<String> winHandles = driver.getWindowHandles();
		//driver.close();
		if(winHandles.size()>1)
		{
			for(String winHandle: winHandles)
			{
				if(winHandle != parentWinHandle)
				{
					driver.switchTo().window(winHandle);
				}
			}
		}
	}
	
	public void BookingsNav()
	{
		bookings.click();
	}
	
	public void docsNav()
	{
		docs.click();
	}
	
	public String getDashHeading()
	{
		return dashboardHeading.getText();
	}
	
	public String getSalesHeading()
	{
		return salesHeading.getText();
	}
	
	public String num_of_ConfirmedBookings()
	{
		return confirmedBookingCount.getText();
	}
	
	public void confirmedBookingsNav()
	{
		confirmedBookingCount.click();
	}
	
	public String num_of_PendingBookings()
	{
		return pendingBookingCount.getText();
	}
	
	public void pendingBookingsNav()
	{
		pendingBookingCount.click();
	}
	
	public String num_of_CancelledBookings()
	{
		return cancelledBookingCount.getText();
	}
	
	public void cancelledBookingsNav()
	{
		cancelledBookingCount.click();
	}
	
	public String num_of_PaidBookings()
	{
		return paidBookingCount.getText();
	}
	
	public void paidBookingsNav()
	{
		paidBookingCount.click();
	}
	
	public String num_of_UnpaidBookings()
	{
		return unpaidBookingCount.getText();
	}
	
	public void unpaidBookingsNav()
	{
		unpaidBookingCount.click();
	}
	
	public String num_of_RefundedBookings()
	{
		return refundedBookingCount.getText();
	}
	
	public void refundedBookingsNav()
	{
		refundedBookingCount.click();
	}
	
	public Boolean statusOfRevChart()
	{
		return revenueChart.isDisplayed();
	}
	
	

}
