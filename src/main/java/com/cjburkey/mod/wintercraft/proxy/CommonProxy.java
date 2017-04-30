package com.cjburkey.mod.wintercraft.proxy;

import java.io.File;
import com.cjburkey.mod.wintercraft.Wintercraft;
import com.cjburkey.mod.wintercraft.biome.ModBiomeHandler;
import com.cjburkey.mod.wintercraft.block.ModBlocks;
import com.cjburkey.mod.wintercraft.cfg.ModConfigHandler;
import com.cjburkey.mod.wintercraft.crafting.ModCrafting;
import com.cjburkey.mod.wintercraft.event.ModEvents;
import com.cjburkey.mod.wintercraft.gui.ModGuiHandler;
import com.cjburkey.mod.wintercraft.item.ModItems;
import com.cjburkey.mod.wintercraft.loot.ModLoots;
import com.cjburkey.mod.wintercraft.material.ModMaterials;
import com.cjburkey.mod.wintercraft.tab.ModTabs;
import com.cjburkey.mod.wintercraft.tile.ModTileEntities;
import com.cjburkey.mod.wintercraft.world.ModWorldGenerators;
import net.minecraftforge.fml.common.event.FMLInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPostInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPreInitializationEvent;
import net.minecraftforge.fml.common.network.NetworkRegistry;

public class CommonProxy {
	
	public void preinit(FMLPreInitializationEvent e) {
		ModConfigHandler.commonPreinit(new File(e.getSuggestedConfigurationFile().getParentFile(), "/Wintercraft/main.cfg"));
		ModTabs.commonPreinit();
		ModMaterials.commonPreinit();
		ModTileEntities.commonPreinit();
		ModBlocks.commonPreinit();
		ModItems.commonPreinit();
		ModLoots.commonPreinit();
		ModWorldGenerators.commonPreinit();
	}

	public void init(FMLInitializationEvent e) {
		ModEvents.commonInit();
		ModCrafting.commonInit();
		NetworkRegistry.INSTANCE.registerGuiHandler(Wintercraft.instance, new ModGuiHandler());
	}

	public void postinit(FMLPostInitializationEvent e) {
		ModBiomeHandler.commonPostinit();
	}
	
}