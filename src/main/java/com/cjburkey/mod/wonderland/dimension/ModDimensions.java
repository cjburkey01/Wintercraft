package com.cjburkey.mod.wonderland.dimension;

import com.cjburkey.mod.wonderland.cfg.ModConfigHandler;
import net.minecraft.world.DimensionType;
import net.minecraftforge.common.DimensionManager;

public final class ModDimensions {
	
	public static DimensionType wonderlandDimensionType;
	
	public static void commonInit() {
		int id = ModConfigHandler.wonderlandDimensionId;
		wonderlandDimensionType = DimensionType.register("wonderland_dimension", "_wonderland", id, WorldProviderWonderland.class, false);
		DimensionManager.registerDimension(id, wonderlandDimensionType);
	}
	
}