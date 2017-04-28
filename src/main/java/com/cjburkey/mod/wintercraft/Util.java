package com.cjburkey.mod.wintercraft;

import java.util.Random;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.nbt.NBTTagList;
import net.minecraft.nbt.NBTTagString;

public final class Util {
	
	public static int randomRange(Random rand, int min, int max) {
		return rand.nextInt((max - min) + 1) + min;
	}
	
}