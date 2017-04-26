package com.cjburkey.mod.wintercraft.item;

import java.util.ArrayList;
import java.util.List;
import com.cjburkey.mod.wintercraft.ModInfo;
import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.block.model.ModelResourceLocation;
import net.minecraft.item.Item;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.fml.common.registry.GameRegistry;

public class ModItems {
	
	private static final List<Item> items = new ArrayList<>();
	
	public static Item itemCandyCane;
	
	public static final void commonPreinit() {
		itemCandyCane = registerItem(new ItemCandyCane(), "item_candy_cane");
	}
	
	public static final void clientInit() {
		for(Item item : items) registerRender(item);
	}
	
	private static final void registerRender(Item item) {
		Minecraft.getMinecraft().getRenderItem().getItemModelMesher().register(item, 0, new ModelResourceLocation(item.getRegistryName(), "inventory"));
	}
	
	public static final Item registerItem(Item item, String name) {
		item.setUnlocalizedName(name);
		item.setRegistryName(new ResourceLocation(ModInfo.ID, name));
		GameRegistry.register(item);
		items.add(item);
		return item;
	}
	
}