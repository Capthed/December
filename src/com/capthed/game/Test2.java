package com.capthed.game;

import com.capthed.abyss.Abyss;
import com.capthed.abyss.Timer;
import com.capthed.abyss.component.GameComponent;
import com.capthed.abyss.input.Keyboard;
import com.capthed.abyss.input.Keys;
import com.capthed.abyss.input.Mouse;
import com.capthed.abyss.math.Vec2;

public class Test2 extends GameComponent{

	private boolean des = true;
	
	public Test2() {
		super();
	}
	
	public void update() {
		if (Mouse.isKeyPressed(Keys.GLFW_MOUSE_BUTTON_1) || Mouse.isKeyDown(Keys.GLFW_MOUSE_BUTTON_2)) {
			float x = Mouse.getX();
			float y = Mouse.getY();
			
			new Test3(new Vec2(x, y), new Vec2(8, 8)).setLayer(10);
		}
		
		if (Keyboard.isKeyPressed(Keys.GLFW_KEY_ESCAPE)) {
			Abyss.stop();
		}
		
		if (Timer.getTimeRunning() >= 3 && des) {
			des = false;
			destroy(2);
		}
	}
}
