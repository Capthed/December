package com.capthed.abyss;

import com.capthed.abyss.gfx.Display;
import com.capthed.util.Debug;

public abstract class Abyss {

	private static final String VERSION = "v1.0d";
	
	private static Thread mainGameLoop;
	private static Game game;
	private static int w, h;
	
	/** Creates the engine (temporary). */
	public static void create(int w, int h, Game game) {
		Abyss.game = game;
		Abyss.w = w;
		Abyss.h = h;
		
		mainGameLoop = new Thread(new GameLoop(w, h));
		
		Debug.setDebug(true);
		Timer.start();
	}
	
	/** Called from the Game.initDisplay() method. */
	public static void createDisplay(String title, boolean decorated) {
		Display.create(w, h, title, decorated);
	}
	
	/** Called before createDisplay. */
	public static void setFullscreen(boolean b) {
		Display.fulscreen(b);
	}
	
	/** Starts the game thread */
	public static void start() {
		if (game == null) {
			Debug.err("Game not created. Call Abyss.create(Game)");
		}
		
		GameLoop.setAlive(true);
		mainGameLoop.start();
	}
	
	/** Stops the game thread */
	public static void stop() {
		GameLoop.setAlive(false);
	}
	
	public static Game getGame() { return game; }
	
	public static String getVersion() { return VERSION; }
}