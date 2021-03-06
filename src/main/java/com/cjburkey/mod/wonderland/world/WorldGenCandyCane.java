package com.cjburkey.mod.wonderland.world;

import java.util.Random;
import com.cjburkey.mod.wonderland.Util;
import com.cjburkey.mod.wonderland.block.ModBlocks;
import com.cjburkey.mod.wonderland.cfg.ModConfigHandler;
import net.minecraft.block.Block;
import net.minecraft.block.state.IBlockState;
import net.minecraft.init.Blocks;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import net.minecraft.world.chunk.IChunkGenerator;
import net.minecraft.world.chunk.IChunkProvider;
import net.minecraftforge.fml.common.IWorldGenerator;

public final class WorldGenCandyCane implements IWorldGenerator {
	
	public void generate(Random random, int chunkX, int chunkZ, World world, IChunkGenerator chunkGenerator, IChunkProvider chunkProvider) {
		int id = world.provider.getDimension();
		if(id == 0 || id == ModConfigHandler.wonderlandDimensionId) generate(world, random, chunkX * 16, chunkZ * 16);
	}
	
	private void generate(World world, Random rand, int i, int j) {
		rand = new Random();
		if(rand.nextInt(ModConfigHandler.candyCaneChance) == 0) {
			int x = i + rand.nextInt(16);
			int z = j + rand.nextInt(16);
			int y = Util.getTallestPoint(world, x, z) + 1;
			for(int yP = 0; yP < Util.randomRange(rand, ModConfigHandler.candyCaneMinHeight, ModConfigHandler.candyCaneMaxHeight); yP ++) {
				world.setBlockState(new BlockPos(x, y + yP, z), ModBlocks.blockCandyCane.getDefaultState());
			}
		}
	}
	
}