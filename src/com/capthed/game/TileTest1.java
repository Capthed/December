package com.capthed.game;

import com.capthed.abyss.component.Tile;
import com.capthed.abyss.gfx.Texture;
import com.capthed.abyss.math.Vec2;

public class TileTest1 extends Tile {

	public TileTest1(Vec2 pos, Vec2 size) {
		super(pos, size);
	}
	
	public TileTest1(Vec2 pos, Vec2 size, Texture tex) {
		super(pos, size, tex);
	}

	public TileTest1(int b, Texture tex) {
		super(b, tex);
	}

	@Override
	public Tile build(Vec2 pos, Vec2 size) {
		return new TileTest1(pos, size, tex);
	}
}
