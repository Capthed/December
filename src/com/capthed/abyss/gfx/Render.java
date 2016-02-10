package com.capthed.abyss.gfx;

import com.capthed.abyss.GameLoop;
import com.capthed.abyss.component.GameObject;
import com.capthed.abyss.font.CharElement;
import com.capthed.abyss.math.Vec2;

import static org.lwjgl.opengl.GL11.*;

public abstract class Render {
	
	/** Renders a quaternion texture. */
	public static void quadTex(GameObject go) {
		if (!checkBoundaries(go)) return;
		
		float x = go.getPos().x();
		float y = go.getPos().y() ;
		float w = go.getSize().x();
		float h = go.getSize().y();
		float l = go.getLayer();
		
		go.getTex().bind();
		glBegin(GL_QUADS);
		
		{
			glTexCoord2f(0, 1f);
	        glVertex3f(x, y, l);
	 
	        glTexCoord2f(1f, 1f);
	        glVertex3f(x + w, y, l);
	 
	        glTexCoord2f(1f, 0);
	        glVertex3f(x + w, y + h, l);
	 
	        glTexCoord2f(0, 0);
	        glVertex3f(x, y + h, l);
		}
		
		glEnd();
		Texture.unbind();
		
		GameLoop.addTex();
	}
	
	/** Renders a CharElement texture. */
	public static void charTex(Vec2 pos, Vec2 size, CharElement ce) {
		float x = pos.x();
		float y = pos.y();
		float w = size.x();
		float h = size.y();
		
		float su = ce.getUVPos().x();
		float sv = ce.getUVPos().y();
		float eu = ce.getUVSize().x();
		float ev = ce.getUVSize().y();
		
		float l = RenderUtil.layerLimit();
		
		ce.getFont().getTex().bind();
		glBegin(GL_QUADS);
		
		{
			glTexCoord2f(su, ev);
	        glVertex3f(x, y, l);
	 
	        glTexCoord2f(eu, ev);
	        glVertex3f(x + w, y, l);
	 
	        glTexCoord2f(eu, sv);
	        glVertex3f(x + w, y + h, l);
	 
	        glTexCoord2f(su, sv);
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