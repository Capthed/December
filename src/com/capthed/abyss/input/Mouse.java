package com.capthed.abyss.input;

import java.nio.DoubleBuffer;

import org.lwjgl.BufferUtils;

import com.capthed.abyss.gfx.Display;

import static org.lwjgl.glfw.GLFW.*;

public class Mouse {

	private static int size = 12;
	
	private static boolean[] keysDown;
	private static int[] keysPressed;
	
	private static DoubleBuffer x;
    private static DoubleBuffer y;
	
	static void init() {
		keysDown = new boolean [size];
		keysPressed = new int[size];
		
		x = BufferUtils.createDoubleBuffer(1);
	    y = BufferUtils.createDoubleBuffer(1);
		
	    updatePos();
	    
		for (int i = 0; i < size; i++) {
			keysDown[i] = false;
			keysPressed[i] = 0;
		}
	}
	
	public static boolean isKeyDown(int key) {
		boolean check = glfwGetMouseButton(Display.getDisplay(), key) == GLFW_PRESS ? true : false;
		keysDown[key] = check;
		
		return check;
	}
	
	public static boolean isKeyPressed(int key) {
		boolean check = glfwGetMouseButton(Display.getDisplay(), key) == GLFW_PRESS ? true : false;
		
		if (check && keysPressed[key] == 0)
			keysPressed[key] = 1;
		
		if (glfwGetMouseButton(Display.getDisplay(), key) == GLFW_RELEASE)
			keysPressed[key] = 0;
		
		return keysPressed[key] == 1 ? true : false;
	}
	
	private static void updatePos() {
		x = BufferUtils.createDoubleBuffer(1);
	    y = BufferUtils.createDoubleBuffer(1);
		
		glfwGetCursorPos(Display.getDisplay(), x, y);	
	}
	
	// TODO: Vec2 pos
	
	public static float getX() {
		updatePos();
		x.rewind();
		
		return (float)x.get();
	}
	
	public static float getY() {
		updatePos();
		y.rewind();
		
		return (float)x.get();
	}
	
	static void postProcess() {
		for (int i = 0; i < size; i++) { 
			if (keysPressed[i] == 1)
				keysPressed[i] = 2;
		}
	}
}