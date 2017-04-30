package com.cjburkey.mod.wonderland.tab;

import com.cjburkey.mod.wonderland.block.ModBlocks;
import com.cjburkey.mod.wonderland.item.ModItems;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.item.ItemStack;

public final class ModTabs {
	
	public static CreativeTabs tabItems;
	public static CreativeTabs tabBlocks;
	
	public static void commonPreinit() {
		tabItems = new CreativeTabs("wintercraft_items") {
			public ItemStack getTabIconItem() {
				return new ItemStack(ModItems.itemCandyCane);
			}
		};

		tabBlocks = new CreativeTabs("wintercraft_blocks") {
			public ItemStack getTabIconItem() {
				return new ItemStack(ModBlocks.blockCandyCane);
			}
		};
	}
	
}