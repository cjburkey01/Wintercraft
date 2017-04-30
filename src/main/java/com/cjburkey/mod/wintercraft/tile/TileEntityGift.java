package com.cjburkey.mod.wintercraft.tile;

import java.util.ArrayList;
import com.cjburkey.mod.wintercraft.block.ModBlocks;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.inventory.IInventory;
import net.minecraft.inventory.ItemStackHelper;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.nbt.NBTTagList;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.NonNullList;
import net.minecraft.util.text.ITextComponent;
import net.minecraft.util.text.TextComponentTranslation;

public class TileEntityGift extends TileEntity implements IInventory {
	
	private ItemStack[] inventory;
	private boolean locked = false;
	public boolean drop = true;
	
	public TileEntityGift() {
		inventory = new ItemStack[getSizeInventory()];
		for(int i = 0; i < inventory.length; i ++) inventory[i] = ItemStack.EMPTY;
	}

	public String getName() {
		return "container.tile_entity_gift";
	}

	public boolean hasCustomName() {
		return false;
	}
	
	public ITextComponent getDisplayName() {
		return new TextComponentTranslation(getName());
	}

	public int getSizeInventory() {
		return 1;
	}

	public boolean isEmpty() {
		for(int i = 0; i < inventory.length; i ++) {
			if(getStackInSlot(i) != ItemStack.EMPTY) return false; 
		}
		return true;
	}
	
	public boolean shouldDrop() {
		return drop && !isEmpty();
	}

	public ItemStack getStackInSlot(int index) {
		if(index < 0 || index >= getSizeInventory()) return ItemStack.EMPTY;
		return inventory[index];
	}

	public ItemStack decrStackSize(int index, int count) {
		if(getStackInSlot(index) != ItemStack.EMPTY) {
			ItemStack itemstack;
			if(getStackInSlot(index).getCount() <= count) {
				itemstack = getStackInSlot(index);
				setInventorySlotContents(index, ItemStack.EMPTY);
				markDirty();
				return itemstack;
			} else {
				itemstack = getStackInSlot(index).splitStack(count);
				if(getStackInSlot(index).getCount() <= 0) setInventorySlotContents(index, ItemStack.EMPTY);
				else setInventorySlotContents(index, getStackInSlot(index));
				markDirty();
				return itemstack;
			}
		} else return ItemStack.EMPTY;
	}

	public ItemStack removeStackFromSlot(int index) {
		ItemStack stack = getStackInSlot(index);
		setInventorySlotContents(index, ItemStack.EMPTY);
		return stack;
	}

	public void setInventorySlotContents(int index, ItemStack stack) {
		if(index < 0 || index >= getSizeInventory()) return;
		if(stack != ItemStack.EMPTY && stack.getCount() > getInventoryStackLimit()) stack.setCount(getInventoryStackLimit());
		if(stack != ItemStack.EMPTY && stack.getCount() == 0) stack = ItemStack.EMPTY;
		inventory[index] = stack;
		markDirty();
	}

	public int getInventoryStackLimit() {
		return 64;
	}

	public boolean isUsableByPlayer(EntityPlayer player) {
		return world.getTileEntity(getPos()) == this && player.getDistanceSq(pos.add(0.5, 0.5, 0.5)) <= 64;
	}

	public void openInventory(EntityPlayer player) {
		
	}

	public void closeInventory(EntityPlayer player) {
		
	}

	public boolean isItemValidForSlot(int index, ItemStack stack) {
		return !stack.isItemEqual(new ItemStack(ModBlocks.blockGift));
	}

	public int getField(int id) {
		return 0;
	}

	public void setField(int id, int value) {
		
	}

	public int getFieldCount() {
		return 0;
	}

	public void clear() {
		for(int i = 0; i < getSizeInventory(); i ++) setInventorySlotContents(i, ItemStack.EMPTY);
	}
	
	public void lock() {
		locked = true;
		this.markDirty();
	}
	
	public boolean isLocked() {
		return locked;
	}
	
	public NBTTagCompound writeToNBT(NBTTagCompound nbt) {
		NBTTagList list = new NBTTagList();
		for(int i = 0; i < getSizeInventory(); i ++) {
			if(getStackInSlot(i) != ItemStack.EMPTY) {
				NBTTagCompound stackTag = new NBTTagCompound();
				stackTag.setByte("Slot", (byte) i);
				getStackInSlot(i).writeToNBT(stackTag);
				list.appendTag(stackTag);
			}
		}
		nbt.setTag("Items", list);
		nbt.setBoolean("locked", locked);
		return super.writeToNBT(nbt);
	}
	
	public void readFromNBT(NBTTagCompound nbt) {
		NBTTagList list = nbt.getTagList("Items", 10);
		for(int i = 0; i < list.tagCount(); i ++) {
			NBTTagCompound stackTag = list.getCompoundTagAt(i);
			int slot = stackTag.getByte("Slot") & 255;
			setInventorySlotContents(slot, new ItemStack(stackTag));
		}
		locked = nbt.getBoolean("locked");
		super.readFromNBT(nbt);
	}
	
	public NBTTagCompound saveToNbt(NBTTagCompound nbt) {
		NonNullList<ItemStack> items = NonNullList.<ItemStack>withSize(getSizeInventory(), ItemStack.EMPTY);
		for(int i = 0; i < inventory.length; i ++) items.set(i, inventory[i]);
		ItemStackHelper.saveAllItems(nbt, items, false);
		nbt.setBoolean("locked", locked);
		return nbt;
	}
	
}