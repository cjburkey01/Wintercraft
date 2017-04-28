package com.cjburkey.mod.wintercraft;

import java.util.Random;

public final class Util {
	
	public static int randomRange(Random rand, int min, int max) {
		return rand.nextInt((max - min) + 1) + min;
	}
	
}