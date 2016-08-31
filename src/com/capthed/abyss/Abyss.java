package com.capthed.abyss;

import java.io.File;

import com.capthed.abyss.component.TestEnt;
import com.capthed.abyss.gfx.Camera;
import com.capthed.abyss.gfx.Display;
import com.capthed.abyss.gfx.RenderUtil;
import com.capthed.abyss.gfx.Texture;
import com.capthed.abyss.map.Map;
import com.capthed.abyss.map.MapManager;
import com.capthed.abyss.math.Vec2;
import com.capthed.util.Debug;

public class Abyss {

	private static final String VERSION = "v1.0.3";

	private static Thread mainGameLoop;
	private static Game game = null;
	private static int w, h;
	
	public static boolean created = false;
	
	public static void main(String[] args) {
		//openTestDisplay(700, 700, "isus");
		new ExtDebugPrompt();
	}
	
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
		DebugPrompt.threadSwitch = true;
		Debug.print("Destroying");
		Display.destroy();
		game.closed();
	}
	
	/** 
	 * Inializes the camera and map. 
	 * @return The initialized Map.
	 */
	public static Map fastInit() {
		Camera cam = new Camera(new Vec2(0, 0));
		Camera.setCurrent(cam);
		
		Map m = new Map("FastInit map");
		MapManager.setCurrent(m);
		
		return m;
	}
	
	/** Opens a blank window using the engine. */
	public static void openTestDisplay(int w, int h, String title) {
		new Abyss().otd(w, h, title);
	}
	
	// helper method for openTestDisplay
	private void otd(int w, int h, String title) {
		new TestDisplay(w, h, title);
	}
	
	/** Must be called before Abyss.start(). Set to -1 for unlimited fps. */
	public static void setFPS(int fps) { GameLoop.setFps(fps); }
	
	public static Game getGame() { return game; }
	
	public static String getVersion() { return VERSION; }
	
	public static void setDimension(Vec2 dim) {
		w = (int) dim.x();
		h = (int) dim.y();
	}
	
	/** @return Information about this version of the engine. */
	public static String getInfo() {
		return "Abyss " + VERSION + " designed for top-down 2D games."
				+ "\nUsing OpenGL " + RenderUtil.getGLVersionUse() 
				+ "\nCurrently supporting around 2,500 textured components on screen"
				+ "\nand 300,000 non-textured components at 60 FPS.";
	}

	private class TestDisplay implements Game {

		private final String title;
		
		public TestDisplay(int w, int h, String title) {
			this.title = title;
			Abyss.create(w, h, this);
			Abyss.setFPS(-1);
			Abyss.start();
		}
		
		@Override
		public void init() {
			Camera cam = new Camera(new Vec2(0, 0));
			Camera.setCurrent(cam);
			
			Map m = new Map("Test map");
			MapManager.setCurrent(m);
			
			TestEnt e = new TestEnt(new Vec2(100, 100), new Vec2(32, 32), Texture.getByID(0));
			MapManager.getCurrent().add(e);
		}
	
		@Override
		public void initDisplay() {
			Abyss.setFullscreen(false);
			Abyss.createDisplay(title, true);
		}
	
		@Override
		public void constInit() {
			
		}
	
		@Override
		public void constUpdate() {
			DebugPrompt.check();
		}
	
		@Override
		public void constRender() {
			
		}
	
		@Override
		public void closing() {
			
		}
	
		@Override
		public void closed() {
			
		}
	
		@Override
		public boolean process(String input) {
			return false;
		}
	}
}