package com.capthed.abyss.math;

import com.capthed.abyss.Timer;

public class Tween {

	private float start, end;
	private long time;
	private Type t;
	private long timePassed = 0;
	private long timeStart = -1;
	private boolean finished = false;
	private float value = 0;
	
	/** Types of appending the value. */
	public enum Type {
		LINEAR, EXPONENTIAL_UP, EXPONENTIAL_DOWN;
	}
	
	/** The start and end values, the time in ms of iteration and the type of iteration. */
	public Tween(float start, float end, long time, Type t) {
		this.start = start;
		this.end = end;
		this.time = time;
		this.t = t;
	}
	
	/** Must be called to update the tween. */
	public void update() {
		if (finished) return;
		
		if (timeStart == -1)
			timeStart = Timer.getTime();
		
		timePassed = Timer.getTime() - timeStart;
		
		if (t == Type.LINEAR) 
			linear();
		else if (t == Type.EXPONENTIAL_UP)
			exspUp();
		else if (t == Type.EXPONENTIAL_DOWN)
			exspDown();
		
		if (value > end || timePassed >= time) {
			value = end;
			finished = true;
		}
		
		if (value == end)
			finished = true;
	}

	private void exspUp() {
		value = (float)((end - start) * Math.pow(2, 10 * ((float)(timePassed / time) -1.0f)) + start);
	}

	private void exspDown() {
		value = (float)((end - start) * (-Math.pow(2, -10 * (float)(timePassed / time)) + 1.0f) + start);
	}

	private void linear() {
		value = (end - start) * timePassed / time + start;
	}
	
	/** @return The value of the current iteration. */
	public float value() { return value; }
	
	public boolean isFinished() { return finished; }

	public float getStart() {
		return start;
	}

	public float getEnd() {
		return end;
	}

	public float getTime() {
		return time;
	}

	public long getTimePassed() {
		return timePassed;
	}
}