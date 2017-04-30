package com.cjburkey.mod.wonderland.biome;

import java.lang.reflect.Field;
import com.cjburkey.mod.wonderland.Log;
import com.cjburkey.mod.wonderland.cfg.ModConfigHandler;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.registry.RegistryNamespaced;
import net.minecraft.world.biome.Biome;

public final class ModBiomeHandler {
	
	public static void commonPostinit() {
		if(ModConfigHandler.frozenWorld) {
			RegistryNamespaced<ResourceLocation, Biome> biomes = Biome.REGISTRY;
			Log.info("Setting to freezing biomes.");
			biomes.forEach(b -> {
				try {
					Field tempField = Biome.class.getDeclaredField("temperature");
					tempField.setAccessible(true);
					tempField.set(b, -10.0f);
					Log.info("Set temperature of " + b.getBiomeName() + " to " + tempField.getFloat(b));
				} catch(Exception e) {
					Log.info("Couldn't overwrite default temperature for biome: " + b.getBiomeName());
				}
			});
			Log.info("Finished freezing biomes.");
		} else {
			Log.info("Not freezing biomes.");
		}
	}
	
}