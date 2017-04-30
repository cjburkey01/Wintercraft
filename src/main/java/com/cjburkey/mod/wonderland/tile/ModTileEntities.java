package com.cjburkey.mod.wonderland.tile;

import net.minecraftforge.fml.common.registry.GameRegistry;

public final class ModTileEntities {
	
	public static final void commonPreinit() {
		GameRegistry.registerTileEntity(TileEntityGift.class, "tile_entity_gift");
		GameRegistry.registerTileEntity(TileEntityStocking.class, "tile_entity_stocking");
	}
	
}