package net.phptravels.pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.Select;

import net.phptravels.utilities.PageScroller;
import net.phptravels.utilities.Waiter;

public class UserAgentProfilePage {
	
	WebDriver driver;
	
	@FindBy(xpath="//input[@name='firstname']")
	WebElement firstName;
	
	@FindBy(xpath="//input[@name='lastname']")
	WebElement lastName;
	
	@FindBy(xpath="//input[@name='phone']")
	WebElement phone;
	
	@FindBy(xpath="//input[@name='email']")
	WebElement email;
	
	@FindBy(xpath="//input[@name='password']")
	WebElement password;
	
	@FindBy(xpath="//select[@id='from_country']")
	WebElement country;
	
	@FindBy(xpath="//input[@name='state']")
	WebElement state;
	
	@FindBy(xpath="//input[@name='city']")
	WebElement city;
	
	@FindBy(xpath="//input[@name='fax']")
	WebElement fax;
	
	@FindBy(xpath="//input[@name='zip']")
	WebElement zip;
	
	@FindBy(xpath="//input[@name='address1']")
	WebElement addr1;
	
	@FindBy(xpath="//input[@name='address2']")
	WebElement addr2;
	
	@FindBy(xpath="//button[contains(text(),'Update Profile')]")
	WebElement saveBtn;
	
	@FindBy(xpath="//div[@class='alert alert-success']")
	WebElement successMsg;
	
	@FindBy(id="cookie_stop")
	WebElement cookies;
	
	public UserAgentProfilePage(WebDriver driver)
	{
		this.driver = driver;
		PageFactory.initElements(driver, this);
	}
	
	public void setFirstName(String fnam)
	{
		firstName.clear();
		firstName.sendKeys(fnam);
	}
	
	public void setLastName(String lnam)
	{
		lastName.clear();
		lastName.sendKeys(lnam);
	}
	
	public void setPhone(String ph)
	{
		phone.clear();
		phone.sendKeys(ph);
	}
	
	
	public void setEmail(String em)
	{
		email.clear();
		email.sendKeys(em);
	}
	
	public void setPassword(String pass)
	{
		password.clear();
		password.sendKeys(pass);
	}
	
	public void setCountry(String cou)
	{
		Select opt = new Select(country);
		opt.selectByVisibleText(cou);
	}
	
	public void setState(String sta)
	{
		state.clear();
		state.sendKeys(sta);
	}
	
	public void setCity(String cit)
	{
		city.clear();
		city.sendKeys(cit);
	}
	
	public void setFax(String fa)
	{
		fax.clear();
		fax.sendKeys(fa);
	}
	
	public void setZip(String zi)
	{
		zip.clear();
		zip.sendKeys(zi);
	}
	
	public void setAddressLine1(String add1)
	{
		addr1.clear();
		addr1.sendKeys(add1);
	}
	
	public void setAddressLine2(String add2)
	{
		addr2.clear();
		addr2.sendKeys(add2);
	}
	
	public void saveProfile() throws InterruptedException
	{
		PageScroller.scrollIntoView(driver, saveBtn);
		saveBtn.click();
	}
	
	public String getUpdateStatus()
	{
		Waiter.waitForEle(driver, successMsg, 1500);
		return successMsg.getText();
	}
	
	
}
