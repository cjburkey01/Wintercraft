package com.cjburkey.mod.wintercraft;

import com.cjburkey.mod.wintercraft.cfg.ModConfigHandler;
import com.cjburkey.mod.wintercraft.proxy.CommonProxy;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.fml.client.event.ConfigChangedEvent.OnConfigChangedEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.Mod.EventHandler;
import net.minecraftforge.fml.common.Mod.Instance;
import net.minecraftforge.fml.common.SidedProxy;
import net.minecraftforge.fml.common.event.FMLInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPostInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPreInitializationEvent;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;

@Mod(name = ModInfo.NAME, modid = ModInfo.ID, version = ModInfo.VERSION)
public final class Wintercraft {
	
	@Instance
	public static Wintercraft instance;
	
	@SidedProxy(clientSide = ModInfo.CLIENT_PROXY, serverSide = ModInfo.SERVER_PROXY)
	public static CommonProxy proxy;
	
	@EventHandler
	public void preinit(FMLPreInitializationEvent e) {
		Log.info("Started PreInit");
		proxy.preinit(e);
		Log.info("Finished PreInit");
	}

	@EventHandler
	public void init(FMLInitializationEvent e) {
		Log.info("Started Init");
		MinecraftForge.EVENT_BUS.register(this);
		proxy.init(e);
		Log.info("Finished Init");
	}

	@EventHandler
	public void postinit(FMLPostInitializationEvent e) {
		Log.info("Started PostInit");
		proxy.postinit(e);
		Log.info("Finished PostInit");
	}
	
	@SubscribeEvent
	public void onConfigChanged(OnConfigChangedEvent eventArgs) {
		if(eventArgs.getModID().equals(ModInfo.ID)) ModConfigHandler.syncConfig();
	}
	
}