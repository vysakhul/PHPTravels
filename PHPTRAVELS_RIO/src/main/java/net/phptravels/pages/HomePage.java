package net.phptravels.pages;

import java.util.List;
import java.util.Set;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import net.phptravels.utilities.Waiter;

public class HomePage {
	
	WebDriver driver;
	
	@FindBy(id="ACCOUNT")
	WebElement accountBtn;
	
	@FindBy(xpath="//ul[@aria-labelledby='ACCOUNT']/li")
	List<WebElement> accountMenu;
	
	public HomePage(WebDriver driver)
	{
		this.driver = driver;
		PageFactory.initElements(driver, this);
	}
	
	public void clickAcBtn()
	{
		accountBtn.click();
	}
	
	public void selectAccount(String ac)
	{
		for (WebElement ele: accountMenu)
		{
			if(ele.getText().equals(ac))
			{
				ele.click();
				break;
			}
		}
		String parentWinHandle = driver.getWindowHandle();
		Set<String> winHandles = driver.getWindowHandles();
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
	
	public void waitForPage()
	{
		Waiter.waitForEle(driver, accountBtn, 2000);
	}

}
