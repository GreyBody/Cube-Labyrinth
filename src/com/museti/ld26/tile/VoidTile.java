package com.museti.ld26.tile;

import com.museti.ld26.graphics.Screen;
import com.museti.ld26.graphics.Sprite;

public class VoidTile extends Tile {

	public VoidTile(Sprite sprite) {
		super(sprite);
	}

	public void render(int x, int y, Screen screen) {
		screen.renderTile(x << 4, y << 4, this);//<<4
	}

	public boolean solid() {
		return true;
	}
}
