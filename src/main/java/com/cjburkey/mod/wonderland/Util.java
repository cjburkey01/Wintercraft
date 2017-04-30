package com.cjburkey.mod.wonderland;

import java.util.Random;
import net.minecraft.block.state.IBlockState;
import net.minecraft.entity.Entity;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.text.TextComponentString;
import net.minecraft.util.text.TextComponentTranslation;
import net.minecraft.world.World;

public final class Util {
	
	public static int randomRange(Random rand, int min, int max) {
		return rand.nextInt((max - min) + 1) + min;
	}
	
	public static void sendMsg(Entity to, String msg) {
		to.sendMessage(new TextComponentString(msg));
	}
	
	public static String translate(String unloc) {
		return new TextComponentTranslation(unloc).getFormattedText();
	}
	
	public static int getTallestPoint(World world, int x, int z) {
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