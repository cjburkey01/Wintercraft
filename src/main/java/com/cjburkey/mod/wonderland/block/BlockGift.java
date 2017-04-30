package com.cjburkey.mod.wonderland.block;

import java.util.Random;
import com.cjburkey.mod.wonderland.Log;
import com.cjburkey.mod.wonderland.WinterWonderland;
import com.cjburkey.mod.wonderland.gui.ModGuiHandler;
import com.cjburkey.mod.wonderland.loot.ModLoots;
import com.cjburkey.mod.wonderland.tile.TileEntityGift;
import net.minecraft.block.Block;
import net.minecraft.block.ITileEntityProvider;
import net.minecraft.block.SoundType;
import net.minecraft.block.material.Material;
import net.minecraft.block.state.IBlockState;
import net.minecraft.entity.item.EntityItem;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.BlockRenderLayer;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.EnumHand;
import net.minecraft.util.math.AxisAlignedBB;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.IBlockAccess;
import net.minecraft.world.World;

public final class BlockGift extends Block implements ITileEntityProvider {

	public BlockGift() {
		super(Material.WOOD);
		
		this.setHardness(1.0f);
		this.setSoundType(SoundType.WOOD);
	}
	
	public AxisAlignedBB getBoundingBox(IBlockState state, IBlockAccess source, BlockPos pos) {
		return new AxisAlignedBB(1d / 16d, 0d, 1d / 16d, 15d / 16d, 10d / 16d, 15d / 16d);
	}
	
	public boolean isOpaqueCube(IBlockState state) {
		return false;
	}
	
	public boolean isFullCube(IBlockState state) {
		return false;
	}
	
	public boolean isTranslucent(IBlockState state) {
		return true;
	}
	
	public BlockRenderLayer getBlockLayer() {
		return BlockRenderLayer.TRANSLUCENT;
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
			} else player.openGui(WinterWonderland.instance, ModGuiHandler.GUI_GIFT_ID, world, pos.getX(), pos.getY(), pos.getZ());
		}
		return true;
	}
	
	public static ItemStack getRandomLootGift() {
		TileEntityGift g = new TileEntityGift();
		g.setInventorySlotContents(0, ModLoots.randomLoot());
		Log.info(g);
		return getGiftBlockItem(g);
	}
	
	public static ItemStack getGiftBlockItem(TileEntityGift gift) {
		ItemStack stack = new ItemStack(ModBlocks.blockGift);
		NBTTagCompound nbt1 = new NBTTagCompound();
		NBTTagCompound nbt2 = new NBTTagCompound();
		gift.lock();
		nbt1.setTag("BlockEntityTag", gift.saveToNbt(nbt2));
		nbt1.setBoolean("DISPLAY", true);
		stack.setTagCompound(nbt1);
		return stack;
	}
	
	public void breakBlock(World worldIn, BlockPos pos, IBlockState state) {
		TileEntityGift tileEntity = (TileEntityGift) worldIn.getTileEntity(pos);
		if(tileEntity.shouldDrop()) {
			tileEntity.lock();
			spawnAsEntity(worldIn, pos, getGiftBlockItem(tileEntity));
		} else if(!tileEntity.isLocked()) {
			spawnAsEntity(worldIn, pos, new ItemStack(this));
		}
		super.breakBlock(worldIn, pos, state);
	}
	
}