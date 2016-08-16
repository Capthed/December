package com.capthed.abyss;

import java.util.ArrayList;
import java.util.StringTokenizer;

import com.capthed.abyss.component.GameComponent;
import com.capthed.abyss.component.GameObject;
import com.capthed.abyss.component.gui.GUITextField;
import com.capthed.abyss.component.gui.GUITextFieldListener;
import com.capthed.abyss.font.Font;
import com.capthed.abyss.font.Text;
import com.capthed.abyss.gfx.Animation;
import com.capthed.abyss.gfx.Display;
import com.capthed.abyss.gfx.RenderUtil;
import com.capthed.abyss.gfx.Texture;
import com.capthed.abyss.input.Controller;
import com.capthed.abyss.input.Keyboard;
import com.capthed.abyss.input.Keys;
import com.capthed.abyss.map.MapManager;
import com.capthed.abyss.math.Vec2;
import com.capthed.util.Debug;

public class DebugPrompt {

	private static Texture tex = new Texture("res/debugPrompt.png");
	private static GUITextField field, field2, field3; // field2 = result field, field3 = msg
	private static DebugPrompt dp = new DebugPrompt();
	private static ArrayList<String> prevCmd = new ArrayList<String>();
	private static boolean enter = false;
	public static boolean threadSwitch = false;
	public static boolean var0 = false;
	
	private DebugPrompt() {}
	
	public void init() {
		tex.load();
	}
	
	public void move(Vec2 delta) {
		field.setPos(Vec2.add(field.getPos(), delta));
		field2.setPos(Vec2.add(field2.getPos(), delta));
		field3.setPos(Vec2.add(field3.getPos(), delta));
	}
	
	public void initPrompt() {
		int pW = Display.getWidth() * 3 / 4;
		
		Font font = GameLoop.getDebugFont();
		Text txt = new Text(new Vec2(0, Display.getHeight() - 34 / 2), new Vec2(20 / 2, 32 / 2), ">:", font.getLex());
		txt.setColor(0.83f, 0.88f, 0.23f, 1).setLayer(RenderUtil.debugLayer());
		field = (GUITextField) new GUITextField(txt, new Vec2(pW, 32), tex, new GUITextFieldListener() {
			public void onKeyEnetered(int k) {
				if (k == Keys.GLFW_KEY_ENTER) {
					enter = true;
					if (!process(field.getText().replace(">:", "")))
						field2.setText("Invalid command");
					else
						prevCmd.add(field.getText().replace(">:", ""));
					field.setText(">:");
				}
				if (k == Keys.GLFW_KEY_ESCAPE) {
					field.setFocus(false);
					field.setEnabled(false);
					field.setText(">:");
					
					field2.setEnabled(false);
					field3.setEnabled(false);
				}
			}
		}).setLayer(RenderUtil.debugLayer() - 1);
		field.setImmutable(2);
		
		Text txt2 = new Text(new Vec2(0, Display.getHeight() - 65 / 2), new Vec2(20 / 2, 32 / 2), "", font.getLex());
		txt2.setColor(0.83f, 0.88f, 0.23f, 1).setLayer(RenderUtil.debugLayer());
		field2 = (GUITextField) new GUITextField(txt2, new Vec2(pW, 64), tex, new GUITextFieldListener()).setLayer(RenderUtil.debugLayer() - 1);
		field2.setEnabled(true);
		field2.setWriteble(false);
		
		Text txt3 = new Text(new Vec2(0, Display.getHeight() - 97 / 2), new Vec2(20 / 2, 32 / 2),"FPS: " +  GameLoop.getFps(), font.getLex());
		txt3.setColor(0.83f, 0.60f, 0.23f, 1).setLayer(RenderUtil.debugLayer());
		field3 = (GUITextField) new GUITextField(txt3, new Vec2(pW, 96), tex, new GUITextFieldListener()).setLayer(RenderUtil.debugLayer() - 1);
		field3.setEnabled(true);
		field3.setWriteble(false);
	}
	
	private boolean process(String p) {
		StringTokenizer token = new StringTokenizer(p, " ");
		
		String first = "";
		if (token.hasMoreTokens())
			first = token.nextToken();
	
		if (first.equals("exit")) 
			Abyss.stop();
		
		else if(first.equals("debug")) {
			String var0 = token.nextToken();
			if (var0.equals("on"))
				Debug.setDebug(true);
			else if (var0.equals("off"))
				Debug.setDebug(false);
			
			else if (var0.equals("show"))
				GameLoop.setDebugRender(true);
			else if (var0.equals("hide"))
				GameLoop.setDebugRender(false);
			
			field2.setText("Debug toggled");
		}
		else if (first.equals("create")) {
			try {
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
				field2.setText("Created at (" + x + ", " + y + ")");
			} catch(Exception e) {
				field2.setText("Invalid command");
			}
		}
		
		else if (first.equals("snap")) {
			String temp = token.nextToken();
			
			int x = -1;
			try {
				x = Integer.valueOf(temp);
			} catch(Exception e) {
				field2.setText("Invalid command");
				return false;
			}
			
			if (x < GameComponent.getGcs().size()) {
				GameComponent gc = GameComponent.getByID(x);
				if (gc instanceof GameObject) 
					((GameObject)gc).snapCamera();
				else
					field2.setText("Given GC is not a GO");
			} else {
				field2.setText("Index out of bounds");
			}
		}
		
		else if (first.equals("echo")) {
			String temp = token.nextToken();
			
			this.showMsg(temp);
		}
		else if (first.equals("remove")) {
			int id = Integer.valueOf(token.nextToken());
			if (id < GameComponent.getGcs().size() && id != 0) {
				GameComponent.getByID(id).destroy();
				field2.setText("Removed " + id);
			} else
				field2.setText("No GC with given id");
			
		}
		else if (p.equals("glversion")) {
			field2.setText("Min GL version: " + RenderUtil.getGLVersionUse());
		}
		else if (first.equals("mouse")) {
			String var0 = token.nextToken();
			if (var0.equals("on"))
				Display.setShowMouse(true);
			else if (var0.equals("off"))
				Display.setShowMouse(false);
			
			field2.setText("Mouse toggled");
		}
		else if (first.equals("controller")) {
			String var0 = token.nextToken();
			if (var0.equals("on")) {
				Controller.init();
			}
			else if (var0.equals("off"))
				Controller.setHas(false);
			
			field2.setText("Controller toggled");
		} 
		else if (first.equals("church")) {
			GameLoop.church();
			field2.setText("Capthed history");
		}
		else if (first.equals("version")) {
			field2.setText(Abyss.getVersion());
		}
		else if (first.equals("print")) {
			String temp = token.nextToken();
			final int var0;
			try {
				var0 = Integer.valueOf(temp);
			} catch(Exception e) {
				return false;
			}
			
			new Thread(){
				public void run() {
					enter = false;
					boolean flag = false;
					try{
						while(!enter && !flag && !threadSwitch) {
							field2.setText(GameComponent.getByID(var0).toString());
						}
						flag = true;
					} catch(Exception e) {
						field2.setText("No GC with given id");
					}
				}
			}.start();
		}
		else if (p.equals("help og")) {
			field2.setText("Under construction");
		}
		else if(p.equals("cls")) {
			field2.setText("");
		}
		else {
			return Abyss.getGame().process(p);
		}
		return true;
	}
	
	public void openAgain() {
		if (field.isNull()) {
			initPrompt();
			openPrompt();
			return;
		}
		field.setEnabled(true);
		field.setFocus(true);
		field2.setEnabled(true);
		field3.setEnabled(true);
	}
	
	public static DebugPrompt get() { return dp; }
	
	public void openPrompt() {
		MapManager.getCurrent().add(field);
		MapManager.getCurrent().add(field2);
		MapManager.getCurrent().add(field3);
		field.setFocus(true);
	}
	
	public static Texture getTex() {
		return tex;
	}

	public static void setTex(Texture tex) {
		DebugPrompt.tex = tex;
	}
	
	/** Must be called in an update method if the prompt is enabled. */
	public static void check() {
		if (Keyboard.isKeyPressed(Keys.GLFW_KEY_F1)) {
			if (!var0) {
				DebugPrompt.get().openPrompt();
				var0 = true;
			} else
				DebugPrompt.get().openAgain();
		}
	}
	
	/** outputs the message to the third line of the prompt. */
	public void showMsg(String txt) {
		field3.setText(txt);
	}

	private class DummyObject extends GameObject {
		
		public DummyObject(Vec2 pos, Vec2 size, Texture tex) {
			super(pos, size, tex);
		}
		
		public DummyObject(Vec2 pos, Vec2 size, Animation anim) {
			super(pos, size, anim);
		}
	}
}