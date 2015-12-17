package com.capthed.abyss.component;

import java.util.ArrayList;

import com.capthed.util.Debug;

public abstract class GameComponent {

	private static int currId = 0;
	
	private static ArrayList<GameComponent> gcs = new ArrayList<GameComponent>();
	
	protected int id;
	private boolean initialized = false;
	
	protected boolean enabled = true;
	
	public GameComponent() {
		id = currId++;
		
		gcs.add(this);
	}
	
	/** Called once when the component is initialized. */
	public void init() {}
	
	public void update() {}
	
	@Deprecated
	/** Only here for the loop logic. Render is actually a method of GameObject. */
	public void render() {}
	
	public void setInit(boolean b) { initialized = b; }
	
	public boolean isInit() { return initialized; }
	
	/** The identificator of every component. */
	public int getID() { return id; }
	
	public static int getCurrID() { return currId; }
	
	/** All logic will be called only if this is true. */
	public boolean isEnabled() {
		return enabled;
	}

	public void setEnabled(boolean enabled) {
		this.enabled = enabled;
	}
	
	/** @return True if it is an instance of NullComponent. */
	public boolean isNull() { return false; }

	/** Calls GameComponent.destroy(int) with thw current id. */
	public void destroy() {
		GameComponent.destroy(id);
	}
	
	/** Destroys the component with the set id and replaces it with a NullComponent. */
	public static void destroy(int exid) {
		NullComponent nc = new NullComponent();
		nc.setID(exid);
		
		nc.setEnabled(false);
		
		gcs.set(exid, nc);
		
		Debug.print(getByID(exid), " destroyed");
	}
	
	public static ArrayList<GameComponent> getGcs() {
		return gcs;
	}
	
	public static GameComponent getByID(int var0) {
		return gcs.get(var0);
	}

	public String toString() { return "GameComponent " + id; }
}