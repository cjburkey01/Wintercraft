package com.cjburkey.mod.wintercraft;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public final class Log {
	
	public static Logger getLogger() {
		return LogManager.getLogger(ModInfo.ID);
	}
	
	public static void info(Object msg) {
		getLogger().info(msg + "");
	}
	
}