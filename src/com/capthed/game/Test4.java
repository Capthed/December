package com.capthed.game;

import com.capthed.abyss.Timer;
import com.capthed.abyss.component.Entity;
import com.capthed.abyss.gfx.Render;
import com.capthed.abyss.gfx.Texture;
import com.capthed.abyss.input.Keyboard;
import com.capthed.abyss.input.Keys;
import com.capthed.abyss.math.Vec2;

public class Test4 extends Entity {

	public Test4(Vec2 pos, Vec2 size, Texture tex) {
		super(pos, size, tex);
	}
	
	public void render() {
		Render.quadtTex(this);
	}
	
	public void update() {
		float speed = (float) (32 * Timer.getDelta());
		if (Keyboard.isKeyDown(Keys.GLFW_KEY_D))
			move (new Vec2(speed, 0));
		if (Keyboard.isKeyDown(Keys.GLFW_KEY_A))
			move (new Vec2(-speed, 0));
		if (Keyboard.isKeyDown(Keys.GLFW_KEY_W))
			move (new Vec2(0, speed));
		if (Keyboard.isKeyDown(Keys.GLFW_KEY_S))
			move (new Vec2(speed, -speed));
			
	}
}