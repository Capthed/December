package com.capthed.abyss.gfx;

import static org.lwjgl.opengl.GL11.*;

import org.lwjgl.opengl.GL;

public abstract class RenderUtil {

	private static final int LAYER = 100;
	
	/** Initializes all of OpenGL used for 2D graphics. */
	public static void init2DGL(int w, int h) {
		GL.createCapabilities();
		
		glEnable(GL_TEXTURE_2D);
			
		glMatrixMode(GL_PROJECTION);
		glLoadIdentity();
		glOrtho(0, w, 0, h, -LAYER, LAYER);
		
		glMatrixMode(GL_MODELVIEW);
		glEnable(GL_DEPTH_TEST); // Because we use depth as priority rendering
		
		glEnable(GL_ALPHA_TEST);
		glAlphaFunc(GL_GREATER, 0);
		
		glViewport(0, 0, w, h);
	}
	
	/** Set the clear color of OpenGL. */
	public static void setClearColor(float r, float g, float b, float a) {
		glClearColor(r, g, b, a);
	}
	
	/** Clear OpenGL buffers. */
	public static void clearScreen() {
		glClear(GL_COLOR_BUFFER_BIT | GL_DEPTH_BUFFER_BIT);
	}
	
	/** Changes the render color for all of OpenGL. WARNING - set back to (1, 1, 1, 1) after each use. */
	public static void setColor(float r, float g, float b, float a) {
		glColor4f(r, g, b, a);
	}
	
	/** @return The highest (and lowest - negative) value of a layer. */
	public static int layerLimit() { return LAYER; }
	
	public static String getGLVersionUse() { return "1.0"; }
}
