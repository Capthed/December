package com.capthed.game;

import com.capthed.abyss.component.Tile;
import com.capthed.abyss.gfx.Texture;
import com.capthed.abyss.math.Vec2;

public class TileTest3 extends Tile {

	public TileTest3(int color, Texture tex) {
		super(color, tex);
	}
	
	public TileTest3(Vec2 pos, Vec2 size, Texture tex) {
		super(pos, size, tex);
	}

	@Override
	public Tile build(Vec2 pos, Vec2 size) {
		return new TileTest3(pos, size, tex);
	}

}
