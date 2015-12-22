package com.capthed.game;

import com.capthed.abyss.Abyss;
import com.capthed.abyss.Game;
import com.capthed.abyss.gfx.Display;
import com.capthed.abyss.gfx.Texture;
import com.capthed.abyss.map.Map;
import com.capthed.abyss.map.MapManager;
import com.capthed.abyss.map.Scene;
import com.capthed.util.Debug;

public class TestRun implements Game{

	private static TestRun run;
	public static final Texture tex = new Texture("res/test.png");
	public static final Texture tex2 = new Texture("res/tile_test.png");
	public static Scene scene;
	private static Test2 t2;

	public static void main(String[] args) {
		run = new TestRun();
		
		Abyss.create(1200, 720, run);
		Abyss.start();
	}

	@Override
	public void init() {
		tex.loadTex();
		tex2.loadTex();
		
		//new ExtInput();
		new TileTest1(0xffFFD2C4, tex);
		new TileTest2(0xff6E51FF, tex2);
		
		Map lvl1 = new Map("Level 1", Map.TILE_SIZE.T_64);
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