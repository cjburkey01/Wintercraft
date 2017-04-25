package com.cjburkey.mod.wintercraft;

import com.cjburkey.mod.wintercraft.proxy.CommonProxy;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.Mod.EventHandler;
import net.minecraftforge.fml.common.Mod.Instance;
import net.minecraftforge.fml.common.SidedProxy;
import net.minecraftforge.fml.common.event.FMLInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPostInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPreInitializationEvent;

@Mod(name = ModInfo.NAME, modid = ModInfo.ID, version = ModInfo.VERSION)
public class Wintercraft {
	
	@Instance
	public static Wintercraft instance;
	
	@SidedProxy(clientSide = ModInfo.CLIENT_PROXY, serverSide = ModInfo.SERVER_PROXY)
	public static CommonProxy proxy;
	
	@EventHandler
	public void preinit(FMLPreInitializationEvent e) { proxy.preinit(e); }

	@EventHandler
	public void init(FMLInitializationEvent e) { proxy.init(e); }

	@EventHandler
	public void postinit(FMLPostInitializationEvent e) { proxy.postinit(e); }
	
}