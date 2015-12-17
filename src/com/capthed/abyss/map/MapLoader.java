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

	public static void loadMap(Map map, String path) {
		BufferedImage mapImg = null;
		try {
			mapImg = ImageIO.read(new File(path));
		} catch (IOException e) {
			Debug.err("An error has occured while reading the image for map " + map);
			e.printStackTrace();
		}
		
		byte[] pixels = null;
		if (map != null)
			pixels = ((DataBufferByte) mapImg.getRaster().getDataBuffer()).getData();
		
		int w = mapImg.getWidth();
		
		@SuppressWarnings("unchecked")
		ArrayList<Byte> colors = (ArrayList<Byte>) Tile.getTiles().keySet();
		
		for (int i = 0; i < pixels.length; i++) {
			for (int i2 = 0; i2 < colors.size(); i2++) {
				if (pixels[i] == colors.get(i2)) {
					Vec2 pos = new Vec2((i % w) * map.getTileSize(), (i / w) * map.getTileSize());
					Vec2 mapPos = new Vec2(i, i / w);
					
					Tile.getTiles().get(colors.get(i2)).build(pos, new Vec2(map.getTileSize(), map.getTileSize())).setMapPos(mapPos);
				}
			}
		}
	}
}