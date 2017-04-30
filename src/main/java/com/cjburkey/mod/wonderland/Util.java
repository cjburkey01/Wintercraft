package com.cjburkey.mod.wonderland;

import java.util.Random;
import net.minecraft.entity.Entity;
import net.minecraft.util.text.TextComponentString;
import net.minecraft.util.text.TextComponentTranslation;

public final class Util {
	
	public static int randomRange(Random rand, int min, int max) {
		return rand.nextInt((max - min) + 1) + min;
	}
	
	public static void sendMsg(Entity to, String msg) {
		to.sendMessage(new TextComponentString(msg));
	}
	
	public static String translate(String unloc) {
		return new TextComponentTranslation(unloc).getFormattedText();
	}
	
}