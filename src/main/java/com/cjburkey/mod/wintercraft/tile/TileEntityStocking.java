package com.cjburkey.mod.wintercraft.tile;

import java.util.UUID;
import net.minecraft.block.state.IBlockState;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;

public class TileEntityStocking extends TileEntity {
	
	private UUID player;
	
	public void setOwner(EntityPlayer player) {
		this.player = player.getUniqueID();
		markDirty();
	}
	
	public UUID getOwner() {
		return player;
	}
	
	public boolean hasOwner() {
		return player != null;
	}
	
	public boolean doesPlayerOwn(EntityPlayer player) {
		if(!hasOwner()) return false;
		return this.player.equals(player.getUniqueID());
	}
	
	public NBTTagCompound writeToNBT(NBTTagCompound nbt) {
		nbt.setUniqueId("owning_player", player);
		return super.writeToNBT(nbt);
	}
	
	public void readFromNBT(NBTTagCompound nbt) {
		super.readFromNBT(nbt);
		player = nbt.getUniqueId("owning_player");
	}
	
	public boolean shouldRefresh(World world, BlockPos pos, IBlockState old, IBlockState neww) {
		return false;
	}
	
}