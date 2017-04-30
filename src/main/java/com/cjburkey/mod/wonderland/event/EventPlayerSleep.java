package com.cjburkey.mod.wonderland.event;

import com.cjburkey.mod.wonderland.block.BlockStocking;
import net.minecraftforge.event.entity.player.PlayerWakeUpEvent;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;

public final class EventPlayerSleep {
	
	@SubscribeEvent
	public void slept(PlayerWakeUpEvent e) {
		if(!e.getEntityPlayer().world.isRemote) BlockStocking.setFullAll(e.getEntityPlayer().world, true);
	}
	
}