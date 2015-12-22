package com.capthed.abyss.map;

public abstract class MapManager {

	private static Map current;
	
	/** Set the current map. Only the scenes from the current map will be called in the game loop. */
	public static void setCurrent(Map map) { 
		if (current != null) {
			current.setCurrent(false);
			
			for (int i = 0; i < current.getScenes().size(); i++)
				current.getScenes().get(i).destroy();
		}
		
		current = map; 
		
		current.setCurrent(true);
	}
	
	public static Map getCurrent() { return current; }
}