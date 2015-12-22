package com.capthed.abyss.map;

import java.util.ArrayList;

import com.capthed.abyss.component.GameComponent;
import com.capthed.abyss.component.GameObject;

public class Scene {

	private ArrayList<Integer> gcs = new ArrayList<Integer>();
	private String name;
	private boolean active = false;
	
	public Scene(String name) {
		this.name = name;
	}
	
	public void add(ArrayList<GameComponent> gc) {
		for (GameComponent var0 : gc)
			gcs.add(var0.getID());
	}
	
	public void add(GameComponent gc) {
		add(gc.getID());
	}
	
	public void add(int id) {
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
			GameComponent.getByID(gcs.get(i)).setEnabled(b);
		}
	}
	
	public void destroy() {
		for (int i : gcs) {
			GameComponent.getByID(i).destroy();
		}
	}
	
	public void init() {
		for (int i = 0; i < gcs.size(); i++) {
			GameComponent gc = GameComponent.getByID(gcs.get(i));
			
			if (!gc.isInit() && gc.isEnabled() && !gc.isNull()) {
				gc.init();
				gc.setInit(true);
			}
		}
	}
	
	public void update() {
		for (int i = 0; i <gcs.size(); i++) {
			GameComponent gc = GameComponent.getByID(gcs.get(i));
			
			if (gc.isEnabled() && !gc.isNull())
				gc.update();
		}
	}
	
	@SuppressWarnings("deprecation")
	public void render() {
		for (int i = 0; i < gcs.size(); i++)  {
			GameComponent gc = GameComponent.getByID(gcs.get(i));
			if (gc instanceof GameObject && gc.isEnabled())
				gc.render();
		}
	}
	
	Scene setActive(boolean b) { 
		this.active = b; 
		return this;
	}
	
	/** @return True if the scene belongs to the currently active map. */
	public boolean isActive() { return active; }
	
	public int getSize() { return gcs.size(); }
	
	public String toString () { return "Scene: " + name; }
}