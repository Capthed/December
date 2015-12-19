package com.capthed.game;

import com.capthed.abyss.component.Tile;
import com.capthed.abyss.gfx.Texture;
import com.capthed.abyss.math.Vec2;

public class TileTest2 extends Tile {

	public TileTest2(Vec2 pos, Vec2 size) {
		super(pos, size);
	}
	
	public TileTest2(Vec2 pos, Vec2 size, Texture tex) {
		super(pos, size, tex);
	}
	
	public TileTest2(int color, Texture tex) {
		super(color, tex);
	}

	@Override
	public Tile build(Vec2 pos, Vec2 size) {
		return new TileTest2(pos, size, tex);
	}

}
