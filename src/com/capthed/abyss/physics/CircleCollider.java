package com.capthed.abyss.physics;

import com.capthed.abyss.component.GameObject;
import com.capthed.abyss.math.Vec2;

public class CircleCollider implements Collider {

	private Vec2 pos;
	private float r;
	private float layer;
	
	public CircleCollider(Vec2 pos, float r) {
		this.pos = pos;
		this.r = r;
	}

	public Vec2 getPos() {
		return pos;
	}

	public void setPos(Vec2 pos) {
		this.pos = pos;
	}

	public float getR() {
		return r;
	}

	public void setR(float r) {
		this.r = r;
	}

	@Override
	public boolean intersects(Collider c) {
		boolean colliding = false;
		
		if (c.getLayer() != layer) return false;
		
		if (c instanceof CircleCollider) {
			CircleCollider cc = (CircleCollider)c;
			
			if (Vec2.sub(getPos(), cc.getPos()).length() <= r + cc.getR())
				colliding = true;
		} 
		else if (c instanceof QuadCollider) {
			colliding = c.intersects(this);
		}
		
		return colliding;
	}

	@Override
	public float getLayer() {
		return layer;
	}

	@Override
	public void setLayer(float l) {
		this.layer = l;
	}

	@Override
	public void move(Vec2 delta) {
		this.pos.add(delta);
	}
	
	public static Vec2 calcCenter(GameObject go) {
		Vec2 v1 = go.getPos();
		Vec2 v2 = go.getSize();
		
		return new Vec2(v1.x() + v2.x() / 2, v1.y() + v2.y() / 2);
	}
}
