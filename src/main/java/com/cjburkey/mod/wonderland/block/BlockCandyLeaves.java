package com.cjburkey.mod.wonderland.block;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import com.cjburkey.mod.wonderland.Util;
import com.cjburkey.mod.wonderland.item.ModItems;
import net.minecraft.block.Block;
import net.minecraft.block.SoundType;
import net.minecraft.block.material.Material;
import net.minecraft.block.state.IBlockState;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.BlockRenderLayer;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.IBlockAccess;
import net.minecraftforge.common.IShearable;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

public final class BlockCandyLeaves extends Block implements IShearable {
	
	public BlockCandyLeaves() {
		super(Material.LEAVES);
		this.setTickRandomly(true);
		this.setHardness(0.2F);
		this.setLightOpacity(1);
		this.setSoundType(SoundType.PLANT);
	}
	
	public int quantityDropped(Random random) {
		return Util.randomRange(random, 0, 10) == 0 ? 1 : 0;
	}
	
	public Item getItemDropped(IBlockState state, Random rand, int fortune) {
		return ModItems.itemCandyCane;
	}

	public boolean isShearable(ItemStack item, IBlockAccess world, BlockPos pos) {
		return true;
	}

	public List<ItemStack> onSheared(ItemStack item, IBlockAccess world, BlockPos pos, int fortune) {
		List<ItemStack> items = new ArrayList<>();
		items.add(new ItemStack(this));
		return null;
	}
	
	public boolean isOpaqueCube(IBlockState state) {
		return false;
	}
	
	@SideOnly(Side.CLIENT)
	public boolean shouldSideBeRendered(IBlockState blockState, IBlockAccess blockAccess, BlockPos pos, EnumFacing side) {
		return super.shouldSideBeRendered(blockState, blockAccess, pos, side);
	}
	
	@SideOnly(Side.CLIENT)
	public BlockRenderLayer getBlockLayer() {
		return BlockRenderLayer.CUTOUT_MIPPED;
	}
	
}