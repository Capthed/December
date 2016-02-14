package com.capthed.abyss.component.gui;

import com.capthed.abyss.component.GameObject;
import com.capthed.abyss.gfx.RenderDebug;
import com.capthed.abyss.gfx.RenderUtil;
import com.capthed.abyss.gfx.Texture;
import com.capthed.abyss.input.Keys;
import com.capthed.abyss.input.Mouse;
import com.capthed.abyss.math.Vec2;

public class GUICheckBox extends GameObject{

	private GUICheckBoxListener handler;
	private boolean state;
	private boolean focus = false;
	private Texture onTex;
	private Texture offTex;
	
	public GUICheckBox(Vec2 pos, Vec2 size, Texture offTex, Texture onTex, boolean startState, GUICheckBoxListener handler) {
		super(pos, size, startState ? onTex : offTex);
		
		this.state = startState;
		this.tex = state ? onTex : offTex;
		this.onTex = onTex;
		this.offTex = offTex;
		this.handler = handler;
	}
	
	public GUICheckBox(Vec2 pos, Vec2 size, Texture tex, boolean startState, GUICheckBoxListener handler) {
		super(pos, size, tex);
		
		this.state = startState;
		this.tex = tex;
		this.handler = handler;
	}

	public void update() {
		boolean exFocus = focus;
		
		if (Mouse.getPos().intersects(pos, size) ) {
			if (Mouse.isKeyPressed(Keys.GLFW_MOUSE_BUTTON_1)) {
				if (state) {
					state = false;
					tex = offTex;
					handler.onChangeState(state);
				} else {
					state = true;
					tex = onTex;
					handler.onChangeState(state);
				}
				
				focus = true;
			}
			
			handler.hover();
		}
		else if (Mouse.isKeyPressed(Keys.GLFW_MOUSE_BUTTON_1))
			focus = false;
		
		if (exFocus != focus) {
			if (focus) handler.onGainFocus();
			else handler.onLoseFocus();
		}
	}
	
	public void render() {
		if (onTex == null) {
			if (state)
				super.render();
			else {
				RenderUtil.setColor(0.2f, 0.2f, 0.2f, 1);
				super.render();
				RenderUtil.resetColor();
			}
		}
		else 
			super.render();
		
		renderCollisionBox();
	}
	
	public void renderCollisionBox() {
		RenderDebug.box(pos, size, layer);
	}
	
	public Texture getOnTex() {
		return onTex;
	}

	public void setOnTex(Texture onTex) {
		this.onTex = onTex;
	}

	public Texture getOffTex() {
		return offTex;
	}

	public void setOffTex(Texture offTex) {
		this.offTex = offTex;
	}
	
	public void setState(boolean state) {
		this.state = state;
	}

	/** @return True if it is on, false otherwise. */
	public boolean getState() {
		return state;
	}
}