package com.capthed.abyss.input;

import static org.lwjgl.glfw.GLFW.glfwPollEvents;

public abstract class Input {

	public static void init() {
		Keyboard.init();
		Mouse.init();
		Controller.init();
	}
	
	public static void update() {
		Controller.update();
	}
	
	public static void postProcess() {
		Keyboard.postProcess();
		Mouse.postProcess();
	}
	
	/** Polls events from GLFW. Called once in the game loop. */
	public static void pollEvents() {
		glfwPollEvents();
	}
}