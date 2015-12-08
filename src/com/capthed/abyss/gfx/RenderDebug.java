package com.capthed.abyss.gfx;

import static org.lwjgl.opengl.GL11.*;

public abstract class RenderDebug {
	
	// The coordinates of the begining + the width and height
	public static void quad(float x, float y, float w, float h) {
		glBegin(GL_QUADS);
		{
			glVertex3f(x, y, 0);
	        glVertex3f(x + w, y, 0);
	        glVertex3f(x + w, y + h, 0);
	        glVertex3f(x, y + h, 0);	
		}
		glEnd();
	}
	
	// The coordiantes of all 3 points
	public static void triangle(float x, float y, float x1, float y1, float x2, float y2) {	
		glBegin(GL_TRIANGLES);
		{
			glVertex3f(x, y, 0);
	        glVertex3f(x1, y1, 0);
	        glVertex3f(x2, y2, 0);
		}
		glEnd();
	}
	
	// The coordinates of the two line points and the width of the line
	public static void line(float x, float y, float x1, float y1, float w) {
		glBegin(GL_LINES);
		{
			glLineWidth(w);
			glVertex3f(x, y, 0);
	        glVertex3f(x1, y1, 0);;
		}
		glEnd();
	}
	
	// Changes the color for ALL OF OPENGL!
	public static void setColor(float r, float g, float b, float a) {
		glColor4f(r, g, b, a);
	}
	
	// Fuck you
	public static void church() {
		RenderDebug.setColor(0.870588f, 0.721569f, 0.529412f, 1);
		RenderDebug.quad(50, 50, 64, 64);
		RenderDebug.setColor(1, 0, 0, 1);
		RenderDebug.triangle(50, 114, 82, 200, 114, 114);	
		RenderDebug.line(82, 200, 82, 250, 4);
		RenderDebug.line(60, 230, 104, 230, 4);
		RenderDebug.setColor(1, 1, 1, 1);
	}
}
