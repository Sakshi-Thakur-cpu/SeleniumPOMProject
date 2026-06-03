package com.qa.opencart.pages;

import java.util.ArrayList;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import com.qa.opencart.constants.AppConstants;
import com.qa.opencart.utils.Elementutil;

public class Accountspage {
	private WebDriver driver;
	private Elementutil eleUtil;
	
	
	private By logOutLink=By.linkText("Logout");
	private By headers=By.cssSelector("div#content h2");
	private By search=By.name("search");
	private By searchIcon=By.cssSelector("div#search button");
	
	
	public Accountspage(WebDriver driver){
		this.driver=driver;
		eleUtil=new Elementutil(driver);
	}
	
	
	public String getAccountPageTitle() {
		String accPageTitle=eleUtil.waitForTitleContainsAndReturn(AppConstants.ACCOUNTS_PAGE_TITLE
				, AppConstants.DEFAULT_SHORT_TIMEOUT);
		System.out.println(accPageTitle);
		return accPageTitle;
	}
	
	
	public boolean isLogOutLinkExist() {
		return eleUtil.isElementDisplayed(logOutLink);
	}
	
	public List<String> getAccountPageHeader() {
		List<WebElement> headerList=eleUtil.waitForElementsVisible(headers, AppConstants.DEFAULT_SHORT_TIMEOUT);
		List<String> headerValueList=new ArrayList<String>();
		for(WebElement e:headerList) {
			String header=e.getText();
			headerValueList.add(header);
		}
		return headerValueList;
	}
	
	public Resultpage doSearch(String searchKey) {
		System.out.println("Searching product" + searchKey);
		WebElement searchelement = eleUtil.waitForElementVisible(search, AppConstants.DEFAULT_SHORT_TIMEOUT);
		searchelement.clear();
		searchelement.sendKeys(searchKey);
		eleUtil.doClick(searchIcon);
		return new Resultpage(driver);
	}
	

}
