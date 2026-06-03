package com.qa.opencart.constants;

import java.util.List;

public class AppConstants {
	
	public static final String LOGIN_PAGE_TITLE="Account Login";
	public static final String ACCOUNTS_PAGE_TITLE="My Account";
	public static final String LOGIN_PAGE_FRACTION_URL="?route=account/login";
	public static final String REGISTER_PAGE_TITLE="Register Account";
	
	public static final int DEFAULT_SHORT_TIMEOUT=5;
	public static final int DEFAULT_MEDIUM_TIMEOUT=10;
	public static final int DEFAULT_LONG_TIMEOUT=15;
	
	public static final String USER_REGISTER_SUCCESS_MESSG = "Your Account Has Been Created!";
	
	public static final List<String> EXPECTED_ACCOUNT_PAGE_HEADERS_LIST= List.of("My Account","My Orders",
			"My Affiliate Account","Newsletter");

}
