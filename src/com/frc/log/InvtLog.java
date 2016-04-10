package com.frc.log;

import org.apache.log4j.Logger;

public class InvtLog {
	private static boolean isOn = false;
	private static Logger logger = Logger.getLogger("wf.investment");
	
	public static Logger getLogger() {
		return logger;
	}
	public static void setLogger(Logger logger) {
		InvtLog.logger = logger;
	}
	public static void info(Object obj, String message) {
		if (isOn) {
			logger.info(message);
		}
	}
	public static void debug(Object obj, String message) {
		if (isOn) {
			logger.debug(message);
		}
	}
	public static void error(Object obj, String message) {
		if (isOn) {
			logger.error(message);
		}
	}
	public static void fatal(Object obj, String message) {
		if (isOn) {
			logger.fatal(message);
		}
	}
	public static void enter(Object obj, String message) {
		String log = String.format("[enter] %s", message);
		if (isOn) {
			logger.info(log);
		}
	}
	public static void exit(Object obj, String message) {
		String log = String.format("[exit] %s", message);
		if (isOn) {
			logger.info(log);
		}
	}
}
