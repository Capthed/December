package com.capthed.abyss.component;

import java.util.ArrayList;

public abstract class GameComponent {

	private static int currId = 0;
	protected boolean nullified = false; // boli me kurac za glupo ime
	
	protected static ArrayList<GameComponent> gcs = new ArrayList<GameComponent>();
	
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

	static void setCurrID(int currId) {
		GameComponent.currId = currId;
	}

	public void setInit(boolean b) { initialized = b; }
	
	public boolean isInit() { return initialized; }
	
	/** The identificator of every component. */
	public int getID() { return id; }
	
	/** The id that the next created component will get. */
	public static int getCurrID() { return currId; }
	
	/** All logic will be called only if this is true. */
	public boolean isEnabled() {
		return enabled;
	}

	public void setEnabled(boolean enabled) {
		this.enabled = enabled;
	}
	
	/** @return True if it is an instance of NullComponent. */
	public boolean isNull() { return nullified; }

	/** Calls GameComponent.destroy(int) with thw current id. */
	public void destroy() {
		GameComponent.destroy(id);
	}
	
	/** Destroys the component with the set id and replaces it with a NullComponent. */
	public static void destroy(int exid) {
		NullComponent nc = new NullComponent();
		
		if (getByID(exid) instanceof GameObject) {
			GameObject go = (GameObject)getByID(exid);
			go.setEnabled(false);
		}
		else
			getByID(exid).setEnabled(false);
		
		getByID(exid).nullified = true;
		
		nc.setID(exid);
		
		nc.setEnabled(false);
		
		gcs.set(exid, nc);
	}
	
	/** @return All The GameComponents created. */
	public static ArrayList<GameComponent> getGcs() {
		return gcs;
	}
	
	/** @return The GameObject with the requested id. */
	public static GameComponent getByID(int var0) {
		return gcs.get(var0);
	}

	public String toString() { return "GC " + id; }
}