package net.phptravels.utilities;

import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;

public class PageScroller {
	public static void scrollIntoView(WebDriver driver, WebElement ele) throws InterruptedException
	{
	JavascriptExecutor js = (JavascriptExecutor) driver;
	js.executeScript("arguments[0].scrollIntoView({block: 'center'});", ele);
	Thread.sleep(500);
	}
	
	public static void scollToTop(WebDriver driver) throws InterruptedException
	{
	JavascriptExecutor js = (JavascriptExecutor) driver;
	js.executeScript("window.scrollBy(0,-document.body.scrollHeight)");
	Thread.sleep(500);
	}
	
	public static void scollToBottom(WebDriver driver) throws InterruptedException
	{
	JavascriptExecutor js = (JavascriptExecutor) driver;
	js.executeScript("window.scrollBy(0,document.body.scrollHeight)");
	Thread.sleep(500);
	}
	
	
	public static void scrollWhile(WebDriver driver, WebElement ele) throws InterruptedException
	{
		while(!ele.isDisplayed())
			{
			Actions act = new Actions(driver);
			act.scrollToElement(ele);
			act.perform();
			act.sendKeys(Keys.ARROW_DOWN);
			act.perform();
			}
	}
}
