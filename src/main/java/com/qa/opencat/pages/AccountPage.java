package com.qa.opencat.pages;

import java.util.ArrayList;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import com.qa.opencart.constants.AppConstants;
import com.qa.opencart.util.ElementUtil;

public class AccountPage {
	
	private WebDriver driver;
	ElementUtil eu;
	
	public AccountPage(WebDriver driver)
	{
		this.driver=driver;
		eu=new ElementUtil(driver);
	}
	
	private By logoutlink=By.xpath("(//a[text()='Logout'])[2]");
	private By accountheader=By.cssSelector("div#content h2");
	private By search=By.name("search");
	private By searchbtn=By.cssSelector(".input-group button");
	
	public String accountPageTitle()
	{
		return eu.waitforTitleAndFetch(AppConstants.DEFAULT_Short_TIMEOUT, AppConstants.Account_Page_Title_Value);
	}
	public String accountPageUrl()
	{
		return driver.getCurrentUrl();
	}
	public boolean verifyLogoutButton()
	{
		return driver.findElement(logoutlink).isDisplayed();
	}
	public boolean searchExist()
	{
		return eu.waitForElementVisible(search, AppConstants.DEFAULT_Short_TIMEOUT).isDisplayed();
	}
	
	public List<String> getTextAccountHeader()
	{
		List<WebElement>list=driver.findElements(accountheader);
		List<String> textlist=new ArrayList<String>();
		for(WebElement e:list)
		{
			String text=e.getText();
			textlist.add(text);
		}
		return textlist;
	}
	
	public SearchPage search(String searchkey)
	{
		if(searchExist())
		{
			eu.doSendKeys(search, searchkey);
			eu.doClick(searchbtn);
			return new SearchPage(driver);
		}
		else
		{
			System.out.println("Search field is not present in");
			return null;
		}
	}
	
	
}
