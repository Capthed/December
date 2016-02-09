package com.capthed.abyss.font;

import java.util.HashMap;

import com.capthed.abyss.gfx.Render;
import com.capthed.abyss.gfx.RenderUtil;
import com.capthed.abyss.math.Vec2;
import com.capthed.util.Debug;

public class Text {

	private Vec2 pos;
	private Vec2 charSize;
	private String txt;
	private HashMap<Character, CharElement> lex;
	private float r = 1, g = 1, b = 1, a = 1;
	
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
			else {
				RenderUtil.setColor(r, g, b, a);
				Render.charTex(Vec2.add(pos, new Vec2(charSize.x() * i, charSize.y())), charSize, ce);
				RenderUtil.setColor(1, 1, 1, 1);
			}
		}
	}
	
	public void setColor(float r, float g, float b , float a) {
		this.r = r;
		this.g = g;
		this.b = b;
		this.a = a;
	}
	
	public Vec2 getPos() {
		return pos;
	}

	public Vec2 getCharSize() {
		return charSize;
	}

	public void setCharSize(Vec2 charSize) {
		this.charSize = charSize;
	}

	public void setPos(Vec2 pos) {
		this.pos = pos;
	}

	public String getText() { return txt; }
	
	public void setText(String txt) { this.txt = txt; }
}