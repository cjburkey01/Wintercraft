package com.cjburkey.mod.wintercraft.cfg;

import java.io.File;
import net.minecraftforge.common.config.Configuration;

public class ModConfigHandler {
	
	private static boolean frozenWorld;
	private static File file;
	private static Configuration cfg;
	
	public static final void commonPreinit(File cfgFile) {
		file = cfgFile;
		syncConfig();
	}
	
	public static final void syncConfig() {
		cfg = new Configuration(file);
		cfg.load();
		frozenWorld = cfg.getBoolean("frozenWorld", "World", true, "Snows in every biome, also spawns with snow cover.  Works retroactively and across all worlds.");
		if(cfg.hasChanged()) cfg.save();
	}
	
	public static Configuration getConfig() { return cfg; }
	public static boolean isWorldFrozen() { return frozenWorld; }
	
}