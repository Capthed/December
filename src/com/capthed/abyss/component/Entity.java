package com.capthed.abyss.component;

import com.capthed.abyss.gfx.Animation;
import com.capthed.abyss.gfx.Camera;
import com.capthed.abyss.gfx.Texture;
import com.capthed.abyss.input.Keyboard;
import com.capthed.abyss.input.Keys;
import com.capthed.abyss.map.MapManager;
import com.capthed.abyss.math.Vec2;
import com.capthed.util.Debug;

public abstract class Entity extends GameObject {

	protected Vec2 delta = new Vec2(0, 0);
	protected boolean hasCamera = false;
	
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
		this.delta = delta;
		this.pos.add(delta);
		
		if (hasCamera)
			Camera.getCurrent().move(delta);
		if (collider != null) {
			collider.move(delta);
		}
	}
	
	/** Calls tryMove(delta, false). */
	public void tryMove(Vec2 delta) {
		tryMove(delta, false);
	}
	
	/** 
	 * Moves the entity for delta if there are no collisions. Otherwise it calls the <code>collided(Collider)</code>
	 * method on both colliding GameObjects.
	 * 
	 * @param silente If true, the program will not output an error if the Entity has no collider.
	 */
	public void tryMove(Vec2 delta, boolean silente) {
		if (collider == null) {
			move (delta);
			if (!silente)
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
		
		if (col) {
			move(Vec2.mult(Vec2.REV, delta));
		}
	}
	
	/** 
	 * Must be called every frame from the update method. Moves the player on a WASD basis 
	 * with the speed given as the argument.
	 */
	public void genericPlayerUpdate(Vec2 speed) {
		Vec2 sX = new Vec2(speed.x(), 0);
		Vec2 sY = new Vec2(0, speed.y());
		
		if (Keyboard.isKeyDown(Keys.GLFW_KEY_W))
			tryMove(new Vec2(sY), true);
		if (Keyboard.isKeyDown(Keys.GLFW_KEY_A))
			tryMove(new Vec2(sX).mult(new Vec2(-1, -1)), true);
		if (Keyboard.isKeyDown(Keys.GLFW_KEY_D))
			tryMove(new Vec2(sX), true);
		if (Keyboard.isKeyDown(Keys.GLFW_KEY_S))
			tryMove(new Vec2(sY).mult(new Vec2(-1, -1)), true);
	}

	/** @return The current speed. */
	public Vec2 getDelta() {
		return delta;
	}
	
	public boolean hasCamera() {
		return hasCamera;
	}

	/** True if the camera should follow the player. */
	public void setCamera(boolean hasCamera) {
		this.hasCamera = hasCamera;
	}
	
	public String toString() {
		return super.toString().replace("GO", "E") + " > " + delta.toString();
	}
}