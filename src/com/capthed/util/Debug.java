package com.capthed.util;

import java.util.ArrayList;

public abstract class Debug {

	private static boolean debug = false;
	
	public static <T> void print(T t, String txt) {
		if (!debug) return;
		
		System.out.println(t.toString() + txt);
	}
	
	public static <T> void print(ArrayList<T> list, String txt) {
		if (!debug) return;
		
		for(int i = 0; i < list.size(); i++)
			System.out.print(list.get(i) + txt);
	}
	
	public static <T> void print(T[] list, String txt) {
		if (!debug) return;
		
		for(int i = 0; i < list.length; i++)
			System.out.print(list[i] + txt);
	}
	
	public static boolean isDebug() { return debug; }
	
	public static void setDebug(boolean b) { debug = b; };
}
