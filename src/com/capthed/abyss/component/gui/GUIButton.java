package com.capthed.abyss.component.gui;

import com.capthed.abyss.component.GameObject;
import com.capthed.abyss.gfx.Animation;
import com.capthed.abyss.gfx.RenderDebug;
import com.capthed.abyss.gfx.Texture;
import com.capthed.abyss.input.Keys;
import com.capthed.abyss.input.Mouse;
import com.capthed.abyss.math.Vec2;

public class GUIButton extends GameObject {
	
	private GUIButtonListener handler;
	private boolean focus = false;
	
	public GUIButton(Vec2 pos, Vec2 size, GUIButtonListener handler) {
		super(pos, size);
		
		this.handler = handler;
	}
	
	public GUIButton(Vec2 pos, Vec2 size, Texture tex, GUIButtonListener handler) {
		super(pos, size, tex);
		
		this.handler = handler;
	}
	
	public GUIButton(Vec2 pos, Vec2 size, Animation anim, GUIButtonListener handler) {
		super(pos, size, anim);
		
		this.handler = handler;
	}
	
	public void update() {
		boolean exFocus = focus;
		if (Mouse.getPos().intersects(pos, size)) {
			handler.hover();
			
			if (Mouse.isKeyPressed(Keys.GLFW_MOUSE_BUTTON_1)) {
				handler.clicked();
				
				focus = true;
			}
		}
		else if (Mouse.isKeyPressed(Keys.GLFW_MOUSE_BUTTON_1))
			focus = false;
		
		if (exFocus != focus) {
			if (focus) handler.onGainFocus();
			else handler.onLoseFocus();
		}
	}
	
	public void render() {
		super.render();
		renderCollisionBox();
	}
	
	public void renderCollisionBox() {
		RenderDebug.box(pos, size, this.layer);
	}

	public GUIButtonListener getHandler() {
		return handler;
	}

	public void setHandler(GUIButtonListener handler) {
		this.handler = handler;
	}
}