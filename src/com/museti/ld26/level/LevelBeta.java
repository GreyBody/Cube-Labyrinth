package com.museti.ld26.level;

import java.awt.image.BufferedImage;
import java.io.IOException;

import javax.imageio.ImageIO;

public class LevelBeta extends Level {

	public LevelBeta(String path) {
		super(path);
	}

	protected void loadLevel(String path) {
		try {
			BufferedImage img = ImageIO.read(World.class.getResource(path));
			int w = width = img.getWidth();
			int h = height = img.getHeight();
			tiles = new int[w * h];
			img.getRGB(0, 0, w, h, tiles, 0, w);
		} catch (IOException e) {
			e.printStackTrace();
			System.out.println("Could not load level file!");
		}
	}

	protected void generateLevel() {
	}

	public void update() {
	}
}
