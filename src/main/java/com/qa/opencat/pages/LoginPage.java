package com.qa.opencat.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

import com.qa.opencart.constants.AppConstants;
import com.qa.opencart.util.ElementUtil;

import io.qameta.allure.Step;

public class LoginPage {
	
	private WebDriver driver;
	ElementUtil eu;
	// private locators
	private By emailid=By.id("input-email");
	private By password=By.id("input-password");
	private By loginbtn=By.xpath("//input[@value='Login']");
	private By forgetpwdlnk=By.xpath("(//a[text()='Forgotten Password'])[1]");
	private By registerLink = By.linkText("Register");
	
	//page constructor
	
	public LoginPage(WebDriver driver)
	{
		this.driver=driver;
		eu=new ElementUtil(driver);
	}
	//page action methods
	@Step("Getting the title of the page")
	public String getTitle()
	{
		
		return eu.waitforTitleAndFetch(AppConstants.DEFAULT_Short_TIMEOUT,AppConstants.Login_Page_Title_Value);
		//String title=driver.getTitle();
		//System.out.println("title is"+title);
		//return title;
	}
	@Step("Getting the url of the page")
	public String geturl()
	{
		String url=driver.getCurrentUrl();
		System.out.println("title is"+url);
		return url;
	}
	public boolean forgotLinkPresent()
	{
		return eu.waitForElementVisible(forgetpwdlnk,AppConstants.DEFAULT_MEDIUM_TIMEOUT ).isDisplayed();
	}
	@Step("Entering username:{0} and password:{1}")
	public AccountPage doLogin(String uname,String pwd)
	{
		eu.waitForElementVisible(emailid, AppConstants.DEFAULT_Short_TIMEOUT).sendKeys(uname);
		eu.waitForElementVisible(password, AppConstants.DEFAULT_Short_TIMEOUT).sendKeys(pwd);
		//driver.findElement(emailid).sendKeys(uname);
		//driver.findElement(password).sendKeys(pwd);
		driver.findElement(loginbtn).click();
		return new AccountPage(driver);
	}
	@Step("Navigating to the register page")
	public RegisterPage navigateToRegisterPage() {
		eu.waitForElementVisible(registerLink, AppConstants.DEFAULT_Short_TIMEOUT).click();
		return new RegisterPage(driver);
	}
}
