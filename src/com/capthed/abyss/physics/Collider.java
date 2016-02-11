package com.capthed.abyss.physics;

import com.capthed.abyss.math.Vec2;

public interface Collider {

	/** The collision layer. Not the same as the render layer. */
	public float getLayer();
	
	public Collider setLayer(float l);
	
	/** @return True if it is intersecting with the given collider. */
	public boolean intersects(Collider c);
	
	public void move(Vec2 delta);
	
	public void detroy();
}
