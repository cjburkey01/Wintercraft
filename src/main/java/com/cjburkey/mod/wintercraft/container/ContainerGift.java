package com.cjburkey.mod.wintercraft.container;

import com.cjburkey.mod.wintercraft.Log;
import com.cjburkey.mod.wintercraft.tile.TileEntityGift;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.inventory.Container;
import net.minecraft.inventory.IInventory;
import net.minecraft.inventory.Slot;
import net.minecraft.item.ItemStack;

public class ContainerGift extends Container {
	
	private TileEntityGift tileEntityGift;
	
	public ContainerGift(IInventory player, TileEntityGift tileEntity) {
		tileEntityGift = tileEntity;
		
		for(int y = 0; y < 3; y ++) for(int x = 0; x < 3; x ++) addSlotToContainer(new Slot(tileEntityGift, x + y * 3, 62 + x * 18, 17 + y * 18));
		for(int y = 0; y < 3; y ++) for(int x = 0; x < 9; x ++) addSlotToContainer(new Slot(player, x + y * 9 + 9, 8 + x * 18, 84 + y * 18));
		for(int x = 0; x < 9; x ++) addSlotToContainer(new Slot(player, x, 8 + x * 18, 142));
	}
	
	public ItemStack transferStackInSlot(EntityPlayer player, int fromSlot) {
		ItemStack previous = ItemStack.EMPTY;
		Slot slot = (Slot) inventorySlots.get(fromSlot);
		if(slot != null && slot.getHasStack()) {
			ItemStack current = slot.getStack();
			previous = current.copy();
			if(fromSlot < 9) { if(!mergeItemStack(current, 9, 45, true)) return ItemStack.EMPTY;
			} else { if(!mergeItemStack(current, 0, 9, false)) return ItemStack.EMPTY; }
			if(current.getCount() == 0) slot.putStack(ItemStack.EMPTY);
			else slot.onSlotChanged();
			if(current.getCount() == previous.getCount()) return ItemStack.EMPTY;
			slot.onTake(player, current);
		}
		return previous;
	}
	
	public boolean canInteractWith(EntityPlayer player) {
		return tileEntityGift.isUsableByPlayer(player);
	}
	
}