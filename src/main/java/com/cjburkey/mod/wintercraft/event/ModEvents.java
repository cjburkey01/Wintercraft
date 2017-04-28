package com.cjburkey.mod.wintercraft.event;

import net.minecraftforge.common.MinecraftForge;

public final class ModEvents {
	
	public static void commonInit() {
		MinecraftForge.EVENT_BUS.register(new EventUseIceSkates());
	}
	
}