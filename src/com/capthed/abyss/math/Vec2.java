package com.capthed.abyss.math;



public class Vec2 {

	public static final Vec2 ZERO = new Vec2(0, 0);
	public static final Vec2 REV = new Vec2(-1, -1);
	
	private float x, y;
	
	public Vec2(Vec2 v) {
		this.x = v.x();
		this.y = v.y();
	}
	
	public Vec2(float x, float y) {
		this.x = x;
		this.y = y;
	}

	public float x() {
		return x;
	}

	public void setX(float x) {
		this.x = x;
	}

	public float y() {
		return y;
	}

	public void setY(float y) {
		this.y = y;
	}
	
	public Vec2 add(Vec2 v) {
		this.x += v.x();
		this.y += v.y();
		
		return this;
	}
	
	public static Vec2 add(Vec2 v1, Vec2 v2) {
		return new Vec2(v1.x() + v2.x(), v1.y() + v2.y());
	}
	
	public Vec2 sub(Vec2 v) {
		this.x -= v.x();
		this.y -= v.y();
		
		return this;
	}
	
	public static Vec2 sub(Vec2 v1, Vec2 v2) {
		return new Vec2(v1.x() - v2.x(), v1.y() - v2.y());
	}
	
	public Vec2 mult(Vec2 v) {
		this.x *= v.x();
		this.y *= v.y();
		
		return this;
	}
	
	public static Vec2 mult(Vec2 v1, Vec2 v2) {
		return new Vec2(v1.x() * v2.x(), v1.y() * v2.y());
	}
	
	public Vec2 div(Vec2 v) {
		this.x /= v.x();
		this.y /= v.y();
		
		return this;
	}
	
	public static Vec2 div(Vec2 v1, Vec2 v2) {
		return new Vec2(v1.x() / v2.x(), v1.y() / v2.y());
	}
	
	public float dot(Vec2 v) {
		return (x * v.x()) + (y * v.y()); 
	}
	
	public static float dot (Vec2 v1, Vec2 v2) {
		return (v1.x() * v2.x()) + (v1.y() * v2.y());
	}
	
	public float sqrLength() {
		return (x*x) + (y*y);
	}
	
	public float length() {
		return (float)Math.sqrt(sqrLength());
	}
	
	public Vec2 normalize() {
		float l = length();
		
		this.x /= l;
		this.y /= l;
		
		return this;
	}
	
	public static Vec2 normalize(Vec2 v) {
		return new Vec2(v.x() / v.length(), v.y() / v.length());
	}
	
	public Vec2 abs() {
		x = Math.abs(x);
		y = Math.abs(y);
		
		return this;
	}
	
	public static Vec2 abs(Vec2 v) {
		return new Vec2(Math.abs(v.x()), Math.abs(v.y()));
	}
	
	public boolean equals(Vec2 v) {
		if (this.x == v.x() && this.y == v.y())
			return true;
		
		return false;
	}
	
	public boolean intersects(Vec2 pos, Vec2 size) {
		return Vec2.intersects(this, pos, size);
	}
	
	public static boolean intersects(Vec2 point, Vec2 pos, Vec2 size) {
		Vec2 add = Vec2.add(pos, size);
		if (point.x >= pos.x() && point.x <= add.x() && point.y >= pos.y() && point.y <= add.y())
			return true;
		else 
			return false;
	}
	
	public String toString() { return "x: " + x + ", y: " + y; }
}