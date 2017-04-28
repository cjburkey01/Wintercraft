package com.cjburkey.mod.wintercraft.block;

import java.util.Random;
import com.cjburkey.mod.wintercraft.Wintercraft;
import com.cjburkey.mod.wintercraft.gui.ModGuiHandler;
import com.cjburkey.mod.wintercraft.tile.TileEntityGift;
import net.minecraft.block.Block;
import net.minecraft.block.ITileEntityProvider;
import net.minecraft.block.SoundType;
import net.minecraft.block.material.Material;
import net.minecraft.block.state.IBlockState;
import net.minecraft.entity.item.EntityItem;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemBlock;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
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
	
	public int quantityDropped(IBlockState state, int fortune, Random random) {
		return 0;
	}
	
	public boolean onBlockActivated(World world, BlockPos pos, IBlockState state, EntityPlayer player, EnumHand hand, EnumFacing facing, float hitX, float hitY, float hitZ) {
		TileEntityGift te = (TileEntityGift) world.getTileEntity(pos);
		if(!world.isRemote) {
			if(te.isLocked()) {
				te.drop = false;
				EntityItem ent = new EntityItem(world, pos.getX() + 0.5f, pos.getY() + 1, pos.getZ() + 0.5f, te.getStackInSlot(0));
				ent.setVelocity(0, 0, 0);
				world.spawnEntity(ent);
				world.setBlockToAir(pos);
			} else player.openGui(Wintercraft.instance, ModGuiHandler.GUI_GIFT_ID, world, pos.getX(), pos.getY(), pos.getZ());
		}
		return true;
	}
	
	public void breakBlock(World worldIn, BlockPos pos, IBlockState state) {
		TileEntityGift tileEntity = (TileEntityGift) worldIn.getTileEntity(pos);
		if(tileEntity.shouldDrop()) {
			ItemBlock b = ModBlocks.getItem(this);
			ItemStack stack = new ItemStack(b);
			NBTTagCompound nbt1 = new NBTTagCompound();
			NBTTagCompound nbt2 = new NBTTagCompound();
			tileEntity.lock();
			nbt1.setTag("BlockEntityTag", tileEntity.saveToNbt(nbt2));
			nbt1.setBoolean("DISPLAY", true);
			stack.setTagCompound(nbt1);
			spawnAsEntity(worldIn, pos, stack);
		} else if(!tileEntity.isLocked()) {
			spawnAsEntity(worldIn, pos, new ItemStack(this));
		}
		super.breakBlock(worldIn, pos, state);
	}
	
}