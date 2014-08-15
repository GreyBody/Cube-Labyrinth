package com.museti.ld26.entity;

import java.util.Random;

import com.museti.ld26.graphics.Screen;
import com.museti.ld26.level.Level;


public abstract class Entity {

	public int x, y;
	private boolean removed = false;
	protected Level level;
	protected final Random random = new Random();

	public Entity(Level level) {
		this.level = level;
	}

	public void update() {
	}

	public void render(Screen screen) {
	}

	public void renderOver(Screen screen) {
	}

	public void remove() {
		removed = true;
	}

	public boolean isRemoved() {
		return removed;
	}
}