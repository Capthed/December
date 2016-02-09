package com.capthed.abyss.util;

import javax.swing.JOptionPane;

import com.capthed.abyss.Abyss;
import com.capthed.abyss.GameLoop;
import com.capthed.abyss.component.GameComponent;
import com.capthed.abyss.component.GameObject;
import com.capthed.abyss.input.Keyboard;
import com.capthed.abyss.input.Keys;
import com.capthed.abyss.input.Mouse;
import com.capthed.util.Debug;

public abstract class Util {

	private static boolean var0 = false;
	
	public static void showMsg(String txt) {
		JOptionPane.showMessageDialog(null, txt);
	}
	
	public static void check() {
		if (Keyboard.isKeyPressed(Keys.GLFW_KEY_ESCAPE)) {
			Abyss.stop();
		}
		
		if (Keyboard.isKeyPressed(Keys.GLFW_KEY_F2)) {
			if (!var0) {
				DebugPrompt.get().openPrompt();
				var0 = true;
			} else
				DebugPrompt.get().openAgain();
		}
		
		if (Keyboard.isKeyPressed(Keys.GLFW_KEY_F1)) {
			GameLoop.setDebugRender(!GameLoop.isDebugRender());
		}
		
		if (Mouse.isKeyPressed(Keys.GLFW_MOUSE_BUTTON_2)) {
			for (int i = 0; i < GameComponent.getGcs().size(); i++) {
				if (GameComponent.getGcs().get(i).isEnabled() && GameComponent.getGcs().get(i) instanceof GameObject) {
					GameObject go = (GameObject)GameComponent.getGcs().get(i);
					
					if (Mouse.getPos().intersects(go.getPos(), go.getSize())) {
						go.destroy();
						break;
					}
				}
			}
		}
		
		if (Mouse.isKeyPressed(Keys.GLFW_MOUSE_BUTTON_3)) {
			for (int i = 0; i < GameComponent.getGcs().size(); i++) {
				if (GameComponent.getGcs().get(i).isEnabled() && GameComponent.getGcs().get(i) instanceof GameObject) {
					GameObject go = (GameObject)GameComponent.getGcs().get(i);
					
					if (Mouse.getPos().intersects(go.getPos(), go.getSize())) {
						Util.showMsg( "ID: " + go.getID());
						break;
					}
				}
			}
		}
	}
	
	public static void info() {
		showMsg("Esc - Close the game\n"
				   + "F1 - Render debug info\n" 
				   + "F2 - Debug prompt\n"
				   + "Left Click - Destroy the selected object\n"
				   + "Middle click - Show the selected object's id\n");
	}
}