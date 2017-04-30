package com.cjburkey.mod.wonderland.packet;

import com.cjburkey.mod.wonderland.ModInfo;
import net.minecraftforge.fml.common.network.NetworkRegistry;
import net.minecraftforge.fml.common.network.simpleimpl.SimpleNetworkWrapper;
import net.minecraftforge.fml.relauncher.Side;

public final class ModPackets {
	
	public static SimpleNetworkWrapper network;
	
	public static void commonPreinit() {
		network = NetworkRegistry.INSTANCE.newSimpleChannel(ModInfo.ID + "_channel_wrapper");
	}
	
}