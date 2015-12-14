package com.capthed.abyss.gfx;

import static org.lwjgl.opengl.GL11.*;

public abstract class RenderDebug {
	
	/** Draws a quad on the screen with the start coordinates and the width and height */
	public static void quad(float x, float y, float w, float h) {
		glDisable(GL_TEXTURE_2D);
		glBegin(GL_QUADS);
		{
			glVertex3f(x, y, 0);
	        glVertex3f(x + w, y, 0);
	        glVertex3f(x + w, y + h, 0);
	        glVertex3f(x, y + h, 0);	
		}
		glEnd();
		glEnable(GL_TEXTURE_2D);
	}
	
	/** Draws a triangle on the screen with the coordinates of all the three points. */
	public static void triangle(float x, float y, float x1, float y1, float x2, float y2) {	
		glDisable(GL_TEXTURE_2D);
		glBegin(GL_TRIANGLES);
		{
			glVertex3f(x, y, 0);
	        glVertex3f(x1, y1, 0);
	        glVertex3f(x2, y2, 0);
		}
		glEnd();
		glEnable(GL_TEXTURE_2D);
	}
	
	/** Draws a line on the screen with the coordinates of the two points and the line width. */
	public static void line(float x, float y, float x1, float y1, float w) {
		glDisable(GL_TEXTURE_2D);
		glBegin(GL_LINES);
		{
			glLineWidth(w);
			glVertex3f(x, y, 0);
	        glVertex3f(x1, y1, 0);;
		}
		glEnd();
		glEnable(GL_TEXTURE_2D);
	}
	
	/** Fuck you */
	public static void church() {
		RenderUtil.setColor(0.870588f, 0.721569f, 0.529412f, 1);
		RenderDebug.quad(50, 50, 64, 64);
		RenderUtil.setColor(1, 0, 0, 1);
		RenderDebug.triangle(50, 114, 82, 200, 114, 114);	
		RenderDebug.line(82, 200, 82, 250, 4);
		RenderDebug.line(60, 230, 104, 230, 4);
		RenderUtil.setColor(1, 1, 1, 1);
	}
}
