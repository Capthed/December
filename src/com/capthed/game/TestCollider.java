package com.capthed.game;

import com.capthed.abyss.Timer;
import com.capthed.abyss.component.Entity;
import com.capthed.abyss.component.GameObject;
import com.capthed.abyss.gfx.RenderDebug;
import com.capthed.abyss.gfx.RenderUtil;
import com.capthed.abyss.gfx.Texture;
import com.capthed.abyss.input.Keyboard;
import com.capthed.abyss.input.Keys;
import com.capthed.abyss.input.Mouse;
import com.capthed.abyss.math.Vec2;
import com.capthed.util.Debug;

public class TestCollider extends Entity {

	public TestCollider(Vec2 pos, Vec2 size, Texture tex) {
		super(pos, size, tex);
	}
	
	public void init () {
		layer = 5;
	}
	
	public void update() {
		float speed = (float) (64 * Timer.getDelta());
		if (Keyboard.isKeyDown(Keys.GLFW_KEY_LEFT_SHIFT))
			speed = (float) (400 * Timer.getDelta());
		if (Keyboard.isKeyDown(Keys.GLFW_KEY_D))
			tryMove (new Vec2(speed, 0));
		if (Keyboard.isKeyDown(Keys.GLFW_KEY_A))
			tryMove (new Vec2(-speed, 0));
		if (Keyboard.isKeyDown(Keys.GLFW_KEY_W))
			tryMove (new Vec2(0, speed));
		if (Keyboard.isKeyDown(Keys.GLFW_KEY_S))
			tryMove (new Vec2(0, -speed));
		
		if (Mouse.isKeyPressed(Keys.GLFW_MOUSE_BUTTON_1)) {
			TestCollider2 t = TestRun.other;
			
			Vec2 delta = Vec2.sub(t.getPos(), Mouse.getPos());
			delta.mult(new Vec2(-1, -1));
			t.move(delta);
		}
	}
	
	public void collided(GameObject go) {
		Debug.print(go, "");
	}
	
	public void render() {
		RenderUtil.setColor(1, 1, 1, 0.1f);
		super.render();
		RenderUtil.setColor(1, 1, 1, 1);
		
		RenderDebug.collider(collider);
	}
}
