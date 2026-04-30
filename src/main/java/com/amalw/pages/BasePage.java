package com.amalw.pages;

import org.openqa.selenium.*;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import com.amalw.driver.DriverFactory;

import java.time.Duration;

/* provides common WebDriver operations */

public abstract class BasePage {

	private WebDriverWait wait;
	
	private static final int DEFAULT_TIMEOUT = 15;
	//private WebDriverWait wait;

	protected WebDriver getDriver() {
		return DriverFactory.getDriver();
	}

	// Create WebDriverWait using default timeout
	protected WebDriverWait getWait() {
		
		if (wait == null) {
			wait = new WebDriverWait(getDriver(), Duration.ofSeconds(DEFAULT_TIMEOUT)); 
		}
		return wait;
	}

	// Click an element after waiting until it is clickable
	protected void click(By locator) {
		getWait().until(ExpectedConditions.elementToBeClickable(locator)).click();
	}

	// Type text into an element after clearing existing value
	protected void type(By locator, String text) {
		WebElement element = getWait().until(ExpectedConditions.visibilityOfElementLocated(locator));
		element.clear();
		element.sendKeys(text);
	}

	// Get visible text from an element
	protected String getText(By locator) {
		return getWait().until(ExpectedConditions.visibilityOfElementLocated(locator)).getText();
	}

	// Open the URL to test
	protected void navigateTo(String url) {
		getDriver().get(url);
	}

	// Check if element is displayed within given timeout
	protected boolean isElementDisplayed(By locator, int timeoutSeconds) {
		try {
			new WebDriverWait(getDriver(), Duration.ofSeconds(timeoutSeconds))
					.until(ExpectedConditions.visibilityOfElementLocated(locator));
			return true;
		} catch (TimeoutException e) {
			return false;
		}
	}

	protected void waitForElementVisible(By locator) {
		getWait().until(ExpectedConditions.visibilityOfElementLocated(locator));
	}
}