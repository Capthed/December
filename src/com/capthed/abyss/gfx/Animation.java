package com.capthed.abyss.gfx;

import java.util.ArrayList;

import com.capthed.abyss.Timer;

public class Animation {

	private static ArrayList<Animation> ids = new ArrayList<Animation>();
	
	private static int currId = 0;
	
	private Texture[] texs;
	private Type t;
	private long dTime;
	private long lastTime = -1;
	private int currTex = 0;
	private int maxTex;
	private boolean var0 = false;
	private int id;
	
	public enum Type {
		ONCE, LOOP, BOUNCE_LOOP;
	}
	
	// time in ms
	public Animation(Texture[] texs, long dTime, Type t) {
		this.t = t;
		this.texs = texs;
		this.dTime = dTime;
		
		id = currId++;
		ids.add(this);
		
		maxTex = texs.length;
	}

	public Texture getTexure() {
		if (lastTime == -1) lastTime = Timer.getTime();
		
		long currTime = Timer.getTime();
		
		if (currTime - lastTime >= dTime) {
			lastTime = currTime;
			
			if (t == Type.ONCE && currTex < maxTex) {
				currTex++;
			} 
			else if (t == Type.LOOP) {
				if (currTex == maxTex - 1) currTex = 0;
				else currTex++;
			}
			else if (t == Type.BOUNCE_LOOP) {
				if (currTex == 0) var0 = false;
				else if (currTex == maxTex - 1) var0 = true;
				
				if (!var0) currTex++;
				else currTex--;
			}
		}
		
		return texs[currTex];
	}
	
	public float getdTime() {
		return dTime;
	}

	public Type getType() {
		return t;
	}

	public void setType(Type t) {
		this.t = t;
	}

	public int getId() {
		return id;
	}

	public Texture[] getTexs() {
		return texs;
	}
	
	public static Animation getByID(int id) {
		return ids.get(id);
	}
}
