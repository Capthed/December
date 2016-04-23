package com.capthed.testamb.tiles;

import java.util.Random;

import com.capthed.abyss.component.Tile;
import com.capthed.abyss.gfx.Animation;
import com.capthed.abyss.gfx.Animation.Type;
import com.capthed.abyss.gfx.Texture;
import com.capthed.abyss.math.Vec2;

public class Path extends Tile {

	public Path(Vec2 pos, Vec2 size, Texture tex) {
		super(pos, size, tex);
	}
	
	public Path(Vec2 pos, Vec2 size, Animation anim) {
		super(pos, size, anim);
		
		if (new Random().nextInt() % 2 == 0) {
			animation.setType(Type.BOUNCE_LOOP);
			this.animation.setdTime(170);
			System.out.println(id);
		}
	}
	
	public Path(String name, int c, Texture tex) {
		super(name, c, tex);
	}
	
	public Path(String name, int c, Animation anim) {
		super(name, c, anim);
	}

	@Override
	public Tile build(Vec2 pos, Vec2 size) {
		return new Path(pos, size, animation);
	}
}