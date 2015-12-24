package com.capthed.game;

import com.capthed.abyss.component.Entity;
import com.capthed.abyss.gfx.RenderDebug;
import com.capthed.abyss.gfx.Texture;
import com.capthed.abyss.math.Vec2;

public class TestCollider2 extends Entity {

	public TestCollider2(Vec2 pos, Vec2 size, Texture tex) {
		super(pos, size, tex);
	}
	
	public void render() {
		super.render();
		
		RenderDebug.collider(collider);
	}
}
