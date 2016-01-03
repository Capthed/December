package com.capthed.abyss.map;

import java.util.ArrayList;

import com.capthed.abyss.component.GameComponent;

public class Map {

	private static final int TILE_SIZE_DEF = 32;
	
	private ArrayList<Scene> scenes = new ArrayList<Scene>();
	private String name;
	private int tileSize;
	private boolean current = false;
	
	/** The possible tile sizes for a map. */
	public enum TILE_SIZE {
		T_16, T_32, T_64, T_128;
	}
	
	public Map(String name) {
		scenes.add(new Scene(name + " map misc"));
		
		this.name = name;
		this.tileSize = TILE_SIZE_DEF;
	}
	
	public Map(String name, TILE_SIZE t) {
		scenes.add(new Scene(name + " map misc"));
		
		this.name = name;

		if (t == TILE_SIZE.T_16)
			tileSize = 16;
		else if (t == TILE_SIZE.T_32)
			tileSize = 32;
		else if (t == TILE_SIZE.T_64)
			tileSize = 64;
		else if (t == TILE_SIZE.T_128)
			tileSize = 128;
	}
	
	public void init() {
		for(Scene s : scenes)
			s.init();
	}
	
	public void update() {
		for(Scene s : scenes)
			s.update();
	}
	
	public void render() {
		for(Scene s : scenes)
			s.render();
	}
	
	public void add(Scene s) {
		scenes.add(s);
		s.setActive(true);
	}
	
	public void add(GameComponent gc) {
		scenes.get(0).add(gc);
	}
	
	/** Loads the map from a file. Shoul be called after it has been set to the current map in the MapManager. */
	public void load(String path) {
		MapLoader.loadMap(this, path);
	}

	public ArrayList<Scene> getScenes() {
		return scenes;
	}

	public String getName() {
		return name;
	}

	public int getTileSize() {
		return tileSize;
	}
	
	public boolean isCurrent() {
		return current;
	}

	void setCurrent(boolean current) {
		this.current = current;
		
		for (int i = 0; i < scenes.size(); i++) 
			scenes.get(i).setActive(current);
	}

	public String toString() { return "Map " + name; }
}