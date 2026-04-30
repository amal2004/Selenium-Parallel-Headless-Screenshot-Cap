package com.amalw.utils;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.text.SimpleDateFormat;
import java.util.Date;

import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;

import com.amalw.config.ConfigManager;

//ScreenshotManager is a utility class responsible for capturing and storing
//browser screenshots during test execution.

public class ScreenshotManager {
	
	// Reads screenshot base directory from properties file
	private static final String BASE_DIR = ConfigManager.get("screenshot.dir", "screenshots");
	
	// Prevents instantiation 
	private ScreenshotManager() {}
	 
	
	// Captures screenshot, creates unique file name and stores it in the base dir under test name
	public static String screenCapture(WebDriver driver, String testName, String testClass) {
		
		// Defensive check to avoid NullPointerException at runtime
		  if (driver == null) {
	            throw new IllegalArgumentException("Driver is null. Cannot take screenshot.");
	        }
		
		  //Unique timestamp
		  String timeStamp = new SimpleDateFormat("yyyyMMdd_HHmmss").format(new Date());
		  String directory = BASE_DIR + "/" + testClass; // Inside the base directory by test name
		  String fileName = testName + "_" + timeStamp + ".png";

		try {
			Path dirPath = Paths.get(directory);
			Files.createDirectories(dirPath);

			// Screenshot capture with driver support
			File source = ((TakesScreenshot) driver).getScreenshotAs(OutputType.FILE);
			Path destination = dirPath.resolve(fileName);

			//Copy screenshot to target location
			Files.copy(source.toPath(), destination, StandardCopyOption.REPLACE_EXISTING);

			System.out.println("Screenshot saved: " + destination.toAbsolutePath());
			
			// Return the path 
			return destination.toAbsolutePath().toString();

		} catch (IOException e) {
			throw new RuntimeException("Failed to capture screenshot", e);
		}

	}
}
