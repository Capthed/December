package com.capthed.abyss.component;

import com.capthed.abyss.gfx.Animation;
import com.capthed.abyss.gfx.Texture;
import com.capthed.abyss.map.MapManager;
import com.capthed.abyss.math.Vec2;
import com.capthed.util.Debug;

public abstract class Entity extends GameObject {

	public Entity(Vec2 pos, Vec2 size) {
		super(pos, size);
	}
	
	public Entity(Vec2 pos, Vec2 size, Texture tex) {
		super(pos, size, tex);
		
		this.pos = pos;
		this.size = size;
		this.tex = tex;
	}
	
	public Entity(Vec2 pos, Vec2 size, Animation anim) {
		super(pos, size, anim);
		
		this.pos = pos;
		this.size = size;
		this.animation = anim;
	}
	
	/** Moves the entity regardless of physics for delta. */
	public void move(Vec2 delta) {
		this.pos.add(delta);
		
		if (collider != null) {
			collider.move(delta);
		}
	}
	
	/** 
	 * Moves the entity for delta if there are no collisions. Otherwise it calls the <code>collided(Collider)</code>
	 * method on both colliding GameObjects.
	 */
	public void tryMove(Vec2 delta) {
		if (collider == null) {
			move (delta);
			Debug.err(this + " does not have a collider and should use move(Vec2) instead of tryMove(Vec2)");
			return;
		}
		
		boolean col = false;
		
		move(delta);
		
		for (int i = 0; i < MapManager.getCurrent().getScenes().size(); i++) {
			for (int i2 = 0; i2 <  MapManager.getCurrent().getScenes().get(i).getGcs().size(); i2++) {
				GameComponent gc =  getByID(MapManager.getCurrent().getScenes().get(i).getGcs().get(i2));
				
				if (gc instanceof GameObject) {
					GameObject go = (GameObject) gc;
					
					if (go.isCollidable() && go != this) {
						if (this.collider.intersects(go.getCollider())) {
							this.collided(go);
							go.collided(this);
							col = true;
						}
					}
				}
			}
		}
		
		if (col)
			move(Vec2.mult(Vec2.REV, delta));
	}
}