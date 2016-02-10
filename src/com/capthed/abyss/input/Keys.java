package com.capthed.abyss.input;

import org.lwjgl.glfw.GLFW;

/**
 * Temporary class that extends GLFW to enable use of keyboard and ouse keys. 
 * Example : <code>Keys.GLFW_KEY_SPACE</code>
 */
public class Keys extends GLFW {

	public static final int BUTTON_A = 0;
	public static final int BUTTON_B = 1;
	public static final int BUTTON_X = 2;
	public static final int BUTTON_Y = 3;
	public static final int BUTTON_LB = 4;
	public static final int BUTTON_RB = 5;
	public static final int BUTTON_SELECT = 6;
	public static final int BUTTON_START = 7;
	public static final int BUTTON_LEFTANALOG = 8;
	public static final int BUTTON_RIGHTANALOG = 9;
	public static final int BUTTON_UP = 10;
	public static final int BUTTON_RIGHT = 11;
	public static final int BUTTON_DOWN = 12;
	public static final int BUTTON_LEFT = 13;
	public static final int LA_L_R = 0;
	public static final int LA_U_D = 1;
	public static final int LT_RT = 2;
	public static final int RA_U_D = 3;
	public static final int RA_L_R = 4;
	
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