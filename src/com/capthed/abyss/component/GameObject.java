package com.capthed.abyss.component;

import com.capthed.abyss.GameLoop;
import com.capthed.abyss.gfx.Animation;
import com.capthed.abyss.gfx.Render;
import com.capthed.abyss.gfx.RenderDebug;
import com.capthed.abyss.gfx.Texture;
import com.capthed.abyss.math.Vec2;
import com.capthed.abyss.physics.Collider;

public abstract class GameObject extends GameComponent {

	protected Vec2 pos;
	protected Vec2 size;
	protected Texture tex = null;
	protected int layer = 0;
	protected Collider collider = null;
	protected boolean collidable = false;
	protected Animation animation;
	
	/** Used only with Tile prototypes. */
	public GameObject() {
		super();
		
		GameComponent.getGcs().remove(id);
		setCurrID(getCurrID() - 1);
	}
	
	public GameObject(Vec2 pos, Vec2 size) {
		super();
		
		this.pos = pos;
		this.size = size;
	}
	
	public GameObject(Vec2 pos, Vec2 size, Texture tex) {
		super();
		
		this.pos = pos;
		this.size = size;
		this.tex = tex;
	}
	
	public GameObject(Vec2 pos, Vec2 size, Animation anim) {
		super();
		
		this.pos = pos;
		this.size = size;
		this.animation = anim;
	}
	
	public GameObject setCollider(Collider c) {
		this.collider = c;
		if (c != null)
			collidable = true;
		else
			collidable = false;
		
		if (enabled)
			GameLoop.addCollider(true);
		
		return this;
	}
	
	public Collider getCollider() { return collider; }
	
	public void render() {
		if (animation != null) {
			tex = animation.getTexure();
			Render.quadTex(this);
		} 
		else if (tex != null) {
			Render.quadTex(this);
		}
		else {
			RenderDebug.quad(pos.x(), pos.y(), size.x(), size.y());
		}
	}
	
	/** Called when the object has collided. */
	public void collided(GameObject go) {
		
	}

	public Vec2 getSize() {
		return size;
	}

	public void setSize(Vec2 size) {
		this.size = size;
	}

	public Vec2 getPos() {
		return pos;
	}
	
	public Texture getTex() {
		return tex;
	}

	public void setTex(Texture tex) {
		this.tex = tex;
	}

	/** @return The render priority layer. */
	public int getLayer() {
		return layer;
	}

	/** 
	 * The layer to be rendered to. Higher number = higher render priority. 
	 * RenderUtil.layerLimit() >= layer >= -RenderUtil.layerLimit()
	 */
	public GameObject setLayer(int layer) {
		this.layer = layer;
		
		return this;
	}

	public void setEnabled(boolean b) {
		this.enabled = b;
		
		if (!enabled && collider != null)
			GameLoop.addCollider(false);
	}
	
	public boolean isCollidable() {
		return collidable;
	}

	public Animation getAnimation() {
		return animation;
	}

	public void setAnimation(Animation animation) {
		this.animation = animation;
	}
}
