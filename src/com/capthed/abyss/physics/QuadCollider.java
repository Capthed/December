package com.capthed.abyss.physics;

import com.capthed.abyss.math.Vec2;

public class QuadCollider implements Collider {

	private float layer;
	private Vec2 pos, size;
	private Vec2 center = null;
	
	public QuadCollider(Vec2 pos, Vec2 size) {
		this.pos = pos;
		this.size = size;
		
		center = Vec2.add(pos, Vec2.div(size, new Vec2(2, 2)));
	}

	public Vec2 getPos() {
		return pos;
	}

	public void setPos(Vec2 pos) {
		this.pos = pos;
	}

	public Vec2 getSize() {
		return size;
	}

	public void setSize(Vec2 size) {
		this.size = size;
	}
	
	public Vec2 topRight() {
		return Vec2.add(pos, size);
	}
	
	public Vec2 bottomLeft() {
		return pos;
	}

	@Override
	public boolean intersects(Collider c) {
		return intersects(c, false);
	}
	
	public boolean intersects(Collider c, boolean flag) {
		boolean colliding = false;
		
		if (c.getLayer() != layer) return false;
		
		Vec2[] vertices = { pos, 
							Vec2.add(pos, new Vec2(0, size.y())), 
							Vec2.add(pos, size), 
							Vec2.add(pos, new Vec2(size.x(), 0))};
		
		if (c instanceof QuadCollider) {
			QuadCollider qc = (QuadCollider) c;
			
			if (!flag && qc.intersects(this, true)) return true;
			
			for (int i = 0; i < vertices.length; i++) {
				float x = vertices[i].x();
				float y = vertices[i].y();
				
				if (x >= qc.bottomLeft().x() && x <= qc.topRight().x() && y <= qc.topRight().y() && y >= qc.bottomLeft().y()) {
					colliding = true;
					break;
				}
			}
		}
		else if (c instanceof CircleCollider) {
			CircleCollider cc = (CircleCollider) c;
			
			Vec2 distV = Vec2.sub(center, cc.getPos());
			
			Vec2 rad = Vec2.mult(distV, Vec2.REV);
			rad.normalize();
			
			rad.mult(new Vec2(cc.getR(), cc.getR()));
			
			distV.add(rad);
			
			distV.add(center);
			
			if (distV.x() >= bottomLeft().x() && distV.x() <= topRight().x() && distV.y() >= bottomLeft().y() && distV.y() <= topRight().y()) {
				colliding = true;
			}
			else if (cc.getPos().x() >= bottomLeft().x() && cc.getPos().x() <= topRight().x() && cc.getPos().y() >= bottomLeft().y() && cc.getPos().y() <= topRight().y())
				colliding = true;
		}
		
		return colliding;
	}

	public Vec2 getCenter() {
		return center;
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
		
		center = Vec2.add(pos, Vec2.div(size, new Vec2(2, 2)));
	}

	@Override
	public void detroy() {
		pos = new Vec2(-1, -1);
		size = new Vec2(-1, -1);
	}
}