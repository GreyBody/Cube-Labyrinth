package com.museti.ld26.tile;

import com.museti.ld26.Config;
import com.museti.ld26.graphics.Screen;
import com.museti.ld26.graphics.Sprite;

public class TrapTile extends Tile {

	public TrapTile(Sprite sprite) {
		super(sprite);
	}

	public void render(int x, int y, Screen screen) {
		screen.renderTile(x * 16, y * 16, this);
	}

	public boolean solid() {
		return false;
	}

	public boolean applyDamage() {
		return true;
	}

	public int damage() {
		if(Config.powerups[3] == 4) {
			return 0;
		}
		if(Config.powerups[2] == 3) {
			return 12;
		}
		return 25;
	}
}
