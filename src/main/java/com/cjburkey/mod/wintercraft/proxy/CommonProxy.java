package com.cjburkey.mod.wintercraft.proxy;

import java.io.File;
import com.cjburkey.mod.wintercraft.biome.ModBiomeHandler;
import com.cjburkey.mod.wintercraft.block.ModBlocks;
import com.cjburkey.mod.wintercraft.cfg.ModConfigHandler;
import com.cjburkey.mod.wintercraft.crafting.ModCrafting;
import com.cjburkey.mod.wintercraft.item.ModItems;
import net.minecraftforge.fml.common.event.FMLInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPostInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPreInitializationEvent;

public class CommonProxy {
	
	public void preinit(FMLPreInitializationEvent e) {
		ModConfigHandler.commonPreinit(new File(e.getSuggestedConfigurationFile().getParentFile(), "/Wintercraft/main.cfg"));
		ModBlocks.commonPreinit();
		ModItems.commonPreinit();
	}

	public void init(FMLInitializationEvent e) {
		ModBiomeHandler.commonInit();
		ModCrafting.commonInit();
	}

	public void postinit(FMLPostInitializationEvent e) {
		
	}
	
}