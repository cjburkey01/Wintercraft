package com.cjburkey.mod.wintercraft.material;

import com.cjburkey.mod.wintercraft.ModInfo;
import net.minecraft.init.SoundEvents;
import net.minecraft.item.Item.ToolMaterial;
import net.minecraft.item.ItemArmor.ArmorMaterial;
import net.minecraftforge.common.util.EnumHelper;

public final class ModMaterials {
	
	public static ArmorMaterial materialArmorWinter;
	public static ToolMaterial materialToolWinter;
	
	public static void commonPreinit() {
		materialArmorWinter = EnumHelper.addArmorMaterial("material_winter", ModInfo.ID + ":material_winter", 10, new int[] { 1, 3, 2, 1 }, 0, SoundEvents.ITEM_ARMOR_EQUIP_GENERIC, 5);
		materialToolWinter = EnumHelper.addToolMaterial("material_tool_winter", 1, 100, 6.0F, 1.5F, 0);
	}
	
}