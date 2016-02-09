package com.capthed.abyss;

import java.util.HashMap;

import com.capthed.abyss.component.GameComponent;
import com.capthed.abyss.component.NullComponent;
import com.capthed.abyss.font.CharElement;
import com.capthed.abyss.font.Font;
import com.capthed.abyss.font.Text;
import com.capthed.abyss.gfx.Camera;
import com.capthed.abyss.gfx.Display;
import com.capthed.abyss.gfx.RenderUtil;
import com.capthed.abyss.gfx.Texture;
import com.capthed.abyss.input.Input;
import com.capthed.abyss.map.MapManager;
import com.capthed.abyss.math.Vec2;
import com.capthed.abyss.util.DebugPrompt;
import com.capthed.util.Debug;

public class GameLoop implements Runnable {
	
	// This cannot be changed or the game logic will break
	private static final int UPS = 60;
	
	private static int w, h;
	private static int texNum = 0;
	private static int colNum = 0;
	private static boolean isAlive = false;
	private static int fps = 120;
	private static int currFps, currUps;
	private static long msRenderPerSecond = 1000 / fps;
	
	private static boolean debugRender;
	private static Text fpsTxt;
	private static Text upsTxt;
	private static Text nullTxt;
	private static Text deltaTxt;
	private static Text runningTxt;
	private static Text compTxt;
	private static Text texTxt;
	private static Text collTxt;
	private static Font debugFont;
	private static HashMap<Character, CharElement> lex;
	
	public GameLoop(int w, int h) {
		GameLoop.w = w;
		GameLoop.h = h;
	}
	
	/** The main game loop. */
	public void run() {
		
		initSubsystems();
		
		debugRender = Debug.isDebug();
		
		Debug.print("", "");
		Debug.print("Using Abyss ", Abyss.getVersion() + "");
		Debug.print(Abyss.getInfo(), "");
		Debug.print("**********************", "");
		Debug.print("", "Main game loop started");
		Debug.print("FPS " + fps, "\t\tUPS " + UPS);
		Debug.print("**********************", "");
		
		long lastTime = Timer.getTime ();
		long startTime = Timer.getTime ();
		long msPerSecond = (1000 / UPS);
		long elapsedTime = 0;
		long elapsedRenderTime = 0;
		int ups = 0;
		int fpsCount = 0;
		long timeCount = 0;
		while(isAlive) {
			if (Display.isCloseRequested())
				Abyss.stop();
			
			if (fps == -1) msRenderPerSecond = 0;
			
			if (Camera.getCurrent() == null) {
				Debug.err("Camera not initialized");
			}
			
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
				fpsTxt.setText("FPS: " + currFps);
				Debug.print ("UPS: " + currUps, "");
				upsTxt.setText("UPS: " + currUps);
				Debug.print(Timer.getDelta(), " delta");
				deltaTxt.setText(Timer.getDelta() + " delta");
				Debug.print("Running for " + Timer.getTimeRunning(), " s");		
				runningTxt.setText("Running for " + Timer.getTimeRunning() + " s");
				Debug.print("GameComponents created: ", GameComponent.getGcs().size() + 1 + "");
				compTxt.setText("GameComponents created: " + GameComponent.getGcs().size() + 1);
				Debug.print("NullComponents: ", NullComponent.numNulls+ "");
				nullTxt.setText("NullComponents: " + NullComponent.numNulls);
				Debug.print("Textures active: ", texNum + "");
				texTxt.setText("Textures active: " + texNum);
				Debug.print("Colliders active: ", colNum + "");
				collTxt.setText("Colliders active: " + colNum );
				Debug.print("", "");
			}
		}
	}
	
	private static void initDebug() {
		Texture t = new Texture("res/debugFont_alpha.png");
		t.loadTex();
		
		debugFont = new Font(t, .0625f, .125f, 0, 0);
		lex = debugFont.loadLex(new String[] {"", "", "!\"#$%&'()*+,-./0", "123456789:;<=>?@", "ABCDEFGHIJKLMNOP", 
				"QRSTUVWXYZ[\\]{_", "{abcdefghijklmno", "pqrstuvwxyz"});
		
		Vec2 pos = new Vec2(30, 700);
		Vec2 size = new Vec2(16, 16);
		
		fpsTxt = new Text(Vec2.sub(pos, new Vec2(0, 30)), size, "Loading...", lex);
		upsTxt = new Text(Vec2.sub(pos, new Vec2(0, 60)), size, "Loading...", lex);
		deltaTxt = new Text(Vec2.sub(pos, new Vec2(0, 90)), size, "Loading...", lex);
		runningTxt = new Text(Vec2.sub(pos, new Vec2(0, 120)), size, "Loading...", lex);
		compTxt = new Text(Vec2.sub(pos, new Vec2(0, 150)), size, "Loading...", lex);
		nullTxt =  new Text(Vec2.sub(pos, new Vec2(0, 180)), size, "Loading...", lex);
		texTxt = new Text(Vec2.sub(pos, new Vec2(0, 210)), size, "Loading...", lex);
		collTxt = new Text(Vec2.sub(pos, new Vec2(0, 240)), size, "Loading...", lex);
		
		DebugPrompt.get().initPrompt();
	}
	
	public static void moveDebug(Vec2 delta) {
		fpsTxt.setPos(Vec2.add(fpsTxt.getPos(), delta));
		upsTxt.setPos(Vec2.add(upsTxt.getPos(), delta));
		deltaTxt.setPos(Vec2.add(deltaTxt.getPos(), delta));
		runningTxt.setPos(Vec2.add(runningTxt.getPos(), delta));
		compTxt.setPos(Vec2.add(compTxt.getPos(), delta));
		nullTxt.setPos(Vec2.add(nullTxt.getPos(), delta));
		texTxt.setPos(Vec2.add(texTxt.getPos(), delta));
		collTxt.setPos(Vec2.add(collTxt.getPos(), delta));
		
		DebugPrompt.get().move(delta);
	}
	
	private static void debugRender() {
		if (!debugRender) return;
		
		RenderUtil.setColor(0, 1, 0, 1);
		
		fpsTxt.render();
		upsTxt.render();
		deltaTxt.render();
		runningTxt.render();
		compTxt.render();
		nullTxt.render();
		texTxt.render();
		collTxt.render();
		
		RenderUtil.setColor(1, 1, 1, 1);
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
		texNum = 0;
		
		RenderUtil.clearScreen();
		
		MapManager.getCurrent().render();
		
		debugRender();
		
		Abyss.getGame().constRender();
				
		Display.swap();
	}
	
	/** Initializes all the systems in the game thread before running the loop. */
	private static void initSubsystems() {
		Abyss.getGame().initDisplay();
		
		RenderUtil.init2DGL(w, h);	
		RenderUtil.setClearColor(0f, 0f, 0f, 1);	
		
		Display.show();
		
		initDebug();
		
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
	
	// true to add, false to subtract
	public static void addCollider(boolean b) { 
		if (b) colNum++;
		else colNum--;
	}
	
	public static void addTex() { texNum++; }
	
	public static int getRenderedTextures() { return texNum; }

	public static boolean isDebugRender() {
		return debugRender;
	}

	public static void setDebugRender(boolean debugRender) {
		GameLoop.debugRender = debugRender;
	}

	public static Font getDebugFont() {
		return debugFont;
	}
}