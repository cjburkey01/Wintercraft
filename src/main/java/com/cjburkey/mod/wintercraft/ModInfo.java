package com.cjburkey.mod.wintercraft;

public class ModInfo {

	private static final String PROXY_BASE = ModInfo.class.getPackage().getName() + ".proxy";
	
	public static final String NAME = "Wintercraft";
	public static final String ID = "wintercraft";
	public static final String VERSION = "1.0.0";
	public static final String CLIENT_PROXY = PROXY_BASE + ".ClientProxy";
	public static final String SERVER_PROXY = PROXY_BASE + ".ServerProxy";
	
}