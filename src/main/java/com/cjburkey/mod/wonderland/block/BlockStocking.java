package com.cjburkey.mod.wonderland.block;

import java.util.List;
import javax.annotation.Nullable;
import com.cjburkey.mod.wonderland.Log;
import com.cjburkey.mod.wonderland.Util;
import com.cjburkey.mod.wonderland.tile.TileEntityStocking;
import net.minecraft.block.Block;
import net.minecraft.block.BlockDirectional;
import net.minecraft.block.ITileEntityProvider;
import net.minecraft.block.SoundType;
import net.minecraft.block.material.Material;
import net.minecraft.block.properties.IProperty;
import net.minecraft.block.properties.PropertyBool;
import net.minecraft.block.state.BlockStateContainer;
import net.minecraft.block.state.IBlockState;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.BlockRenderLayer;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.EnumHand;
import net.minecraft.util.Mirror;
import net.minecraft.util.Rotation;
import net.minecraft.util.math.AxisAlignedBB;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.text.TextFormatting;
import net.minecraft.world.IBlockAccess;
import net.minecraft.world.World;

public final class BlockStocking extends BlockDirectional implements ITileEntityProvider {
	
	public static final PropertyBool IS_FULL = PropertyBool.create("full");
	private static final AxisAlignedBB AABB_DOWN = new AxisAlignedBB(0.0d, 15.0d / 16.0d, 0.0d, 1.0d, 1.0d, 1.0d);
    private static final AxisAlignedBB AABB_UP = new AxisAlignedBB(0.0d, 0.0D, 0.0d, 1.0d, 1.0d / 16.0d, 1.0d);
    private static final AxisAlignedBB AABB_NORTH = new AxisAlignedBB(0.0d, 0.0d, 15.0d / 16.0d, 1.0d, 1.0d, 1.0d);
    private static final AxisAlignedBB AABB_SOUTH = new AxisAlignedBB(0.0d, 0.0d, 0.0D, 1.0d, 1.0d, 1.0d / 16.0d);
    private static final AxisAlignedBB AABB_WEST = new AxisAlignedBB(15.0d / 16.0d, 0.0d, 0.0d, 1.0d, 1.0d, 1.0d);
    private static final AxisAlignedBB AABB_EAST = new AxisAlignedBB(0.0D, 0.0d, 0.0d, 1.0d / 16.0d, 1.0d, 1.0d);
	
	public BlockStocking() {
		super(Material.CLOTH);
		
		this.setDefaultState(this.blockState.getBaseState().withProperty(FACING, EnumFacing.NORTH).withProperty(IS_FULL, false));
		this.setHardness(0.75f);
		this.setSoundType(SoundType.CLOTH);
	}
	
	public boolean onBlockActivated(World world, BlockPos pos, IBlockState state, EntityPlayer player, EnumHand hand, EnumFacing facing, float x, float y, float z) {
		if(!world.isRemote) {
			TileEntityStocking tile = (TileEntityStocking) world.getTileEntity(pos);
			if(!tile.hasOwner()) {
				claim(world, pos, player);
			} else {
				if(!tile.doesPlayerOwn(player)) Util.sendMsg(player, TextFormatting.RED + Util.translate("str.stocking_owned_by_other"));
				else {
					if(isFull(state)) {
						Util.sendMsg(player, TextFormatting.GREEN + Util.translate("str.stocking_got"));
						setFull(world, pos, state, false);
						giveGift(world, pos);
					} else Util.sendMsg(player, TextFormatting.RED + Util.translate("str.stocking_empty"));
				}
			}
		}
		return true;
	}
	
	public void onBlockPlacedBy(World world, BlockPos pos, IBlockState state, EntityLivingBase player, ItemStack stack) {
		if(!world.isRemote) if(player instanceof EntityPlayer) claim(world, pos, (EntityPlayer) player);
	}
	
	public void claim(World world, BlockPos pos, EntityPlayer player) {
		((TileEntityStocking) world.getTileEntity(pos)).setOwner(player);
		Util.sendMsg(player, TextFormatting.GREEN + Util.translate("str.stocking_claimed"));
	}
	
	public void giveGift(World world, BlockPos pos) {
		Block.spawnAsEntity(world, pos, BlockGift.getRandomLootGift());
	}
	
	public void breakBlock(World world, BlockPos pos, IBlockState state) {
		if(isFull(state)) giveGift(world, pos);
		super.breakBlock(world, pos, state);
	}
	
	public static void setFull(World world, BlockPos pos, IBlockState state, boolean full) {
		world.setBlockState(pos, state.withProperty(IS_FULL, full));
	}
	
	public static boolean isFull(IBlockState state) {
		return state.getValue(IS_FULL);
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
		return BlockRenderLayer.CUTOUT;
	}
	
	public AxisAlignedBB getBoundingBox(IBlockState state, IBlockAccess source, BlockPos pos) {
		EnumFacing enumfacing = (EnumFacing) state.getValue(FACING);
		switch (enumfacing) {
			case EAST:
				return AABB_EAST;
			case WEST:
				return AABB_WEST;
			case SOUTH:
				return AABB_SOUTH;
			case NORTH:
			default:
				return AABB_NORTH;
			case UP:
				return AABB_UP;
			case DOWN:
				return AABB_DOWN;
		}
	}
	
	public AxisAlignedBB getCollisionBoundingBox(IBlockState blockState, IBlockAccess worldIn, BlockPos pos) {
		return null;
	}
	
	public IBlockState getStateForPlacement(World worldIn, BlockPos pos, EnumFacing facing, float hitX, float hitY, float hitZ, int meta, EntityLivingBase placer) {
		if(canPlaceBlock(worldIn, pos, facing.getOpposite())) return this.getDefaultState().withProperty(FACING, facing).withProperty(IS_FULL, false);
		return this.getDefaultState().withProperty(FACING, EnumFacing.NORTH).withProperty(IS_FULL, false);
	}
	
	private static boolean canPlaceBlock(World world, BlockPos pos, EnumFacing dir) {
		if(dir.equals(EnumFacing.UP) || dir.equals(EnumFacing.DOWN)) return false;
		BlockPos outPos = pos.offset(dir);
		return world.getBlockState(outPos).isSideSolid(world, outPos, dir.getOpposite());
	}
	
	public void neighborChanged(IBlockState state, World worldIn, BlockPos pos, Block blockIn, BlockPos fromPos) {
		if(this.checkForDrop(worldIn, pos, state) && !canPlaceBlock(worldIn, pos, ((EnumFacing)state.getValue(FACING)).getOpposite())) {
			this.dropBlockAsItem(worldIn, pos, state, 0);
			worldIn.setBlockToAir(pos);
		}
	}
	
	public boolean canPlaceBlockOnSide(World worldIn, BlockPos pos, EnumFacing side) {
		return canPlaceBlock(worldIn, pos, side.getOpposite());
	}
	
	public boolean canPlaceBlockAt(World worldIn, BlockPos pos) {
		for (EnumFacing enumfacing : EnumFacing.values()) {
			if (canPlaceBlock(worldIn, pos, enumfacing)) {
				return true;
			}
		}
		return false;
	}
	
	public IBlockState getStateFromMeta(int meta) {
		return this.getDefaultState().withProperty(FACING, getFacing(meta)).withProperty(IS_FULL, Boolean.valueOf((meta & 8) > 0));
	}
	
	public int getMetaFromState(IBlockState state) {
		int i = 0;
		i = i | ((EnumFacing) state.getValue(FACING)).getIndex();
		if(((Boolean) state.getValue(IS_FULL)).booleanValue()) i |= 8;
		return i;
	}
	
	public IBlockState withRotation(IBlockState state, Rotation rot) {
		return state.withProperty(FACING, rot.rotate((EnumFacing) state.getValue(FACING)));
	}
	
	public IBlockState withMirror(IBlockState state, Mirror mirrorIn) {
		return state.withRotation(mirrorIn.toRotation((EnumFacing) state.getValue(FACING)));
	}

	public TileEntity createNewTileEntity(World worldIn, int meta) {
		return new TileEntityStocking();
	}
	
	protected BlockStateContainer createBlockState() {
		return new BlockStateContainer(this, new IProperty[] { FACING, IS_FULL });
	}
	
	private boolean checkForDrop(World worldIn, BlockPos pos, IBlockState state) {
		if(this.canPlaceBlockAt(worldIn, pos)) {
			return true;
		} else {
			this.dropBlockAsItem(worldIn, pos, state, 0);
			worldIn.setBlockToAir(pos);
			return false;
		}
	}
	
	@Nullable
	public static EnumFacing getFacing(int meta) {
		int i = meta & 7;
		return i > 5 ? null : EnumFacing.getFront(i);
	}
	
	public static void setFullAll(World world, boolean full) {
		List<TileEntity> loaded = world.loadedTileEntityList;
		for(TileEntity te : loaded) {
			if(te instanceof TileEntityStocking) {
				TileEntityStocking tile = (TileEntityStocking) te;
				if(tile.hasOwner()) {
					IBlockState state = world.getBlockState(tile.getPos());
					BlockStocking block = (BlockStocking) state.getBlock();
					block.setFull(world, tile.getPos(), state, full);
					if(full) {
						EntityPlayer player = world.getPlayerEntityByUUID(tile.getOwner());
						if(player != null) Util.sendMsg(player, TextFormatting.GREEN + "" + TextFormatting.BOLD + Util.translate("str.stocking_santa_came"));
					}
					Log.info("Filled stocking at: " + tile.getPos());
				}
			}
		}
	}
	
}