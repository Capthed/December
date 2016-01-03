package com.capthed.game;

import com.capthed.abyss.Abyss;
import com.capthed.abyss.Game;
import com.capthed.abyss.gfx.Animation;
import com.capthed.abyss.gfx.RenderDebug;
import com.capthed.abyss.gfx.Texture;
import com.capthed.abyss.map.Map;
import com.capthed.abyss.map.MapManager;
import com.capthed.abyss.map.Scene;
import com.capthed.abyss.math.Vec2;
import com.capthed.abyss.physics.CircleCollider;
import com.capthed.abyss.physics.QuadCollider;
import com.capthed.util.Debug;

public class TestRun implements Game{

	private static TestRun run;
	public static final Texture tex = new Texture("res/test.png");
	public static final Texture tex2 = new Texture("res/tile_test.png");
	public static final Texture texColl = new Texture("res/coll1.png");
	public static final Texture texColl2 = new Texture("res/coll2.png");
	public static final Texture run1 = new Texture("res/run1.png");
	public static final Texture run2 = new Texture("res/run2.png");
	public static final Texture run3 = new Texture("res/run3.png");
	public static final Texture tex3 = new Texture("res/tile3.png");
	private static TestCollider t;
	private static TestCollider2 t1;
	public static Scene scene;
	private static Test2 t2;
	public static TestCollider2 other;
	private Animation anim;

	public static void main(String[] args) {
		run = new TestRun();
		
		Debug.setDebug(true);
		
		Abyss.create(1000, 720, run);
		Abyss.setFPS(60);
		Abyss.start();
	}

	@Override
	public void init() {
		tex.loadTex();
		tex2.loadTex();
		tex3.loadTex();
		texColl.loadTex();
		texColl2.loadTex();
		run1.loadTex();
		run2.loadTex();
		run3.loadTex();
		
		anim = new Animation(new Texture[] {run2, run3}, 100, Animation.Type.BOUNCE_LOOP);
		
		AnimTest at = (AnimTest) new AnimTest(new Vec2(500, 500), new Vec2(64, 64), anim).setLayer(10);
		
		//new ExtInput();
		new TileTest1(0xffFFD2C4, tex);
		new TileTest2(0xff6E51FF, tex2);
		new TileTest3(0xffFF2857, tex3);
		
		Vec2 pos = new Vec2(500, 250);
		Vec2 size = new Vec2(64, 64);
		Vec2 delta = new Vec2(0, -100);
		Vec2 delta2 = new Vec2(100, 100);
		
		other = (TestCollider2) new TestCollider2(pos, size, texColl).setLayer(20);
		other.setCollider(new QuadCollider(new Vec2(pos), new Vec2(size))); // MIS
		
		t = (TestCollider) new TestCollider(Vec2.add(pos, delta), new Vec2(size), texColl2).setLayer(30);
		t.setCollider(new QuadCollider(Vec2.add(pos, delta), new Vec2(new Vec2(64, 64)))); // WASD
		
		t1 = (TestCollider2) new TestCollider2(Vec2.add(pos, delta2), new Vec2(size), run1).setLayer(19);
		t1.setCollider(new CircleCollider(CircleCollider.calcCenter(t1), 32)); // ONI S KRUGON
		
		Map lvl1 = new Map("Level 1", Map.TILE_SIZE.T_64);
	
		lvl1.add(other);
		
		lvl1.add(t1);
		lvl1.add(at);
		lvl1.add(t);
		
		MapManager.setCurrent(lvl1);
		
		lvl1.load("res/lvl2.png");
		
		t2 = new Test2(); // ZA GASIT
	}

	@Override
	public void initDisplay() {
		Abyss.setFullscreen(false);
		Abyss.createDisplay("Abyss " + Abyss.getVersion(), true);
	}
	
	@Override
	public void constInit() {
		t2.init();
	}

	@Override
	public void constUpdate() {
		t2.update();
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