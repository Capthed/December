package com.capthed.abyss.component;

import com.capthed.abyss.GameLoop;
import com.capthed.abyss.gfx.Animation;
import com.capthed.abyss.gfx.Camera;
import com.capthed.abyss.gfx.Display;
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
	protected boolean blend = false;
	
	public static Texture def = new Texture("res/default.png");
	
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
		this.tex = def;
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
		this.animation = new Animation(anim);
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
			Render.object(this);
		} 
		else if (tex != null) {
			Render.object(this);
		}
		else {
			RenderDebug.quad(pos.x(), pos.y(), size.x(), size.y());
		}
	}
	
	/** Called when the object has collided. */
	public void collided(GameObject go) {
		
	}
	
	public void destroy() {
		super.destroy();
		
		if (collider != null)  collider.detroy();
	}
	
	public static void destroy(int id) {
		GameObject go = (GameObject) GameComponent.getByID(id);
		
		if (go.isCollidable()) go.getCollider().detroy();
		GameComponent.destroy(id);
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
	
	public Vec2 getCenter() {
		return new Vec2(pos.x() + size.x() / 2, pos.y() + size.y() / 2);
	}
	
	public Animation getAnimation() {
		return animation;
	}

	public void setAnimation(Animation animation) {
		this.animation = animation;
	}

	public boolean isBlend() {
		return blend;
	}

	/** 
	 * If blend is enabled then render priority goes by place in render row, not depth for this object. 
	 * Advice: don't fuck around with this if not needed.
	 */
	public void setBlend(boolean blend) {
		this.blend = blend;
	}
	
	/** Adjusts the camera so that it centers the GameObject. */
	public void snapCamera() {
		float w = pos.x() - Display.getWidth() / 2 + size.x() / 2;
		float h = pos.y() - Display.getHeight() / 2 + size.y() / 2;
		
		Camera.getCurrent().setPos(new Vec2(w, h));
	}
	
	public String toString() {
		return super.toString() + " > " + pos.toString() + " > (GO)";
	}
}
