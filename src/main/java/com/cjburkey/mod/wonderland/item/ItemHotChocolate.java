package com.cjburkey.mod.wonderland.item;

import com.cjburkey.mod.wonderland.cfg.ModConfigHandler;
import com.cjburkey.mod.wonderland.dimension.TeleporterNoPortal;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.EntityPlayerMP;
import net.minecraft.item.EnumAction;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.ActionResult;
import net.minecraft.util.EnumActionResult;
import net.minecraft.util.EnumHand;
import net.minecraft.world.World;

public class ItemHotChocolate extends Item {
	
	public ItemHotChocolate() {
		//ItemPotion
	}
	
	public ItemStack onItemUseFinish(ItemStack stack, World world, EntityLivingBase user) {
		if(!world.isRemote) {
			if(user instanceof EntityPlayerMP) {
				int dimId = ModConfigHandler.wonderlandDimensionId;
				EntityPlayerMP player = (EntityPlayerMP) user;
				player.timeUntilPortal = 10;
				player.getServer().getPlayerList().transferPlayerToDimension(player, dimId, new TeleporterNoPortal(player, dimId));
				//player.changeDimension(dimId);
			}
		}
		return stack;
	}
	
	public ActionResult<ItemStack> onItemRightClick(World world, EntityPlayer player, EnumHand hand) {
		player.setActiveHand(hand);
		return new ActionResult(EnumActionResult.SUCCESS, player.getHeldItem(hand));
	}
	
	public int getMaxItemUseDuration(ItemStack stack) {
		return 20;
	}
	
	public EnumAction getItemUseAction(ItemStack stack) {
		return EnumAction.DRINK;
	}
	
}