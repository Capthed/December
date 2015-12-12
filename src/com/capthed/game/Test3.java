package com.capthed.game;

import java.util.Random;

import com.capthed.abyss.component.GameObject;
import com.capthed.abyss.gfx.RenderUtil;
import com.capthed.abyss.math.Vec2;

public class Test3 extends GameObject {

	private Random rand = new Random();
	
	private float r, g, b;
	
	public Test3(Vec2 pos, Vec2 size) {
		super(pos, size);
	}
	
	public void init() {
		r = (float)rand.nextGaussian() + 0.2f;
		g = (float)rand.nextGaussian() + 0.2f;
		b = (float)rand.nextGaussian() + 0.2f;
	}
	
	public void render() {
		RenderUtil.setColor(r, g, b, 1);
		super.render();
		RenderUtil.setColor(1, 1, 1, 1);
	}
}