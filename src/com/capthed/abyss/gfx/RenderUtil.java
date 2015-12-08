package com.capthed.abyss.gfx;

import static org.lwjgl.opengl.GL11.*;

import org.lwjgl.opengl.GL;

public abstract class RenderUtil {

	public static void init2DGL(int w, int h) {
		GL.createCapabilities();
		
		glBlendFunc(GL_SRC_ALPHA, GL_ONE_MINUS_SRC_ALPHA);
		
		glMatrixMode(GL_PROJECTION);
		glLoadIdentity();
		glOrtho(0, w, 0, h, -100, 100);
		
		glMatrixMode(GL_MODELVIEW);
		glEnable(GL_DEPTH_TEST); // Because we use depth as priority rendering
		glEnable(GL_BLEND);
		
		glViewport(0, 0, w, h);
	}
	
	public static void setClearColor(float r, float g, float b, float a) {
		glClearColor(r, g, b, a);
	}
	
	public static void clearScreen() {
		glClear(GL_COLOR_BUFFER_BIT | GL_DEPTH_BUFFER_BIT);
	}
}
