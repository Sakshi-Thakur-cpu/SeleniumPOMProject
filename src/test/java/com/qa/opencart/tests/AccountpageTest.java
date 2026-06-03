package com.qa.opencart.tests;

import java.util.List;

import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import com.qa.opencart.base.BaseTest;
import com.qa.opencart.constants.AppConstants;

public class AccountpageTest extends BaseTest{
	
	@BeforeClass
	public void accSetup() {
		accountPage=loginPage.doLoginPage(prop.getProperty("username"), prop.getProperty("password"));
	}
	
	
	@Test
	public void accountPageTitleTest() {
		String accPageTitle=accountPage.getAccountPageTitle();
		Assert.assertEquals(accPageTitle, AppConstants.ACCOUNTS_PAGE_TITLE);
		}
	
	//@Test
	public void isLogOutLinkExistTest() {
		Assert.assertTrue(accountPage.isLogOutLinkExist());
	}
	
	@Test
	public void accountPageHeaderTest() {
		List<String> actualheaders= accountPage.getAccountPageHeader();
		Assert.assertEquals(actualheaders, AppConstants.EXPECTED_ACCOUNT_PAGE_HEADERS_LIST);
	}
	
	@DataProvider
	public Object[][] searchKey() {
		return new Object[][] {
				{ "macbook", 3 }, { "imac", 1 }, { "samsung", 2 } };
	}
	
	@Test(dataProvider = "searchKey")
	public void doSearchTest(String search,int count) {
	resultpage=accountPage.doSearch(search);	
	Assert.assertEquals(resultpage.getSearchResultCount(), count);
	}
	
	@DataProvider
	public Object[][] getSearchData() {
		return new Object[][] { { "macbook", "MacBook Pro" }, { "imac", "iMac" } };
	}
	
	@Test(dataProvider = "getSearchData")
	public void searchTest(String searchParam,String selectProduct) {
		resultpage=accountPage.doSearch(searchParam);	
		productInfoPage= resultpage.selectProduct(selectProduct);
		Assert.assertEquals(productInfoPage.getProductHeader(), selectProduct);
		
	}
	
	
	

}
