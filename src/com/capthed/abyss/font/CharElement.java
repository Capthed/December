package com.capthed.abyss.font;

import com.capthed.abyss.math.Vec2;

public class CharElement {
	
	private Font font;
	private Vec2 pos;
	private char sign;
	private Vec2 uvPos;
	private Vec2 uvSize;
	
	public CharElement(Font font, Vec2 pos, char sign) {
		this.font = font;
		this.pos = pos;
		this.sign = sign;
		
		uvPos = new Vec2(pos.x() * (font.getLetterW() + font.getDeltaX()), pos.y() * (font.getLetterH() + font.getDeltaY()));
		uvSize = new Vec2((pos.x() + 1) * font.getLetterW() + pos.x() * font.getDeltaX(), ((pos.y() + 1) * font.getLetterH() + pos.y() * font.getDeltaY()));
	}

	public Vec2 getUVPos() {
		return uvPos;
	}

	public void setUVPos(Vec2 uvPos) {
		this.uvPos = uvPos;
	}

	public Vec2 getUVSize() {
		return uvSize;
	}

	public void setUVSize(Vec2 uvSize) {
		this.uvSize = uvSize;
	}

	public Font getFont() {
		return font;
	}

	public void setFont(Font font) {
		this.font = font;
	}

	public Vec2 getPos() {
		return pos;
	}

	public void setPos(Vec2 pos) {
		this.pos = pos;
	}

	public char getSign() {
		return sign;
	}

	public void setSign(char sign) {
		this.sign = sign;
	}
}