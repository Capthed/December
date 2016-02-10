package com.capthed.abyss.afx;

import java.util.ArrayList;

import org.lwjgl.openal.ALCCapabilities;
import org.lwjgl.openal.ALContext;
import org.lwjgl.openal.ALDevice;

public abstract class SoundUtil {

	private static ArrayList<Sound> sounds = new ArrayList<Sound>();
	
	public static void initOpenAL() {
		ALContext context = ALContext.create();
		ALDevice device = context.getDevice();
		 
		context.makeCurrent();
		
		ALCCapabilities capabilities = device.getCapabilities();
		 
		if (!capabilities.OpenALC10)
		    throw new RuntimeException("OpenAL Context Creation failed");
	}
	
	public static void update() {
		for (int i = 0; i < sounds.size(); i++)
			sounds.get(i).update();
	}
	
	/** Adds a sound that has to be updated. Used for looping sound. */
	public static void add (Sound s) {
		sounds.add(s);
	}
}
