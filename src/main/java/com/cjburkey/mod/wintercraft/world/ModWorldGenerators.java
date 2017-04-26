package com.cjburkey.mod.wintercraft.world;

import net.minecraftforge.fml.common.registry.GameRegistry;

public class ModWorldGenerators {
	
	public static final void commonPreinit() {
		GameRegistry.registerWorldGenerator(new WorldGenCandyCane(), 99);
	}
	
}