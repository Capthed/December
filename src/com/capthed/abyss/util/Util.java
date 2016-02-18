package com.capthed.abyss.util;

import javax.swing.JOptionPane;

import com.capthed.abyss.Abyss;
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
	
	public static void info(String addition) {
		showMsg("Abyss " + Abyss.getVersion() + "\n"+
				"debug -on/off\n             -show/hide\n" + 
				"create XxY WxH A|T id(A|T) layer\n" + 
				"remove id\n"+
				"mouse -on/off\n"+
				"controller -on/off\n" + addition
				);
	}
}