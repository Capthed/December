package com.capthed.abyss.font;

import java.util.HashMap;

import com.capthed.abyss.gfx.Texture;
import com.capthed.abyss.math.Vec2;

public class Font {

	private Texture tex;
	private float letterW, letterH;
	private float deltaX, deltaY;
	private CharElement space;
	
	public Font(Texture tex, float letterW, float letterH, float deltaX, float deltaY) {
		this.tex = tex;
		this.letterW = letterW;
		this.letterH = letterH;
		this.deltaX = deltaX;
		this.deltaY = deltaY;
		
		space = new CharElement(this, new Vec2(0, 0), ' ');
		space.setUVPos(new Vec2(0, 0));
		space.setUVSize(new Vec2(0, 0));
	}
	
	public HashMap<Character, CharElement> loadLex(String[] chars) {
		HashMap<Character, CharElement> lex = new HashMap<Character, CharElement>();
		
		for (int i = 0; i < chars.length; i++) {
			for (int i2 = 0; i2 < chars[i].length(); i2++) {
				lex.put(chars[i].charAt(i2), new CharElement(this, new Vec2(i2, i), chars[i].charAt(i2)));
			}
		}
		
		lex.put(' ', space);
		
		return lex;
	}

	public Texture getTex() {
		return tex;
	}

	public void setTex(Texture tex) {
		this.tex = tex;
	}

	public float getLetterW() {
		return letterW;
	}

	public void setLetterW(float letterW) {
		this.letterW = letterW;
	}

	public float getLetterH() {
		return letterH;
	}

	public void setLetterH(float letterH) {
		this.letterH = letterH;
	}

	public float getDeltaX() {
		return deltaX;
	}

	public void setDeltaX(float deltaX) {
		this.deltaX = deltaX;
	}

	public float getDeltaY() {
		return deltaY;
	}

	public void setDeltaY(float deltaY) {
		this.deltaY = deltaY;
	}
}