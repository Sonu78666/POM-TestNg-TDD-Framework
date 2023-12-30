package com.qa.opencart.base;

import java.util.Properties;

import org.openqa.selenium.WebDriver;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.asserts.SoftAssert;

import com.qa.opencart.factory.DriverFactory;
import com.qa.opencart.tests.RegisterTest;
import com.qa.opencat.pages.AccountPage;
import com.qa.opencat.pages.LoginPage;
import com.qa.opencat.pages.ProductInfoPage;
import com.qa.opencat.pages.RegisterPage;
import com.qa.opencat.pages.SearchPage;

public class BaseTest {
	
	DriverFactory df;
	WebDriver driver;
	protected Properties prop;
	protected LoginPage lp;
	protected AccountPage ap;
	protected SearchPage searchpage;
	protected ProductInfoPage productinfo;
	protected RegisterPage registerPage;
	
	protected SoftAssert softAssert;
	@BeforeTest
	public void setUp()
	{
		 df=new DriverFactory();
		 prop=df.init_pop();
		 driver=df.initDriver(prop);
		 lp=new LoginPage(driver);
		 softAssert=new SoftAssert();
	}
	@AfterTest
	public void tearDown()
	{
		driver.quit();
	}
}
