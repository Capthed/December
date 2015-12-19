package com.capthed.abyss.map;

import java.util.ArrayList;

import com.capthed.abyss.component.GameComponent;
import com.capthed.util.Debug;

public class Scene {

	private ArrayList<Integer> gcs = new ArrayList<Integer>();
	private String name;
	private boolean active = false;
	
	public Scene(String name) {
		this.name = name;
		
		Debug.print("Initialized ", this.toString());
	}
	
	public void add(ArrayList<GameComponent> gc) {
		for (GameComponent var0 : gc)
			gcs.add(var0.getID());
	}
	
	public void add(GameComponent gc) {
		add(gc.getID());
	}
	
	public void add(int id) {
		Debug.print("added " + id, "");
		gcs.add(id);
	}
	
	public void remove(GameComponent gc) {
		remove(gc.getID());
	}
	
	public void remove(int id) {
		gcs.remove(id);
	} 
	
	public void setEnabled(boolean b) {
		for (int i = 0; i < gcs.size(); i++) {
			Debug.print("\t " + i, " is " + b);
			GameComponent.getByID(gcs.get(i)).setEnabled(b);
		}
	}
	
	public void destroy() {
		for (int i : gcs) {
			GameComponent.getByID(i).destroy();
		}
	}
	
	public Scene setActive(boolean b) { 
		this.active = b; 
		return this;
	}
	
	public boolean isActive() { return active; }
	
	public int getSize() { return gcs.size(); }
	
	public String toString () { return "Scene: " + name; }
}