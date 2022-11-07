package net.phptravels.utilities;

import java.util.ArrayList;
import java.util.List;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.Select;



public class DropdownListChecker {
	
	
	public static Boolean getListStatus(WebDriver driver, WebElement dropdownEle, String[] expEleList) throws InterruptedException
	{
		int counter=0;
		int expListLen = expEleList.length;
		Select eType = new Select(dropdownEle);
		ArrayList<String> actList =  new ArrayList<String>();
		List<WebElement> options = eType.getOptions();
		for(WebElement option: options)
		{
		actList.add(option.getText());
		}
		for(String actType: actList)
		{
			for(String expType: expEleList)
			{
				System.out.println("Checking act Type: "+actType+ " with exp Type: "+expType);
				if(actType.equals(expType))
				{

				counter = counter + 1;
				}
			}
		}
		if(counter == expListLen)
		{
			return true;
		}
		else
		{
			return false;
		}
	}
	
	public static String getSelectedDropDownValue(WebElement drp)
	{
		Select opt = new Select(drp);
		String drpValue = opt.getFirstSelectedOption().getText().toLowerCase();
		return drpValue;
	}
	
	public static void setDropDownto(WebElement ele, String choice)
	{
		Select opt = new Select(ele);
		opt.selectByVisibleText(choice);
	}

}
