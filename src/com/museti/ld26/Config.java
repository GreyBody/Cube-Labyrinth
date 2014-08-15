package com.museti.ld26;

public class Config {

	public static String title = "Cube Labyrinth";
	public static String version = "1.1 Beta";
	public static boolean debug = false;
	public static boolean won = false;
	public static boolean dead = false;
	public static boolean potatowin = false;
	public static int loc = 0;
	
	public static int health = 100;
	public static int money = 0;
	public static int lvlId = 0;
	public static int[] itemSpace = { 0, 0, 0, 0 };
	public static int[] powerups = { 0, 0, 0, 0 };

	public static int[] door_state = { 0, 0, 0, 0, 0 };
	public static int[] chest_act = { 0, 0, 0, 0, 0, 0 };
	public static int[] chest1 = { 50, 1, 0, 0, 0 };
	public static int[] chest2 = { 50, 2, 0, 0, 0 };
	public static int[] chest3 = { 50, 0, 0, 3, 0 };

	public static int[] chest3_power = { 0, 2, 0, 0 };
	public static int[] chest2_power = { 0, 2, 3, 0 };
	public static int[] chest1_power = { 1, 2, 3, 4 };
}
