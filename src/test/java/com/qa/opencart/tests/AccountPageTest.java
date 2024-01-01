package com.qa.opencart.tests;

import static org.testng.Assert.assertEquals;

import java.util.List;

import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import com.qa.opencart.base.BaseTest;
import com.qa.opencart.constants.AppConstants;
import com.qa.opencat.pages.AccountPage;
import com.qa.opencat.pages.ProductInfoPage;
import com.qa.opencat.pages.SearchPage;

public class AccountPageTest extends BaseTest {
	
	@BeforeClass
	public void accPageSetup()
	{
		ap=lp.doLogin(prop.getProperty("username").trim(),prop.getProperty("password").trim());
	}
	
	@Test(priority=1)
	public void verifyAccPageTitle()
	{
		String actualtitle=ap.accountPageTitle();
		Assert.assertEquals(actualtitle,AppConstants.Account_Page_Title_Value);
	}

	@Test(priority=2)
	public void verifyAccLogOutBtn()
	{
		boolean b=ap.verifyLogoutButton();
		Assert.assertTrue(b);
	}
	
	@Test(priority=3)
	public void AccPageHeaderTextTest()
	{
		List<String>list=ap.getTextAccountHeader();
		Assert.assertEquals(list.size(), 4);
	}

	@Test(priority=3)
	public void AccPageValueTest()
	{
		List<String>list=ap.getTextAccountHeader();
		System.out.println("Actual array list is "+list);
		//List<String> Expectedlist=AppConstants.EXPECTED_ACCOUNT_PAGE_HEADER_LIST;
		Assert.assertEquals(list, AppConstants.EXPECTED_ACCOUNT_PAGE_HEADER_LIST);
	}
	
	@DataProvider
	public Object[][] searchData()
	{
		return new Object[][] {
			{"Macbook"},
			{"iMac"},
			{"Apple"}
		};
	}

	@Test(priority=4,dataProvider ="searchData" )
	public void doSearchTest(String searckey)
	{
		searchpage=ap.search(searckey);
		
		Assert.assertTrue(searchpage.getSearchProductCount()>0);
	}
	
	@DataProvider
	public Object[][] selectProductTestData()
	{
		return new Object[][] {
			{"Macbook","MacBook Pro"},
			{"iMac","iMac"},
			{"Apple","Apple Cinema 30\""}
		};
	}

	
	@Test(priority=5,dataProvider="selectProductTestData")
	public void selectProductTest(String searchkey,String productname)
	{
		searchpage=ap.search(searchkey);
		if(searchpage.getSearchProductCount()>0)
		{
		 productinfo=searchpage.selectProduct(productname);
		 String actualproductHeader=productinfo.GetProductHeaderValue();
		 System.out.println("i am on this");
		 Assert.assertEquals(actualproductHeader,productname);
		 System.out.println("i am on that");
		}
		
	}
	
	
}
