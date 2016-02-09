package com.capthed.abyss.input;

import org.lwjgl.glfw.GLFW;

/**
 * Temporary class that extends GLFW to enable use of keyboard and ouse keys. 
 * Example : <code>Keys.GLFW_KEY_SPACE</code>
 */
public class Keys extends GLFW {

	public static int getKey(char c) {
		int key = -1;
		
		if (c >= 'A' && c <= 'Z')
			key = (int)c;
		else if (c >= 'a' && c <= 'z')
			key = (int)(c - 32);
		
		return key;
	}
	
	public static char getSign(int key) {
		return (char)key;
	}
}