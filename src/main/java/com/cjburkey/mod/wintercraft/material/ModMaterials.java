package com.cjburkey.mod.wintercraft.material;

import com.cjburkey.mod.wintercraft.ModInfo;
import net.minecraft.init.SoundEvents;
import net.minecraft.item.ItemArmor.ArmorMaterial;
import net.minecraftforge.common.util.EnumHelper;

public class ModMaterials {
	
	public static ArmorMaterial materialWinter;
	
	public static final void commonPreinit() {
		materialWinter = EnumHelper.addArmorMaterial("material_winter", ModInfo.ID + ":material_winter", 10, new int[] { 1, 3, 2, 1 }, 0, SoundEvents.ITEM_ARMOR_EQUIP_GENERIC, 5);
	}
	
}