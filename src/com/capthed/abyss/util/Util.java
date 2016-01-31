package com.capthed.abyss.util;

import javax.swing.JOptionPane;
import javax.swing.UIManager;
import javax.swing.UIManager.LookAndFeelInfo;

import com.capthed.abyss.Abyss;
import com.capthed.abyss.component.GameComponent;
import com.capthed.abyss.component.GameObject;
import com.capthed.abyss.input.Keyboard;
import com.capthed.abyss.input.Keys;
import com.capthed.abyss.input.Mouse;
import com.capthed.util.Debug;

public abstract class Util {

	public static void setLAFNimbus() {
		try {
		    for (LookAndFeelInfo info : UIManager.getInstalledLookAndFeels()) {
		        if ("Nimbus".equals(info.getName())) {
		            UIManager.setLookAndFeel(info.getClassName());
		            break;
		        }
		    }
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	public static void showMsg(String txt) {
		JOptionPane.showMessageDialog(null, txt);
	}
	
	public static void check() {
		if (Keyboard.isKeyPressed(Keys.GLFW_KEY_ESCAPE)) {
			Abyss.stop();
		}
		
		if (Keyboard.isKeyPressed(Keys.GLFW_KEY_G)) {
			CommandLine.get().createObject();
		}
		
		if (Keyboard.isKeyPressed(Keys.GLFW_KEY_T)) {
			Debug.setDebug(!Debug.isDebug());
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
				   + "G - Command line for creating objects\n"
				   + "T - Toggle debug mode\n"
				   + "Left Click - Destroy the selected object\n"
				   + "Middle click - Show the selected object's id\n");
	}
}