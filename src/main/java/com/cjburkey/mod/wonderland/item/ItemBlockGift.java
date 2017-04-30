package com.cjburkey.mod.wonderland.item;

import java.util.List;
import net.minecraft.block.Block;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemBlock;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.util.text.TextComponentTranslation;
import net.minecraft.util.text.TextFormatting;

public final class ItemBlockGift extends ItemBlock {

	public ItemBlockGift(Block b) {
		super(b);
	}
	
	public void addInformation(ItemStack stack, EntityPlayer playerIn, List<String> tooltip, boolean advanced) {
		NBTTagCompound nbttc = stack.getTagCompound();
		if(nbttc != null && nbttc.hasKey("DISPLAY") && nbttc.getBoolean("DISPLAY")) {
			tooltip.add(TextFormatting.BOLD + "" + TextFormatting.GOLD + new TextComponentTranslation("str.gift_has_contents").getFormattedText());
			return;
		}
		tooltip.add(TextFormatting.BLUE + new TextComponentTranslation("str.gift_no_has_contents").getFormattedText());
	}
	
}