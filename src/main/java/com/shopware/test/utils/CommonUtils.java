package com.shopware.test.utils;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.Properties;

public class CommonUtils {
	
	public static String getValueProperties(String property) {
		Properties properties;
		
	   try {
			properties = new Properties();
			
			 String fileSeparator = System.getProperty("file.separator");
			 ClassLoader classLoader = ClassLoader.getSystemClassLoader();

			properties.load(new FileReader(new File(classLoader.getResource("configure" + fileSeparator + "config.properties").getFile())));
			 return properties.getProperty(property);
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	   return null;
	}
}
