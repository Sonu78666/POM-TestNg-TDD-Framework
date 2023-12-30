package com.qa.opencart.tests;

import org.testng.Assert;
import org.testng.annotations.Test;

import com.qa.opencart.base.BaseTest;

import io.qameta.allure.Description;
import io.qameta.allure.Epic;
import io.qameta.allure.Severity;
import io.qameta.allure.SeverityLevel;
import io.qameta.allure.Story;
import net.bytebuddy.build.Plugin.Factory.UsingReflection.Priority;


@Epic("Login test Epic")
@Story("Login test")
public class LoginPageTest extends BaseTest {
	
	
	@Description("Getting the url of the login page....author-Sonu Yadav")
	@Test(priority=1)
	public void loginTitleTest()
	{
		String actualtilte=lp.getTitle();
		Assert.assertEquals(actualtilte, "Account Login");
	}
	
	
	@Test(priority=2)
	public void getPageUrlTest()
	{
		String actualurl=lp.geturl();
		Assert.assertTrue(actualurl.contains("route=account/login"));
	}
	
	@Severity(SeverityLevel.CRITICAL)
	@Description("checking for forgot link")
	@Test(priority=3)
	public void fotgotpassTest()
	{
		boolean value=lp.forgotLinkPresent();
		Assert.assertTrue(value);
	}
	@Test(priority=4)
	public void doLoginTest()
	{
		ap=lp.doLogin(prop.getProperty("username").trim(),prop.getProperty("password").trim());
		Assert.assertEquals(ap.accountPageTitle(), "My Account");
	}
}
