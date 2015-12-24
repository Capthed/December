package com.capthed.abyss.physics;

import com.capthed.abyss.math.Vec2;

public interface Collider {

	public float getLayer();
	
	public void setLayer(float l);
	
	public boolean intersects(Collider c);
	
	public void move(Vec2 delta);
}
