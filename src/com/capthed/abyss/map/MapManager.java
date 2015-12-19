package com.capthed.abyss.map;

public abstract class MapManager {

	private static Map current;
	
	public static void setCurrent(Map map) { 
		if (current != null)
			current.setCurrent(false);
		
		current = map; 
		
		current.setCurrent(true);
	}
	
	public static Map getCurrent() { return current; }
}
