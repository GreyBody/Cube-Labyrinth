package com.museti.ld26.tile;

import com.museti.ld26.graphics.Screen;
import com.museti.ld26.graphics.Sprite;

public class ChestTile extends Tile {

	public ChestTile(Sprite sprite) {
		super(sprite);
	}

	public void render(int x, int y, Screen screen) {
		screen.renderTile(x * 16, y * 16, this);
	}
	
	public boolean giveReward() {
		return true;
	}
}
