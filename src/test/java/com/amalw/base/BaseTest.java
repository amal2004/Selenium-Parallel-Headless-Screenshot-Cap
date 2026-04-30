package com.amalw.base;

import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Optional;
import org.testng.annotations.Parameters;

import com.amalw.driver.DriverFactory;


/* BaseTest is the foundation for all test classes. All test classes extend the BaseTest */

public class BaseTest {

	// Runs before each test method
	@BeforeMethod(alwaysRun = true)
	@Parameters("browser")
	public void setUp(@Optional("chrome") String browser) throws Exception {

		// Initialize WebDriver for current thread
		DriverFactory.initDriver(browser);
	}

	// Runs after each test method
	@AfterMethod(alwaysRun = true)
	public void tearDown() {
		
		// Quit WebDriver and clean ThreadLocal
		DriverFactory.quitDriver();
	}

}
