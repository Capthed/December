package com.capthed.game;

import com.capthed.abyss.Timer;
import com.capthed.abyss.component.Entity;
import com.capthed.abyss.component.GameObject;
import com.capthed.abyss.gfx.RenderDebug;
import com.capthed.abyss.gfx.Texture;
import com.capthed.abyss.math.Vec2;
import com.capthed.abyss.physics.CircleCollider;
import com.capthed.util.Debug;

public class TestCollider2 extends Entity {

	public TestCollider2(Vec2 pos, Vec2 size) {
		super(pos, size);
	}
	
	public TestCollider2(Vec2 pos, Vec2 size, Texture tex) {
		super(pos, size, tex);
	}
	
	public void collided(GameObject go) {
		Debug.print(go, "");
	}
	
	public void update() {
		if (!(collider instanceof CircleCollider)) return;
		
		double A = Timer.getDelta() * 300;
		double A1 = Timer.getDelta() * 300;
		double val = Timer.getTimeRunning();
		
		double x = A * Math.sin(val * Math.PI);
		double y = A1 * Math.cos(val * Math.PI);
		
		tryMove(new Vec2((float)x,(float) y));
	}
	
	public void render() {
		super.render();
		
		RenderDebug.collider(collider);
	}
}
