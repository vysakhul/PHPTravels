package net.phptravels.pages;

import java.util.List;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import net.phptravels.constants.AutomationConstants;
import net.phptravels.utilities.Waiter;

public class SupplierDashboardPage {
	
WebDriver driver;
	
	@FindBy(xpath="//div[contains(text(),'Dashboard')]")  
	WebElement dashboard;
	
	@FindBy(xpath="//a[normalize-space()='Website']")
	WebElement website; 
	
	@FindBy(xpath="//a[@class='nav-link loadeffect']")
	WebElement bookings;
	
	@FindBy(xpath="//a[normalize-space()='Docs']")
	WebElement docs;
	
	@FindBy(xpath="//h1[contains(text(), 'Dash')]")
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
	
	@FindBy(xpath="//a[@aria-controls='carsmodule']")
	WebElement carsMenu;
	
	@FindBy(xpath="//div[@id='carsmodule']/nav/a")
	List<WebElement> cars_menu_items;
	
	@FindBy(xpath="//a[@aria-controls='flightsmodule']")
	WebElement flightsMenu;
	
	@FindBy(xpath="//div[@id='flightsmodule']/nav/a")
	List<WebElement> flights_menu_items;
	
	@FindBy(xpath="//a[@aria-controls='hotelsmodule']")
	WebElement hotelsMenu;
	
	@FindBy(xpath="//div[@id='hotelsmodule']/nav/a")
	List<WebElement> hotels_menu_items;
	
	@FindBy(xpath="//a[@aria-controls='visamodule']")
	WebElement visaMenu;
	
	@FindBy(xpath="//div[@id='visamodule']/nav/a")
	List<WebElement> visa_menu_items;
	
	@FindBy(xpath="//a[@aria-controls='toursmodule']")
	WebElement toursMenu;
	
	@FindBy(xpath="//div[@id='toursmodule']/nav/a")
	List<WebElement> tours_menu_items;
	
	@FindBy(xpath="//div[@class='drawer-menu-divider'][2]//following-sibling::a")
	WebElement bookingsMenu;
	
	
	public SupplierDashboardPage(WebDriver driver)
	{
		this.driver = driver;
		PageFactory.initElements(driver, this);
	}
	
	public void DashboardNav()
	{
		dashboard.click();
	}
	
	public void WebsiteNav()
	{
		website.click();
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
	
	public void bookingsMenu()
	{
		bookingsMenu.click();
	}
	
	public String num_of_ConfirmedBookings()
	{
		System.out.println("confirmedBookingCount: "+confirmedBookingCount.getText());
		return confirmedBookingCount.getText();
	}
	
	public void confirmedBookingsNav()
	{
		confirmedBookingCount.click();
	}
	
	public String num_of_PendingBookings()
	{
		System.out.println("pendingBookingCount: "+pendingBookingCount.getText());
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
	
	
	public Boolean checkCarsMenuItems()
	{
		int counter = 0;
		if(cars_menu_items.size() == AutomationConstants.CARS_MENU_ITEMS.length)
		{
			for(WebElement item: cars_menu_items)
			{
				for(int i=0;i<AutomationConstants.CARS_MENU_ITEMS.length;i++)
				{
					if(item.getText().equals(AutomationConstants.CARS_MENU_ITEMS[i]))
					{
						counter=counter+1;
					}
				}
			}
		}
		else
		{
			return false;
		}
		
		if(counter == cars_menu_items.size())
		{
			return true;
		}
		else
		{
			return false;
		}
	}
	
	public Boolean checkFlightsMenuItems()
	{
		int counter = 0;
		if(flights_menu_items.size() == AutomationConstants.FLIGHTS_MENU_ITEMS.length)
		{
			for(WebElement item: flights_menu_items)
			{
				for(int i=0;i<AutomationConstants.FLIGHTS_MENU_ITEMS.length;i++)
				{
					if(item.getText().equals(AutomationConstants.FLIGHTS_MENU_ITEMS[i]))
					{
						counter=counter+1;
					}
				}
			}
		}
		else
		{
			return false;
		}
		
		if(counter == flights_menu_items.size())
		{
			return true;
		}
		else
		{
			return false;
		}
	}
	
	
	public Boolean checkHotelsMenuItems()
	{
		int counter = 0;
		if(hotels_menu_items.size() == AutomationConstants.HOTELS_MENU_ITEMS.length)
		{
			for(WebElement item: hotels_menu_items)
			{
				for(int i=0;i<AutomationConstants.HOTELS_MENU_ITEMS.length;i++)
				{
					if(item.getText().equals(AutomationConstants.HOTELS_MENU_ITEMS[i]))
					{
						counter=counter+1;
					}
				}
			}
		}
		else
		{
			return false;
		}
		
		if(counter == hotels_menu_items.size())
		{
			return true;
		}
		else
		{
			return false;
		}
	}
	
	
	public Boolean checkVisaMenuItems()
	{
		int counter = 0;
		if(visa_menu_items.size() == AutomationConstants.VISA_MENU_ITEMS.length)
		{
			for(WebElement item: visa_menu_items)
			{
				for(int i=0;i<AutomationConstants.VISA_MENU_ITEMS.length;i++)
				{
					if(item.getText().equals(AutomationConstants.VISA_MENU_ITEMS[i]))
					{
						counter=counter+1;
					}
				}
			}
		}
		else
		{
			return false;
		}
		
		if(counter == visa_menu_items.size())
		{
			return true;
		}
		else
		{
			return false;
		}
	}
	
	
	public Boolean checkToursMenuItems()
	{
		int counter = 0;
		if(tours_menu_items.size() == AutomationConstants.TOURS_MENU_ITEMS.length)
		{
			for(WebElement item: tours_menu_items)
			{
				for(int i=0;i<AutomationConstants.TOURS_MENU_ITEMS.length;i++)
				{
					if(item.getText().equals(AutomationConstants.TOURS_MENU_ITEMS[i]))
					{
						counter=counter+1;
					}
				}
			}
		}
		else
		{
			return false;
		}
		
		if(counter == tours_menu_items.size())
		{
			return true;
		}
		else
		{
			return false;
		}
	}
	
	
	public int getBookingCount(String booktype)
	{
		booktype = booktype.toLowerCase();
		int count = -1; 
		if(booktype.equals("confirmed"))
		{
			try {
			count = Integer.parseInt(confirmedBookingCount.getText());
			} catch(NumberFormatException ex) {System.out.println("Exception caught");}
		}
		else if(booktype.equals("pending"))
		{
			try {
			count = Integer.parseInt(pendingBookingCount.getText());
			} catch(NumberFormatException ex) {System.out.println("Exception caught");}
		}
		else if(booktype.equals("cancelled"))
		{
			count = Integer.parseInt(cancelledBookingCount.getText());
		}
		else if(booktype.equals("paid"))
		{
			count = Integer.parseInt(paidBookingCount.getText());
		}
		else if(booktype.equals("unpaid"))
		{
			count = Integer.parseInt(unpaidBookingCount.getText());
		}
		else if(booktype.equals("refunded"))
		{
			count = Integer.parseInt(refundedBookingCount.getText());
		}
		if(count == -1)
		{
			System.out.println("Invalid booking type requested");
		}
		return count;
	}
	
	
	
	public Boolean isDisplayedAndClickable(WebElement menu)
	{
		Boolean menu_disp_status = menu.isDisplayed();
		Boolean menu_click_status = menu.isEnabled();
		if(menu_disp_status && menu_click_status)
		{
			return true;
		}
		else
		{
			return false;
		}
	}
	
	public Boolean statusOfFlights()
	{
		return isDisplayedAndClickable(flightsMenu);
	}
	
	public Boolean statusOfCars()
	{
		return isDisplayedAndClickable(carsMenu);
	}
	
	public Boolean statusOfHotels()
	{
		return isDisplayedAndClickable(hotelsMenu);
	}
	
	public Boolean statusOfVisa()
	{
		return isDisplayedAndClickable(visaMenu);
	}
	
	public Boolean statusOfTours()
	{
		return isDisplayedAndClickable(toursMenu);
	}
	
	public Boolean statusOfBookings()
	{
		return isDisplayedAndClickable(bookingsMenu);
	}
	
	
	public void waitforDash()
	{
		Waiter.waitForEle(driver, dashboardHeading, 1500);
	}

}
