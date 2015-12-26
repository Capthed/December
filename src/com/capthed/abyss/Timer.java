package com.capthed.abyss;

public abstract class Timer {

	public static final float SECOND = 1000.0f;
	private static long startTime = 0;
	private static long delta;
	
	public static void start() {
		startTime = getTime();
	}
	
	/** @return The current time in ms */
	public static long getTime() { return System.currentTimeMillis(); }
	
	/** @return The time passed from last frame to this one in seconds*/
	public static double getDelta() { 
		double temp = delta / (double) SECOND;
		return temp; 
	}
	
	static void setDelta(long d) { delta = d; }

	/** @return The time passed since the program has been started in seconds */
	public static double getTimeRunning() {
		return (getTime() - startTime) / (double) SECOND;
	}
}