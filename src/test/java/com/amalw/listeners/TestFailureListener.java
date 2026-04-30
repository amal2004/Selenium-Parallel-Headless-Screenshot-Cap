package com.amalw.listeners;

import org.openqa.selenium.WebDriver;
import org.testng.ITestListener;
import org.testng.ITestResult;
import com.amalw.driver.DriverFactory;
import com.amalw.utils.ScreenshotManager;

// TestNG listener responsible for capturing screenshots 
// automatically on test failure and skip events
public class TestFailureListener implements ITestListener {

	@Override
	public void onTestFailure(ITestResult result) {
		// capture screenshot when fails
		capture(result, "FAILED");

	}

	@Override
	public void onTestSkipped(ITestResult result) {
		// capture screenshot when skips
		capture(result, "SKIPPED");
	}

	// Implementation of capture screenshot based on test result
	private void capture(ITestResult result, String status) {

		// Using the thread safe web driver instance
		WebDriver driver = DriverFactory.getDriver();

		if (driver == null) {
			System.err.println("Driver is null, skipping screenshot...");
			return;
		}

		// Extract test metadata for structured naming
		String className = result.getTestClass().getRealClass().getSimpleName();
		String methodName = result.getMethod().getMethodName();

		try {
			String path = ScreenshotManager.screenCapture(driver, className, methodName);
			System.out.println(status + " Screenshot: " + path);

		} catch (Exception e) {
			System.err.println("Screenshot capture failed: " + e.getMessage());
		}

	}

}
