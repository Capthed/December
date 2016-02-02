package com.capthed.abyss.util;

import java.util.StringTokenizer;

import javax.swing.JOptionPane;

import com.capthed.abyss.component.GameComponent;
import com.capthed.abyss.component.GameObject;
import com.capthed.abyss.gfx.Animation;
import com.capthed.abyss.gfx.Texture;
import com.capthed.abyss.map.MapManager;
import com.capthed.abyss.math.Vec2;

public class CommandLine {

	private static final CommandLine cmd = new CommandLine();
	
	private CommandLine() {}
	
	public void createObject() {
		try {
			String info = JOptionPane.showInputDialog(null, "XxY WxH A|T ID(A|T) layer");
			if (info == null || info.trim() == "") return;
			
			StringTokenizer token = new StringTokenizer(info, " ");
			
			String vec1 = token.nextToken();
			StringTokenizer tokenVec1 = new StringTokenizer(vec1, "x");
			float x = Float.valueOf(tokenVec1.nextToken());
			float y = Float.valueOf(tokenVec1.nextToken());
			Vec2 pos = new Vec2(x, y);
			
			String vec2 = token.nextToken();
			StringTokenizer tokenVec2 = new StringTokenizer(vec2, "x");
			float w = Float.valueOf(tokenVec2.nextToken());
			float h = Float.valueOf(tokenVec2.nextToken());
			Vec2 size = new Vec2(w, h);
			
			String kurac = token.nextToken();
			char var0 = kurac.toCharArray()[0];
			
			String tex = token.nextToken();
			int id = Integer.valueOf(tex);
			
			String l = token.nextToken();
			int layer = Integer.valueOf(l);
			
			DummyObject dob = null;
			if (var0 == 'T')
				dob = (DummyObject) new DummyObject(pos, size, Texture.getByID(id)).setLayer(layer);
			else if (var0 == 'A')
				dob = (DummyObject) new DummyObject(pos, size, Animation.getByID(id)).setLayer(layer);
			
			MapManager.getCurrent().add(dob);
		} catch(Exception e) {
			Util.showMsg("Invalid input");
		}
	}
	
	public void remove() {
		String info = JOptionPane.showInputDialog(null, "ID of object to remove: ");
		if (info == null || info.trim() == "") return;
		
		int id = Integer.valueOf(info);
		if (id < GameComponent.getGcs().size())
			GameComponent.getByID(id).destroy();
		else
			Util.showMsg("ID non-existant");
	}
	
	public static CommandLine get() { return cmd; }
	
	private class DummyObject extends GameObject {
		
		public DummyObject(Vec2 pos, Vec2 size, Texture tex) {
			super(pos, size, tex);
		}
		
		public DummyObject(Vec2 pos, Vec2 size, Animation anim) {
			super(pos, size, anim);
		}
	}
}