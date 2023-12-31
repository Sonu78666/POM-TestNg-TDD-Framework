package com.qa.opencart.factory;

import java.util.Properties;

import org.openqa.selenium.chrome.ChromeOptions;

public class OptionsManager {
	
	private Properties prop;
	private ChromeOptions co;
	
	public OptionsManager(Properties prop)
	{
		this.prop=prop;
	}
	
	public ChromeOptions getChromeOption()
	{
		co=new ChromeOptions();
		//co.addArguments("--remote-allow-origins=*");
		if(Boolean.parseBoolean(prop.getProperty("headless").trim()))
		{
			System.out.println("Running browser in headless mode");
			co.addArguments("--headless");
			
		}
		if(Boolean.parseBoolean(prop.getProperty("incognito").trim()))
		{
			co.addArguments("--incognito");
			
		}
		return co;
	}
	
	
}
