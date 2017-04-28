package com.cjburkey.mod.wintercraft.gui;

import com.cjburkey.mod.wintercraft.container.ContainerGift;
import com.cjburkey.mod.wintercraft.tile.TileEntityGift;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import net.minecraftforge.fml.common.network.IGuiHandler;

public class ModGuiHandler implements IGuiHandler {
	
	public static final int GUI_GIFT_ID = 0;
	
	public Object getServerGuiElement(int id, EntityPlayer player, World world, int x, int y, int z) {
		if(id == GUI_GIFT_ID) return new ContainerGift(player.inventory, (TileEntityGift) world.getTileEntity(new BlockPos(x, y, z)));
		return null;
	}
	
	public Object getClientGuiElement(int id, EntityPlayer player, World world, int x, int y, int z) {
		if(id == GUI_GIFT_ID) return new GuiGift(player.inventory, (TileEntityGift) world.getTileEntity(new BlockPos(x, y, z)));
		return null;
	}
	
}