package com.cjburkey.mod.wonderland.dimension;

import com.cjburkey.mod.wonderland.Log;
import net.minecraft.entity.Entity;
import net.minecraft.entity.player.EntityPlayerMP;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.Teleporter;
import net.minecraft.world.WorldServer;

public class TeleporterNoPortal extends Teleporter {
	
	private WorldServer world;

	public TeleporterNoPortal(EntityPlayerMP player, int id) {
		super(player.getServer().worldServerForDimension(id));
		world = player.getServer().worldServerForDimension(id);
	}
	
	public void placeInPortal(Entity entity, float yaw) {
		entity.setVelocity(0.0d, 0.0d, 0.0d);
		entity.fallDistance = 0;
		double x = entity.posX;
		double z = entity.posZ;
		double y = world.getHeight();
		while(world.isAirBlock(new BlockPos(x, y, z))) y --;
		entity.setLocationAndAngles(x, y + 3f, z, entity.rotationYaw, 0.0F);
	}
	
}