package com.cjburkey.mod.wintercraft.world;

import net.minecraftforge.fml.common.registry.GameRegistry;

public final class ModWorldGenerators {
	
	public static void commonPreinit() {
		GameRegistry.registerWorldGenerator(new WorldGenCandyCane(), 99);
	}
	
}