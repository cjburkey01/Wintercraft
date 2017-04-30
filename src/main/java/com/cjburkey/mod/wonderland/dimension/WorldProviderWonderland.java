package com.cjburkey.mod.wonderland.dimension;

import javax.annotation.Nullable;
import com.cjburkey.mod.wonderland.biome.ModBiomeHandler;
import net.minecraft.util.math.Vec3d;
import net.minecraft.world.DimensionType;
import net.minecraft.world.WorldProvider;
import net.minecraft.world.biome.BiomeProviderSingle;
import net.minecraft.world.chunk.IChunkGenerator;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

public class WorldProviderWonderland extends WorldProvider {
	
	protected void init() {
		this.biomeProvider = new BiomeProviderSingle(ModBiomeHandler.biomeWonderland);
		this.hasNoSky = false;
		this.hasSkyLight = true;
	}
	
	public float calculateCelestialAngle(long worldTime, float partialTicks) {
		return 0.0f;
	}
	
	@SideOnly(Side.CLIENT)
	public float[] calcSunriseSunsetColors(float celestialAngle, float partialTicks) {
		return null;
	}
	
	public boolean isSkyColored() {
		return false;
	}

	public boolean isSurfaceWorld() {
		return false;
	}
	
	public boolean canCoordinateBeSpawn(int x, int z) {
		return false;
	}
	
	public boolean canRespawnHere() {
		return false;
	}
	
	@SideOnly(Side.CLIENT)
	public boolean doesXZShowFog(int x, int z) {
		return false;
	}

	@SideOnly(Side.CLIENT)
	public Vec3d getFogColor(float x, float z) {
		return new Vec3d(1.0d, 1.0d, 1.0d);
	}
	
	public IChunkGenerator createChunkGenerator() {
		return new ChunkGeneratorWonderland(world, world.getSeed());
	}

	public DimensionType getDimensionType() {
		return ModDimensions.wonderlandDimensionType;
	}
	
}