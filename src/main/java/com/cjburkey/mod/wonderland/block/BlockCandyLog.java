package com.cjburkey.mod.wonderland.block;

import net.minecraft.block.BlockLog.EnumAxis;
import net.minecraft.block.BlockRotatedPillar;
import net.minecraft.block.SoundType;
import net.minecraft.block.material.Material;
import net.minecraft.util.EnumFacing;

public final class BlockCandyLog extends BlockRotatedPillar {
	
	public BlockCandyLog() {
		super(Material.WOOD);
		
		this.setDefaultState(this.blockState.getBaseState().withProperty(AXIS, EnumFacing.Axis.Y));
		this.setHardness(3.0f);
		this.setHarvestLevel("axe", 0);
		this.setSoundType(SoundType.WOOD);
		//BlockLeaves
	}
	
}