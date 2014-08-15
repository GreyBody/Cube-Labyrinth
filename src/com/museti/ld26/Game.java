package com.museti.ld26;

import java.awt.Canvas;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.image.BufferStrategy;
import java.awt.image.BufferedImage;
import java.awt.image.DataBufferInt;

import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JOptionPane;

import com.museti.ld26.entity.mob.Player;
import com.museti.ld26.graphics.Screen;
import com.museti.ld26.input.Keyboard;
import com.museti.ld26.input.Mouse;
import com.museti.ld26.level.Level;

public class Game extends Canvas implements Runnable {
	private static final long serialVersionUID = 1L;

	public static int scale = 3;
	public static int width = 300;
	public static int height = (width / 16 * 10);

	private Thread thread;
	private JFrame frame;
	private Keyboard key;
	private static Mouse mouse;
	private Level level;
	private Player player;
	private boolean running = false;

	private Screen screen;

	private BufferedImage image = new BufferedImage(width, height, BufferedImage.TYPE_INT_ARGB);
	private int[] pixels = ((DataBufferInt) image.getRaster().getDataBuffer()).getData();

	public Game() {
		Dimension size = new Dimension(width * scale, height * scale);
		setPreferredSize(size);
		screen = new Screen(width, height);
		frame = new JFrame();
		key = new Keyboard();
		mouse = new Mouse();
		level = level.world;
		player = new Player(level, 16 * 13, 16 * 13, key);
		addKeyListener(key);
	}

	public synchronized void start() {
		running = true;
		thread = new Thread(this, "Display");
		thread.start();
	}

	public synchronized void stop() {
		running = false;
		try {
			thread.join();
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}

	private int frames = 0;

	public void run() {
		long lastTime = System.nanoTime();
		long timer = System.currentTimeMillis();
		final double ns = 1000000000.0 / 60.0;
		double delta = 0;
		frames = 0;
		int updates = 0;
		requestFocus();
		while (running) {
			long now = System.nanoTime();
			delta += (now - lastTime) / ns;
			lastTime = now;
			while (delta >= 1) {
				update();
				updates++;
				delta--;
			}
			render();
			frames++;

			if (System.currentTimeMillis() - timer > 1000) {
				timer += 1000;
				if (Config.debug) {
					frame.setTitle(Config.title + " - " + frames + "fps || Ticks " + updates + " || " + Config.version);
				}
				updates = 0;
				frames = 0;
			}
		}
	}

	int x = 0, y = 0;

	public void update() {
		key.update();
		player.update();
		level.update();
	}

	public void render() {
		BufferStrategy bs = getBufferStrategy();
		if (bs == null) {
			createBufferStrategy(3);
			return;
		}
		screen.clear();
		if (Config.won) {
			JOptionPane.showMessageDialog(frame, "Congrats! You won, now i suppose you'll want a potato.", "You Won!", JOptionPane.WARNING_MESSAGE);
			frame.dispose();
		}
		if (Config.potatowin) {
			JOptionPane.showMessageDialog(frame, "Congrats! You saved the royal potato. You are now a member of the potato guard.", "You Save The Royal Potato!", JOptionPane.WARNING_MESSAGE);
			frame.dispose();
		}
		if (Config.dead) {
			JOptionPane.showMessageDialog(frame, "What a Fail, You Lost! Now the potato empire will be destroyed!", "You Lost!", JOptionPane.WARNING_MESSAGE);
			frame.dispose();
		}

		if (Config.lvlId == 0) {
			level = level.world;
		}
		if (Config.lvlId == 1) {
			level = level.lvl_alpha;
		}
		if (Config.lvlId == 2) {
			level = level.lvl_beta;
		}
		if (Config.lvlId == 3) {
			level = level.lvl_gamma;
		}

		int xScroll = player.x - screen.width / 2;
		int yScroll = player.y - screen.height / 2;
		level.render(xScroll, yScroll, screen);
		level.renderObject(xScroll, yScroll, screen);
		player.render(screen);
		level.renderOver(xScroll, yScroll, screen);
		player.renderOver(screen);
		level.renderTop(xScroll, yScroll, screen);

		screen.renderItems(4);
		screen.renderPowers(4);

		if (Config.loc < 1) {
			screen.drawTextDialog("Find the keys to win", "Try and find the powerups", "Avoid the radiation pockets", "", "", 0xFFFFFFFF, 0xFFFFFFFF, 0xFFFFFFFF, 0xFFFFFFFF, 0xFFFFFFFF);
		}

		for (int i = 0; i < pixels.length; i++) {
			pixels[i] = screen.pixels[i];
		}
		Graphics g = bs.getDrawGraphics();
		g.setColor(Color.black);
		g.fillRect(0, 0, getWidth(), getHeight());
		g.drawImage(image, 0, 0, getWidth(), getHeight(), null);
		g.setColor(Color.white);
		Font fon = new Font("Arial", 1, 15);
		g.setFont(fon);
		if (Config.debug) {
			int xCoord = player.x / 16;
			int yCoord = player.y / 16;
			String param1 = "Width: " + getWidth() + " - Height: " + getHeight();
			String param2 = "X: " + xCoord + " Y: " + yCoord;
			g.drawString(param1, (width - (param1.length() * 7)), 12);
			g.drawString(param2, (width - (param2.length() * 7)), 28);
		}
		g.drawString("Health: " + Config.health, 5, 15);
		g.dispose();
		bs.show();
	}

	public static void main(String[] args) {
		Game game = new Game();
		game.frame.setResizable(false);
		game.frame.setTitle(Config.title + " - " + Config.version);
		game.frame.setIconImage(new ImageIcon(Game.class.getResource("/misc/icon.png")).getImage());
		game.frame.add(game);
		game.frame.pack();
		game.frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		game.frame.setLocationRelativeTo(null);

		game.frame.setVisible(true);
		game.start();
		game.addMouseListener(mouse);
		game.addMouseMotionListener(mouse);
	}
}
