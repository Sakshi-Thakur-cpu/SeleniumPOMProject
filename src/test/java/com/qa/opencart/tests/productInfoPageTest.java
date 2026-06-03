package com.qa.opencart.tests;

import java.util.Map;

import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import com.qa.opencart.base.BaseTest;

public class productInfoPageTest extends BaseTest{
	
	@BeforeClass
	public void productInfoSetup() {
		accountPage=loginPage.doLoginPage(prop.getProperty("username"), prop.getProperty("password"));
	}
	
	@Test
	public void getProductHeaderTest() {
		resultpage=accountPage.doSearch("macbook");	
		productInfoPage= resultpage.selectProduct("MacBook Pro");
		Assert.assertEquals(productInfoPage.getProductHeader(), "MacBook Pro");
	}

//	Brand: Apple
//	Product Code: Product 18
//	Reward Points: 800
//	Availability: Out Of Stock
//	$2,000.00
//	Ex Tax: $2,000.00
	@Test
	public void getProductDataTest() {
		resultpage=accountPage.doSearch("macbook");	
		productInfoPage= resultpage.selectProduct("MacBook Pro");
		Map<String, String> actualProductData= productInfoPage.getProductData();
		softAssert.assertEquals(actualProductData.get("Brand"), "Apple");
		softAssert.assertEquals(actualProductData.get("Product Code"), "Product 18");
		softAssert.assertEquals(actualProductData.get("Reward Points"), "800");
		softAssert.assertEquals(actualProductData.get("Availability"), "Out Of Stock");
		softAssert.assertEquals(actualProductData.get("Product Price"), "$2,000.00");
		softAssert.assertEquals(actualProductData.get("extax Price"), "$2,000.00");
		softAssert.assertAll();
		
	}
}
