package com.capthed.abyss.input;

import static org.lwjgl.glfw.GLFW.GLFW_PRESS;
import static org.lwjgl.glfw.GLFW.GLFW_RELEASE;

import org.lwjgl.glfw.GLFWKeyCallback;

public class Keyboard extends GLFWKeyCallback {
	
	private static int size = 65536;
	
	public static boolean[] keysDown;
	public static int[] keysPressed;
	public static boolean listening = true;
	
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
		if (action == GLFW_RELEASE)
			keysDown[key] = false;
		else
			keysDown[key] = true;
			
		if (action == GLFW_PRESS && keysPressed[key] == 0)
			keysPressed[key] = 1;
		else if (action == GLFW_RELEASE || listening == false)
			keysPressed[key] = 0;
	}
	
	/** @return True if the key is held down. */
	public static boolean isKeyDown(int key) {
		if (!listening) return false;
		return keysDown[key];
	}
	
	/** 
	 * @return True if the key is pressed. Returns true just the frame that the key was clicked and false until
	 * it is released and clicked again.
	 */
	public static boolean isKeyPressed(int key) {
		if (!listening) return false;
		return keysPressed[key] == 1 ? true : false;
	}
	
	static void postProcess() {
		for (int i = 0; i < size; i++) { 
			if (keysPressed[i] == 1)
				keysPressed[i] = 2;
		}
	}

	public static boolean isListening() {
		return listening;
	}

	public static void setListening(boolean listening) {
		Keyboard.listening = listening;
	}
}