package com.capthed.abyss.font;

import java.util.HashMap;

import com.capthed.abyss.gfx.Render;
import com.capthed.abyss.math.Vec2;
import com.capthed.util.Debug;

public class Text {

	private Vec2 pos;
	private Vec2 charSize;
	private String txt;
	private HashMap<Character, CharElement> lex;
	
	public Text(Vec2 pos, Vec2 charSize, String txt, HashMap<Character, CharElement> lex) {
		this.pos = pos;
		this.charSize = charSize;
		this.txt = txt;
		this.lex = lex;
	}
	
	public void render() {
		for (int i = 0; i < txt.length(); i++) {
			CharElement ce = null;
			ce = lex.get(txt.charAt(i));
			
			if (ce == null) {
				Debug.err("Texture does not exist for simbol '" + txt.charAt(i) + "' ");
			}
			else
				Render.charTex(Vec2.add(pos, new Vec2(charSize.x() * i, charSize.y())), charSize, ce);
		}
	}
}