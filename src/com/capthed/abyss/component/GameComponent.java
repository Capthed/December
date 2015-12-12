package com.capthed.abyss.component;

import java.util.ArrayList;

public abstract class GameComponent {

	private static int currId = 0;
	
	public static ArrayList<GameComponent> gcs = new ArrayList<GameComponent>();
	
	private int id;
	private boolean initialized = false;
	
	public GameComponent() {
		id = currId++;
		
		gcs.add(this);
		
		// TODO: remove the ArrayList
	}
	
	public void init() {}
	
	public void update() {}
	
	@Deprecated
	public void render() {}
	
	public void setInit(boolean b) { initialized = b; }
	
	public boolean isInit() { return initialized; }
	
	public int getID() { return id; }
	
	public static int getCurrID() { return currId; }
	
	public String toString() { return "GameComponent " + id; }
}