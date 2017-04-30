package com.cjburkey.mod.wonderland;

import com.cjburkey.mod.wonderland.cfg.ModConfigHandler;
import com.cjburkey.mod.wonderland.proxy.CommonProxy;
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
public final class WinterWonderland {
	
	@Instance
	public static WinterWonderland instance;
	
	@SidedProxy(clientSide = ModInfo.CLIENT_PROXY, serverSide = ModInfo.SERVER_PROXY)
	public static CommonProxy proxy;
	
	@EventHandler
	public void preinit(FMLPreInitializationEvent e) {
		long start = now();
		Log.info("Started PreInit");
		proxy.preinit(e);
		Log.info("Finished PreInit(" + (now() - start) + " ms).");
	}

	@EventHandler
	public void init(FMLInitializationEvent e) {
		long start = now();
		Log.info("Started Init");
		MinecraftForge.EVENT_BUS.register(this);
		proxy.init(e);
		Log.info("Finished Init(" + (now() - start) + " ms).");
	}

	@EventHandler
	public void postinit(FMLPostInitializationEvent e) {
		long start = now();
		Log.info("Started PostInit");
		proxy.postinit(e);
		Log.info("Finished PostInit(" + (now() - start) + " ms).");
	}
	
	@SubscribeEvent
	public void onConfigChanged(OnConfigChangedEvent eventArgs) {
		if(eventArgs.getModID().equals(ModInfo.ID)) ModConfigHandler.syncConfig();
	}
	
	private static long now() {
		return System.currentTimeMillis();
	}
	
}