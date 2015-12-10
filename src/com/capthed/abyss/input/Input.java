package com.capthed.abyss.input;

import static org.lwjgl.glfw.GLFW.glfwPollEvents;

public abstract class Input {

	public static void init() {
		Keyboard.init();
		Mouse.init();
	}
	
	public static void postProcess() {
		Keyboard.postProcess();
		Mouse.postProcess();
	}
	
	public static void pollEvents() {
		glfwPollEvents();
	}
}