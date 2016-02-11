package com.capthed.abyss.physics;

import com.capthed.abyss.component.GameObject;
import com.capthed.abyss.math.Vec2;

/**
 * <b>WARNING</b><br>
 * 
 * This collider is not very stable or accurate (especially when colliding with (x, y) quads where x != y)
 * so it is better to use it as rarely as possible or in contained situations. 
 */
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
	public Collider setLayer(float l) {
		this.layer = l;
		
		return this;
	}

	@Override
	public void move(Vec2 delta) {
		this.pos.add(delta);
	}
	
	/** Calculates the center for the given GameObject's position and size. */
	public static Vec2 calcCenter(GameObject go) {
		Vec2 v1 = go.getPos();
		Vec2 v2 = go.getSize();
		
		return new Vec2(v1.x() + v2.x() / 2, v1.y() + v2.y() / 2);
	}

	@Override
	public void detroy() {
		pos = new Vec2(-1, -1);
		r = -1;
	}
}
