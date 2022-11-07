package net.phptravels.scripts;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.lang.reflect.Method;
import java.time.Duration;
import java.util.Properties;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.safari.SafariDriver;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import io.github.bonigarcia.wdm.WebDriverManager;

public class TestBase {
	
	WebDriver driver;
	public static Properties prop = null;
	public static void configLoad()
	{
		try {
		prop = new Properties();
		FileInputStream ip = new FileInputStream(System.getProperty("user.dir") + "/src/test/resources" + "/config.properties");
		prop.load(ip);
		}
		catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		
	}
	
	public void userSetup()
	{
		configLoad();
		String browsername = prop.getProperty("browser");
		if(browsername.equals("chrome"))
		{
			WebDriverManager.chromedriver().setup();
			driver = new ChromeDriver();
		}
		else if(browsername.equals("firefox"))
		{
			WebDriverManager.firefoxdriver().setup();
			driver = new FirefoxDriver();
		}
		else if(browsername.equals("edge"))
		{
			WebDriverManager.edgedriver().setup();
			driver = new EdgeDriver();
		}
		else if(browsername.equals("safari"))
		{
			WebDriverManager.safaridriver();
			driver = new SafariDriver();
		}
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));

	}
	
	@BeforeMethod
	public void name(Method method) {
	      Test testClass = method.getAnnotation(Test.class);
	      if(testClass.groups()[0].equals("admin"))
	      {
	    	  loadAdminUrl();
	      }
	      else if(testClass.groups()[0].equals("user"))
	      {
	    	  loadUserUrl();
	      }
	      else if(testClass.groups()[0].equals("agent"))
	      {
	    	  loadAgentUrl();
	      }
	      else if(testClass.groups()[0].equals("supplier"))
	      {
	    	  loadSupplierUrl();
	      }
	   }
	
	
	public void loadUserUrl()
	{
		userSetup();
		driver.get(prop.getProperty("url1"));
		driver.manage().window().maximize();
	}
	
	public void loadAgentUrl()
	{
		userSetup();
		driver.get(prop.getProperty("url1"));
		driver.manage().window().maximize();
	}
	
	
	public void loadAdminUrl()
	{
		userSetup();
		driver.get(prop.getProperty("url2"));
		driver.manage().window().maximize();
	}
	
	
	public void loadSupplierUrl()
	{
		userSetup();
		driver.get(prop.getProperty("url3"));
		driver.manage().window().maximize();
	}
	

	
}
