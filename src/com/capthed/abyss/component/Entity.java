package com.capthed.abyss.component;

import com.capthed.abyss.math.Vec2;

public abstract class Entity extends GameObject {

	public Entity(Vec2 pos, Vec2 size) {
		super(pos, size);
	}
	
	public void move(Vec2 delta) {
		float x = this.pos.getX();
		float y = this.pos.getY();
		
		pos.setX(x + delta.getX());
		pos.setY(y + delta.getY());
	}
}