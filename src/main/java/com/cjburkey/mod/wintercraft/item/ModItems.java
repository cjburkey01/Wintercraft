package com.cjburkey.mod.wintercraft.item;

import java.util.ArrayList;
import java.util.List;
import com.cjburkey.mod.wintercraft.Log;
import com.cjburkey.mod.wintercraft.ModInfo;
import com.cjburkey.mod.wintercraft.tab.ModTabs;
import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.block.model.ModelResourceLocation;
import net.minecraft.inventory.EntityEquipmentSlot;
import net.minecraft.item.Item;
import net.minecraft.item.ItemBlock;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.fml.common.registry.GameRegistry;

public final class ModItems {
	
	private static final List<Item> items = new ArrayList<>();
	
	public static Item itemCandyCane;
	public static Item itemWrappingPaper;
	
	public static Item itemWinterHelmet;
	public static Item itemWinterChestplate;
	public static Item itemWinterLeggings;
	public static Item itemWinterBoots;
	
	public static Item itemWinterSword;
	public static Item itemWinterPick;
	public static Item itemWinterAxe;
	public static Item itemWinterShovel;
	public static Item itemWinterHoe;
	
	public static void commonPreinit() {
		itemCandyCane = registerItem(new ItemCandyCane(), "item_candy_cane");
		itemWrappingPaper = registerItem(new Item(), "item_wrapping_paper");

		itemWinterHelmet = registerItem(new ItemWinterArmor(1, EntityEquipmentSlot.HEAD), "item_winter_helmet");
		itemWinterChestplate = registerItem(new ItemWinterArmor(1, EntityEquipmentSlot.CHEST), "item_winter_chestplate");
		itemWinterLeggings = registerItem(new ItemWinterArmor(2, EntityEquipmentSlot.LEGS), "item_winter_leggings");
		itemWinterBoots = registerItem(new ItemWinterArmor(1, EntityEquipmentSlot.FEET), "item_winter_boots");
		
		itemWinterSword = registerItem(new ItemWinterSword(), "item_winter_sword");
		itemWinterPick = registerItem(new ItemWinterPick(), "item_winter_pickaxe");
		itemWinterAxe = registerItem(new ItemWinterAxe(), "item_winter_axe");
		itemWinterShovel = registerItem(new ItemWinterShovel(), "item_winter_shovel");
		itemWinterHoe = registerItem(new ItemWinterHoe(), "item_winter_hoe");
	}
	
	public static void clientInit() {
		for(Item item : items) registerRender(item);
	}
	
	private static void registerRender(Item item) {
		Minecraft.getMinecraft().getRenderItem().getItemModelMesher().register(item, 0, new ModelResourceLocation(item.getRegistryName(), "inventory"));
	}
	
	public static Item registerItem(Item item, String name) {
		item.setUnlocalizedName(name);
		item.setRegistryName(new ResourceLocation(ModInfo.ID, name));
		if(!(item instanceof ItemBlock)) item.setCreativeTab(ModTabs.tabItems);
		GameRegistry.register(item);
		items.add(item);
		Log.info("Registered: " + name);
		return item;
	}
	
}