package com.cjburkey.mod.wintercraft.container.slot;

import com.cjburkey.mod.wintercraft.block.ModBlocks;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.inventory.IInventory;
import net.minecraft.inventory.Slot;
import net.minecraft.item.ItemStack;
import net.minecraft.tileentity.TileEntity;

public class SlotContainerGift extends Slot {
	
	TileEntity e;
	
	public SlotContainerGift(IInventory inventory, int index, int x, int y, TileEntity e) {
		super(inventory, index, x, y);
		this.e = e;
	}
	
	public boolean isItemValid(ItemStack stack) {
		return !stack.isItemEqual(new ItemStack(ModBlocks.blockGift));
	}
	
}