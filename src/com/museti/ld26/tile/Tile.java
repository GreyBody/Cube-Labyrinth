package com.museti.ld26.tile;

import com.museti.ld26.graphics.Screen;
import com.museti.ld26.graphics.Sprite;

public class Tile {

	public int x, y;
	public Sprite sprite;
	public Sprite object;
	public Sprite overlay;
	public Sprite top;

	public static Tile voidTile = new VoidTile(Sprite.voidSprite);
	public static Tile trans = new TransTile(Sprite.trans);
	public static Tile floor = new FloorTile(Sprite.floor);
	public static Tile barrier_a = new BarrierTile(Sprite.barrier_a);
	public static Tile chest_a = new ChestTile(Sprite.chest_a);
	public static Tile chest_b = new ChestTile(Sprite.floor);
	public static Tile door_blue = new DoorTile(Sprite.door_blue);
	public static Tile blue_tele = new TeleportTile(Sprite.blue_tele);
	public static Tile door_red = new DoorTile(Sprite.door_red);
	public static Tile door_green = new DoorTile(Sprite.door_green);
	public static Tile finish = new FinishTile(Sprite.purple_finish);
	public static Tile purplepath = new TrapTile(Sprite.purplepath);
	public static Tile potato = new PotatoTile(Sprite.potato);
	public static Tile triangle = new TriangleTile(Sprite.triangle);
	public static Tile floor_trap = new TrapTile(Sprite.floor);
	public static Tile floor_barrier = new FloorBarrierTile(Sprite.floor);
	public static Tile barrier_path = new BarrierPathTile(Sprite.barrier_a);

	public Tile(Sprite sprite) {
		this.sprite = sprite;
	}

	public Tile(Sprite sprite, Sprite overlay) {//Not Used
		this.sprite = sprite;
		this.overlay = overlay;
	}
	
	public Tile(Sprite sprite, Sprite object, Sprite overlay) {//Not Used
		this.sprite = sprite;
		this.object = object;
		this.overlay = overlay;
	}
	
	public Tile(Sprite sprite, Sprite object, Sprite overlay, Sprite top) {//Not Used
		this.sprite = sprite;
		this.object = object;
		this.overlay = overlay;
		this.top = top;
	}

	public void render(int x, int y, Screen screen) {
	}
	
	public void renderObject(int x, int y, Screen screen) {
	}

	public void renderOver(int x, int y, Screen screen) {
	}
	
	public void renderTop(int x, int y, Screen screen) {
	}
	
	public boolean Tele() {
		return false;
	}
	
	public boolean solid() {
		return false;
	}
	
	public boolean door() {
		return false;
	}

	public boolean giveReward() {
		return false;
	}
	
	public boolean applyDamage() {
		return false;
	}
	
	public int damage() {
		return 0;
	}
	
	public boolean finish() {
		return false;
	}
}
