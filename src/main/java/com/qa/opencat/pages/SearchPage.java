package com.qa.opencat.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

import com.qa.opencart.constants.AppConstants;
import com.qa.opencart.util.ElementUtil;

public class SearchPage {

	private WebDriver driver;
	private ElementUtil eu;
	
	private By searchproductresult=By.cssSelector("div#content div.product-layout");
	
	public SearchPage(WebDriver driver)
	{
		this.driver=driver;
		eu=new ElementUtil(driver);
	}
	
	public int getSearchProductCount()
	{
		int productcount= eu.waitForElementsVisible(searchproductresult, AppConstants.DEFAULT_MEDIUM_TIMEOUT).size();
		System.out.println(productcount);
		return productcount;
	}
	
	public ProductInfoPage selectProduct(String productname)
	{
		By productLocatorBy=By.linkText(productname);
		eu.waitForElementVisible(productLocatorBy,AppConstants.DEFAULT_Long_TIMEOUT).click();
		return new ProductInfoPage(driver);
	}
}
