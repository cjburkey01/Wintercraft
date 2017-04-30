package com.cjburkey.mod.wonderland.crafting;

import java.util.ArrayList;
import java.util.List;
import com.cjburkey.mod.wonderland.block.ModBlocks;
import com.cjburkey.mod.wonderland.item.ModItems;
import net.minecraft.init.Blocks;
import net.minecraft.init.Items;
import net.minecraft.item.ItemStack;
import net.minecraft.item.crafting.CraftingManager;
import net.minecraft.item.crafting.IRecipe;
import net.minecraftforge.fml.common.registry.GameRegistry;
import net.minecraftforge.oredict.ShapedOreRecipe;

public final class ModCrafting {
	
	public static void commonInit() {
		addNormalItems();
		addNormalBlocks();
		addTools();
	}
	
	private static void addNormalItems() {
		registerCrafting(new ItemStack(ModItems.itemCandyCane, 1), new Object[] {
			"xxy",
			"yxy",
			"yxy",
			Character.valueOf('x'), Items.SUGAR,
			Character.valueOf('y'), "dyeRed"
		});
		registerCrafting(new ItemStack(ModItems.itemWrappingPaper, 1), new Object[] {
			"xyx",
			"yxy",
			"xyx",
			Character.valueOf('x'), "dyeGreen",
			Character.valueOf('y'), "dyeRed"
		});
	}
	
	private static void addNormalBlocks() {
		registerCrafting(new ItemStack(ModBlocks.blockWreath, 1), new Object[] {
			" x ",
			"xyx",
			" x ",
			Character.valueOf('x'), "treeLeaves",
			Character.valueOf('y'), "stickWood"
		});
		registerCrafting(new ItemStack(ModBlocks.blockGift, 1), new Object[] {
			"yyy",
			"yxy",
			"yyy",
			Character.valueOf('x'), Blocks.CHEST,
			Character.valueOf('y'), ModItems.itemWrappingPaper
		});
	}
	
	private static void addTools() {
		registerCrafting(new ItemStack(ModItems.itemWinterSword, 1), new Object[] {
			"x",
			"x",
			"y",
			Character.valueOf('x'), ModItems.itemCandyCane,
			Character.valueOf('y'), "stickWood"
		});
		registerCrafting(new ItemStack(ModItems.itemWinterPick, 1), new Object[] {
			"xxx",
			" y ",
			" y ",
			Character.valueOf('x'), ModItems.itemCandyCane,
			Character.valueOf('y'), "stickWood"
		});
		registerCrafting(new ItemStack(ModItems.itemWinterAxe, 1), new Object[] {
			"xx",
			"xy",
			" y",
			Character.valueOf('x'), ModItems.itemCandyCane,
			Character.valueOf('y'), "stickWood"
		});
		registerCrafting(new ItemStack(ModItems.itemWinterAxe, 1), new Object[] {
			"xx",
			"yx",
			"y ",
			Character.valueOf('x'), ModItems.itemCandyCane,
			Character.valueOf('y'), "stickWood"
		});
		registerCrafting(new ItemStack(ModItems.itemWinterShovel, 1), new Object[] {
			"x",
			"y",
			"y",
			Character.valueOf('x'), ModItems.itemCandyCane,
			Character.valueOf('y'), "stickWood"
		});
		registerCrafting(new ItemStack(ModItems.itemWinterHoe, 1), new Object[] {
			"xx",
			" y",
			" y",
			Character.valueOf('x'), ModItems.itemCandyCane,
			Character.valueOf('y'), "stickWood"
		});
		registerCrafting(new ItemStack(ModItems.itemWinterHoe, 1), new Object[] {
			"xx",
			"y ",
			"y ",
			Character.valueOf('x'), ModItems.itemCandyCane,
			Character.valueOf('y'), "stickWood"
		});
	}
	
	private static void registerCrafting(ItemStack out, Object[] crafting) {
		GameRegistry.addRecipe(new ShapedOreRecipe(out, crafting));
	}
	
	private static void removeCrafting(ItemStack item) {
		List<IRecipe> toRemove = new ArrayList<>();
		for(IRecipe r : CraftingManager.getInstance().getRecipeList()) if(r.getRecipeOutput().isItemEqual(item)) toRemove.add(r);
		for(IRecipe r : toRemove) CraftingManager.getInstance().getRecipeList().remove(r);
	}
	
}