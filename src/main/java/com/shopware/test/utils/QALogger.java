package com.shopware.test.utils;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class QALogger {
	static Logger logger;
	
	public static void setLog(Object o) {
		logger = LogManager.getLogger(o.getClass().getSimpleName());
	}
	
	public static void info(String message) {
		logger.info(message);
	}
	
	public static void debug(String message) {
		logger.debug(message);
	}
	
	public static void error(String message) {
		logger.error(message);
	}
	
	public static void error(String message, Throwable e) {
		logger.error(message, e);
	}
	
	
	
}
