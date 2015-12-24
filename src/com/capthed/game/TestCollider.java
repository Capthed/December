package com.capthed.game;

import com.capthed.abyss.Timer;
import com.capthed.abyss.component.Entity;
import com.capthed.abyss.gfx.RenderDebug;
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
	
	public void update() {
		float speed = (float) (64 * Timer.getDelta());
		if (Keyboard.isKeyDown(Keys.GLFW_KEY_LEFT_SHIFT))
			speed = (float) (128 * Timer.getDelta());
		if (Keyboard.isKeyDown(Keys.GLFW_KEY_D))
			move (new Vec2(speed, 0));
		if (Keyboard.isKeyDown(Keys.GLFW_KEY_A))
			move (new Vec2(-speed, 0));
		if (Keyboard.isKeyDown(Keys.GLFW_KEY_W))
			move (new Vec2(0, speed));
		if (Keyboard.isKeyDown(Keys.GLFW_KEY_S))
			move (new Vec2(speed, -speed));
		
		if (Mouse.isKeyPressed(Keys.GLFW_MOUSE_BUTTON_1)) {
			TestCollider2 t = TestRun.other;
			
			Vec2 delta = Vec2.sub(t.getPos(), Mouse.getPos());
			delta.mult(new Vec2(-1, -1));
			t.move(delta);
		}
	}
	
	public void render() {
		super.render();

		if (collider.intersects(TestRun.other.getCollider()))
			Debug.print("Colliding", "");
		
		RenderDebug.collider(collider);
	}
}
