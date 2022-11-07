package net.phptravels.pages;

import java.util.List;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import net.phptravels.utilities.Waiter;

public class UserAgentDashboardPage {
	
	WebDriver driver;
	
	@FindBy(xpath="//span[@class='author__meta']")
	WebElement welcomeMsg;
	
	@FindBy(xpath="//ul[@class='sidebar-menu list-items']/li[1]")
	WebElement dashMenu;
	
	@FindBy(xpath="//ul[@class='sidebar-menu list-items']/li[2]")
	WebElement bookingsMenu;
	
	@FindBy(xpath="//ul[@class='sidebar-menu list-items']/li[3]")
	WebElement fundsMenu;
	
	@FindBy(xpath="//ul[@class='sidebar-menu list-items']/li[4]")
	WebElement profileMenu;
	
	@FindBy(xpath="//ul[@class='sidebar-menu list-items']/li[5]")
	WebElement logout;
	
	@FindBy(xpath="//div[@class='logo']")
	WebElement homeicon;
	
	@FindBy(xpath="//strong[contains(text(),'Let’s book your next trip!')]")
	WebElement homeHead;
	
	@FindBy(xpath="//a[@title='hotels']")
	WebElement hotelsNav;
	
	@FindBy(xpath="//h2[normalize-space()='SEARCH FOR BEST HOTELS']")
	WebElement hotelHead;
	
	@FindBy(xpath="//a[@title='flights']")
	WebElement flightsNav;
	
	@FindBy(xpath="//h2[normalize-space()='SEARCH FOR BEST FLIGHTS']")
	WebElement flightsHead;
	
	@FindBy(xpath="//a[@title='tours']")
	WebElement toursNav;
	
	@FindBy(xpath="//h2[normalize-space()='Find the best tours packages']")
	WebElement toursHead;
	
	@FindBy(xpath="//a[@title='cars']")
	WebElement transfersNav;
	
	@FindBy(xpath="//h2[normalize-space()='Book Your Transfer Today']")
	WebElement transfersHead;
	
	@FindBy(xpath="//a[@title='visa']")
	WebElement visaNav;
	
	@FindBy(xpath="//h2[normalize-space()='Submit Your Visa Today!']")
	WebElement visaHead;
	
	@FindBy(xpath="//a[@title='blog']")
	WebElement blogNav;
	
	@FindBy(xpath="//h2[normalize-space()='PHPTRAVELS Blog']")
	WebElement blogHead;
	
	@FindBy(xpath="//a[@title='offers']")
	WebElement offersNav;
	
	@FindBy(xpath="//h2[normalize-space()='PHPTRAVELS Offers']")
	WebElement offersHead;
	
	@FindBy(xpath="//li[@class='footm']/a[1]")
	WebElement companyNav;
	
	@FindBy(xpath="//div[@class='d-flex header-top-bar']/div[2]//ul/li")
	List<WebElement> currency; 
	
	@FindBy(xpath="//div[@class='d-flex header-top-bar']/div[2]//button")
	WebElement currencyDrp;
	
	@FindBy(xpath="//button[@id='languages']")
	WebElement languageDrp;
	
	@FindBy(xpath="//ul[@aria-labelledby = 'languages']/li")
	List<WebElement> languages; 
	
	@FindBy(id="cookie_stop")
	WebElement cookies;
	
	
	public UserAgentDashboardPage(WebDriver driver)
	{
		this.driver = driver;
		PageFactory.initElements(driver, this);
	}
	
	public void dashboardMenu()
	{
		dashMenu.click();
	}
	
	public void bookingsMenu()
	{
		bookingsMenu.click();
	}
	
	public void addFundsMenu()
	{
		fundsMenu.click();
	}
	
	public void myProfileMenu()
	{
		profileMenu.click();
	}
	
	public void logout()
	{
		logout.click();
	}
	
	public void homeNav()
	{
		homeicon.click();
		Waiter.waitForEle(driver, homeHead, 1000);
		
	}
	
	public void hotelsNav()
	{
		hotelsNav.click();
		Waiter.waitForEle(driver, hotelHead, 1000);
	}
	
	public void flightsNav()
	{
		flightsNav.click();
		Waiter.waitForEle(driver, flightsHead, 1000);
	}
	
	public void toursNav()
	{
		toursNav.click();
		Waiter.waitForEle(driver, toursHead, 1000);
	}
	
	public void transfersNav()
	{
		transfersNav.click();
		Waiter.waitForEle(driver, transfersHead, 1000);
	}
	
	public void visaNav()
	{
		visaNav.click();
		Waiter.waitForEle(driver, visaHead, 1000);
	}
	
	public void blogNav()
	{
		blogNav.click();
		Waiter.waitForEle(driver, blogHead, 1000);
	}
	
	public void offersNav()
	{
		offersNav.click();
		Waiter.waitForEle(driver, offersHead, 1000);
	}
	
	
	
	public void companyNav()
	{
		companyNav.click();
	}
	
	public void clickLangDropDown()
	{
		languageDrp.click();
	}
	
	public void clickCurrencyDropDown()
	{
		currencyDrp.click();
	}
	
	public String getCurrentCurrency()
	{
		return currencyDrp.getText();
	}
	
	public void setCurrency(String cur)
	{
		for(WebElement ele: currency)
		{
			if(ele.getText().equals(cur))
			{
				ele.click();
				break;
			}
		}
	}
	
	public void setLanguage(String lang)
	{
		for(WebElement ele: languages)
		{
			if(ele.getText().equals(lang))
			{
				ele.click();
				break;
			}
		}
	}
	
	public void waitForPage()
	{
		Waiter.waitForEle(driver, homeHead, 1500);
	}

}
