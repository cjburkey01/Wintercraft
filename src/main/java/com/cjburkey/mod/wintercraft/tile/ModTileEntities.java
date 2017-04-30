package com.cjburkey.mod.wintercraft.tile;

import net.minecraftforge.fml.common.registry.GameRegistry;

public class ModTileEntities {
	
	public static final void commonPreinit() {
		GameRegistry.registerTileEntity(TileEntityGift.class, "tile_entity_gift");
		GameRegistry.registerTileEntity(TileEntityStocking.class, "tile_entity_stocking");
	}
	
}