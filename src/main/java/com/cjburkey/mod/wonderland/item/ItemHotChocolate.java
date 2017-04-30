package com.cjburkey.mod.wonderland.item;

import java.util.List;
import com.cjburkey.mod.wonderland.Util;
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
import net.minecraft.util.text.TextFormatting;
import net.minecraft.world.World;

public class ItemHotChocolate extends Item {
	
	public ItemStack onItemUseFinish(ItemStack stack, World world, EntityLivingBase user) {
		if(!world.isRemote) {
			if(user instanceof EntityPlayerMP) {
				EntityPlayerMP player = (EntityPlayerMP) user;
				if(!player.isCreative()) stack.setCount(stack.getCount() - 1);
				player.timeUntilPortal = 10;
				int wonderlandId = ModConfigHandler.wonderlandDimensionId;
				if(player.dimension == 0 || player.dimension == wonderlandId) {
					int toDim = (player.dimension == wonderlandId) ? 0 : wonderlandId;
					player.getServer().getPlayerList().transferPlayerToDimension(player, toDim, new TeleporterNoPortal(player, toDim));
				}
			}
		}
		return stack;
	}
	
	public void addInformation(ItemStack stack, EntityPlayer player, List<String> tooltip, boolean advanced) {
		String tip = TextFormatting.BLUE + "" + TextFormatting.BOLD;
		if(player.dimension == 0) tip += Util.translate("str.hot_chocolate_can_drink");
		else if(player.dimension == ModConfigHandler.wonderlandDimensionId) tip += Util.translate("str.hot_chocolate_return");
		else return;
		tooltip.add(tip);
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