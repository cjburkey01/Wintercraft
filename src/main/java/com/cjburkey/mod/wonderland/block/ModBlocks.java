package com.cjburkey.mod.wonderland.block;

import java.util.HashMap;
import com.cjburkey.mod.wonderland.ModInfo;
import com.cjburkey.mod.wonderland.item.ItemBlockGift;
import com.cjburkey.mod.wonderland.item.ModItems;
import com.cjburkey.mod.wonderland.tab.ModTabs;
import net.minecraft.block.Block;
import net.minecraft.item.ItemBlock;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.fml.common.registry.GameRegistry;

public final class ModBlocks {
	
	private static final HashMap<Block, ItemBlock> blocks = new HashMap<>();
	
	public static Block blockCandyCane;
	public static Block blockWreath;
	public static Block blockGift;
	public static Block blockStocking;
	public static Block blockCandyLog;
	public static Block blockCandyLeaves;
	
	public static void commonPreinit() {
		blockCandyCane = registerBlock(new BlockCandyCane(), "block_candy_cane");
		blockWreath = registerBlock(new BlockWreath(), "block_wreath");
		blockGift = registerBlock(blockGift = new BlockGift(), new ItemBlockGift(blockGift), "block_gift");
		blockStocking = registerBlock(new BlockStocking(), "block_stocking");
		blockCandyLog = registerBlock(new BlockCandyLog(), "block_candy_log");
		blockCandyLeaves = registerBlock(new BlockCandyLeaves(), "block_candy_leaves");
	}
	
	private static Block registerBlock(Block block, String name) {
		return registerBlock(block, new ItemBlock(block), name);
	}
	
	private static Block registerBlock(Block block, ItemBlock item, String name) {
		block.setUnlocalizedName(name);
		block.setRegistryName(new ResourceLocation(ModInfo.ID, name));
		block.setCreativeTab(ModTabs.tabBlocks);
		GameRegistry.register(block);
		blocks.put(block, (ItemBlock) ModItems.registerItem(item, name));
		return block;
	}
	
	public static ItemBlock getItem(Block block) {
		return blocks.get(block);
	}
	
}