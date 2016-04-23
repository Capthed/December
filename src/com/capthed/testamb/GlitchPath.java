package com.capthed.testamb;

import com.capthed.abyss.component.GameObject;
import com.capthed.abyss.gfx.Animation;
import com.capthed.abyss.gfx.Texture;
import com.capthed.abyss.math.Vec2;

public class GlitchPath extends GameObject {

	public GlitchPath(Vec2 pos, Vec2 size, Texture tex) {
		super(pos, size, tex);
	}
	
	public GlitchPath(Vec2 pos, Vec2 size, Animation anim) {
		super(pos, size, anim);
	}
}
