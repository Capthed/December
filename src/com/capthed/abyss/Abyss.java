package com.capthed.abyss;

import com.capthed.util.Debug;

public abstract class Abyss {

	private static Thread mainGameLoop;
	private static Game game;
	private static int w, h;
	
	/** Creates the engine (temporary). */
	public static void create(String title, int w, int h, Game game) {
		Abyss.game = game;
		Abyss.w = w;
		Abyss.h = h;
		
		mainGameLoop = new Thread(new GameLoop(w, h, title));
		
		Debug.setDebug(true);
		Timer.start();
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
}