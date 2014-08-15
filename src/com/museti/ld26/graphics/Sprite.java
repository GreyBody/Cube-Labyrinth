package com.museti.ld26.graphics;

public class Sprite {

	public final int SIZE;
	private int x, y;
	private int w = SpriteSheet.w, h = SpriteSheet.h;
	public int[] pixels;
	private SpriteSheet sheet;

	//Special Sprites
	public static Sprite voidSprite = new Sprite(16, 0x00000000);
	public static Sprite trans = new Sprite(16, 0, 3, SpriteSheet.tiles);

	//Level Sprites
	public static Sprite floor = new Sprite(16, 0xFF81828A);
	public static Sprite barrier_a = new Sprite(16, 0xFF45454A);
	public static Sprite chest_a = new Sprite(16, 0xFF9B5000);
	public static Sprite door_blue = new Sprite(16, 0xFF0000FF);
	public static Sprite door_red = new Sprite(16, 0xFFFF0000);
	public static Sprite door_green = new Sprite(16, 0xFF00FF00);
	public static Sprite blue_tele = new Sprite(16, 0, 0, SpriteSheet.tiles);
	public static Sprite purple_finish = new Sprite(16, 1, 0, SpriteSheet.tiles);
	public static Sprite purplepath = new Sprite(16, 0xFF4800FF);
	public static Sprite pot = new Sprite(16, 3, 0, SpriteSheet.tiles);
	public static Sprite crack = new Sprite(16, 3, 1, SpriteSheet.tiles);
	public static Sprite pcp = new Sprite(16, 3, 2, SpriteSheet.tiles);
	public static Sprite meth = new Sprite(16, 3, 3, SpriteSheet.tiles);
	public static Sprite key_blue = new Sprite(16, 0, 0, SpriteSheet.items);
	public static Sprite key_red = new Sprite(16, 1, 0, SpriteSheet.items);
	public static Sprite key_green = new Sprite(16, 2, 0, SpriteSheet.items);
	public static Sprite potato = new Sprite(16, 3, 0, SpriteSheet.items);
	public static Sprite player_A = new Sprite(16, 0, 0, SpriteSheet.player);
	public static Sprite player_B = new Sprite(16, 1, 0, SpriteSheet.player);
	public static Sprite triangle = new Sprite(16, 0, 1, SpriteSheet.player);

	public Sprite(int size, int x, int y, SpriteSheet sheet) {
		SIZE = size;
		pixels = new int[SIZE * SIZE];
		this.x = x * size;
		this.y = y * size;
		this.sheet = sheet;
		load();
	}

	public Sprite(int size, int color) {
		SIZE = size;
		pixels = new int[SIZE * SIZE];
		setColor(color);
	}

	public void setColor(int color) {
		for (int i = 0; i < SIZE * SIZE; i++) {
			pixels[i] = color;
		}
	}

	private void load() {
		for (int y = 0; y < SIZE; y++) {
			for (int x = 0; x < SIZE; x++) {
				pixels[x + y * SIZE] = sheet.pixels[(x + this.x) + (y + this.y) * sheet.SIZE];
			}
		}
	}
}
