package com.qa.opencart.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

import com.qa.opencart.constants.AppConstants;
import com.qa.opencart.utils.Elementutil;

public class LoginPage {
	
	private WebDriver driver;
	private Elementutil eleUtil;
	
	//private by locators
	private By emailId=By.id("input-email");
	private By password=By.id("input-password");
	private By loginBtn=By.xpath("//input[@type=\"submit\"]");
	private By forgetPwd=By.linkText("Forgotten Password");
	private By registerLink=By.linkText("Register");
	
	
	//public page constructor
	public LoginPage(WebDriver driver) {
		this.driver=driver;
		eleUtil=new Elementutil(driver);
	}
	
	
	//public page actions classes/methods
	
	public String getLoginPageTitle() {
		String title=eleUtil.waitForTitleContainsAndReturn(AppConstants.LOGIN_PAGE_TITLE, AppConstants.DEFAULT_SHORT_TIMEOUT);
		System.out.println("Login page title is:" + title);
		return title;
	}
	
	public String getLoginPageUrl() {
		String url=eleUtil.waitForURLContainsAndReturn(AppConstants.LOGIN_PAGE_FRACTION_URL, AppConstants.DEFAULT_SHORT_TIMEOUT);
		System.out.println("Login page url is:"+ url);
		return url;
	}
	
	public boolean forgetPwdLinkExist() {
		return eleUtil.isElementDisplayed(forgetPwd);
	}
	
	public Accountspage doLoginPage(String email,String pwd){
		eleUtil.waitForElementVisible(emailId, AppConstants.DEFAULT_SHORT_TIMEOUT).sendKeys(email);
		eleUtil.doSendKeys(password, pwd);
		eleUtil.doClick(loginBtn);
		return new Accountspage(driver);
	}
	
	public RegisterPage navigateToRegisterpage() {
		eleUtil.doClick(registerLink);
		return new RegisterPage(driver);
		
		
	}

}
