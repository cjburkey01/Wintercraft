package com.cjburkey.mod.wintercraft.crafting;

import com.cjburkey.mod.wintercraft.item.ModItems;
import net.minecraft.init.Items;
import net.minecraft.item.ItemStack;
import net.minecraftforge.fml.common.registry.GameRegistry;
import net.minecraftforge.oredict.ShapedOreRecipe;

public final class ModCrafting {
	
	public static void commonInit() {
		addNormalItems();
		addTools();
	}
	
	private static void addNormalItems() {
		registerCrafting(new ItemStack(ModItems.itemCandyCane), new Object[] {
			"xxy",
			"yxy",
			"yxy",
			Character.valueOf('x'), Items.SUGAR,
			Character.valueOf('y'), "dyeRed"
		});
	}
	
	private static void addTools() {
		registerCrafting(new ItemStack(ModItems.itemWinterSword), new Object[] {
			"x",
			"x",
			"y",
			Character.valueOf('x'), ModItems.itemCandyCane,
			Character.valueOf('y'), "stickWood"
		});
		registerCrafting(new ItemStack(ModItems.itemWinterPick), new Object[] {
			"xxx",
			" y ",
			" y ",
			Character.valueOf('x'), ModItems.itemCandyCane,
			Character.valueOf('y'), "stickWood"
		});
		registerCrafting(new ItemStack(ModItems.itemWinterAxe), new Object[] {
			"xx",
			"xy",
			" y",
			Character.valueOf('x'), ModItems.itemCandyCane,
			Character.valueOf('y'), "stickWood"
		});
		registerCrafting(new ItemStack(ModItems.itemWinterAxe), new Object[] {
			"xx",
			"yx",
			"y ",
			Character.valueOf('x'), ModItems.itemCandyCane,
			Character.valueOf('y'), "stickWood"
		});
		registerCrafting(new ItemStack(ModItems.itemWinterShovel), new Object[] {
			"x",
			"y",
			"y",
			Character.valueOf('x'), ModItems.itemCandyCane,
			Character.valueOf('y'), "stickWood"
		});
		registerCrafting(new ItemStack(ModItems.itemWinterHoe), new Object[] {
			"xx",
			" y",
			" y",
			Character.valueOf('x'), ModItems.itemCandyCane,
			Character.valueOf('y'), "stickWood"
		});
		registerCrafting(new ItemStack(ModItems.itemWinterHoe), new Object[] {
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
	
}