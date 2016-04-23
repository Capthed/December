package com.capthed.abyss.component;

import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectOutputStream;
import java.util.HashMap;

import com.capthed.abyss.gfx.Animation;
import com.capthed.abyss.gfx.RenderUtil;
import com.capthed.abyss.gfx.Texture;
import com.capthed.abyss.map.MapManager;
import com.capthed.abyss.math.Vec2;

public abstract class Tile extends GameObject {
	
	private static HashMap<Integer, Tile> tiles = new HashMap<Integer, Tile>();
	private static String savePath = "tile_info/";
	
	protected Vec2 mapPos;
	protected int color;
	protected String name;
	private boolean aort; // animation = 1, texture = 0
	
	/** The prototype constructor. Called only once in the code. */
	public Tile(String name, int color, Texture tex) {
		super();
		this.tex = tex;
		this.name = name;
		this.color = color;
		this.aort = false;
		
		layer = -RenderUtil.layerLimit() + 5;
		saveData();
		
		tiles.put(color, this);
	}
	
	/** The prototype constructor. Called only once in the code. */
	public Tile(String name, int color, Animation anim) {
		super();
		this.animation = anim;
		this.name = name;
		this.color = color;
		this.aort = true;
		
		layer = -RenderUtil.layerLimit() + 5;
		saveData();
		
		tiles.put(color, this);
	}
	
	// saves the tile info to a file so it can be used in the editor
	private void saveData() {
		try {
			FileOutputStream fout = new FileOutputStream(savePath + name + ".dat");
			ObjectOutputStream oos = new ObjectOutputStream(fout);
			
			oos.writeUTF(name);
			oos.writeInt(color);
			oos.writeBoolean(aort);
			
			if (aort) {
				oos.writeUTF(animation.getTexs()[0].getPath());
			} else
				oos.writeUTF(tex.getPath());
			
			oos.flush();
			oos.close();
			fout.close();
			
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	public Tile(Vec2 pos, Vec2 size) {
		super(pos, size);
		
		MapManager.getCurrent().add(this);
	}
	
	public Tile(Vec2 pos, Vec2 size, Texture tex) {
		super(pos, size, tex);
		
		MapManager.getCurrent().add(this);
	}
	
	public Tile(Vec2 pos, Vec2 size, Animation anim) {
		super(pos, size, anim);
		
		MapManager.getCurrent().add(this);
	}

	/** @return The tile based position of the tile. */
	public Vec2 getMapPos() {
		return mapPos;
	}

	public void setMapPos(Vec2 mapPos) {
		this.mapPos = mapPos;
	}
	
	/** Used to create a tile then return the made tile. */
	public abstract Tile build(Vec2 pos, Vec2 size);

	public static HashMap<Integer, Tile> getTiles() {
		return tiles;
	}

	public int getColor() {
		return color;
	}

	public String getName() {
		return name;
	}

	public static String getSavePath() {
		return savePath;
	}

	/** 
	 * Set the path to which tile data will be saved. The data is used for the editor.
	 * The path should be of format "destination/to/save/" without the name of the final .dat file.
	 */
	public static void setSavePath(String savePath) {
		Tile.savePath = savePath;
	}
}