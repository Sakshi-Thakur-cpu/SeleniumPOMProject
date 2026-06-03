package com.qa.opencart.tests;

import org.testng.Assert;
import org.testng.annotations.Test;

import com.qa.opencart.base.BaseTest;
import com.qa.opencart.constants.AppConstants;
import com.qa.opencart.pages.Accountspage;

public class LoginPageTest extends BaseTest{
	
	//AAA pattern
	//Arrange act assert
	
	@Test
	public void loginPagetitleTest() {
		String actTitle=loginPage.getLoginPageTitle();
		Assert.assertEquals(actTitle, AppConstants.LOGIN_PAGE_TITLE);
	}
	
	@Test
	public void loginPageUrlTest() {
		String url=loginPage.getLoginPageUrl();
		Assert.assertTrue(url.contains(AppConstants.LOGIN_PAGE_FRACTION_URL));
	}
	
	@Test
	public void forgetPwdLinkTest() {
		Assert.assertTrue(loginPage.forgetPwdLinkExist());
	}
	
	@Test(priority = Integer.MAX_VALUE)
	public void doLoginTest() {
		 accountPage=loginPage.doLoginPage(prop.getProperty("username"), prop.getProperty("password"));
		Assert.assertEquals(accountPage.getAccountPageTitle(), AppConstants.ACCOUNTS_PAGE_TITLE);
		
	}
	

}
