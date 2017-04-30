package com.cjburkey.mod.wintercraft.event;

import com.cjburkey.mod.wintercraft.block.BlockStocking;
import net.minecraftforge.event.entity.player.PlayerWakeUpEvent;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;

public class EventPlayerSleep {
	
	@SubscribeEvent
	public void slept(PlayerWakeUpEvent e) {
		if(!e.getEntityPlayer().world.isRemote) BlockStocking.setFullAll(e.getEntityPlayer().world, true);
	}
	
}