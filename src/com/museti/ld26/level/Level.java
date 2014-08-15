package com.museti.ld26.level;

import com.museti.ld26.Config;
import com.museti.ld26.graphics.Screen;
import com.museti.ld26.tile.Tile;

public class Level {

	protected int width, height;
	protected int[] tilesInt;
	protected int[] tiles;
	public static Level world = new World("/worlds/tut.png");
	public static Level lvl_alpha = new LevelAlpha("/worlds/worldalpha.png");
	public static Level lvl_beta = new LevelBeta("/worlds/worldbeta.png");
	public static Level lvl_gamma = new LevelGamma("/worlds/worldgamma.png");

	public Level(int width, int height) {
		this.width = width;
		this.height = height;
		tilesInt = new int[width * height];
		genRanLevel();
	}

	public Level(String path) {
		loadLevel(path);
		genRanLevel();
	}

	protected void genRanLevel() {
	}

	protected void loadLevel(String path) {
	}

	public void update() {
	}

	private void time() {
	}

	public void render(int xScroll, int yScroll, Screen screen) {
		screen.setOffset(xScroll, yScroll);
		int x0 = xScroll >> 4;
		int x1 = (xScroll + screen.width + 16) >> 4;
		int y0 = yScroll >> 4;
		int y1 = (yScroll + screen.height + 16) >> 4;

		for (int y = y0; y < y1; y++) {
			for (int x = x0; x < x1; x++) {
				getTile(x, y).render(x, y, screen);
			}
		}
	}

	public void renderObject(int xScroll, int yScroll, Screen screen) {
		screen.setOffset(xScroll, yScroll);
		int x0 = xScroll >> 4;
		int x1 = (xScroll + screen.width + 16) >> 4;
		int y0 = yScroll >> 4;
		int y1 = (yScroll + screen.height + 16) >> 4;

		for (int y = y0; y < y1; y++) {
			for (int x = x0; x < x1; x++) {
				getObjectTile(x, y).render(x, y, screen);
			}
		}
	}

	public void renderOver(int xScroll, int yScroll, Screen screen) {
		screen.setOffset(xScroll, yScroll);
		int x0 = xScroll >> 4;
		int x1 = (xScroll + screen.width + 16) >> 4;
		int y0 = yScroll >> 4;
		int y1 = (yScroll + screen.height + 16) >> 4;

		for (int y = y0; y < y1; y++) {
			for (int x = x0; x < x1; x++) {
				getOverTile(x, y).render(x, y, screen);
			}
		}
	}

	public void renderTop(int xScroll, int yScroll, Screen screen) {
		screen.setOffset(xScroll, yScroll);
		int x0 = xScroll >> 4;
		int x1 = (xScroll + screen.width + 16) >> 4;
		int y0 = yScroll >> 4;
		int y1 = (yScroll + screen.height + 16) >> 4;

		for (int y = y0; y < y1; y++) {
			for (int x = x0; x < x1; x++) {
				getTopTile(x, y).render(x, y, screen);
			}
		}
	}

	public int getX() {
		return 0;
	}

	public int getY() {
		return 0;
	}

	public Tile getTile(int x, int y) {
		if (x < 0 || y < 0 || x >= width || y >= height)
			return Tile.voidTile;
		if (tiles[x + y * width] == 0xFF000000)//Void Tile
			return Tile.voidTile;
		if (tiles[x + y * width] == 0xFFFFFFFF)//Floor
			return Tile.floor;
		if (tiles[x + y * width] == 0xFF010101)//Barrier Alpha
			return Tile.barrier_a;
		if (tiles[x + y * width] == 0xFF9B5000)//Chest Alpha
			return Tile.chest_a;
		if (tiles[x + y * width] == 0xFF0000FA)//Blue Teleport
			return Tile.blue_tele;
		if (Config.powerups[3] != 4) {
			if (tiles[x + y * width] == 0xFF0094FF)//Floor Trap
				return Tile.floor_trap;
			if (tiles[x + y * width] == 0xFF7F0037)//Floor Barrier
				return Tile.floor_barrier;
			if (tiles[x + y * width] == 0xFF21007F)//Wall Path
				return Tile.barrier_path;
		} else {
			if (tiles[x + y * width] == 0xFF0094FF)//Floor Trap
				return Tile.purplepath;
			if (tiles[x + y * width] == 0xFF7F0037)//Floor Barrier
				return Tile.barrier_a;
			if (tiles[x + y * width] == 0xFF21007F)//Wall Path
				return Tile.floor;
		}
		if (tiles[x + y * width] == 0xFFFF746D)//Finish
			return Tile.finish;
		if (tiles[x + y * width] == 0xFF7FFF8E)//Finish Potato
			return Tile.potato;
		if (tiles[x + y * width] == 0xFFC0C0C0)//Chest Floor
			return Tile.chest_b;

		if (Config.door_state[0] < 1) {
			if (tiles[x + y * width] == 0xFF0000FF)//Door Blue
				return Tile.door_blue;
		} else {
			if (tiles[x + y * width] == 0xFF0000FF)//Door Blue- Floor
				return Tile.floor;
		}
		if (Config.door_state[1] < 1) {
			if (tiles[x + y * width] == 0xFFFF0000)//Door Red
				return Tile.door_red;
		} else {
			if (tiles[x + y * width] == 0xFFFF0000)//Door Red- Floor
				return Tile.floor;
		}
		if (Config.door_state[2] < 1) {
			if (tiles[x + y * width] == 0xFF00FF00)//Door Green
				return Tile.door_green;
		} else {
			if (tiles[x + y * width] == 0xFF00FF00)//Door Green- Floor
				return Tile.floor;
		}
		if (Config.door_state[4] < 1) {
			if (tiles[x + y * width] == 0xFFDAFF7F)//Triangle Door
				return Tile.triangle;
		} else {
			if (tiles[x + y * width] == 0xFFDAFF7F)//Triangle Door- Floor
				return Tile.floor;
		}
		return Tile.voidTile;
	}

	public Tile getObjectTile(int x, int y) {
		if (x < 0 || y < 0 || x >= width || y >= height)
			return Tile.voidTile;
		if (tiles[x + y * width] == 0xFF000000)//Void Tile
			return Tile.voidTile;
		return Tile.trans;
	}

	public Tile getOverTile(int x, int y) {
		if (x < 0 || y < 0 || x >= width || y >= height)
			return Tile.voidTile;
		if (tiles[x + y * width] == 0xFF000000)//Void Tile
			return Tile.voidTile;
		return Tile.trans;
	}

	public Tile getTopTile(int x, int y) {
		if (x < 0 || y < 0 || x >= width || y >= height)
			return Tile.voidTile;
		if (tiles[x + y * width] == 0xFF000000)//Void Tile
			return Tile.voidTile;
		return Tile.trans;
	}
}
