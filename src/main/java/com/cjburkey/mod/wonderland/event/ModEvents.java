package com.cjburkey.mod.wonderland.event;

import net.minecraftforge.common.MinecraftForge;

public final class ModEvents {
	
	public static void commonInit() {
		//MinecraftForge.EVENT_BUS.register(new EventUseIceSkates());
		MinecraftForge.EVENT_BUS.register(new EventPlayerSleep());
	}
	
}