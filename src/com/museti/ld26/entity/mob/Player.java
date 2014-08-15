package com.museti.ld26.entity.mob;

import com.museti.ld26.Config;
import com.museti.ld26.graphics.Screen;
import com.museti.ld26.graphics.Sprite;
import com.museti.ld26.input.Keyboard;
import com.museti.ld26.level.Level;
import com.museti.ld26.tile.Tile;

public class Player extends Mob {

	private Keyboard input;
	private Sprite sprite;
	private int anim = 0;
	private boolean walking = false;
	private Tile tile;
	private Tile objectTile;
	public boolean dead = false;

	public Player(Level level, Keyboard input) {
		super(level);
		this.input = input;
		sprite = Sprite.player_A;
		Config.health = 100;
	}

	public Player(Level level, int x, int y, Keyboard input) {
		super(level);
		this.x = x;
		this.y = y;
		this.input = input;
		sprite = Sprite.player_A;
		Config.health = 100;
	}

	public void update() {
		tile = level.getTile((this.x) >> 4, (this.y) >> 4);
		int xa = 0, ya = 0;
		if (anim < 7500)
			anim++;
		else
			anim = 0;

		int xcon = 1;
		if (Config.powerups[1] == 2) {
			xcon = 2;
		}

		if (Config.loc >= 1) {
			if (input.up && !input.left && !input.right && !dead)
				ya -= xcon;
			if (input.down && !input.left && !input.right && !dead)
				ya += xcon;
			if (input.left && !input.up && !input.down && !dead)
				xa -= xcon;
			if (input.right && !input.up && !input.down && !dead)
				xa += xcon;
		}

		if (tile.giveReward()) {
			giveReward();
		}
		if (tile.door()) {
			openDoor();
		}
		if (tile.Tele()) {
			tele();
		}
		if (tile.finish()) {
			Config.won = true;
		}
		if (xa != 0 || ya != 0) {
			move(xa, ya);
			walking = true;
		} else {
			walking = false;
		}
		if (anim % 20 > 18) {
			if (tile.applyDamage()) {
				checkHealth();
			}
		}
	}

	public void render(Screen screen) {
		int flip = 0;
		if (dir == 1 || dir == 3) {
			sprite = Sprite.player_B;
			if (dir == 3) {
				flip = 3;
			}
		}
		if (dir == 0 || dir == 2) {
			sprite = Sprite.player_A;
			if (dir == 2) {
				flip = 2;
			}
		}
		screen.renderPlayer(x - 16, y - 16, sprite, flip);
	}

	public void renderOver(Screen screen) {
		int flip = 0;
		if (dir == 1 || dir == 3) {
			sprite = Sprite.player_B;
			if (dir == 3) {
				flip = 3;
			}
		}
		if (dir == 0 || dir == 2) {
			sprite = Sprite.player_A;
			if (dir == 2) {
				flip = 2;
			}
		}
		screen.renderOverPlayer(x - 16, y - 16, sprite, flip);
	}

	public boolean hasCollided(int xa, int ya) {
		int xMin = -14;
		int xMax = -3;
		int yMin = -14;
		int yMax = -3;
		for (int x = xMin; x < xMax; x++) {
			if (isSolid(input, xa, ya, x, yMin)) {
				return true;
			}
		}
		for (int x = xMin; x < xMax; x++) {
			if (isSolid(input, xa, ya, x, yMax)) {
				return true;
			}
		}
		for (int y = yMin; y < yMax; y++) {
			if (isSolid(input, xa, ya, xMin, y)) {
				return true;
			}
		}
		for (int y = yMin; y < yMax; y++) {
			if (isSolid(input, xa, ya, xMax, y)) {
				return true;
			}
		}
		return false;
	}

	public void killPlayer(Screen screen) {
		if (getHealth() <= 0) {//Kills Player
			dead = true;
			Config.dead = true;
			return;
		}
	}

	public void checkHealth() {
		if (getHealth() <= 0) {
			return;
		}
		int dmg = tile.damage();
		Config.health -= dmg;
		System.out.println("Damage Applied: " + dmg + " Health: " + getHealth());
	}

	public void teleport(Level lvl, int newX, int newY) {
		this.x = newX * 16;
		this.y = newY * 16;
		level = lvl;
		System.out.println("Teleport Initiated - X: " + newX + " Y: " + newY);
	}

	int newMoney = 0;
	int itemSpaceA = 0;
	int itemSpaceB = 0;
	int itemSpaceC = 0;
	int itemSpaceD = 0;
	int powerupAlpha = 0;
	int powerupBeta = 0;
	int powerupGamma = 0;
	int powerupDelta = 0;

	public void giveReward() {
		if ((this.x / 16) == 17 && (this.y / 16) == 15 && Config.chest_act[0] < 1) {//Standard Blue
			itemSpaceA = Config.chest1[1];
			Config.chest_act[0]++;
		}
		if ((this.x / 16) == 49 && (this.y / 16) == 17 && Config.chest_act[1] == 0) {//Standard Red
			itemSpaceA = Config.chest2[1];
			System.out.println("AAA");
			Config.chest_act[1] = 1;
		}
		if ((this.x / 16) == 62 && (this.y / 16) == 1 && Config.chest_act[2] < 1) {//Standard Green
			itemSpaceA = Config.chest3[3];
			Config.chest_act[2]++;
		}
		if ((this.x / 16) == 59 && (this.y / 16) == 4 && Config.chest_act[3] < 1) {//Power Up
			powerupAlpha = Config.chest1_power[0];
			powerupBeta = Config.chest1_power[1];
			powerupGamma = Config.chest1_power[2];
			powerupDelta = Config.chest1_power[3];
			Config.chest_act[3]++;
		}
		if ((this.x / 16) == 37 && (this.y / 16) == 17 && Config.chest_act[5] < 1) {//Power Up
			powerupAlpha = Config.chest2_power[0];
			powerupBeta = Config.chest2_power[1];
			powerupGamma = Config.chest2_power[2];
			powerupDelta = Config.chest2_power[3];
			Config.chest_act[5]++;
		}
		if ((this.x / 16) == 17 && (this.y / 16) == 5 && Config.chest_act[4] < 1) {//Power Up
			powerupAlpha = Config.chest3_power[0];
			powerupBeta = Config.chest3_power[1];
			powerupGamma = Config.chest3_power[2];
			powerupDelta = Config.chest3_power[3];
			Config.chest_act[4]++;
		}
		Config.itemSpace[0] = itemSpaceA;
		Config.itemSpace[1] = itemSpaceB;
		Config.itemSpace[2] = itemSpaceC;
		Config.itemSpace[3] = itemSpaceD;
		Config.powerups[0] = powerupAlpha;
		Config.powerups[1] = powerupBeta;
		Config.powerups[2] = powerupGamma;
		Config.powerups[3] = powerupDelta;
	}

	public void openDoor() {
		for (int i = 0; i < Config.itemSpace.length; i++) {
			if (Config.itemSpace[i] == 1) {
				Config.door_state[0] = 1;
				Config.itemSpace[0] = 0;
			}
			if (Config.itemSpace[i] == 2) {
				Config.door_state[1] = 1;
				Config.itemSpace[0] = 0;
			}
			if (Config.itemSpace[i] == 3) {
				Config.door_state[2] = 1;
				Config.itemSpace[0] = 0;
			}
			if (Config.itemSpace[i] == 4) {
				Config.door_state[3] = 1;
				Config.itemSpace[0] = 0;
			}
			if (Config.powerups[2] == 4) {
				Config.door_state[4] = 1;
			}
		}
	}

	public void tele() {
		for (int i = 0; i < Config.door_state.length; i++) {
			Config.door_state[i] = 0;
		}
		if ((this.x / 16) == 30 && (this.y / 16) == 30) {
			teleport(level.lvl_alpha, 44, 20);
			Config.lvlId = 1;
		}
		if ((this.x / 16) == 1 && (this.y / 16) == 62) {
			teleport(level.lvl_beta, 2, 2);
			Config.lvlId = 2;
		}
		if ((this.x / 16) == 62 && (this.y / 16) == 43) {
			teleport(level.lvl_gamma, 6, 5);
			Config.lvlId = 3;
		}
	}

	public int getHealth() {
		return Config.health;
	}
}