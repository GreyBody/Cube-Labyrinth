package com.museti.ld26.entity.mob;

import com.museti.ld26.entity.Entity;
import com.museti.ld26.graphics.Sprite;
import com.museti.ld26.input.Keyboard;
import com.museti.ld26.level.Level;
import com.museti.ld26.tile.Tile;

public abstract class Mob extends Entity {

	protected Sprite sprite;
	protected Keyboard input;
	protected int dir = 2;
	protected boolean moving = false;

	public Mob(Level level) {
		super(level);
	}

	public void move(int xa, int ya) {
		if (xa > 0)
			dir = 1;//Right
		if (xa < 0)
			dir = 3;//Left
		if (ya > 0)
			dir = 2;//Down
		if (ya < 0)
			dir = 0;//Up

		if (!hasCollided(xa, ya)) {
			x += xa;
			y += ya;
		}
	}

	public void update() {
	}

	private boolean collision() {
		return false;
	}

	public abstract boolean hasCollided(int xa, int ya);

	protected boolean isSolid(Keyboard input, int xa, int ya, int x, int y) {
		if (level == null)
			return false;
		Tile lastUnderTile = level.getTile((this.x + x) >> 4, (this.y + y) >> 4);
		Tile newUnderTile = level.getTile((this.x + x + xa) >> 4, (this.y + y + ya) >> 4);
		if (!lastUnderTile.equals(newUnderTile) && newUnderTile.solid()) {
			return true;
		}
		return false;
	}

	public void render() {
	}
}