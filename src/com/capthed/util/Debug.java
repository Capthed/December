package com.capthed.util;

import java.util.ArrayList;

public abstract class Debug {

	private static boolean debug = false;
	
	/**
	 * @param t The variable to print
	 * @param txt The description to be printed with the variable.
	 */
	public static <T> void print(T t, String txt) {
		if (!debug) return;
		
		System.out.println(t.toString() + txt);
	}
	
	/**
	 * Same as <code>print(T, String)</code> but doesn't go to a new line.
	 * 
	 * @param t The variable to print
	 * @param txt The description to be printed with the variable.
	 */
	public static <T> void printsl(T t, String txt) {
		if (!debug) return;
		
		System.out.print(t.toString() + txt);
	}
	
	/**
	 * @param t The ArrayList of which to print out every element.
	 * @param txt The description to be printed with the elements.
	 */
	public static <T> void print(ArrayList<T> list, String txt) {
		if (!debug) return;
		
		for(int i = 0; i < list.size(); i++)
			System.out.print(list.get(i) + txt);
	}
	
	/**
	 * @param t The array of which to print out every element.
	 * @param txt The description to be printed with the elements.
	 */
	public static <T> void print(T[] list, String txt) {
		if (!debug) return;
		
		for(int i = 0; i < list.length; i++)
			System.out.print(list[i] + txt);
	}
	
	public static void err (String txt) {
		if (!debug) return;
		
		System.err.println(txt);
	}

	public static boolean isDebug() { return debug; }
	
	public static void setDebug(boolean b) { debug = b; };
}
