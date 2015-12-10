package com.capthed.abyss;

import com.capthed.abyss.gfx.Display;
import com.capthed.abyss.gfx.RenderDebug;
import com.capthed.abyss.gfx.RenderUtil;
import com.capthed.abyss.input.Input;
import com.capthed.abyss.input.Keyboard;
import com.capthed.abyss.input.Keys;
import com.capthed.abyss.input.Mouse;
import com.capthed.util.Debug;

public class GameLoop implements Runnable {
	
	// This cannot be changed or the game logic will break
	private static final int UPS = 60;
	
	private static boolean isAlive = false;
	private static int fps = 120;
	private static int currFps, currUps;
	private static int msRenderPerSecond = 1000 / fps;
	
	public void run() {
		Debug.print("", "Main game loop started");
		
		initSubsystems();
		
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
				Debug.print("Running for " + Timer.getTimeRunning(), " ms");		
				Debug.print("", "");
			}
		}
	}

	private static void init() {}
	
	private static void update() {
		Input.pollEvents();
		
		if (Keyboard.isKeyPressed(Keys.GLFW_KEY_SPACE) && Keyboard.isKeyPressed(Keys.GLFW_KEY_A))
			Debug.print("je", "");
		
		if (Mouse.isKeyPressed(Keys.GLFW_MOUSE_BUTTON_1))
			Debug.print(Keys.GLFW_MOUSE_BUTTON_1, " GLFW_MOUSE_BUTTON_1");
		
		Debug.print(Mouse.getX(), " x");
		
		Input.postProcess();
	}
	
	private static void render() {
		RenderUtil.clearScreen();
		
		
		RenderDebug.church();
				
		
		Display.swap();
	}
	
	private static void initSubsystems() {
		Display.create(800, 600, "Hello World!", true);
		
		RenderUtil.init2DGL(800, 600);	
		RenderUtil.setClearColor(0, 0, 0, 1);	
		
		Input.init();
	}

	public static boolean isAlive() {
		return isAlive;
	}

	public static void setAlive(boolean isAlive) {
		GameLoop.isAlive = isAlive;
	}

	public static int getFps() {
		return fps;
	}

	public static void setFps(int fps) {
		GameLoop.fps = fps;
		msRenderPerSecond = 1000 / fps;
	}

	public static int getCurrFps() {
		return currFps;
	}

	public static int getCurrUps() {
		return currUps;
	}

	public static int getUps() {
		return UPS;
	}
}
