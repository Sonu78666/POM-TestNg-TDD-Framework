package com.qa.opencart.tests;

import java.util.Map;

import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import com.qa.opencart.base.BaseTest;

public class ProductInfoTest extends BaseTest{
	
	@BeforeClass
	public void accPageSetup()
	{
		ap=lp.doLogin(prop.getProperty("username").trim(),prop.getProperty("password").trim());
	}
	
	@DataProvider
	public Object[][] imageAccountTestdata()
	{
		return new Object[][]
				{
			     {"Macbook","MacBook Pro",4},
			     {"iMac","iMac",3}
			     
				};
	}
	@Test(priority = 1,dataProvider = "imageAccountTestdata")
	public void imageAccountTest(String searchkey,String productname,int imagecount)
	{
		searchpage=ap.search(searchkey);
		productinfo=searchpage.selectProduct(productname);
		int actualcount=productinfo.getProductImageCount();
		Assert.assertEquals(actualcount, imagecount);
	}
	@Test(priority = 2)
	public void productInfoTest()
	{
		searchpage=ap.search("Macbook");
		productinfo=searchpage.selectProduct("MacBook Pro");
		Map<String,String> actualmap=productinfo.getProductInfo();
		softAssert.assertEquals(actualmap.get("Brand"), "Apple");
		softAssert.assertEquals(actualmap.get("Product Code"), "Product 18");
		softAssert.assertEquals(actualmap.get("productname"), "MacBook Pro");
		softAssert.assertEquals(actualmap.get("pricekey"), "$2,000.00");
		softAssert.assertAll();
		
	}
	@DataProvider
	public Object[][] addtoCartTestData()
	{
		return new Object[][]
				{
			     {"Macbook","MacBook Pro"},
			     {"iMac","iMac"}
			     
				};
	}
	@Test(priority=3,dataProvider ="addtoCartTestData")
	public void addToCartTest(String searchkey,String productname)
	{
		searchpage=ap.search(searchkey);
		productinfo=searchpage.selectProduct(productname);
		String actualmsg=productinfo.addToCart();
		softAssert.assertEquals(actualmsg,"Success: You have added "+productname+" Pro to your shopping cart!");
		
	}

}
