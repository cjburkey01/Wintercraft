package com.cjburkey.mod.wintercraft.crafting;

import com.cjburkey.mod.wintercraft.item.ModItems;
import net.minecraft.init.Items;
import net.minecraft.item.ItemStack;
import net.minecraftforge.fml.common.registry.GameRegistry;
import net.minecraftforge.oredict.ShapedOreRecipe;

public class ModCrafting {
	
	public static final void commonInit() {
		registerCrafting(new ItemStack(ModItems.itemCandyCane), new Object[] {
			"xxy",
			"yxy",
			"yxy",
			Character.valueOf('x'), Items.SUGAR,
			Character.valueOf('y'), "dyeRed"
		});
	}
	
	public static final void registerCrafting(ItemStack out, Object[] crafting) {
		GameRegistry.addRecipe(new ShapedOreRecipe(out, crafting));
	}
	
}