package com.capthed.abyss.font;

import com.capthed.abyss.gfx.Texture;

public class Font {

	private Texture tex;
	private float letterW, letterH;
	private float deltaX, deltaY;
	
	public Font(Texture tex, float letterW, float letterH, float deltaX, float deltaY) {
		this.tex = tex;
		this.letterW = letterW;
		this.letterH = letterH;
		this.deltaX = deltaX;
		this.deltaY = deltaY;
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