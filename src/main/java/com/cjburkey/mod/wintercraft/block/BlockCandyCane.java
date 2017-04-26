package com.cjburkey.mod.wintercraft.block;

import java.util.Random;
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
import net.minecraft.world.World;

public class BlockCandyCane extends Block {
	
	public BlockCandyCane() {
		super(Material.WOOD);
		this.setSoundType(SoundType.WOOD);
		this.setHardness(1.2f);
		this.setLightLevel(1.0f);
	}
	
	public boolean canSilkHarvest(World world, BlockPos pos, IBlockState state, EntityPlayer player) {
		return true;
	}
	
	public int quantityDroppedWithBonus(int fortune, Random random) {
		return MathHelper.clamp(this.quantityDropped(random) + random.nextInt(fortune + 1), 1, 4);
	}
	
	public int quantityDropped(Random random) {
		return 2 + random.nextInt(3);
	}
	
	public Item getItemDropped(IBlockState state, Random rand, int fortune) {
		return ModItems.itemCandyCane;
	}
	
	public MapColor getMapColor(IBlockState state) {
		return MapColor.RED;
	}
	
}