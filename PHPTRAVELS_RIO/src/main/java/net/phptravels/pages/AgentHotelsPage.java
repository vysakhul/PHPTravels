package net.phptravels.pages;

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.Select;

import net.phptravels.utilities.Waiter;

public class AgentHotelsPage {
	
	WebDriver driver;
	
	@FindBy(xpath="//*[@id=\"fadein\"]/span/span/span[1]/input")
	WebElement city;
	
	@FindBy(xpath="//*[@id=\"hotels-search\"]/div/div/div[1]/div/div/div/span/span[1]/span/span[2]")
	WebElement cityDrp;
	
	@FindBy(xpath="//input[@id='checkin']")
	WebElement checkin;
	
	@FindBy(xpath="//input[@id='checkout']")
	WebElement checkout;
	
	@FindBy(xpath="//div[@class='datepicker dropdown-menu'][1]//thead/tr/th[2]")
	WebElement month_yrCheckin;
	
	//*[@id=\"fadein\"]/div[5]/div[1]/table/thead/tr[1]/th[2]
	
	@FindBy(xpath="//div[@class='datepicker dropdown-menu'][2]//thead/tr/th[2]")
	WebElement month_yrCheckout;
	
	//*[@id=\"fadein\"]/div[6]/div[1]/table/thead/tr[1]/th[2]
	
	@FindBy(xpath="//*[@id=\"fadein\"]/div[5]/div[1]/table/thead/tr[1]/th[3]/i")
	WebElement nextArrCheckin;
	
	@FindBy(xpath="//*[@id=\"fadein\"]/div[6]/div[1]/table/thead/tr[1]/th[3]/i")
	WebElement nextArrCheckout;
	
	@FindBy(xpath="//a[@role='button']")
	WebElement travellersDrop;
	
	@FindBy(xpath="//input[@id='rooms']")
	WebElement rooms;
	
	@FindBy(xpath="//input[@id='adults']")
	WebElement adults;
	
	@FindBy(xpath="//input[@id='childs']")
	WebElement childs;
	
	@FindBy(xpath="//select[@id='ages4']")
	WebElement childAge;
	
	@FindBy(xpath="//select[@id='nationality']")
	WebElement nationality;
	
	@FindBy(xpath="//button[@id='submit']")
	WebElement searchBtn;
	
	@FindBy(xpath="//p[@class='card-meta']")
	List<WebElement> hotelLocation;
	
	public AgentHotelsPage(WebDriver driver)
	{
		this.driver = driver;
		PageFactory.initElements(driver, this);
	}
	
	
	public void setCity(String cname) throws InterruptedException
	{
		cityDrp.click();
		city.sendKeys(cname);
		Thread.sleep(5000);
		city.sendKeys(Keys.ENTER);
	}
	
	public void setCheckin(String date)
	{
		checkin.click();
		String splitter[] = date.split("-");
		String year = splitter[2];
		String month = splitter[1];
		String day = splitter[0];
		String month_year = month +" "+year;
		System.out.println("Data being used by picker, Month_Year: "+month_year+ ", and day: "+day);
		Waiter.waitForEle(driver, month_yrCheckin, 1500);
		System.out.println("Data received from calender: "+month_yrCheckin.getText());
		while(!month_yrCheckin.getText().equals(month_year))
		{
			nextArrCheckin.click();
		}
		
		for(int i=1;i<=4;i++)
		{
			for(int j=1;j<=7;j++)
			{
				//WebElement d = driver.findElement(By.xpath("//*[@id=\"fadein\"]/div[6]/div[1]/table/tbody/tr["+i+"]/td["+j+"]"));
				WebElement d = driver.findElement(By.xpath("//div[@class='datepicker dropdown-menu'][1]//tbody/tr["+i+"]/td["+j+"]"));
				System.out.println("I am looking at day: "+d.getText()+" in "+month_year);
				if(d.getText().equals(day))
				{
					System.out.println("Found it!!");
					d.click();
					break;
				}
			}
		}
//		checkin.click();
	}
	
	
	public void setCheckout(String date)
	{
//		checkout.click();
		String splitter[] = date.split("-");
		String year = splitter[2];
		String month = splitter[1];
		String day = splitter[0];
		String month_year = month +" "+year;
		//System.out.println("Data being used by picker, Month_Year: "+month_year+ ", and day: "+day);
		Waiter.waitForEle(driver, month_yrCheckout, 1500);
		while(!month_yrCheckout.getText().equals(month_year))
		{
			nextArrCheckout.click();
		}
		
		loop:
		for(int i=1;i<=4;i++)
		{
			for(int j=1;j<=7;j++)
			{
				//WebElement d = driver.findElement(By.xpath("//*[@id=\"fadein\"]/div[7]/div[1]/table/tbody/tr["+i+"]/td["+j+"]"));
				WebElement d = driver.findElement(By.xpath("//div[@class='datepicker dropdown-menu'][2]//tbody/tr["+i+"]/td["+j+"]"));
				System.out.println("I am looking at day: "+d.getText()+" in "+month_year);
				if(d.getText().equals(day))
				{
					System.out.println("Found it!!");
					d.click();
					break loop;
				}
			}
		}
	}
	
	
	public void setTravellers(String ro, String ad, String ch, String nat)
	{
		
		travellersDrop.click();
		rooms.clear();
		rooms.sendKeys(ro);
		adults.clear();
		adults.sendKeys(ad);
		childs.clear();
		childs.sendKeys(ch);
		Select opt = new Select(nationality);
		opt.selectByVisibleText(nat);
	}
	
	public void clickSearch() throws InterruptedException
	{
		searchBtn.click();
		Thread.sleep(2000);
	}
	
	
	public Boolean getHotelLocation(String location)
	{
		int counter = 0;
		for(WebElement loc: hotelLocation)
		{
		  if(loc.getText().equals(location))
		  {
			  counter=counter+1;
		  }
		}
		if(counter == hotelLocation.size())
		{
			return true;
		}
		else
		{
			return false;
		}
	}
}
