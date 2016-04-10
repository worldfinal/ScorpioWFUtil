package com.frc.log;

import org.apache.log4j.Logger;

public class WFCommonLog {
	private static Logger logger = Logger.getLogger("wf.common");
	
	public static void info(Object obj, String message) {
		logger.info(message);
	}
	public static void debug(Object obj, String message) {
		logger.debug(message);
	}
	public static void error(Object obj, String message) {
		logger.error(message);
	}
	public static void fatal(Object obj, String message) {
		logger.fatal(message);
	}
	public static void enter(Object obj, String message) {
		String log = String.format("[enter] %s", message);
		logger.info(log);
	}
	public static void exit(Object obj, String message) {
		String log = String.format("[exit] %s", message);
		logger.info(log);
	}
}
