package com.capthed.abyss;

import com.capthed.abyss.gfx.Display;
import com.capthed.abyss.gfx.RenderUtil;
import com.capthed.abyss.input.Input;
import com.capthed.abyss.map.MapManager;
import com.capthed.util.Debug;

public class GameLoop implements Runnable {
	
	// This cannot be changed or the game logic will break
	private static final int UPS = 60;
	
	private static int w, h;
	private static boolean isAlive = false;
	private static int fps = 120;
	private static int currFps, currUps;
	private static int msRenderPerSecond = 1000 / fps;
	
	public GameLoop(int w, int h) {
		GameLoop.w = w;
		GameLoop.h = h;
	}
	
	/** The main game loop. */
	public void run() {
		initSubsystems();
		
		Debug.print("**********************", "");
		Debug.print("", "Main game loop started");
		Debug.print("FPS " + fps, "\t\tUPS " + UPS);
		Debug.print("**********************", "");
		
		long lastTime = Timer.getTime ();
		long startTime = Timer.getTime ();
		int msPerSecond = 1000 / UPS;
		long elapsedTime = 0;
		long elapsedRenderTime = 0;
		int ups = 0;
		int fpsCount = 0;
		long timeCount = 0;
		while(isAlive) {
			if (Display.isCloseRequested())
				Abyss.stop(); //TODO:
			
			lastTime = startTime;
			startTime = Timer.getTime ();
			elapsedTime += (startTime - lastTime);
			elapsedRenderTime += (startTime - lastTime); 

			timeCount += (startTime - lastTime);

			// Updates the set ammount of times per second (ups)
			if (elapsedTime >= msPerSecond) {
				Timer.setDelta (elapsedTime);
				elapsedTime = 0;
				ups++;

				init();
				update ();
			}
			
			// Renders the set ammount of times per second (fps)
			if (elapsedRenderTime >= msRenderPerSecond) {
				render ();
				elapsedRenderTime = 0;
				fpsCount++;
			}

			// FPS and UPS meter.
			if (timeCount >= Timer.SECOND) {
				timeCount = 0;
				currFps = fpsCount;
				currUps = ups;
				ups = 0;
				fpsCount = 0;
				Debug.print ("FPS: " + currFps, "");
				Debug.print ("UPS: " + currUps, "");
				Debug.print(Timer.getDelta(), " delta");
				Debug.print("Running for " + Timer.getTimeRunning(), " s");		
				Debug.print("", "");
			}
		}
	}

	/** Calls the init methods of GameComponents*/
	private static void init() {
		MapManager.getCurrent().init();
		
		Abyss.getGame().constInit();
	}
	
	private static void update() {
		Input.pollEvents();
		
		MapManager.getCurrent().update();
		
		Abyss.getGame().constUpdate();
		
		Input.postProcess();
	}
	
	private static void render() {
		RenderUtil.clearScreen();
		
		MapManager.getCurrent().render();
		
		Abyss.getGame().constRender();
				
		Display.swap();
	}
	
	/** Initializes all the systems in the game thread before running the loop. */
	private static void initSubsystems() {
		Abyss.getGame().initDisplay();
		
		Display.show();
		
		RenderUtil.init2DGL(w, h);	
		RenderUtil.setClearColor(0f, 0f, 0f, 1);	
		
		Abyss.getGame().init();
		
		Input.init();
	}

	/** @return True if the game loop is running. */
	public static boolean isAlive() {
		return isAlive;
	}

	public static void setAlive(boolean isAlive) {
		GameLoop.isAlive = isAlive;
	}

	/** @return The preferred FPS. */
	public static int getFps() {
		return fps;
	}

	public static void setFps(int fps) {
		GameLoop.fps = fps;
		msRenderPerSecond = 1000 / fps;
	}

	/** @return The actual FPS of the previous second */
	public static int getCurrFps() {
		return currFps;
	}

	/** @return The actual UPS of the previous second */
	public static int getCurrUps() {
		return currUps;
	}

	/**@return The fixed UPS. */
	public static int getUps() {
		return UPS;
	}

	public static void setW(int w) {
		GameLoop.w = w;
	}

	public static void setH(int h) {
		GameLoop.h = h;
	}
}