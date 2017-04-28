package com.cjburkey.mod.wintercraft.event;

import net.minecraftforge.common.MinecraftForge;

public class ModEvents {
	
	public static final void commonInit() {
		MinecraftForge.EVENT_BUS.register(new EventUseIceSkates());
	}
	
}