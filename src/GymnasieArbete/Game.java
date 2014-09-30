package GymnasieArbete;

import java.awt.Canvas;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Graphics;
import java.awt.image.BufferStrategy;
import java.awt.image.BufferedImage;
import java.awt.image.DataBufferInt;

import javax.swing.JFrame;

import GymnasieArbete.entities.mob.Enemy;
import GymnasieArbete.entities.mob.Player;
import GymnasieArbete.graphics.Screen;
import GymnasieArbete.input.Keyboard;
import GymnasieArbete.input.Mouse;
import GymnasieArbete.level.Level;
import GymnasieArbete.level.TileCoordinate;

public class Game extends Canvas implements Runnable {
	private static final long serialVersionUID = 1L;

	private static int width = 300;
	private static int height = width / 16 * 9;
	public static int scale = 3;
	public static String title = "Prison Break 2D";

	private Thread thread;
	private JFrame frame;
	private Keyboard key;
	private Level level;
	private Player player;
	private Enemy enemy;
	private Menu menu;
	private PauseMenu paused;
	public static boolean running = false;

	private Screen screen;

	private BufferedImage image = new BufferedImage(width, height, BufferedImage.TYPE_INT_RGB);
	private int[] pixels = ((DataBufferInt) image.getRaster().getDataBuffer()).getData();

	public static enum STATE {
		MENU, PAUSED, GAME
	};

	public static STATE state = STATE.MENU;

	public Game() {
		Dimension size = new Dimension(Game.width * Game.scale, Game.height * Game.scale);
		setPreferredSize(size);

		screen = new Screen(width, height);
		frame = new JFrame();
		key = new Keyboard();
		level = Level.spawn;
		TileCoordinate playerSpawn = new TileCoordinate(32, 32);
		player = new Player(playerSpawn.x(), playerSpawn.y(), key);
		enemy = new Enemy(playerSpawn.x() + 10, playerSpawn.y() + 10);
		player.init(level);
		menu = new Menu();
		paused = new PauseMenu();

		addKeyListener(key);

		Mouse mouse = new Mouse();
		addMouseListener(mouse);
		addMouseMotionListener(mouse);
	}

	public static int getWindowWidth() {
		return width * scale;
	}
	
	public static int getWindowHeight() {
		return height * scale;
	}
	
	public synchronized void start() {
		running = true;
		thread = new Thread(this, "Display");
		thread.start();
	}

	public synchronized void stop() {
		running = false;
		System.exit(ABORT);
		try {
			thread.join();
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}

	public void run() {
		long lastTime = System.nanoTime();
		long timer = System.currentTimeMillis();
		final double ns = 1000000000.0 / 60.0;
		double delta = 0;
		int frames = 0;
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
				System.out.println(updates + " UPS " + frames + " FPS");
				frame.setTitle(title + " | " + updates + " UPS " + frames + " FPS");
				updates = 0;
				frames = 0;
			}
		}
		stop();
	}

	public void update() {
		if (state == STATE.GAME) {
			key.update();
			player.update();
			enemy.update();
			level.update();
		}
	}

	public void render() {
		BufferStrategy bs = getBufferStrategy();
		if (bs == null) {
			createBufferStrategy(3);
			return;
		}


		Graphics g = bs.getDrawGraphics();
		g.drawImage(image, 0, 0, getWidth(), getHeight(), null);
		
		if (state == STATE.GAME) {
			screen.clear();
			int xScroll = player.x - screen.width / 2;
			int yScroll = player.y - screen.height / 2;
			level.render(xScroll, yScroll, screen);
			player.render(screen);
			enemy.render(screen);

			for (int i = 0; i < pixels.length; i++) {
				pixels[i] = screen.pixels[i];
			}

			g.setColor(Color.WHITE);
			//g.fillRect(Mouse.getX() - 32, Mouse.getY() - 32, 64, 64);
		}
		else if (state == STATE.MENU) {
			menu.render(g);
		}
		
		else if (state == STATE.PAUSED) {
			paused.render(g);
		}
		
		g.dispose();
		bs.show();
	}

	public static void main(String[] args) {
		Game game = new Game();
		game.frame.setLayout(new FlowLayout());
		game.frame.setResizable(false);
		game.frame.setTitle(title);
		game.frame.add(game);
		game.frame.pack();
		game.frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		game.frame.setLocationRelativeTo(null);
		game.frame.setVisible(true);
		game.start();
	}

}
