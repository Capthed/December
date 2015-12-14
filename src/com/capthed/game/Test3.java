package com.capthed.game;

import com.capthed.abyss.component.GameObject;
import com.capthed.abyss.gfx.Render;
import com.capthed.abyss.math.Vec2;

public class Test3 extends GameObject {

	/*private Random rand = new Random();
	
	private float r, g, b;*/
	
	public Test3(Vec2 pos, Vec2 size) {
		super(pos, size, TestRun.tex);
	}
	
	public void init() {
		/*r = (float)rand.nextGaussian() + 0.2f;
		g = (float)rand.nextGaussian() + 0.2f;
		b = (float)rand.nextGaussian() + 0.2f;*/
	}
	
	public void render() {
		Render.quadtTex(this);
	}
}