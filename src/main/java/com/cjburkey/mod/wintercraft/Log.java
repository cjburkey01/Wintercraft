package com.cjburkey.mod.wintercraft;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class Log {
	
	public static final Logger getLogger() {
		return LogManager.getLogger(ModInfo.ID);
	}
	
	public static final void info(Object msg) {
		getLogger().info(msg + "");
	}
	
}