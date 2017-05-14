package com.cjburkey.mod.wonderland.world;

import java.util.Random;
import com.cjburkey.mod.wonderland.Util;
import com.cjburkey.mod.wonderland.block.ModBlocks;
import com.cjburkey.mod.wonderland.cfg.ModConfigHandler;
import net.minecraft.block.state.IBlockState;
import net.minecraft.init.Blocks;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import net.minecraft.world.chunk.IChunkGenerator;
import net.minecraft.world.chunk.IChunkProvider;
import net.minecraftforge.fml.common.IWorldGenerator;

public class WorldGenCandyTree implements IWorldGenerator {
	
	public void generate(Random random, int chunkX, int chunkZ, World world, IChunkGenerator chunkGenerator, IChunkProvider chunkProvider) {
		int id = world.provider.getDimension();
		if(id == ModConfigHandler.wonderlandDimensionId) generate(world, random, chunkX * 16, chunkZ * 16);
	}
	
	private void generate(World world, Random rand, int i, int j) {
		rand = new Random();
		if(rand.nextInt(ModConfigHandler.candyTreeChance) == 0) {
			int x = i + rand.nextInt(16);
			int z = j + rand.nextInt(16);
			int y = Util.getTallestPoint(world, x, z) + 1;
			int topOfWood = y;
			int height;
			topOfWood += (height = Util.randomRange(rand, ModConfigHandler.candyTreeMinHeight, ModConfigHandler.candyTreeMaxHeight));
			for(int yP = 0; yP < height; yP ++) {
				world.setBlockState(new BlockPos(x, y + yP, z), ModBlocks.blockCandyLog.getDefaultState());
			}
			for(int at = 3; at > 0; at --) {
				for(int xx = -at; xx <= at; xx ++) {
					for(int zz = -at; zz <= at; zz ++) {
						if(!(xx == 0 && zz == 0))
							world.setBlockState(new BlockPos(x + xx, topOfWood - (at - 1), z + zz), ModBlocks.blockCandyLeaves.getDefaultState());
						if((xx == 0 || zz == 0) && at - 1 <= 0)
							world.setBlockState(new BlockPos(x + xx, topOfWood - (at - 1), z + zz), ModBlocks.blockCandyLeaves.getDefaultState());
					}
				}
			}
		}
	}
	
}