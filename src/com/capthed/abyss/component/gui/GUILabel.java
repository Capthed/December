package com.capthed.abyss.component.gui;

import java.util.ArrayList;

import com.capthed.abyss.component.GameObject;
import com.capthed.abyss.font.Font;
import com.capthed.abyss.font.Text;
import com.capthed.abyss.gfx.Texture;
import com.capthed.abyss.math.Vec2;

public class GUILabel extends GameObject {

	private String txt;
	private Font font;
	private Vec2 charSize;
	
	private ArrayList<Text> texts = new ArrayList<Text>();
	
	public GUILabel(Vec2 pos, Vec2 size, Texture tex, String txt, Vec2 charSize, Font font) {
		super(pos, size, tex);
		
		this.txt = txt;
		this.charSize = charSize;
		this.font = font;
		
		calc();
	}
	
	private void calc() {
		String[] txts = new String[100];
		
		for (int i = 0; i < txts.length; i++) {
			txts[i] = "";
		}
		
		int index = 0;
		int last = 0;
		for (int i = 0; i < txt.length(); i++) {
			if (last >= size.x()) {
				last = 0;
				index++;
			}
			
			txts[index] += txt.charAt(i);
			last+=charSize.x();
		}
		
		for (int i = 0; i < txts.length; i++) {
			Vec2 tPos = new Vec2(pos.x(), pos.y() + size.y() - (i + 2) * charSize.y());
			
			if (tPos.y() - pos.y() >= -charSize.y())
				texts.add(new Text(tPos, charSize, txts[i], font.getLex()));
		}
	}
	
	public void render() {
		super.render();
		
		for (Text t : texts)
			t.render();
	}
}
