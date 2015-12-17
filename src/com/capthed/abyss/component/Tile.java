package com.capthed.abyss.component;

import java.util.HashMap;

import com.capthed.abyss.gfx.Texture;
import com.capthed.abyss.map.MapManager;
import com.capthed.abyss.math.Vec2;

public abstract class Tile extends GameObject {

	private static HashMap<Byte, Tile> tiles = new HashMap<Byte, Tile>();
	protected Vec2 mapPos;
	protected byte color;
	
	public Tile(byte color, Texture tex) {
		super();
		this.color = color;
		
		tiles.put(color, this);
	}
	
	public Tile(Vec2 pos, Vec2 size) {
		super(pos, size);
		
		MapManager.getCurrent().add(this);
	}
	
	public Tile(Vec2 pos, Vec2 size, Texture tex) {
		super(pos, size, tex);
		
		MapManager.getCurrent().add(this);
	}
	
	public Vec2 getMapPosition() {
		return mapPos;
	}

	public Vec2 getMapPos() {
		return mapPos;
	}

	public void setMapPos(Vec2 mapPos) {
		this.mapPos = mapPos;
	}
	
	/** Used to create a tile then return the made tile. */
	public abstract Tile build(Vec2 pos, Vec2 size);

	public static HashMap<Byte, Tile> getTiles() {
		return tiles;
	}

	public byte getColor() {
		return color;
	}
}