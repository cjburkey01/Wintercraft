package com.cjburkey.mod.wintercraft.loot;

import net.minecraft.init.Items;
import net.minecraft.item.ItemStack;

public class ModLoots {
	
	public static void commonPreinit() {
		
	}
	
	public static ItemStack randomLoot() {
		return new ItemStack(Items.DIAMOND, 3);
	}
	
}