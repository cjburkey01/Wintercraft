package com.cjburkey.mod.wintercraft.block;

import java.util.Random;
import com.cjburkey.mod.wintercraft.Log;
import com.cjburkey.mod.wintercraft.Util;
import com.cjburkey.mod.wintercraft.item.ModItems;
import net.minecraft.block.Block;
import net.minecraft.block.SoundType;
import net.minecraft.block.material.MapColor;
import net.minecraft.block.material.Material;
import net.minecraft.block.state.IBlockState;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.Item;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.MathHelper;
import net.minecraft.world.IBlockAccess;
import net.minecraft.world.World;

public final class BlockCandyCane extends Block {
	
	public BlockCandyCane() {
		super(Material.WOOD);
		this.setSoundType(SoundType.WOOD);
		this.setHardness(1.2f);
	}
	
	public void neighborChanged(IBlockState state, World world, BlockPos pos, Block blockIn, BlockPos fromPos) {
		BlockPos below = new BlockPos(pos.getX(), pos.getY() - 1, pos.getZ());
		IBlockState bs = world.getBlockState(below);
		if(bs == null || bs.getBlock() == null ||  world.isAirBlock(below)) world.destroyBlock(pos, true);
	}
	
	public boolean canSilkHarvest(World world, BlockPos pos, IBlockState state, EntityPlayer player) {
		return true;
	}
	
	public int quantityDroppedWithBonus(int fortune, Random random) {
		return quantityDropped(random);
	}
	
	public int quantityDropped(Random random) {
		return Util.randomRange(random, 1, 3);
	}
	
	public Item getItemDropped(IBlockState state, Random rand, int fortune) {
		return ModItems.itemCandyCane;
	}
	
	public MapColor getMapColor(IBlockState state) {
		return MapColor.RED;
	}
	
}