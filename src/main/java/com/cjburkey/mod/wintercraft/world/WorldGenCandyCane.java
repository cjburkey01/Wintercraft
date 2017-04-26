package com.cjburkey.mod.wintercraft.world;

import java.util.Random;
import com.cjburkey.mod.wintercraft.block.ModBlocks;
import net.minecraft.block.Block;
import net.minecraft.block.state.IBlockState;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import net.minecraft.world.chunk.IChunkGenerator;
import net.minecraft.world.chunk.IChunkProvider;
import net.minecraftforge.fml.common.IWorldGenerator;

public class WorldGenCandyCane implements IWorldGenerator {
	
	public void generate(Random random, int chunkX, int chunkZ, World world, IChunkGenerator chunkGenerator, IChunkProvider chunkProvider) {
		generate(world, random, chunkX * 16, chunkZ * 16);
	}
	
	private void generate(World world, Random rand, int i, int j) {
		if(rand.nextInt(10) == 2) {
			int x = i + rand.nextInt(16);
			int z = j + rand.nextInt(16);
			int y = getTallestPoint(world, x, z) + 1;
			for(int yP = 0; yP < 1 + rand.nextInt(10); yP ++) {
				world.setBlockState(new BlockPos(x, y + yP, z), ModBlocks.blockCandyCane.getDefaultState());
			}
		}
	}
	
	private int getTallestPoint(World world, int x, int z) {
		for(int y = world.getHeight(); y > 0; y --) {
			BlockPos pos = new BlockPos(x, y, z);
			IBlockState state = world.getBlockState(pos);
			if(!state.getBlock().isAir(state, world, pos) && !state.getBlock().isReplaceable(world, pos) && state.getBlock().causesSuffocation(state)) {
				return y;
			}
		}
		return world.getHeight();
	}
	
}