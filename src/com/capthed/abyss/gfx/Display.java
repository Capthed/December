package com.capthed.abyss.gfx;

import static org.lwjgl.glfw.GLFW.*;
import static org.lwjgl.system.MemoryUtil.*;

import org.lwjgl.glfw.GLFWKeyCallback;

import com.capthed.util.Debug;

public abstract class Display {

	private static long display;
	private static GLFWKeyCallback keyCallback;
	
	public static void create(int w, int h, String title) {
		glfwInit();
		
		glfwWindowHint(GLFW_VISIBLE, GLFW_FALSE);
		glfwWindowHint(GLFW_RESIZABLE, GLFW_FALSE);
		
		display = glfwCreateWindow(w, h, title, NULL, NULL);
		if (display == NULL)
			Debug.print("Display not created", "");
		
		
		// TODO: edit when input is considered
		glfwSetKeyCallback(display, keyCallback = new GLFWKeyCallback() {
            @Override
            public void invoke(long window, int key, int scancode, int action, int mods) {
                if ( key == GLFW_KEY_ESCAPE && action == GLFW_RELEASE )
                    glfwSetWindowShouldClose(window, GLFW_TRUE);
            }
        });
		
		glfwSetWindowPos(display, 200, 200);
		
		glfwMakeContextCurrent(display);
		
		glfwShowWindow(display);
	}
	
	public static void swap() {
		 glfwSwapBuffers(display);
	}
	
	public static boolean isCloseRequested() {
		return glfwWindowShouldClose(display) == GLFW_TRUE ? true : false;
	}
	
	// TODO:
	public static void wtf() {
		glfwPollEvents();
	}
}
