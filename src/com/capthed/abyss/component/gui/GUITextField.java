package com.capthed.abyss.component.gui;

import com.capthed.abyss.component.GameObject;
import com.capthed.abyss.font.Text;
import com.capthed.abyss.gfx.Texture;
import com.capthed.abyss.input.Keyboard;
import com.capthed.abyss.input.Keys;
import com.capthed.abyss.input.Mouse;
import com.capthed.abyss.math.Vec2;

public class GUITextField extends GameObject {

	private Text txt;
	private boolean focus = false;
	private GUITextFieldListener handler;
	private int immutable;
	
	public GUITextField(Text txt, Vec2 size, Texture tex, GUITextFieldListener handler) {
		super(txt.getPos(), size, tex);
		
		this.handler = handler;
		this.tex = tex;
		this.txt = txt;
		this.size = size;
		this.pos = txt.getPos();
		txt.setPos(Vec2.sub(pos, Vec2.div(txt.getCharSize(), new Vec2(-8, 1))));
	}
	
	public void setText(String txt) {
		this.txt.setText(txt);
	}

	public String getText() { return txt.getText(); }
	
	public void append(char c) {
		String var0 = String.valueOf(c);
		setText(getText() + var0);
	}
	
	public void update() {
		boolean exFocus = focus;
		if (Mouse.getPos().intersects(pos, size)) {
			handler.hover();
			
			if (Mouse.isKeyPressed(Keys.GLFW_MOUSE_BUTTON_1)) {
				handler.clicked();
				
				focus = true;
			}
		}
		else if (Mouse.isKeyPressed(Keys.GLFW_MOUSE_BUTTON_1))
			focus = false;
		
		if (exFocus != focus) {
			if (focus) handler.onGainFocus();
			else handler.onLoseFocus();
			Keyboard.setListening(!focus);
		}
		
		if (focus) {
			Keyboard.setListening(true);
			for (int i = (int)'A'; i <= (int)'Z'; i++)
				if (Keyboard.isKeyPressed(i)) {
					int k = i;
					if (!Keyboard.isKeyDown(Keys.GLFW_KEY_LEFT_SHIFT))
						k+=32;
						
					check(k);
					handler.onKeyEnetered(k);
				}
			
			for (int i = (int)'0'; i <= (int)'9'; i++)
				if (Keyboard.isKeyPressed(i)) {
					check(i);
					handler.onKeyEnetered(i);
				}
			
			if (Keyboard.isKeyPressed(Keys.GLFW_KEY_SPACE)) {
				check(Keys.GLFW_KEY_SPACE);
				handler.onKeyEnetered(Keys.GLFW_KEY_SPACE);
			}
		
			if (Keyboard.isKeyPressed(Keys.GLFW_KEY_ENTER))
				handler.onKeyEnetered(Keys.GLFW_KEY_ENTER);
			
			if (Keyboard.isKeyPressed(Keys.GLFW_KEY_BACKSPACE)) {
				String temp = getText();
				String isus = "";
				for (int i = 0; i < immutable ; i++)
					isus += temp.charAt(i);
				
				for (int i = immutable; i < temp.length() - 1; i++)
					isus += temp.charAt(i);
				
				setText(isus);
			}
			if (exFocus == focus)
				Keyboard.setListening(false);
		}
	}
	
	private void check(int key) {
		if (size.x() >= (txt.getText().length() + 1) * txt.getCharSize().x()) 
			append(Keys.getSign(key));
	}
	
	public void render() {
		super.render();
		txt.render();
	}

	public GUITextFieldListener getHandler() {
		return handler;
	}

	public void setHandler(GUITextFieldListener handler) {
		this.handler = handler;
	}
	
	public void setPos(Vec2 pos) {
		txt.setPos(Vec2.sub(pos, Vec2.div(txt.getCharSize(), new Vec2(-8, 1))));
		
		this.pos = pos;
	}

	public Text getTxt() {
		return txt;
	}

	public boolean isFocus() {
		return focus;
	}

	public void setFocus(boolean focus) {
		this.focus = focus;
		Keyboard.setListening(!focus);
	}

	public void setImmutable(int i) {
		immutable = i;
	}
}