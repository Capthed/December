package com.capthed.abyss.map;

import java.awt.image.BufferedImage;
import java.awt.image.DataBufferByte;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;

import javax.imageio.ImageIO;

import com.capthed.abyss.component.Tile;
import com.capthed.abyss.math.Vec2;
import com.capthed.util.Debug;

public abstract class MapLoader {

	private static int r = 0, g = 0, b = 0;
	
	static void loadMap(Map map, String path) {
		BufferedImage mapImg = null;
		try {
			mapImg = ImageIO.read(new File(path));
		} catch (IOException e) {
			Debug.err("An error has occured while reading the image for map: " + map);
			e.printStackTrace();
		}
		
		byte[] pixelsB = null;
		if (map != null)
			pixelsB = ((DataBufferByte) mapImg.getRaster().getDataBuffer()).getData();
		
		Debug.print(pixelsB.length, " length");
		
		int var0 = 0;
		int[] pixels = new int[mapImg.getHeight() * mapImg.getWidth()];
		int iter = 0;
		for (int i = 0; i < pixelsB.length; i++) {
			if (i % 3 == 0) {
				b = pixelsB[i] + var0;
			} else if (i % 3 == 1) {
				g = pixelsB[i] + var0;
			}else if (i % 3 == 2) {
				r = pixelsB[i] + var0;
				
				convertRGB();
				
				Debug.print(r + "r " + g + "g " + b, "b ");
				
				pixels[iter] = (0xff << 24) | ((r&0xff) << 16) | ((g&0xff) << 8) | (b&0xff);
				
				Debug.print(Integer.toHexString(pixels[iter]), "");
				iter++;
			}
		}
		
		int w = mapImg.getWidth();
		
		ArrayList<Integer> colors = new ArrayList<Integer>();
		colors.addAll(Tile.getTiles().keySet());
		
		for (int i = 0; i < pixels.length; i++) {
			for (int i2 = 0; i2 < colors.size(); i2++) {
				if (pixels[i] == colors.get(i2)) {
					Vec2 pos = new Vec2((i % w) * map.getTileSize(), (i / w) * map.getTileSize());
					Vec2 mapPos = new Vec2(i, i / w);
					
					Tile.getTiles().get(colors.get(i2)).build(pos, new Vec2(map.getTileSize(), map.getTileSize())).setMapPos(mapPos);
					
					Debug.print("ide", "");
					continue;
				} 
			}
		}
	}
	
	private static void convertRGB() {
		if (r < 0)
			r += 256;
		if (g < 0)
			g += 256;
		if (b < 0)
			b += 256;
	}
}