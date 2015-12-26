package com.capthed.game;

import com.capthed.abyss.component.Entity;
import com.capthed.abyss.component.GameObject;
import com.capthed.abyss.gfx.RenderDebug;
import com.capthed.abyss.gfx.Texture;
import com.capthed.abyss.math.Vec2;
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
	
	public void render() {
		super.render();
		
		RenderDebug.collider(collider);
	}
}
