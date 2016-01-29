package com.capthed.game;

import com.capthed.abyss.Abyss;
import com.capthed.abyss.Timer;
import com.capthed.abyss.component.GameComponent;
import com.capthed.abyss.input.Keyboard;
import com.capthed.abyss.input.Keys;
import com.capthed.abyss.map.Map;
import com.capthed.abyss.map.MapManager;

public class Test2 extends GameComponent{

	private boolean des = false;
	
	public Test2() {
		super();
	}
	
	public void update() {
		check();
	}
	
	private void check() {
		if (Keyboard.isKeyPressed(Keys.GLFW_KEY_ESCAPE)) {
			Abyss.stop();
		}
		
		if (Keyboard.isKeyPressed(Keys.GLFW_KEY_G)) {
			if (!TestRun.slider.isNull())
			TestRun.slider.destroy();
		}
		
		if (Timer.getTimeRunning() >= 5 && des) {
			des = false;
			
			Map lvl2 = new Map("Level 2", Map.TILE_SIZE.T_64);
			
			MapManager.setCurrent(lvl2);
			lvl2.load("res/lvl2.png");
		}
	}
}
