package com.museti.ld26.graphics;

import java.awt.image.BufferedImage;
import java.io.IOException;

import javax.imageio.ImageIO;

public class SpriteSheet {

	private String path;
	public final int SIZE;
	public int[] pixels;
	public static int w, h;

	public static SpriteSheet player = new SpriteSheet("/sprites/player.png", 32);
	public static SpriteSheet items = new SpriteSheet("/sprites/items.png", 64);
	public static SpriteSheet health = new SpriteSheet("/sprites/health.png", 30);
	public static SpriteSheet tiles = new SpriteSheet("/sprites/tiles.png", 64);

	public SpriteSheet(String path, int size) {
		this.path = path;
		SIZE = size;
		pixels = new int[SIZE * SIZE];
		load();
	}

	public int getPixel(int x, int y) {
		if (x < 0 || x >= w || y < 0 || y >= h)
			return 0xFF000000;
		return pixels[x + y * w];
	}

	private void load() {
		try {
			BufferedImage img = ImageIO.read(SpriteSheet.class.getResource(path));
			w = img.getWidth();
			h = img.getHeight();
			img.getRGB(0, 0, w, h, pixels, 0, w);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}
