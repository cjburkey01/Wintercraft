package com.cjburkey.mod.wonderland.loot;

import net.minecraft.init.Items;
import net.minecraft.item.ItemStack;

public final class ModLoots {
	
	public static void commonPreinit() {
		
	}
	
	public static ItemStack randomLoot() {
		return new ItemStack(Items.DIAMOND, 3);
	}
	
}