package com.cjburkey.mod.wintercraft.item;

import com.cjburkey.mod.wintercraft.material.ModMaterials;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.inventory.EntityEquipmentSlot;
import net.minecraft.item.ItemArmor;
import net.minecraft.item.ItemStack;
import net.minecraft.world.World;

public final class ItemWinterArmor extends ItemArmor {
	
	public ItemWinterArmor(int renderIndexIn, EntityEquipmentSlot type) {
		super(ModMaterials.materialArmorWinter, renderIndexIn, type);
	}
	
	public void onArmorTick(World world, EntityPlayer player, ItemStack stack) {
		if(stack.getItemDamage() >= stack.getMaxDamage()) {
			stack.setCount(0);
		}
	}
	
}