package com.cjburkey.mod.wonderland.biome;

import net.minecraft.init.Blocks;
import net.minecraft.world.biome.Biome;

public final class BiomeWonderland extends Biome {
	
	public BiomeWonderland() {
		super(getProps());
		
		this.spawnableCaveCreatureList.clear();
		this.spawnableCreatureList.clear();
		this.spawnableMonsterList.clear();
		this.spawnableWaterCreatureList.clear();
		
		this.topBlock = Blocks.SNOW.getDefaultState();
		this.fillerBlock = Blocks.ICE.getDefaultState();
	}
	
	public static BiomeProperties getProps() {
		BiomeProperties props = new BiomeProperties("winter_wonderland");
		props.setBaseHeight(0.1f);
		props.setHeightVariation(0.5f);
		props.setTemperature(-10.0f);
		props.setWaterColor(0x79c9f7);
		
		return props;
	}
	
	public int getSkyColorByTemp(float temp) {
		return 0x555555;
	}
	
}