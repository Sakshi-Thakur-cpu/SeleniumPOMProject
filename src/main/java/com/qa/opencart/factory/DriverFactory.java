package com.qa.opencart.factory;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Properties;

import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.io.FileHandler;
import org.openqa.selenium.safari.SafariDriver;

import com.qa.opencart.error.AppError;
import com.qa.opencart.exceptions.BrowserException;
import com.qa.opencart.exceptions.FrameworkException;

public class DriverFactory {
	
	WebDriver driver;
	Properties prop;
	
	public static  ThreadLocal<WebDriver> tlDriver=new ThreadLocal<WebDriver>();
	
	public WebDriver initDriver(Properties prop) {
		
		String browserName=prop.getProperty("browser");
		System.out.println("Browser name is:"+ browserName);
		
		OptionsManager optionManager=new OptionsManager(prop);
		
		
		
		switch(browserName.toLowerCase().trim()) {
		case "chrome":
			//driver=new ChromeDriver(optionManager.getChromeOptions());
			tlDriver.set(new ChromeDriver(optionManager.getChromeOptions()));
			break;
			
		case "firefox":
			//driver=new FirefoxDriver(optionManager.getFirefoxOptions());
			tlDriver.set(new FirefoxDriver(optionManager.getFirefoxOptions()));
			break;
			
		case "edge":
			//driver=new EdgeDriver(optionManager.getEdgeOptions());
			tlDriver.set(new EdgeDriver(optionManager.getEdgeOptions()));
			break;
			
		case "safari":
			//driver=new SafariDriver();
			tlDriver.set(new SafariDriver());
			break;
		
			default:
				System.out.println(AppError.INCORRECT_BROWSER_MESG+ browserName);
				throw new BrowserException(AppError.INCORRECT_BROWSER_MESG+ browserName);
		}
		
		getDriver().manage().window().maximize();
		getDriver().manage().deleteAllCookies();
		getDriver().get(prop.getProperty("url"));
		
		return getDriver();
	}
	
	
	public static WebDriver getDriver() {
		return tlDriver.get();
	}
	
	
//	public Properties initProp() {
//		prop=new Properties();
//		try {
//			FileInputStream ip=new FileInputStream("./src/test/resource/config/config.properties");
//			try {
//				prop.load(ip);
//			} catch (IOException e) {
//				// TODO Auto-generated catch block
//				e.printStackTrace();
//			}
//		} catch (FileNotFoundException e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//		}
//		
//		return prop;
//		
//	}
	
	public Properties initProp() {
		prop = new Properties();
		FileInputStream ip = null;

		String envName = System.getProperty("env");
		// System.out.println("running tests on env: " + envName);
		//Log.info("Running tests on env:  " + envName);

		try {
			if (envName == null) {
				// System.out.println("env is null....hence running tests on QA env");
				//Log.warn("env is null....hence running tests on QA env");
				ip = new FileInputStream("./src/test/resource/config/config.properties");
			} else {
				switch (envName.toLowerCase().trim()) {
				case "qa":
					ip = new FileInputStream("./src/test/resources/config/qa.config.properties");
					break;
				case "dev":
					ip = new FileInputStream("./src/test/resources/config/dev.config.properties");
					break;
				case "stage":
					ip = new FileInputStream("./src/test/resources/config/stage.config.properties");
					break;
				case "uat":
					ip = new FileInputStream("./src/test/resources/config/uat.config.properties");
					break;
				case "prod":
					ip = new FileInputStream("./src/test/resources/config/config.properties");
					break;

				default:
					// System.out.println("plz pass the right env name..." + envName);
					//Log.error("plz pass the right env name..." + envName);
					throw new FrameworkException("INVALID ENV NAME");
				}
			}

			prop.load(ip);
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}

		return prop;
	}
	
	public static String getScreenshot(String methodName) {
		File srcFile = ((TakesScreenshot) getDriver()).getScreenshotAs(OutputType.FILE);// temp dir
		String path = System.getProperty("user.dir") + "/screenshot/" + methodName + "_" + System.currentTimeMillis()
				+ ".png";
		File destination = new File(path);
		try {
			FileHandler.copy(srcFile, destination);
		} catch (IOException e) {
			e.printStackTrace();
		}

		return path;
	}

}
