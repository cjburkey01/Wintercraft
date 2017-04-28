package com.cjburkey.mod.wintercraft.cfg;

import java.io.File;
import net.minecraftforge.common.config.Configuration;

public final class ModConfigHandler {
	
	private static File file;
	private static Configuration cfg;

	public static boolean frozenWorld;
	public static int candyCaneChance;
	public static int candyCaneMinHeight;
	public static int candyCaneMaxHeight;
	public static float iceSkatesSpeed;
	
	public static void commonPreinit(File cfgFile) {
		file = cfgFile;
		syncConfig();
	}
	
	public static void syncConfig() {
		cfg = new Configuration(file);
		cfg.load();
		
		frozenWorld = cfg.getBoolean("frozenWorld", "World", true, "Snows in every biome, also spawns with snow cover.  Works retroactively and across all worlds.");
		candyCaneChance = cfg.getInt("candyCaneChange", "World", 10, 0, 100, "Chance per chunk is (1 / this number)");
		candyCaneMinHeight = cfg.getInt("candyCaneMinHeight", "World", 1, 1, 5, "Lowest height of a candy cane. Must be below or equal to candyCaneMaxHeight");
		candyCaneMaxHeight = cfg.getInt("candyCaneMaxHeight", "World", 10, 1, 20, "Highest height of a candy cane. Must be above or equal to candyCaneMinHeight");
		iceSkatesSpeed = cfg.getFloat("iceSkatesSpeed", "Armor", 0.35f, 0.2f, 1.0f, "The speed at which the ice skates move the player. 0.2 is default player speed.");
		
		if(cfg.hasChanged()) cfg.save();
	}
	
	public static Configuration getConfig() { return cfg; }
	
}