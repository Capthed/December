package com.capthed.abyss.component;

public class NullComponent extends GameComponent {

	public static int numNulls = 0;
	
	public NullComponent() {
		super();
		numNulls++;
		gcs.remove(gcs.size() - 1);
	}
	
	public boolean isNull() { return true; }
	
	public void setID(int newid) { id = newid; }
	
	public String toString() { return "NullComponent " + id; }
}