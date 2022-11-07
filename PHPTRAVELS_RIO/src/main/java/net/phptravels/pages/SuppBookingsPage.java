package net.phptravels.pages;

import java.util.List;
import java.util.Set;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import net.phptravels.utilities.DropdownListChecker;
import net.phptravels.utilities.PageScroller;
import net.phptravels.utilities.Waiter;

public class SuppBookingsPage {
	
WebDriver driver;

	@FindBy(xpath="//div[contains(text(),'Dashboard')]")  
	WebElement dashboard;
	
	@FindBy(xpath="//select[@name='data_length']")
	WebElement entriesCount;
	
	@FindBy(xpath="//input[@type='search']")
	WebElement searchBox;
	
	@FindBy(xpath="//tbody")
	WebElement table;
	
	@FindBy(xpath="//div[@class='panel-body']//tbody/tr")
	List<WebElement> bookingsTableRows;
	
	@FindBy(xpath="//div[@class='panel-body']//tbody/tr/td[2]")
	List<WebElement> bookingId;
	
	@FindBy(xpath="//div[@class='panel-body']//tbody/tr/td[3]")
	List<WebElement> referenceId;
	
	@FindBy(xpath="//div[@class='panel-body']//tbody/tr/td[4]")
	List<WebElement> customerName;
	
	@FindBy(xpath="//select[@id='booking_status']")
	List<WebElement> bookingStatusDrp;
	
	@FindBy(xpath="//select[@id='payment_status']")
	List<WebElement> paymentStatusDrp;
	
	@FindBy(xpath="//div[@class='panel-body']//tbody/tr/td[13]")
	List<WebElement> executeBtn;
	
	@FindBy(xpath="//div[@class='panel-body']//tbody/tr/td[14]")
	List<WebElement> invoiceBtn;
	
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
	
	
	/////////////////////// INVOICE PAGE //////////////////////////////////
	
	@FindBy(xpath="//h3[@class='title']/span")
	WebElement reservationNum;
	
	///////////////////////////////////////////////////////////////////////
	
	public SuppBookingsPage (WebDriver driver)
	{
		this.driver = driver;
		PageFactory.initElements(driver, this);
	}
	
	public void dashboardNav()
	{
		dashboard.click();
	}
	
	public void confirmedBookingNav()
	{
		confirmedBookingCount.click();
	}
	
	public void pendingBookingNav()
	{
		pendingBookingCount.click();
	}
	
	public void cancelledBookingNav()
	{
		cancelledBookingCount.click();
	}
	
	public void paidBookingNav()
	{
		paidBookingCount.click();
	}
	
	public void unpaidBookingNav()
	{
		unpaidBookingCount.click();
	}
	
	public void refundedBookingNav()
	{
		refundedBookingCount.click();
	}
	
	public void searchfor(String str)
	{
		searchBox.sendKeys(str);
	}
	
	public void setShowEntries(int ent) //10, 25, 50, 100
	{
		String entries = String.valueOf(ent);
		DropdownListChecker.setDropDownto(entriesCount, entries);
	}
	
	public void waitForBookingTable()
	{
		Waiter.waitForEle(driver, table, 500);
	}
	
	
	public int rowFinderByRef(int ref)
	{
		int row = -1;
		for(int i=0; i<referenceId.size(); i++)
		{
			int refid = Integer.parseInt(referenceId.get(i).getText());
			if(refid == ref)
			{
				row = i;
				break;
			}
		}
		System.out.println("Booking with Ref: "+ref+ " found in row: " +row);
		return row;
	}
	
	
	public int rowFinderByName(String name)
	{
		name = name.toLowerCase();
		//System.out.println("rowFinderByName is now running");
		int row = -1;
		for(int i=0; i<customerName.size(); i++)
		{
			//System.out.println("Now checking " + customerName.get(i).getText().toLowerCase() + " with " +name);
			if(customerName.get(i).getText().toLowerCase().equals(name))
			{
				row = i;
				break;
			}
		}
		System.out.println("Booking for "+name+ " found in row: " +row);
		return row;
	}
	
	public int rowFinderByBookingStatus(String stat)
	{
		int row = -1;
		//System.out.println("rowFinderByBookingStatus is now running");
		stat = stat.toLowerCase();
		for(int i=0;i<bookingStatusDrp.size();i++)
		{
			String drpValue = DropdownListChecker.getSelectedDropDownValue(bookingStatusDrp.get(i));
			if(drpValue.equals(stat))
			{
				row = i;
				break;
			}
		}
		System.out.println(stat+" booking found in row: " +row);
		return row;
	}
	
	public int rowFinderByPaymentStatus(String paystat)
	{
		int row = -1;
		paystat = paystat.toLowerCase();
		for(int i=0;i<paymentStatusDrp.size();i++)
		{
			String drpValue = DropdownListChecker.getSelectedDropDownValue(paymentStatusDrp.get(i));
			if(drpValue.equals(paystat))
			{
				row = i;
				break;
			}
		}
		System.out.println(paystat+" booking found in row: " +row);
		return row;
	}
	
	
	public void changeBookingStatusFromTo(String bkStatus, String newBkStatus) throws InterruptedException
	{
		int row = rowFinderByBookingStatus(bkStatus);
		if(row != -1)
		{
		DropdownListChecker.setDropDownto(bookingStatusDrp.get(row), newBkStatus);
		Thread.sleep(2500);
		}
		else
		{
			System.out.println("Not Found");
		}
	}
	
	
	public void openInvoiceByPayStatus(String stat) throws InterruptedException  
	{
		int row = rowFinderByPaymentStatus(stat);
		if(row != -1)
		{
			PageScroller.scrollIntoView(driver, invoiceBtn.get(row));
			invoiceBtn.get(row).click();
		}
		String current = driver.getWindowHandle();
		Set<String> handles = driver.getWindowHandles();
		for(String handle: handles)
		{
			if(!current.equals(handle))
			{
				driver.switchTo().window(handle);
			}
		}	
	}
	
	public Boolean invoiceStatus(String stat) throws InterruptedException //returns true if correct invoice was opened
	{
		int row = rowFinderByPaymentStatus(stat);
		String res1 = referenceId.get(row).getText();
		String res2 = bookingId.get(row).getText();
		String exp_reservation_num = res1+"-"+res2;
		System.out.println("Reservation number should be:"+exp_reservation_num);
		openInvoiceByPayStatus(stat);
		String act_reservation_num = reservationNum.getText().substring(20);
		System.out.println("Reservation number from invoice:"+act_reservation_num);
		if(exp_reservation_num.equals(act_reservation_num))
		{
			return true;
		}
		else
		{
			return false;
		}
		
		
	}

}
