package com.capthed.abyss.input;

import java.nio.DoubleBuffer;

import org.lwjgl.BufferUtils;

import com.capthed.abyss.gfx.Camera;
import com.capthed.abyss.gfx.Display;
import com.capthed.abyss.math.Vec2;

import static org.lwjgl.glfw.GLFW.*;

public abstract class Mouse {

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
	
	/** @return True if the key is held down. */
	public static boolean isKeyDown(int key) {
		boolean check = glfwGetMouseButton(Display.getDisplay(), key) == GLFW_PRESS ? true : false;
		keysDown[key] = check;
		
		return check;
	}
	
	/** 
	 * @return True if the key is pressed. Returns true just the frame that the key was clicked and false until
	 * it is released and clicked again.
	 */
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
	
	public static Vec2 getPos() {
		return new Vec2(getX(), getY());
	}
	
	/** @return The x position of the mouse on the screen. */
	public static float getX() {
		updatePos();
		x.rewind();
	
		float fx = (float)(x.get());
		fx += Camera.getCurrent().getPos().x();
		
		return fx;
	}
	
	/** @return The y position of the mouse on the screen. */
	public static float getY() {
		updatePos();
		y.rewind();
		
		float fy = (float)y.get();
		fy -= Camera.getCurrent().getPos().y();
		
		return Display.getHeight() - fy;
	}
	
	static void postProcess() {
		for (int i = 0; i < size; i++) { 
			if (keysPressed[i] == 1)
				keysPressed[i] = 2;
		}
	}
}