package com.capthed.abyss;

import java.io.File;

import com.capthed.abyss.gfx.Display;
import com.capthed.abyss.gfx.RenderUtil;
import com.capthed.util.Debug;

public abstract class Abyss {

	private static final String VERSION = "v1.0.0";

	private static Thread mainGameLoop;
	private static Game game = null;
	private static int w, h;
	
	public static boolean created = false;
	
	/** Creates the engine */
	public static void create(int w, int h, Game game) {
		Abyss.game = game;
		Abyss.w = w;
		Abyss.h = h;
		
		// for exporting purpouses
		System.setProperty("org.lwjgl.librarypath", new File("native").getAbsolutePath());
		
		mainGameLoop = new Thread(new GameLoop(w, h));

		Timer.start();
		
		created = true;
	}
	
	/** Called from the Game.initDisplay() method. */
	public static void createDisplay(String title, boolean decorated) {
		Display.create(w, h, title, decorated);
	}
	
	/** Called before createDisplay. */
	public static void setFullscreen(boolean b) {
		Display.fullscreen(b);
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
		game.closing();
		GameLoop.setAlive(false);
		Debug.print("Destroying");
		Display.destroy();
		game.closed();
	}
	
	/** Must be called before Abyss.start(). Set to -1 for unlimited fps. */
	public static void setFPS(int fps) { GameLoop.setFps(fps); }
	
	public static Game getGame() { return game; }
	
	public static String getVersion() { return VERSION; }
	
	/** @return Information about this version of the engine. */
	public static String getInfo() {
		return "Abyss " + VERSION + " designed for top-down 2D games."
				+ "\nUsing OpenGL " + RenderUtil.getGLVersionUse() 
				+ "\nCurrently supporting around 2,500 textured components on screen"
				+ "\nand 300,000 non-textured components at 60 FPS.";
	}
}