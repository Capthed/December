package com.capthed.game;

import com.capthed.abyss.Abyss;
import com.capthed.abyss.Game;
import com.capthed.abyss.Timer;
import com.capthed.abyss.component.gui.GUIButton;
import com.capthed.abyss.component.gui.GUIButtonListener;
import com.capthed.abyss.component.gui.GUICheckBox;
import com.capthed.abyss.component.gui.GUICheckBoxListener;
import com.capthed.abyss.component.gui.GUISlider;
import com.capthed.abyss.component.gui.GUISliderListener;
import com.capthed.abyss.gfx.Animation;
import com.capthed.abyss.gfx.Camera;
import com.capthed.abyss.gfx.Display;
import com.capthed.abyss.gfx.RenderDebug;
import com.capthed.abyss.gfx.Texture;
import com.capthed.abyss.map.Map;
import com.capthed.abyss.map.MapManager;
import com.capthed.abyss.map.Scene;
import com.capthed.abyss.math.Vec2;
import com.capthed.abyss.physics.CircleCollider;
import com.capthed.abyss.physics.QuadCollider;
import com.capthed.abyss.util.Util;
import com.capthed.util.Debug;

public class TestRun implements Game{

	private static TestRun run;
	public static final Texture tex = new Texture("res/test.png");
	public static final Texture tex2 = new Texture("res/tile_test.png");
	public static final Texture texColl = new Texture("res/coll1.png");
	public static final Texture texColl2 = new Texture("res/coll2.png");
	public static final Texture tex4 = new Texture("res/tile4.jpg");
	public static final Texture tex5 = new Texture("res/tile5.gif");
	public static final Texture tex6 = new Texture("res/tile6.png");
	public static final Texture run1 = new Texture("res/run1.png");
	public static final Texture run2 = new Texture("res/run2.png");
	public static final Texture run3 = new Texture("res/run3.png");
	public static final Texture tex3 = new Texture("res/tile3.png");
	public static final Texture texSlider = new Texture("res/slider.jpg");
	public static final Texture texSlider2 = new Texture("res/slider2.png");
	public static final Texture texbut = new Texture("res/button.png");
	public static GUIButton button;
	private static GUICheckBox cb;
	private static TestCollider t;
	private static TestCollider2 t1;
	public static Scene scene;
	public static Map lvl1;
	public static TestCollider2 other;
	public static Animation anim;
	public static GUISlider slider;
	private static Map.TILE_SIZE tSize; 
	private static int var0; // command line arg size
	private static boolean debug; // command line arg
	public static Camera cam;

	public static void main(String[] args) {
		try {
			var0 = Integer.valueOf(args[0]);
		} catch(Exception e) {
			var0 = 64;
		}
		
		try {
			debug = Integer.valueOf(args[1]) == 1 ? true : false;
		} catch(Exception e) {
			debug = true;
		}
		
		switch(var0) {
		case 16:
			tSize = Map.TILE_SIZE.T_16;
			break;
		case 32:
			tSize = Map.TILE_SIZE.T_32;
			break;
		case 64:
			tSize = Map.TILE_SIZE.T_64;
			break;
		case 128:
			tSize = Map.TILE_SIZE.T_128;
			break;
		}
		
		run = new TestRun();
		
		Debug.setDebug(debug);
		Util.setLAFNimbus();
		
		Abyss.create(1200, 720, run);
		Abyss.setFPS(-1);
		Abyss.start();
	}

	@Override
	public void init() {
		tex.loadTex();
		tex2.loadTex();
		tex3.loadTex();
		tex4.loadTex();
		tex5.loadTex();
		tex6.loadTex();
		texColl.loadTex();
		texColl2.loadTex();
		run1.loadTex();
		run2.loadTex();
		run3.loadTex();
		texSlider2.loadTex();
		texSlider.loadTex();
		texbut.loadTex();
		
		anim = new Animation(new Texture[] {run2, run3}, 100, Animation.Type.BOUNCE_LOOP);
		
		AnimTest at = (AnimTest) new AnimTest(new Vec2(500, 500), new Vec2(var0, var0), anim).setLayer(10);
		
		//new ExtInput();
		new TileTest1(0xffFFD2C4, tex);
		new TileTest2(0xff6E51FF, tex2);
		new TileTest3(0xffFF2857, tex3);
		new TileTest3(0xff282CFF, tex4);
		new TileTest3(0xff3AFF1C, tex5);
		new TileTest4(0xffFFBE3D, tex6);
		
		Vec2 pos = new Vec2(Display.getWidth() / 2 - var0 / 2, Display.getHeight() / 2 + var0 / 2);
		Vec2 size = new Vec2(var0, var0);
		Vec2 delta = new Vec2(0, -100);
		Vec2 delta2 = new Vec2(100, 100);
		
		other = (TestCollider2) new TestCollider2(pos, size, texColl).setLayer(20);
		other.setCollider(new QuadCollider(new Vec2(pos), new Vec2(size))); // MIS
		
		t = (TestCollider) new TestCollider(Vec2.add(pos, delta), new Vec2(size), anim).setLayer(30);
		t.setCollider(new QuadCollider(Vec2.add(pos, delta), new Vec2(new Vec2(size)))); // WASD
		
		cam = new Camera(new Vec2(0, 0));
		Camera.setCurrent(cam.init());
		
		t1 = (TestCollider2) new TestCollider2(Vec2.add(pos, delta2), new Vec2(size), run1).setLayer(19);
		t1.setCollider(new CircleCollider(CircleCollider.calcCenter(t1), size.x() / 2)); // ONI S KRUGON
		
		lvl1 = new Map("Level 1", tSize);
	
		lvl1.add(other);
		
		lvl1.add(t1);
		lvl1.add(at);
		lvl1.add(t);
		
		MapManager.setCurrent(lvl1);
		
		lvl1.load("res/lvl3.png");
		
		button = (GUIButton) new GUIButton(new Vec2(500, 600), new Vec2(64, 64), texbut, new GUIButtonListener() {
			public void clicked() {
				Util.info();
			}
			
			public void onLoseFocus() {
				Debug.print("valja", "");
			}
		}).setLayer(10);
		
		cb = (GUICheckBox) new GUICheckBox(new Vec2(200, 200), new Vec2(32, 32), texColl, texColl2, false, new GUICheckBoxListener() {
			public void onChangeState(boolean b) {
				Debug.print(b,"");
			}
			public void onGainFocus() {
				Debug.print("valja", "");
			}
		}).setLayer(10);
		
		slider = (GUISlider) new GUISlider(new Vec2(300, 300), new Vec2(128, 16), new Vec2(25, 32), texSlider, texSlider2, 10, 100, new GUISliderListener() {
			public void slidding() {
				Debug.print(slider.getValue(), "");
			}
			
			public void onGainFocus() {
				Debug.print("focus");
			}
			
			public void onLoseFocus() {
				Debug.print("defocus");
			}
			
			public void hover() {
				if (Timer.getTimeRunning() <= 2)
					Debug.print("kurac");
			}
		}).setLayer(10);
		lvl1.add(slider);
		
		MapManager.getCurrent().add(cb);
		lvl1.add(button);
	}

	@Override
	public void initDisplay() {
		Abyss.setFullscreen(false);
		Abyss.createDisplay("Abyss " + Abyss.getVersion(), false);
	}
	
	@Override
	public void constInit() {
	}

	@Override
	public void constUpdate() {
		Util.check();
	}

	@Override
	public void constRender() {
		RenderDebug.church();
	}

	@Override
	public void closing() {
		Debug.print("Closing game", "");
	}
}