package com.amalw.config;

import java.io.InputStream;
import java.util.Properties;

import com.amalw.exceptions.FrameworkException;

/* ConfigManager is responsible for loading and managing configuration values
from the config.properties file. */

public class ConfigManager {

	// Properties object to store key-value pairs
	private static final Properties PROPS = new Properties();
	
	//  location of config file
	private static final String CONFIG_FILE = "/config.properties";

	// Static block executes once to load configuration
	static {
		try (InputStream is = ConfigManager.class.getResourceAsStream(CONFIG_FILE)) {
			if (is == null) {
				throw new FrameworkException("Config file not found: " + CONFIG_FILE);
			}
			PROPS.load(is);
		} catch (Exception e) {
			throw new FrameworkException("Failed to load config file");
		}
	}

	private ConfigManager() {
	}

	// Get required property by key
	public static String get(String key) {
		
		// First check system properties from cmd line (-Dkey=value)
		String value = System.getProperty(key);
		if (value == null) {
			// Then check properties file
			value = PROPS.getProperty(key);
			System.out.println("Using default for key: " + key);
		}
		// Validate value
		if (value == null || value.trim().isEmpty()) {
			throw new FrameworkException("Missing config key: " + key);
		}
		return value.trim();
	}

	// Get property with default value
	public static String get(String key, String defaultValue) {

		// Check system property first (-Dkey=value)
		String value = System.getProperty(key);
		
		if (value == null || value.isBlank()) {
			value = PROPS.getProperty(key, defaultValue);
		}
		return value.trim();

	}

	// Get boolean property with default value (ex: headless=true)
	public static boolean getBoolean(String key, boolean defaultValue) {
		return Boolean.parseBoolean(get(key, String.valueOf(defaultValue)));
	}

	// Get integer property with default value (ex: timeout=30)
	public static int getInt(String key, int defaultValue) {
		try {
			return Integer.parseInt(get(key, String.valueOf(defaultValue)));
		} catch (NumberFormatException e) {
			throw new FrameworkException("Invalid integer for key: " + key);
		}
	}

}
