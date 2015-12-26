package com.capthed.abyss.component;

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
	
	public GameObject setCollider(Collider c) {
		this.collider = c;
		if (c != null)
			collidable = true;
		else
			collidable = false;
		return this;
	}
	
	public Collider getCollider() { return collider; }
	
	public void render() {
		if (tex == null)
			RenderDebug.quad(pos.x(), pos.y(), size.x(), size.y());
		else
			Render.quadtTex(this);
	}
	
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

	public boolean isCollidable() {
		return collidable;
	}
}
