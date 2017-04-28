package com.cjburkey.mod.wintercraft.block;

import com.cjburkey.mod.wintercraft.Log;
import com.cjburkey.mod.wintercraft.Wintercraft;
import com.cjburkey.mod.wintercraft.gui.ModGuiHandler;
import com.cjburkey.mod.wintercraft.tile.TileEntityGift;
import net.minecraft.block.Block;
import net.minecraft.block.ITileEntityProvider;
import net.minecraft.block.SoundType;
import net.minecraft.block.material.Material;
import net.minecraft.block.state.IBlockState;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.inventory.InventoryHelper;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.EnumHand;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;

public class BlockGift extends Block implements ITileEntityProvider {

	public BlockGift() {
		super(Material.WOOD);
		
		this.setHardness(1.0f);
		this.setSoundType(SoundType.WOOD);
	}

	public TileEntity createNewTileEntity(World world, int meta) {
		return new TileEntityGift();
	}
	
	public void breakBlock(World world, BlockPos pos, IBlockState state) {
		TileEntityGift te = (TileEntityGift) world.getTileEntity(pos);
		InventoryHelper.dropInventoryItems(world, pos, te);
		super.breakBlock(world, pos, state);
	}
	
	public boolean onBlockActivated(World world, BlockPos pos, IBlockState state, EntityPlayer player, EnumHand hand, EnumFacing facing, float hitX, float hitY, float hitZ) {
		if(!world.isRemote) player.openGui(Wintercraft.instance, ModGuiHandler.GUI_GIFT_ID, world, pos.getX(), pos.getY(), pos.getZ());
		return true;
	}
	
}