package com.capthed.game;

import com.capthed.abyss.Abyss;
import com.capthed.abyss.Game;
import com.capthed.abyss.gfx.Display;
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
	public static Scene scene;
	private static Test2 t2;
	public static TestCollider2 other;

	public static void main(String[] args) {
		run = new TestRun();
		
		Abyss.create(1200, 720, run);
		Abyss.start();
	}

	@Override
	public void init() {
		tex.loadTex();
		tex2.loadTex();
		texColl.loadTex();
		texColl2.loadTex();
		
		//new ExtInput();
		new TileTest1(0xffFFD2C4, tex);
		new TileTest2(0xff6E51FF, tex2);
		
		Vec2 pos = new Vec2(500, 250);
		Vec2 size = new Vec2(64, 64);
		//0, 96
		Vec2 delta = new Vec2(0, -100);
		
		other = new TestCollider2(pos, size, texColl);
		other.setCollider(new QuadCollider(new Vec2(pos), new Vec2(size)));
		
		TestCollider t = new TestCollider(Vec2.add(pos, delta), new Vec2(size), texColl2);
		t.setCollider(new CircleCollider(CircleCollider.calcCenter(t), 64));
		
		Map lvl1 = new Map("Level 1", Map.TILE_SIZE.T_64);
		lvl1.add(other);
		lvl1.add(t);
		MapManager.setCurrent(lvl1);
		
		lvl1.load("res/lvl1.png");
		
		t2 = new Test2();
	}

	@Override
	public void initDisplay() {
		Debug.setDebug(true);
		Display.fulscreen(false);
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
		
	}
}