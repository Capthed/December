package com.capthed.abyss.util;

import javax.swing.JOptionPane;

import com.capthed.abyss.input.Keyboard;
import com.capthed.abyss.input.Keys;

public abstract class Util {

	private static boolean var0 = false;
	
	public static void showMsg(String txt) {
		JOptionPane.showMessageDialog(null, txt);
	}
	
	public static void check() {
		if (Keyboard.isKeyPressed(Keys.GLFW_KEY_F1)) {
			if (!var0) {
				DebugPrompt.get().openPrompt();
				var0 = true;
			} else
				DebugPrompt.get().openAgain();
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