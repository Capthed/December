package com.capthed.abyss.gfx;

import static org.lwjgl.opengl.GL11.GL_NEAREST;
import static org.lwjgl.opengl.GL11.GL_RGB;
import static org.lwjgl.opengl.GL11.GL_RGBA;
import static org.lwjgl.opengl.GL11.GL_TEXTURE_2D;
import static org.lwjgl.opengl.GL11.GL_TEXTURE_MAG_FILTER;
import static org.lwjgl.opengl.GL11.GL_TEXTURE_MIN_FILTER;
import static org.lwjgl.opengl.GL11.GL_UNSIGNED_BYTE;
import static org.lwjgl.opengl.GL11.glBindTexture;
import static org.lwjgl.opengl.GL11.glGenTextures;
import static org.lwjgl.opengl.GL11.glTexImage2D;
import static org.lwjgl.opengl.GL11.glTexParameteri;
import static org.lwjgl.stb.STBImage.stbi_load_from_memory;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.nio.ByteBuffer;
import java.nio.IntBuffer;
import java.nio.channels.Channels;
import java.nio.channels.FileChannel;
import java.nio.channels.ReadableByteChannel;
import java.util.ArrayList;

import org.lwjgl.BufferUtils;

public class Texture {
	
	private static ArrayList<Texture> ids = new ArrayList<Texture>();
	
	private static int currId = 0;
	
	private ByteBuffer image;
	private String path;
	private int w, h, comp;
	private int texID;
	private int id;

	public Texture(String path) {
		this.path = path;
	}
	
	/** Loads the texture into the memory. */
	public Texture loadTex() {	
		ByteBuffer imageBuffer;
		try {
			imageBuffer = ioResourceToByteBuffer(path, 8 * 1024);
		} catch (IOException e) {
			throw new RuntimeException(e);
		}

		IntBuffer w = BufferUtils.createIntBuffer(1);
		IntBuffer h = BufferUtils.createIntBuffer(1);
		IntBuffer comp = BufferUtils.createIntBuffer(1);

		image = stbi_load_from_memory(imageBuffer, w, h, comp, 0);

		this.w = w.get(0);
		this.h = h.get(0);
		this.comp = comp.get(0);
		
		texID = glGenTextures();
		
		glBindTexture(GL_TEXTURE_2D, texID);
		
		if ( this.comp == 3 ){
			glTexImage2D(GL_TEXTURE_2D, 0, GL_RGB, this.w, this.h, 0, GL_RGB, GL_UNSIGNED_BYTE, image);
		} else {
			glTexImage2D(GL_TEXTURE_2D, 0, GL_RGBA, this.w, this.h, 0, GL_RGBA, GL_UNSIGNED_BYTE, image);
		}

		glTexParameteri(GL_TEXTURE_2D, GL_TEXTURE_MAG_FILTER, GL_NEAREST);
		glTexParameteri(GL_TEXTURE_2D, GL_TEXTURE_MIN_FILTER, GL_NEAREST);
		
		unbind();
		
		id = currId++;
		ids.add(this);
		
		return this;
	}
	
	/** Binds the texture to OpenGL. */
	public void bind() {
		glBindTexture(GL_TEXTURE_2D, texID);
	}
	
	private static ByteBuffer ioResourceToByteBuffer(String resource, int bufferSize) throws IOException {
		ByteBuffer buffer;

		File file = new File(resource);
		if ( file.isFile() ) {
			FileInputStream fis = new FileInputStream(file);
			FileChannel fc = fis.getChannel();
			buffer = BufferUtils.createByteBuffer((int)fc.size() + 1);

			while ( fc.read(buffer) != -1 ) ;

			fc.close();
			fis.close();
		} else {
			buffer = BufferUtils.createByteBuffer(bufferSize);

			InputStream source = Thread.currentThread().getContextClassLoader().getResourceAsStream(resource);
			if ( source == null )
				throw new FileNotFoundException(resource);

			try {
				ReadableByteChannel rbc = Channels.newChannel(source);
				try {
					while ( true ) {
						int bytes = rbc.read(buffer);
						if ( bytes == -1 )
							break;
						if ( buffer.remaining() == 0 )
							buffer = resizeBuffer(buffer, buffer.capacity() * 2);
					}
				} finally {
					rbc.close();
				}
			} finally {
				source.close();
			}
		}

		buffer.flip();
		return buffer;
	}
	
	public static Texture getByID(int id) {
		return ids.get(id);
	}

	private static ByteBuffer resizeBuffer(ByteBuffer buffer, int newCapacity) {
		ByteBuffer newBuffer = BufferUtils.createByteBuffer(newCapacity);
		buffer.flip();
		newBuffer.put(buffer);
		return newBuffer;
	}

	public int getTexID() { return texID; }
	
	/** Binds a 0 texture to OpenGL. */
	public static void unbind() {
		glBindTexture(GL_TEXTURE_2D, 0);
	}
	
	public String getPath() { return path; }

	public int getId() {
		return id;
	}
}