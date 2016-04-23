package com.capthed.testamb;

import com.capthed.abyss.Abyss;
import com.capthed.abyss.Game;
import com.capthed.abyss.GameLoop;
import com.capthed.abyss.gfx.Animation;
import com.capthed.abyss.gfx.Animation.Type;
import com.capthed.abyss.gfx.Camera;
import com.capthed.abyss.gfx.Texture;
import com.capthed.abyss.map.Map;
import com.capthed.abyss.map.Map.TILE_SIZE;
import com.capthed.abyss.map.MapManager;
import com.capthed.abyss.math.Vec2;
import com.capthed.abyss.util.Util;
import com.capthed.testamb.tiles.Path;
import com.capthed.util.Debug;

public class Main implements Game {

	private static Main game = new Main();
	private static Texture texTest = new Texture("gres/tiles/test1.png");
	private static Texture texWindmill = new Texture("gres/tiles/windmill.png");
	private static Texture texG1 = new Texture("gres/tiles/glitch1.png");
	private static Texture texG2 = new Texture("gres/tiles/glitch2.png");
	private static Texture texG3 = new Texture("gres/tiles/glitch3.png");
	private static Texture texG4 = new Texture("gres/tiles/glitch4.png");
	
	public static void main(String[] args) {
		Debug.setDebug(false);
		
		Abyss.create(1200, 720, game);
		Abyss.setFPS(-1);
		Abyss.start();
	}
	
	@Override
	public void init() {
		Texture.loadAll();
		
		Animation anim = new Animation(new Texture[]{texG1, texG2, texG3, texG4}, 100 , Type.LOOP);
		float var1 = 64;
		GlitchPath gp = (GlitchPath) new GlitchPath(new Vec2(500, 200), new Vec2(var1, var1), anim).setLayer(10);
		GlitchPath gp1 = (GlitchPath) new GlitchPath(new Vec2(600, 200), new Vec2(var1, var1), texTest).setLayer(10);
		
		Map m1 = new Map("Map 1", TILE_SIZE.T_32);
		Camera cam = new Camera(new Vec2(0, 0));
		Camera.setCurrent(cam);
		
		new Path("Path", 0xff93DEFF, anim);
		
		MapManager.setCurrent(m1);
		m1.load("gres/lvl/lvl1.png");
		
		Windmill w = (Windmill) new Windmill(new Vec2(300, 300), new Vec2(256, 256), texWindmill).setLayer(10);
		m1.add(w);
		m1.add(gp);
		m1.add(gp1);
	}

	@Override
	public void initDisplay() {
		GameLoop.setDebugRender(false);
		Abyss.createDisplay("Ambient", true);
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
		// TODO Auto-generated method stub
		
	}

	@Override
	public void closing() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void closed() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public boolean process(String input) {
		// TODO Auto-generated method stub
		return false;
	}
}