package com.qa.opencart.pages;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import com.qa.opencart.constants.AppConstants;
import com.qa.opencart.utils.Elementutil;

public class ProductInfoPage {

	private WebDriver driver;
	private Elementutil eleUtil;

	private By productHeader = By.tagName("h1");
	private By metadata = By.xpath("(//div[@id=\"content\"]//ul[@class=\"list-unstyled\"])[1]/li");
	private By priceData = By.xpath("(//div[@id=\"content\"]//ul[@class=\"list-unstyled\"])[2]/li");

	Map<String, String> productMap;

	public ProductInfoPage(WebDriver driver) {
		this.driver = driver;
		eleUtil = new Elementutil(driver);
	}

	public String getProductHeader() {
		String productHeadervalue = eleUtil.waitForElementVisible(productHeader, AppConstants.DEFAULT_SHORT_TIMEOUT)
				.getText();
		System.out.println(productHeadervalue);
		return productHeadervalue;

	}

//	Brand: Apple
//	Product Code: Product 18
//	Reward Points: 800
//	Availability: Out Of Stock
	
	private void getProductMetaData() {
		List<WebElement> metaList = eleUtil.getElements(metadata);
		
		for (WebElement meta : metaList) {
			String metaText = meta.getText();
			String metaData[] = metaText.split(":");
			String metaKey = metaData[0].trim();
			String metaValue = metaData[1].trim();
			productMap.put(metaKey, metaValue);
		}
	}

//	$2,000.00
//	Ex Tax: $2,000.00
	
	private void getProductPriceData() {
		List<WebElement> priceList = eleUtil.getElements(priceData);
		String price = priceList.get(0).getText();
		String exPrice = priceList.get(1).getText().split(":")[1].trim();
		productMap.put("Product Price", price);
		productMap.put("extax Price", exPrice);
	}
	
	public Map<String, String> getProductData() {
		productMap=new HashMap<String, String>();
		productMap.put("Product Header", getProductHeader());
		getProductMetaData();
		getProductPriceData();
		System.out.println("product full data:"+ productMap);
		return productMap;
	}

}
