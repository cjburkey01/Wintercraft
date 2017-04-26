package com.cjburkey.mod.wintercraft;

import java.util.Random;

public class Util {
	
	public static final int randomRange(Random rand, int min, int max) {
		return rand.nextInt((max - min) + 1) + min;
	}
	
}