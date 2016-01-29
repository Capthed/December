package com.capthed.abyss.component.gui;

import com.capthed.abyss.component.Entity;
import com.capthed.abyss.component.GameObject;
import com.capthed.abyss.gfx.Texture;
import com.capthed.abyss.input.Keyboard;
import com.capthed.abyss.input.Keys;
import com.capthed.abyss.map.MapManager;
import com.capthed.abyss.math.Vec2;

public class GUISlider extends GameObject {

	private Vec2 sliderPos;
	private Vec2 sliderSize;
	private Entity slider;
	private Texture sliderTex;
	private float maxValue;
	private float value;
	
	public GUISlider(Vec2 pos, Vec2 size, Vec2 sliderSize, Texture tex, Texture sliderTex, float startValue, float maxValue) {
		super(pos, size, tex);
		
		this.value = startValue;
		this.sliderSize = sliderSize;
		this.maxValue = maxValue;
		this.sliderTex = sliderTex;
		
		this.sliderPos = new Vec2(pos.x() + size.x()/  (maxValue / value + 0.001f) - sliderSize.x() / 2, pos.y() + size.y()/  2 - sliderSize.y() / 2);
	}
	
	public void init() {
		slider = (Entity) new Slider(sliderPos, sliderSize, sliderTex).setLayer(layer + 1);
		MapManager.getCurrent().add(slider);
	}
	
	public void update() {
		/*if (Mouse.getPos().intersects(slider.getPos(), slider.getSize())) {*/
			if (Keyboard.isKeyDown(Keys.GLFW_KEY_D) && value <= maxValue) {
				float x = (pos.x() + size.x()/  (maxValue / value + 0.001f) - sliderSize.x() / 2) / maxValue;
				x+=1;
				value += x / size.x() * maxValue;
				
				slider.move(new Vec2(x, 0));
			}
			else if (Keyboard.isKeyDown(Keys.GLFW_KEY_A) && value >= 0) {
				float x = (pos.x() + size.x()/  (maxValue / value + 0.001f) - sliderSize.x() / 2) / maxValue;
				x *= -1;
				x-=1;
				value-= x * -1 / size.x() * maxValue;
				
				slider.move(new Vec2(x, 0));
			}
		
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
	
	private class Slider extends Entity {
		
		public Slider(Vec2 pos, Vec2 size, Texture tex) {
			super(pos, size, tex);
		}
	}
}