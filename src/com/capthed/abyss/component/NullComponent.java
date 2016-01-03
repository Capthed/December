package com.capthed.abyss.component;

public class NullComponent extends GameComponent {

	public boolean isNull() { return true; }
	
	public void setID(int newid) { id = newid; }
	
	public String toString() { return "NullComponent " + id; }
}