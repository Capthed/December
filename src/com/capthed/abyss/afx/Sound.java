package com.capthed.abyss.afx;

import java.io.BufferedInputStream;
import java.nio.FloatBuffer;
import java.nio.IntBuffer;

import org.lwjgl.BufferUtils;
import org.lwjgl.openal.AL10;

import com.capthed.abyss.Timer;
import com.capthed.util.Debug;

public class Sound {

	private String path;
	private float length = -1;
	private double startTime, currTime = 0;
	
	/** Buffers hold sound data. */
	private IntBuffer buffer = BufferUtils.createIntBuffer(1);

	/** Sources are points emitting sound. */
	private IntBuffer source = BufferUtils.createIntBuffer(1);

	/** Position of the source sound. */
	private FloatBuffer sourcePos = (FloatBuffer) BufferUtils.createFloatBuffer(3)
			.put(new float[] { 0.0f, 0.0f, 0.0f }).rewind();

	/** Velocity of the source sound. */
	private FloatBuffer sourceVel = (FloatBuffer) BufferUtils.createFloatBuffer(3)
			.put(new float[] { 0.0f, 0.0f, 0.0f }).rewind();

	/** Position of the listener. */
	private FloatBuffer listenerPos = (FloatBuffer) BufferUtils.createFloatBuffer(3)
			.put(new float[] { 0.0f, 0.0f, 0.0f }).rewind();

	/** Velocity of the listener. */
	private FloatBuffer listenerVel = (FloatBuffer) BufferUtils.createFloatBuffer(3)
			.put(new float[] { 0.0f, 0.0f, 0.0f }).rewind();

	/**
	 * Orientation of the listener. (first 3 elements are "at", second 3 are
	 * "up")
	 */
	FloatBuffer listenerOri = (FloatBuffer) BufferUtils.createFloatBuffer(6)
			.put(new float[] { 0.0f, 0.0f, -1.0f, 0.0f, 1.0f, 0.0f }).rewind();
	
	/** @param path The location of the wav file */
	public Sound(String path) {
		this.path = path;
	}
	
	/**
	 * Wav compression is recomended for large files.
	 * 
	 * @param path The location of the wav file 
	 * @param length Duration of the file in seconds.
	 */
	public Sound(String path, float length) {
		this.path = path;
		this.length = length;
	}

	public void play() {
		AL10.alSourcePlay(source.get(0));
	}
	
	/** The length must be set to use this */
	public void playLoop() {
		if (length == -1) {
			Debug.err("You need to set the length to loop a sound. At: " + this);
			return;
		}
		SoundUtil.add(this);
		startTime = Timer.getTime();
		
		AL10.alSourcePlay(source.get(0));
	}

	public void pause() {
		AL10.alSourcePause(source.get(0));
	}
	
	public void stop() {
		AL10.alSourceStop(source.get(0));
	}

	public void update() {
		currTime = (Timer.getTime() - startTime) / Timer.SECOND;
		if (currTime >= length) {
			startTime = Timer.getTime();
			currTime = 0;
			
			play();
		}
	}
	
	/** Loads the sound file. Must be called before playing. */
	public int load() {
		AL10.alGenBuffers(buffer);

		if (AL10.alGetError() != AL10.AL_NO_ERROR)
			return AL10.AL_FALSE;

		java.io.FileInputStream fin = null;
		try {
			fin = new java.io.FileInputStream(path);
		} catch (java.io.FileNotFoundException ex) {
			ex.printStackTrace();
			return AL10.AL_FALSE;
		}
		WaveData waveFile = WaveData.create(new BufferedInputStream(fin));
		try {
			fin.close();
		} catch (java.io.IOException ex) {
		}

		AL10.alBufferData(buffer.get(0), waveFile.format, waveFile.data,
				waveFile.samplerate);
		waveFile.dispose();

		// Bind the buffer with the source.
		AL10.alGenSources(source);

		if (AL10.alGetError() != AL10.AL_NO_ERROR)
			return AL10.AL_FALSE;

		AL10.alSourcei(source.get(0), AL10.AL_BUFFER, buffer.get(0));
		AL10.alSourcef(source.get(0), AL10.AL_PITCH, 1.0f);
		AL10.alSourcef(source.get(0), AL10.AL_GAIN, 1.0f);
		AL10.alSourcefv(source.get(0), AL10.AL_POSITION, sourcePos);
		AL10.alSourcefv(source.get(0), AL10.AL_VELOCITY, sourceVel);

		if (AL10.alGetError() == AL10.AL_NO_ERROR)
			return AL10.AL_TRUE;
		
		AL10.alListenerfv(AL10.AL_POSITION,  listenerPos);
		 AL10.alListenerfv(AL10.AL_VELOCITY,  listenerVel);
		 AL10.alListenerfv(AL10.AL_ORIENTATION, listenerOri);

		return AL10.AL_FALSE;
	}
	
	/** @return The length of the wav file. */
	public float getLength() {
		return length;
	}

	public void setLength(float length) {
		this.length = length;
	}

	public void delete() {
		 AL10.alDeleteSources(source);
		 AL10.alDeleteBuffers(buffer);
	}
	
	public String toString() { return "Sound " + path; }
}