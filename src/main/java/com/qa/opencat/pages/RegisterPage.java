package com.qa.opencat.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

import com.qa.opencart.constants.AppConstants;
import com.qa.opencart.util.ElementUtil;

public class RegisterPage {

	
	private WebDriver driver;
	private ElementUtil eu;

	public RegisterPage(WebDriver driver) {
		this.driver = driver;
		eu = new ElementUtil(driver);
	}

	private By firstName = By.id("input-firstname");
	private By lastName = By.id("input-lastname");
	private By email = By.id("input-email");
	private By telephone = By.id("input-telephone");
	private By password = By.id("input-password");
	private By confirmpassword = By.id("input-confirm");

	private By subscribeYes = By.xpath("//label[normalize-space()='Yes']//input[@type='radio']");
	private By subscribeNo = By.xpath("//label[normalize-space()='No']//input[@type='radio']");

	private By agreeCheckBox = By.name("agree");
	private By continueButton = By.xpath("//input[@type='submit' and @value='Continue']");

	private By successMessg = By.cssSelector("div#content h1");
	private By logoutLink = By.linkText("Logout");
	private By registerLink = By.linkText("Register");
	
	public boolean registerUser(String firstName, String lastName, String email, String telephone, String password,
			String subscribe) {

		eu.waitForElementVisible(this.firstName, AppConstants.DEFAULT_MEDIUM_TIMEOUT).sendKeys(firstName);
		eu.doSendKeys(this.lastName, lastName);
		eu.doSendKeys(this.email, email);
		eu.doSendKeys(this.telephone, telephone);

		eu.doSendKeys(this.password, password);
		eu.doSendKeys(this.confirmpassword, password);

		if (subscribe.equalsIgnoreCase("yes")) {
			eu.doClick(subscribeYes);
		} else {
			eu.doClick(subscribeNo);
		}
		eu.doActionsClick(agreeCheckBox);
		eu.doClick(continueButton);

		String successMessg = eu.waitForElementVisible(this.successMessg,AppConstants.DEFAULT_MEDIUM_TIMEOUT).getText();
		System.out.println(successMessg);
		if (successMessg.contains(AppConstants.USER_REGISTER_SUCCESS_MESSG)) {
			eu.doClick(logoutLink);
			eu.doClick(registerLink);
			return true;
		}
		return false;

	}
	
	
	
}
