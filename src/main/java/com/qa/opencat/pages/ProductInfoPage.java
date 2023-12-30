package com.qa.opencat.pages;

import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import com.qa.opencart.constants.AppConstants;
import com.qa.opencart.util.ElementUtil;

public class ProductInfoPage {

	
	private WebDriver driver;
	private ElementUtil eu;
	private By productimages=By.cssSelector("ul.thumbnails img");
	private  By productheader=By.tagName("h1");
	private By metadata=By.xpath("(//div[@id='content']//ul[@class='list-unstyled'])[1]/li");
	private By getPriceData=By.xpath("(//div[@id='content']//ul[@class='list-unstyled'])[2]/li");
	private By qtytextbox=By.id("input-quantity");
	private By addtocartBtn=By.xpath("//button[text()='Add to Cart']");
	private By sucessmsgText=By.cssSelector("div.alert.alert-success");
	private Map<String,String> productInfoMap;
	
	
	public ProductInfoPage(WebDriver driver)
	{
		this.driver=driver;
		eu=new ElementUtil(driver);
	}
	
	public String GetProductHeaderValue()
	{
		String text=driver.findElement(productheader).getText();
		System.out.println(text);
		return text;
	}
	public int getProductImageCount()
	{
		int imagecount=eu.waitForElementsVisible(productimages,AppConstants.DEFAULT_MEDIUM_TIMEOUT).size();
		System.out.println(imagecount);
		return imagecount;
		
		
	}
	
	/*MacBook Pro
	Brand: Apple
	Product Code: Product 18
	Reward Points: 800
	Availability: In Stock
	$2,000.00
	*/
	public Map<String, String> getProductInfo()
	{
		productInfoMap=new HashMap<String, String>();
		//productInfoMap=new LinkedHashMap<String, String>();
		productInfoMap.put("productname",GetProductHeaderValue());
		getProductMetadata();
		getPriceData();
		System.out.println(productInfoMap);
		return productInfoMap;
	}
	
	private void getProductMetadata() 
	{
		
		List<WebElement> list=eu.getElements(metadata);
		for(WebElement e:list)
		{
			String meta=e.getText();
			String metainfo[]=meta.split(":");
			String key=metainfo[0].trim();
			String value=metainfo[1].trim();
			productInfoMap.put(key, value);
		}
	}
	private void getPriceData()
	{
		
		List<WebElement> list=eu.getElements(getPriceData);
		String pricevalue=list.get(0).getText();
		String ex=list.get(1).getText();
		String extaxvalue=ex.split(":")[1].trim();
		productInfoMap.put("pricekey",pricevalue);
		productInfoMap.put("extaxkey", extaxvalue);
	}
	public void enterQuantity(int qty)
	{
		eu.waitForElementVisible(qtytextbox, AppConstants.DEFAULT_MEDIUM_TIMEOUT).sendKeys(String.valueOf(qty));
	}
	public String addToCart()
	{
		eu.waitForElementVisible(addtocartBtn,AppConstants.DEFAULT_Long_TIMEOUT).click();
		String successtext=eu.waitForElementVisible(sucessmsgText, AppConstants.DEFAULT_Long_TIMEOUT).getText();
		StringBuilder sb=new StringBuilder(successtext);
		String msg=sb.substring(0,successtext.length()-1).replace("\n","");
		System.out.println("Cart Message is"+msg);
		return msg;
		
	}
	
}
