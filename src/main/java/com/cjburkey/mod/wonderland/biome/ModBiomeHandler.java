package com.cjburkey.mod.wonderland.biome;

import java.lang.reflect.Field;
import com.cjburkey.mod.wonderland.Log;
import com.cjburkey.mod.wonderland.cfg.ModConfigHandler;
import net.minecraft.init.Biomes;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.registry.RegistryNamespaced;
import net.minecraft.world.biome.Biome;
import net.minecraftforge.common.BiomeManager;
import net.minecraftforge.common.BiomeManager.BiomeEntry;
import net.minecraftforge.common.BiomeManager.BiomeType;

public final class ModBiomeHandler {
	
	public static Biome biomeWonderland;
	
	public static void commonInit() {
		biomeWonderland = new BiomeWonderland();
		BiomeManager.addBiome(BiomeType.ICY, new BiomeEntry(biomeWonderland, 30));
	}
	
	public static void commonPostinit() {
		biomeTemperatures();
	}
	
	private static void biomeTemperatures() {
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