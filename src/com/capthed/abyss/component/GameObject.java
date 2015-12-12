package com.capthed.abyss.component;

import com.capthed.abyss.gfx.RenderDebug;
import com.capthed.abyss.math.Vec2;

public abstract class GameObject extends GameComponent {

	protected Vec2 pos;
	protected Vec2 size;
	
	// TODO: add texture and physics
	
	public GameObject(Vec2 pos, Vec2 size) {
		super();
		
		this.pos = pos;
		this.size = size;
	}
	
	public void render() {
		//TODO: remove
		RenderDebug.quad(pos.getX(), pos.getY(), size.getX(), size.getY());
	}

	public Vec2 getSize() {
		return size;
	}

	public void setSize(Vec2 size) {
		this.size = size;
	}

	public Vec2 getPos() {
		return pos;
	}
}
