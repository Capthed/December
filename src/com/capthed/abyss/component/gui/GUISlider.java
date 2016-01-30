package com.capthed.abyss.component.gui;

import com.capthed.abyss.component.Entity;
import com.capthed.abyss.component.GameObject;
import com.capthed.abyss.gfx.RenderDebug;
import com.capthed.abyss.gfx.Texture;
import com.capthed.abyss.input.Keys;
import com.capthed.abyss.input.Mouse;
import com.capthed.abyss.map.MapManager;
import com.capthed.abyss.math.Vec2;

public class GUISlider extends GameObject {

	private GUISliderListener handler;
	private Vec2 sliderPos;
	private Vec2 sliderSize;
	private Entity slider;
	private Texture sliderTex;
	private float maxValue;
	private float value;
	private boolean clicked = false;
	private boolean focus = false;
	private float marginOfError = 15f;
	
	public GUISlider(Vec2 pos, Vec2 size, Vec2 sliderSize, Texture tex, Texture sliderTex, float startValue, float maxValue, GUISliderListener handler) {
		super(pos, size, tex);
		
		this.value = startValue;
		this.sliderSize = sliderSize;
		this.maxValue = maxValue;
		this.sliderTex = sliderTex;
		this.handler = handler;
		
		this.sliderPos = new Vec2(pos.x() + size.x()/  (maxValue / value + 0.001f) - sliderSize.x() / 2, pos.y() + size.y()/  2 - sliderSize.y() / 2);
	}
	
	public void init() {
		slider = (Entity) new Slider(sliderPos, sliderSize, sliderTex).setLayer(layer + 1);
		MapManager.getCurrent().add(slider);
	}
	
	public void update() {
		boolean exFocus = focus;
		if (Mouse.getPos().intersects(sliderPos, sliderSize)) {
			handler.hover();
			
			clicked = true;
		}
		
		float delta = Mouse.getX() - slider.getCenter().x();
		if (Mouse.isKeyDown(Keys.GLFW_MOUSE_BUTTON_1) && clicked) {
			
			if (delta > marginOfError) moveRight();
			else if (delta < -marginOfError) moveLeft();
			
		} else {
			clicked = false;
		}
		
		if (Mouse.isKeyPressed(Keys.GLFW_MOUSE_BUTTON_1)) {
			if (Mouse.getPos().intersects(sliderPos, sliderSize)) 
				focus = true;
			else focus = false;
		}
		
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
		RenderDebug.box(slider.getPos(), slider.getSize(), slider.getLayer());
		RenderDebug.box(pos, size, layer);
	}
	
	private void moveRight() {
		if (value >= maxValue) return;
		
		float x = (pos.x() + size.x()/  (maxValue / value + 0.001f) - sliderSize.x() / 2) / maxValue;
		x+=1;
		value += x / size.x() * maxValue;
		if (value > maxValue) value = maxValue;
		
		slider.move(new Vec2(x, 0));
		
		handler.slidding();
	}
	
	private void moveLeft() {
		if (value <= 0) return;
		
		float x = (pos.x() + size.x()/  (maxValue / value + 0.001f) - sliderSize.x() / 2) / maxValue;
		x *= -1;
		x-=1;
		value-= x * -1 / size.x() * maxValue;
		
		if (value < 0) value = 0;
		
		slider.move(new Vec2(x, 0));
		
		handler.slidding();
	}
	
	public void destroy() {
		super.destroy();
		slider.destroy();
	}
	
	public Vec2 getSliderSize() {
		return sliderSize;
	}

	public void setSliderSize(Vec2 sliderSize) {
		this.sliderSize = sliderSize;
	}

	public Texture getSliderTex() {
		return sliderTex;
	}

	public void setSliderTex(Texture sliderTex) {
		this.sliderTex = sliderTex;
	}

	public float getValue() {
		return value;
	}
	
	public float getMarginOfError() {
		return marginOfError;
	}

	public void setMarginOfError(float marginOfError) {
		this.marginOfError = marginOfError;
	}

	private class Slider extends Entity {
		
		public Slider(Vec2 pos, Vec2 size, Texture tex) {
			super(pos, size, tex);
		}
	}
}