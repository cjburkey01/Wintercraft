package com.cjburkey.mod.wintercraft.tab;

import com.cjburkey.mod.wintercraft.block.ModBlocks;
import com.cjburkey.mod.wintercraft.item.ModItems;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.item.ItemStack;

public class ModTabs {
	
	public static CreativeTabs tabItems;
	public static CreativeTabs tabBlocks;
	
	public static final void commonPreinit() {
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