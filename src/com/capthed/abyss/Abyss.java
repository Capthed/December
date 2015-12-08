package com.capthed.abyss;

import com.capthed.util.Debug;

public abstract class Abyss {

	private static final Thread MAIN_LOOP_THREAD = new Thread(new GameLoop());
	
	public static void main(String[] args) {
		Debug.setDebug(true);
		Timer.start();
		
		start();
	}
	
	private static void start() {
		GameLoop.setAlive(true);
		MAIN_LOOP_THREAD.start();
	}
	
	public static void stop() {
		GameLoop.setAlive(false);
	}
}