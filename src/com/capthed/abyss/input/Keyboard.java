package com.capthed.abyss.input;

import org.lwjgl.glfw.GLFWKeyCallback;

import static org.lwjgl.glfw.GLFW.*;

public class Keyboard extends GLFWKeyCallback {
	
	private static int size = 65536;
	
	public static boolean[] keysDown;
	public static int[] keysPressed;
	
	static void init() {	
		keysDown = new boolean[size];	
		keysPressed = new int[size];	
		
		for (int i = 0; i < size; i++) {
			keysDown[i] = false;
			keysPressed[i] = 0;
		}
	}
	
	@Override
	public void invoke(long window, int key, int scancode, int action, int mods) {
		keysDown[key] = action == GLFW_RELEASE ? false : true;
		
		if (action == GLFW_PRESS && keysPressed[key] == 0)
			keysPressed[key] = 1;
		else if (action == GLFW_RELEASE)
			keysPressed[key] = 0;
	}
	
	public static boolean isKeyDown(int key) {	
		return keysDown[key];
	}
	
	public static boolean isKeyPressed(int key) {
		return keysPressed[key] == 1 ? true : false;
	}
	
	static void postProcess() {
		for (int i = 0; i < size; i++) { 
			if (keysPressed[i] == 1)
				keysPressed[i] = 2;
		}
	}
}