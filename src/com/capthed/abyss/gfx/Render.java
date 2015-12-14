package com.capthed.abyss.gfx;

import com.capthed.abyss.component.GameObject;

import static org.lwjgl.opengl.GL11.*;

public class Render {
	
	public static void quadtTex(GameObject go) {
		float x = go.getPos().getX();
		float y = go.getPos().getY();
		float w = go.getSize().getX();
		float h = go.getSize().getY();
		
		go.getTex().bind();
		glBegin(GL_QUADS);
		
		{
			glTexCoord2f(0, 1);
	        glVertex2f(x, y);
	 
	        glTexCoord2f(1, 1);
	        glVertex2f(x + w, y);
	 
	        glTexCoord2f(1, 0);
	        glVertex2f(x + w, y + h);
	 
	        glTexCoord2f(0, 0);
	        glVertex2f(x, y + h);
		}
		
		glEnd();
		Texture.unbind();
	}
}