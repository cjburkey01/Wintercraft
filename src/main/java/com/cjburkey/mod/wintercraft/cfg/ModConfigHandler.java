package com.cjburkey.mod.wintercraft.cfg;

import java.io.File;
import net.minecraftforge.common.config.Configuration;

public class ModConfigHandler {
	
	private static File file;
	private static Configuration cfg;

	public static boolean frozenWorld;
	public static int candyCaneChance;
	public static int candyCaneMinHeight;
	public static int candyCaneMaxHeight;
	
	public static final void commonPreinit(File cfgFile) {
		file = cfgFile;
		syncConfig();
	}
	
	public static final void syncConfig() {
		cfg = new Configuration(file);
		cfg.load();
		
		frozenWorld = cfg.getBoolean("frozenWorld", "World", true, "Snows in every biome, also spawns with snow cover.  Works retroactively and across all worlds.");
		candyCaneChance = cfg.getInt("candyCaneChange", "World", 10, 0, 100, "Chance per chunk is (1 / this number)");
		candyCaneMinHeight = cfg.getInt("candyCaneMinHeight", "World", 1, 1, 5, "Lowest height of a candy cane. Must be below or equal to candyCaneMaxHeight");
		candyCaneMaxHeight = cfg.getInt("candyCaneMaxHeight", "World", 10, 1, 20, "Highest height of a candy cane. Must be above or equal to candyCaneMinHeight");
		
		if(cfg.hasChanged()) cfg.save();
	}
	
	public static Configuration getConfig() { return cfg; }
	
}