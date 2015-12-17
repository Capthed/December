package com.capthed.abyss.map;

import java.util.ArrayList;

import com.capthed.abyss.component.GameComponent;

public class Map {

	private static final int TILE_SIZE_DEF = 32;
	
	private ArrayList<Scene> scenes = new ArrayList<Scene>();
	private String name;
	private int tileSize;
	private boolean current = false;
	
	public Map(String name) {
		scenes.add(new Scene(name + " map misc"));
		
		this.name = name;
		this.tileSize = TILE_SIZE_DEF;
	}
	
	public Map(String name, int tileSize) {
		scenes.add(new Scene(name + " map misc"));
		
		this.name = name;
		this.tileSize = tileSize;
	}
	
	public void add(Scene s) {
		scenes.add(s);
	}
	
	public void add(GameComponent gc) {
		scenes.get(0).add(gc);
	}
	
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