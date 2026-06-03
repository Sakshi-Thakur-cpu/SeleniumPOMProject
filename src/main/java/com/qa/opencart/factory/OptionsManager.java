package com.qa.opencart.factory;

import java.util.Properties;

import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.edge.EdgeOptions;
import org.openqa.selenium.firefox.FirefoxOptions;

public class OptionsManager {
	
	private Properties prop;
	private ChromeOptions co;
	private FirefoxOptions fo;
	private EdgeOptions eo;
	
	
	public OptionsManager(Properties prop) {
		this.prop=prop;
	}
	
	
	//headless means it is blocking blocking the browser to open in your windows to see the visibility
	//still your cases are running in chrome it's just that chrome is not visible
	//running on cmd
	//mvn clean install -Denv="qa" -Dheadless="true" -Dincognito="true"
	
	public ChromeOptions getChromeOptions() {
		co=new ChromeOptions();
		if(Boolean.parseBoolean(prop.getProperty("headless"))) {
			System.out.println("----Runnning in headless mode");
			co.addArguments("---headless");
		}
		if(Boolean.parseBoolean(prop.getProperty("incognito"))) {
			co.addArguments("--incognito");
		}
		return co;
	}
	
	public FirefoxOptions getFirefoxOptions() {
		if(Boolean.parseBoolean(prop.getProperty("headless"))) {
			System.out.println("----Runnning in headless mode");
			fo.addArguments("--headless");
		}
		if(Boolean.parseBoolean(prop.getProperty("incognito"))){
			fo.addArguments("---incognito");
		}
		return fo;
	}

	public EdgeOptions getEdgeOptions() {
		if(Boolean.parseBoolean(prop.getProperty("headless"))){
			System.out.println("----Runnning in headless mode");
			eo.addArguments("--headless");
		}
		if(Boolean.parseBoolean(prop.getProperty("incognito"))) {
			eo.addArguments("--inPrivate");
		}
		return eo;
	}
}
