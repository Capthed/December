package com.capthed.abyss.gfx;

import com.capthed.abyss.GameLoop;
import com.capthed.abyss.component.GameObject;
import com.capthed.abyss.font.CharElement;
import com.capthed.abyss.math.Vec2;
import com.capthed.util.Debug;

import static org.lwjgl.opengl.GL11.*;

public abstract class Render {
	
	/** Renders a GameObject. */
	public static void object(GameObject go) {
		if (!checkBoundaries(go)) return;
		
		if (go.isBlend())
			texBlended(go.getPos(), go.getSize(), go.getTex(), go.getLayer());
		else
			tex(go.getPos(), go.getSize(), go.getTex(), go.getLayer());
		
		GameLoop.addTex();
	}
	
	/** Renders a CharElement texture. */
	public static void charTex(Vec2 pos, Vec2 size, CharElement ce, int layer) {
		if (!Camera.getCurrent().checkBoundaries(pos, size)) return;
		float x = pos.x();
		float y = pos.y();
		float w = size.x();
		float h = size.y();
		
		float su = ce.getUVPos().x();
		float sv = ce.getUVPos().y();
		float eu = ce.getUVSize().x();
		float ev = ce.getUVSize().y();
		
		float l = layer;
		
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
	

	/** Renders a texture to the screen with the position and size as arguments. */
	public static void texBlended(Vec2 pos, Vec2 size, Texture tex, int layer) {
		float x = pos.x();
		float y = pos.y();
		float w = size.x();
		float h = size.y();
		
		if (!Debug.isDebug()) return;
				
			tex.bind();
			glEnable( GL_BLEND );
			glBlendFunc(GL_SRC_ALPHA, GL_ONE_MINUS_SRC_ALPHA);
			glDepthMask(false);
			
			glBegin(GL_QUADS);
			
			{
				glTexCoord2f(0, 1f);
		        glVertex3f(x, y, layer);
		 
		        glTexCoord2f(1f, 1f);
		        glVertex3f(x + w, y, layer);
		 
		        glTexCoord2f(1f, 0);
		        glVertex3f(x + w, y + h, layer);
		 
		        glTexCoord2f(0, 0);
		        glVertex3f(x, y + h, layer);
			}
			glEnd();
			glDepthMask(true);
			glDisable(GL_BLEND);
			Texture.unbind();
	}
	
	/** Renders a texture to the screen with the position and size as arguments. */
	public static void tex(Vec2 pos, Vec2 size, Texture tex, int layer) {
		float x = pos.x();
		float y = pos.y();
		float w = size.x();
		float h = size.y();
		
		if (!Debug.isDebug()) return;
				
			tex.bind();
			glBegin(GL_QUADS);
			
			{
				glTexCoord2f(0, 1f);
		        glVertex3f(x, y, layer);
		 
		        glTexCoord2f(1f, 1f);
		        glVertex3f(x + w, y, layer);
		 
		        glTexCoord2f(1f, 0);
		        glVertex3f(x + w, y + h, layer);
		 
		        glTexCoord2f(0, 0);
		        glVertex3f(x, y + h, layer);
			}
			glEnd();
			glAlphaFunc(GL_GREATER, 0);
			Texture.unbind();
	}
	
	private static boolean checkBoundaries(GameObject go) {
		return Camera.getCurrent().checkBoundaries(go.getPos(), go.getSize());
	}
}