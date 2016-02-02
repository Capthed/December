package com.capthed.abyss.gfx;

import com.capthed.abyss.GameLoop;
import com.capthed.abyss.component.GameObject;

import static org.lwjgl.opengl.GL11.*;

public abstract class Render {
	
	/** Renders a quaternion texture. */
	public static void quadTex(GameObject go) {
		if (!checkBoundaries(go)) return;
		
		float x = go.getPos().x();
		float y = go.getPos().y();
		float w = go.getSize().x();
		float h = go.getSize().y();
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
		
		GameLoop.addTex();
	}
	
	private static boolean checkBoundaries(GameObject go) {
		return Camera.getCurrent().checkBoundaries(go.getPos(), go.getSize());
	}
}