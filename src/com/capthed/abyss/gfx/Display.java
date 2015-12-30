package com.capthed.abyss.gfx;

import static org.lwjgl.glfw.GLFW.*;
import static org.lwjgl.system.MemoryUtil.*;

import org.lwjgl.PointerBuffer;
import org.lwjgl.glfw.GLFWVidMode;

import com.capthed.abyss.GameLoop;
import com.capthed.abyss.input.Keyboard;
import com.capthed.util.Debug;

public abstract class Display {

	private static long display;
	private static boolean showMouse = true;
	private static boolean fullscreen = false;
	private static int width, height;
	private static Keyboard keyboard;
	
	/** Creates the display with the position, title and use of borders and decorations. */
	public static void create(int w, int h, String title, boolean decorated) {
		width = w;
		height = h;
		
		glfwInit();
		
		glfwWindowHint(GLFW_VISIBLE, GLFW_FALSE);
		glfwWindowHint(GLFW_RESIZABLE, GLFW_FALSE);
		
		GLFWVidMode vidmode = glfwGetVideoMode(glfwGetPrimaryMonitor());
		
		if (fullscreen) {
			decorated = false;
			width = w = vidmode.width();
			height = h = vidmode.height();
			
			GameLoop.setW(width);
			GameLoop.setH(height);
		}
		
		glfwWindowHint(GLFW_DECORATED, decorated ? GLFW_TRUE : GLFW_FALSE);
		
		display = glfwCreateWindow(w, h, title, NULL, NULL);
		if (display == NULL)
			Debug.print("Display not created", "");

		keyboard = new Keyboard();
		glfwSetKeyCallback(display, keyboard);
		
		int w1 = vidmode.width();
		int h1 = vidmode.height();
		
		int tempx = 0, tempy = 0;
		if (Debug.isDebug()) {
			PointerBuffer b = glfwGetMonitors();
			GLFWVidMode vidmodeTemp = null;
			
			try {
				// ovdi mi otvara na drugon monitoru ako je u debug modu
				// samo iskomentiraj cili ovaj if pa ti nece vise javljat nista
				vidmodeTemp = glfwGetVideoMode(b.get(1));
				tempy = vidmodeTemp.height();
				tempx = vidmodeTemp.width();
				h1 = tempy;
				
			} catch(IndexOutOfBoundsException e) {
				Debug.err("Javore picko imas samo jedan monitor, vidi kod za display");
				vidmodeTemp = glfwGetVideoMode(b.get(0));
			}
		}
		
		int posW =  tempx + (w1 - width) / 2;
		int posH = (h1 - height) / 2;
			
		glfwSetWindowPos (display, posW, posH);
		
		glfwMakeContextCurrent(display);
		
		glfwSetInputMode(display, GLFW_CURSOR, showMouse ? GLFW_CURSOR_NORMAL: GLFW_CURSOR_HIDDEN);
	}
	
	public static void fullscreen(boolean fs) { fullscreen = fs; }
	
	public static void show() {	
		glfwShowWindow(display);
	}
	
	/** Swaps the display buffer. Called once in the game loop. */
	public static void swap() {
		 glfwSwapBuffers(display);
	}
	
	/** @return True if the display is closing */
	public static boolean isCloseRequested() {
		return glfwWindowShouldClose(display) == GLFW_TRUE ? true : false;
	}

	/** @return True if the mouse is visible */
	public static boolean isShowMouse() {
		return showMouse;
	}

	public static void setShowMouse(boolean showMouse) {
		Display.showMouse = showMouse;
	}

	/** @return The GLFW Window long. */
	public static long getDisplay() {
		return display;
	}

	public static int getWidth() {
		return width;
	}

	public static int getHeight() {
		return height;
	}
}
