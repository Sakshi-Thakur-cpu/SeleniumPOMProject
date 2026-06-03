package com.qa.opencart.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

import com.qa.opencart.constants.AppConstants;
import com.qa.opencart.utils.Elementutil;

public class Resultpage {
	
	private WebDriver driver;
	private Elementutil eleUtil;
	
	private By searchHeader=By.cssSelector("div#content h1");
	private By results=By.cssSelector("div.product-thumb");
	
	public Resultpage(WebDriver driver) {
		this.driver=driver;
		eleUtil=new Elementutil(driver);
	}
	
	public String getSearchHeader() {
		String searchHeaderValue=eleUtil.waitForElementVisible(searchHeader, AppConstants.DEFAULT_SHORT_TIMEOUT).getText();
	return searchHeaderValue;
	}
	
	public int getSearchResultCount() {
	int resultCountValue=	eleUtil.waitForElementsVisible(results, AppConstants.DEFAULT_SHORT_TIMEOUT).size();
	System.out.println("Result count is:"+resultCountValue);
	return resultCountValue;
	}
	
	public ProductInfoPage selectProduct(String productName) {
		System.out.println("Selecting search product:" + productName);
		eleUtil.waitForElementAndClick(By.linkText(productName), AppConstants.DEFAULT_SHORT_TIMEOUT);
		return new ProductInfoPage(driver);
	}

}
