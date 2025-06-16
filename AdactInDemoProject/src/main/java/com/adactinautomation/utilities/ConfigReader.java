package com.adactinautomation.utilities;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;

import org.apache.logging.log4j.Logger;

public class ConfigReader {
	static Properties prop;
	private static final Logger log = LoggerHelper.getLogger(ConfigReader.class);

	// Static block to load the properties file
	static {
		try {
			String filepath = System.getProperty("user.dir") + "/src/test/resources/ConfigFiles/config.properties";
			FileInputStream fis = new FileInputStream(filepath);
			prop = new Properties();
			prop.load(fis);
			log.info("Configuration file loaded successfully from: " + filepath);
		} catch (IOException e) {
			log.error("Failed to load config.properties file: " + e.getMessage());
			throw new RuntimeException("Failed to load config.properties file.");
		}
	}

	// Method to get property values
	public static String getProperty(String key) {
		String value = prop.getProperty(key);
		if (value != null) {
			log.debug("Retrieved config property: " + key + " = " + value);
		} else {
			log.warn("Requested config property '" + key + "' not found.");
		}
		return value;
	}
}
