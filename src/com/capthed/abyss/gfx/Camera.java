package com.capthed.abyss.gfx;

import static org.lwjgl.opengl.GL11.glTranslatef;

import com.capthed.abyss.Abyss;
import com.capthed.abyss.GameLoop;
import com.capthed.abyss.math.Vec2;

public class Camera {
	
	private static Camera current;
	
	private Vec2 pos;
	
	public Camera(Vec2 pos) {
		this.pos = pos;;
		
		if (Abyss.created)
			init();
	}
	
	public Camera init() {
		glTranslatef(-pos.x(),-pos.y(), 0);
		
		return this;
	}

	public void move(Vec2 delta) {
		if (current == this)
			glTranslatef(-delta.x(), -delta.y(), 0);
		
		GameLoop.moveDebug(delta);
		
		pos.add(delta);
	}
	
	public boolean checkBoundaries(Vec2 pos, Vec2 size) {
		boolean b = true;
		
		float x = pos.x();
		float y = pos.y();
		float w = size.x();
		float h = size.y();
		
		if (x + w <= this.pos.x() || x >= this.pos.x() + Display.getWidth() || y + h <= this.pos.y() || y >= this.pos.y() + Display.getHeight())
			b = false;
		
		return b;
	}

	public Vec2 getPos() {
		return pos;
	}

	public static Camera getCurrent() {
		return current;
	}

	public static void setCurrent(Camera current) {
		Camera.current = current;
	}
}