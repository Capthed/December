package com.capthed.abyss.component;

import com.capthed.abyss.gfx.Texture;
import com.capthed.abyss.math.Vec2;

public class TestEnt extends Entity{

	public TestEnt(Vec2 pos, Vec2 size, Texture tex) {
		super(pos, size, tex);
	}

	public void update() {
		genericPlayerUpdate(new Vec2(2, 2));
	}
}