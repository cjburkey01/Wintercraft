package com.cjburkey.mod.wintercraft.event;

import java.util.HashMap;
import com.cjburkey.mod.wintercraft.Log;
import com.cjburkey.mod.wintercraft.cfg.ModConfigHandler;
import com.cjburkey.mod.wintercraft.item.ModItems;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Blocks;
import net.minecraft.item.ItemStack;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import net.minecraftforge.fml.common.gameevent.TickEvent.PlayerTickEvent;

public class EventUseIceSkates {
	
	private static float defaultSpeed;
	private static float fastSpeed;
	private static boolean init = false;
	
	private HashMap<EntityPlayer, Boolean> didAdd = new HashMap<>();
	private HashMap<EntityPlayer, Boolean> didTake = new HashMap<>();
	
	private final int timerStart = 30;
	private int timer = 0;
	
	@SubscribeEvent
	public void call(PlayerTickEvent e) {
		EntityPlayer player = e.player;
		if(!init) {
			init = true;
			fastSpeed = ModConfigHandler.iceSkatesSpeed;
			defaultSpeed = player.capabilities.getWalkSpeed();
		}
		if(!player.world.isRemote) {
			if(shouldSpeedUp(player) && !didAdd(player)) {
				player.capabilities.setPlayerWalkSpeed(fastSpeed);
				didAdd.put(player, true);
				didTake.put(player, false);
				Log.info("Added to player: " + player.getName());
			} else if(!shouldSpeedUp(player) && !didTake(player)) {
				player.capabilities.setPlayerWalkSpeed(defaultSpeed);
				didAdd.put(player, false);
				didTake.put(player, true);
				Log.info("Took from player: " + player.getName());
			}
			
			timer --;
			if(timer <= 0 && shouldSpeedUp(player) && player.capabilities.isCreativeMode) {
				timer = timerStart;
				damageBoots(player);
			}
		}
	}
	
	private void damageBoots(EntityPlayer player) {
		ItemStack stack = player.inventory.armorItemInSlot(0);
		stack.setItemDamage(stack.getItemDamage() + 1);
	}
	
	private boolean shouldSpeedUp(EntityPlayer player) {
		ItemStack slot = player.inventory.armorItemInSlot(0);
		return (slot != null && slot.getItem() != null && slot.getItem().equals(ModItems.itemWinterBoots) && aboveIce(player));
	}
	
	private boolean aboveIce(EntityPlayer player) {
		BlockPos plyPos = player.getPosition();
		BlockPos below = new BlockPos(plyPos.getX(), plyPos.getY() - 1, plyPos.getZ());
		float slippery = player.world.getBlockState(below).getBlock().slipperiness;
		return slippery > 0.7f;
	}
	
	private boolean didAdd(EntityPlayer p) {
		if(didAdd.containsKey(p)) return didAdd.get(p);
		return false;
	}
	
	private boolean didTake(EntityPlayer p) {
		if(didTake.containsKey(p)) return didTake.get(p);
		return false;
	}
	
}