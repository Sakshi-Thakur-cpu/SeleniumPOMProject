package com.qa.opencart.tests;

import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import com.qa.opencart.base.BaseTest;

public class RegisterPageTest extends BaseTest{
	
	@BeforeClass
	public void regSetup() {
		registerPage=loginPage.navigateToRegisterpage();
	}
	
	public String getrandomEmail() {
		return "uiautomation"+System.currentTimeMillis()+"gmail.com";
	}

	
	@Test
	public void userRegisterTest() {
		registerPage.userRegisteration("Sakshi", "Thakur", getrandomEmail(), "9876543210", "first@1234", "yes");
	}
	
	

}
