package com.cjburkey.mod.wonderland.proxy;

import com.cjburkey.mod.wonderland.item.ModItems;
import net.minecraftforge.fml.common.Mod.EventHandler;
import net.minecraftforge.fml.common.event.FMLInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPostInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPreInitializationEvent;

public final class ClientProxy extends CommonProxy {
	
	public void preinit(FMLPreInitializationEvent e) {
		super.preinit(e);
	}

	public void init(FMLInitializationEvent e) {
		super.init(e);
		
		ModItems.clientInit();
	}

	public void postinit(FMLPostInitializationEvent e) {
		super.postinit(e);
	}
	
}