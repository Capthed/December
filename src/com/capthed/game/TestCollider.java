package com.capthed.game;

import com.capthed.abyss.Timer;
import com.capthed.abyss.component.Entity;
import com.capthed.abyss.gfx.Animation;
import com.capthed.abyss.gfx.RenderDebug;
import com.capthed.abyss.gfx.Texture;
import com.capthed.abyss.input.Keyboard;
import com.capthed.abyss.input.Keys;
import com.capthed.abyss.input.Mouse;
import com.capthed.abyss.math.Vec2;

public class TestCollider extends Entity {

	public TestCollider(Vec2 pos, Vec2 size, Animation tex) {
		super(pos, size, tex);
	}
	
	public TestCollider(Vec2 pos, Vec2 size, Texture tex) {
		super(pos, size, tex);
	}
	
	public void update() {
		float speed = (float) (64 * Timer.getDelta());
		
		Vec2 v = new Vec2(pos);
		
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
		
		
		if (!v.equals(pos)) {
			animation = TestRun.anim;
		} else {
			animation = null;
			tex = TestRun.run1;
		}
		
		if (Mouse.isKeyPressed(Keys.GLFW_MOUSE_BUTTON_1)) {
			TestCollider2 t = TestRun.other;
			
			Vec2 delta = Vec2.sub(t.getPos(), Mouse.getPos());
			delta.mult(new Vec2(-1, -1));
			t.move(delta);
		}
	}
	
	public void render() {
		super.render();
		
		RenderDebug.collider(collider);
	}
}
