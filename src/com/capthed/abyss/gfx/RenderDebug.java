package com.capthed.abyss.gfx;

import static org.lwjgl.opengl.GL11.*;

import com.capthed.abyss.math.Vec2;
import com.capthed.abyss.physics.CircleCollider;
import com.capthed.abyss.physics.Collider;
import com.capthed.abyss.physics.QuadCollider;

public abstract class RenderDebug {
	
	private static int layer = RenderUtil.layerLimit();
	
	/** Draws a quad on the screen with the start coordinates and the width and height */
	public static void quad(float x, float y, float w, float h) {
		glDisable(GL_TEXTURE_2D);
		glBegin(GL_QUADS);
		{
			glVertex3f(x, y, layer);
	        glVertex3f(x + w, y, layer);
	        glVertex3f(x + w, y + h, layer);
	        glVertex3f(x, y + h, layer);	
		}
		glEnd();
		glEnable(GL_TEXTURE_2D);
	}
	
	/** Draws a triangle on the screen with the coordinates of all the three points. */
	public static void triangle(float x, float y, float x1, float y1, float x2, float y2) {	
		glDisable(GL_TEXTURE_2D);
		glBegin(GL_TRIANGLES);
		{
			glVertex3f(x, y, layer);
	        glVertex3f(x1, y1, layer);
	        glVertex3f(x2, y2, layer);
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
			glVertex3f(x, y, layer);
	        glVertex3f(x1, y1, layer);;
		}
		glEnd();
		glEnable(GL_TEXTURE_2D);
	}
	
	public static void line(Vec2 v1, Vec2 v2, float w) {
		line(v1.x(), v1.y(), v2.x(), v2.y(), w);
	}
	
	/** Sets the layer for this class. Default is highest. */
	public static void setLayer(int l) {
		layer = l;
	}
	
	public static void collider(Collider c) {
		RenderUtil.setColor(0, 1, 0, 1);
		glDisable(GL_TEXTURE_2D);
		glLineWidth(2);
		
		if (c instanceof QuadCollider) {
			QuadCollider qc = (QuadCollider)c;
			
			glBegin(GL_LINE_LOOP);
			{
				glVertex3f(qc.bottomLeft().x(), qc.bottomLeft().y(), qc.getLayer());
				glVertex3f(qc.bottomLeft().x(), qc.topRight().y(), qc.getLayer());
				glVertex3f(qc.topRight().x(), qc.topRight().y(), qc.getLayer());
				glVertex3f(qc.topRight().x(), qc.bottomLeft().y(), qc.getLayer());
			}
			glEnd();
		}
		else if (c instanceof CircleCollider) {
			CircleCollider cc = (CircleCollider) c;
			int vertecies = 100;
			double pi2 = Math.PI * 2;
			
			glBegin(GL_LINE_LOOP);
			{
				for (int i = 0; i < vertecies; i++) {
					glVertex3d(cc.getPos().x() + cc.getR() * Math.cos(i * pi2 / vertecies), cc.getPos().y() + cc.getR() * Math.sin(i * pi2 / vertecies), cc.getLayer());
				}
			}
			glEnd();
			
		}
		
		glEnable(GL_TEXTURE_2D);
		RenderUtil.setColor(1, 1, 1, 1);
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