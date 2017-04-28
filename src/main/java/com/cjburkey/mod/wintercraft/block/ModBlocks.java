package com.cjburkey.mod.wintercraft.block;

import java.util.HashMap;
import com.cjburkey.mod.wintercraft.ModInfo;
import com.cjburkey.mod.wintercraft.item.ModItems;
import com.cjburkey.mod.wintercraft.tab.ModTabs;
import net.minecraft.block.Block;
import net.minecraft.item.Item;
import net.minecraft.item.ItemBlock;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.fml.common.registry.GameRegistry;

public final class ModBlocks {
	
	private static final HashMap<Block, Item> blocks = new HashMap<>();
	
	public static Block blockCandyCane;
	public static Block blockWreath;
	public static Block blockGift;
	
	public static void commonPreinit() {
		blockCandyCane = registerBlock(new BlockCandyCane(), "block_candy_cane");
		blockWreath = registerBlock(new BlockWreath(), "block_wreath");
		blockGift = registerBlock(new BlockGift(), "block_gift");
	}
	
	private static Block registerBlock(Block block, String name) {
		block.setUnlocalizedName(name);
		block.setRegistryName(new ResourceLocation(ModInfo.ID, name));
		block.setCreativeTab(ModTabs.tabBlocks);
		GameRegistry.register(block);
		Item item = ModItems.registerItem(new ItemBlock(block), name);
		blocks.put(block, item);
		return block;
	}
	
}