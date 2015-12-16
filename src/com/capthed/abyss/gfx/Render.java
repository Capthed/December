package com.capthed.abyss.gfx;

import com.capthed.abyss.component.GameObject;

import static org.lwjgl.opengl.GL11.*;

public class Render {
	
	/** Renders a quaternion texture. */
	public static void quadtTex(GameObject go) {
		float x = go.getPos().getX();
		float y = go.getPos().getY();
		float w = go.getSize().getX();
		float h = go.getSize().getY();
		float l = go.getLayer();
		
		go.getTex().bind();
		glBegin(GL_QUADS);
		
		{
			glTexCoord2f(0, 1);
	        glVertex3f(x, y, l);
	 
	        glTexCoord2f(1, 1);
	        glVertex3f(x + w, y, l);
	 
	        glTexCoord2f(1, 0);
	        glVertex3f(x + w, y + h, l);
	 
	        glTexCoord2f(0, 0);
	        glVertex3f(x, y + h, l);
		}
		
		glEnd();
		Texture.unbind();
	}
}