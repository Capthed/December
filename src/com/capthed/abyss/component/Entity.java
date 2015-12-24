package com.capthed.abyss.component;

import com.capthed.abyss.gfx.Texture;
import com.capthed.abyss.math.Vec2;

public abstract class Entity extends GameObject {

	public Entity(Vec2 pos, Vec2 size) {
		super(pos, size);
	}
	
	public Entity(Vec2 pos, Vec2 size, Texture tex) {
		super(pos, size, tex);
		
		this.pos = pos;
		this.size = size;
		this.tex = tex;
	}
	
	/** Moves the entity regardless of physics for delta. */
	public void move(Vec2 delta) {
		this.pos.add(delta);
		
		if (collider != null) {
			collider.move(delta);
		}
		
	}
}