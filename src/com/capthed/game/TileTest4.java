package com.capthed.game;

import com.capthed.abyss.component.Tile;
import com.capthed.abyss.gfx.Texture;
import com.capthed.abyss.math.Vec2;

public class TileTest4 extends Tile{

	public TileTest4(Vec2 pos, Vec2 size) {
		super(pos, size);
	}
	
	public TileTest4(Vec2 pos, Vec2 size, Texture tex) {
		super(pos, size, tex);
	}

	public TileTest4(int b, Texture tex) {
		super(b, tex);
	}

	@Override
	public Tile build(Vec2 pos, Vec2 size) {
		return (Tile) new TileTest4(pos, size, tex);
	}
}
