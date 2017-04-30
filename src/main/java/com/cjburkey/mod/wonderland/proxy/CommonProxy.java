package com.cjburkey.mod.wonderland.proxy;

import java.io.File;
import com.cjburkey.mod.wonderland.WinterWonderland;
import com.cjburkey.mod.wonderland.biome.ModBiomeHandler;
import com.cjburkey.mod.wonderland.block.ModBlocks;
import com.cjburkey.mod.wonderland.cfg.ModConfigHandler;
import com.cjburkey.mod.wonderland.crafting.ModCrafting;
import com.cjburkey.mod.wonderland.event.ModEvents;
import com.cjburkey.mod.wonderland.gui.ModGuiHandler;
import com.cjburkey.mod.wonderland.item.ModItems;
import com.cjburkey.mod.wonderland.loot.ModLoots;
import com.cjburkey.mod.wonderland.material.ModMaterials;
import com.cjburkey.mod.wonderland.packet.ModPackets;
import com.cjburkey.mod.wonderland.tab.ModTabs;
import com.cjburkey.mod.wonderland.tile.ModTileEntities;
import com.cjburkey.mod.wonderland.world.ModWorldGenerators;
import net.minecraftforge.fml.common.event.FMLInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPostInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPreInitializationEvent;
import net.minecraftforge.fml.common.network.NetworkRegistry;

public class CommonProxy {
	
	public void preinit(FMLPreInitializationEvent e) {
		ModConfigHandler.commonPreinit(new File(e.getSuggestedConfigurationFile().getParentFile(), "/Wintercraft/main.cfg"));
		ModPackets.commonPreinit();
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
		NetworkRegistry.INSTANCE.registerGuiHandler(WinterWonderland.instance, new ModGuiHandler());
	}

	public void postinit(FMLPostInitializationEvent e) {
		ModBiomeHandler.commonPostinit();
	}
	
}