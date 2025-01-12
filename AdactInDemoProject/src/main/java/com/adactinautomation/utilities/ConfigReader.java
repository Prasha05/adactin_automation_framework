package com.adactinautomation.utilities;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;

public class ConfigReader {
	static Properties prop;

	// Static block to load the properties file
	static {
		try {
			// Dynamically get the file path
			String filepath = System.getProperty("user.dir") + "/src/test/resources/ConfigFiles/config.properties";
			FileInputStream fis = new FileInputStream(filepath);
			prop = new Properties();
			prop.load(fis);
		} catch (IOException e) {
			e.printStackTrace();
			throw new RuntimeException("Failed to load config.properties file.");
		}
	}

	// Method to get property values
	public static String getProperty(String key) {
		return prop.getProperty(key);

	}
}
