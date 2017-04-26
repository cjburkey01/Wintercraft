package com.cjburkey.mod.wintercraft.item;

import com.cjburkey.mod.wintercraft.material.ModMaterials;
import net.minecraft.inventory.EntityEquipmentSlot;
import net.minecraft.item.ItemArmor;

public class ItemWinterArmor extends ItemArmor {
	
	public ItemWinterArmor(int renderIndexIn, EntityEquipmentSlot type) {
		super(ModMaterials.materialWinter, renderIndexIn, type);
	}
	
}