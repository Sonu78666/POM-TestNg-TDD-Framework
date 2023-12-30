package com.qa.opencart.factory;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Properties;

import org.aspectj.util.FileUtil;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.io.FileHandler;

public class DriverFactory {

	public WebDriver driver;
	Properties prop;
	public OptionsManager optionsManager;
	public static String highlight;
	public static ThreadLocal<WebDriver> tlDriver=new ThreadLocal<WebDriver>();
	
	public WebDriver initDriver(Properties prop)
	{
		optionsManager=new OptionsManager(prop);
		//highlight=prop.getProperty(highlight);
		String browsername=prop.getProperty("browser");
		System.out.println("browser name is"+browsername);
		if(browsername.trim().equalsIgnoreCase("chrome"))
		{
			//driver=new ChromeDriver(optionsManager.getChromeOption());
			tlDriver.set(new ChromeDriver(optionsManager.getChromeOption()));
		}
		else if(browsername.trim().equalsIgnoreCase("firefox"))
		{
			//driver=new FirefoxDriver();
			//tlDriver.set(new FirefoxDriver(optionsManager.getFirefoxOption()));
		}
		else if(browsername.trim().equalsIgnoreCase("edge"))
		{
			//driver=new EdgeDriver();
		}
		else
		{
			System.out.println("supplied the wrong browser name..please provide correct browser name:"+browsername);
		}
		getDriver().manage().deleteAllCookies();
		getDriver().manage().window().maximize();
		getDriver().get(prop.getProperty("url"));
		return getDriver();
	}
	
	public synchronized static WebDriver getDriver()
	
	{
		return tlDriver.get();
	}
	public Properties init_pop()
	{
		FileInputStream ip = null;
		prop = new Properties();

		String envName = System.getProperty("env");
		System.out.println("env name is : " + envName);

		try {
			if (envName == null) {
				System.out.println("no env is given...hence running it on QA env..by default");
				ip = new FileInputStream("./src/test/resources/config/qa.config.properties");
			} else {

				switch (envName.toLowerCase().trim()) {
				case "qa":
					ip = new FileInputStream("./src/test/resources/config/qa.config.properties");
					break;
				case "dev":
					ip = new FileInputStream("./src/test/resources/config/dev.config.properties");
					break;
				case "stage":
					ip = new FileInputStream("./src/test/resources/config/stage.config.properties");
					break;
				case "uat":
					ip = new FileInputStream("./src/test/resources/config/uat.config.properties");
					break;
				case "prod":
					ip = new FileInputStream("./src/test/resources/config/config.properties");
					break;

				default:
					System.out.println("please pass the right env name...." + envName);
					break;
				}

			}
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}

		try {
			prop.load(ip);
		} catch (IOException e) {
			e.printStackTrace();
		}
		return prop;
	}
	
	public static String getScreenshot(String methodName) {
		File srcFile = ((TakesScreenshot) getDriver()).getScreenshotAs(OutputType.FILE);
		String path = System.getProperty("user.dir") + "/screenshot/" + methodName + "_" + System.currentTimeMillis()
				+ ".png";
		System.out.println("user directory: " + System.getProperty("user.dir"));
		System.out.println("screenshot path: " + path);
		File destination = new File(path);
		try {
			FileUtil.copyFile(srcFile, destination);
		} catch (IOException e) {
			e.printStackTrace();
		}

		return path;
	}
	
}
